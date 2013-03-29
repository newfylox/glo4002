package ca.ulaval.glo4002.communication;

import java.util.HashMap;

import ca.ulaval.glo4002.common.requestSender.GETRequestSender;
import ca.ulaval.glo4002.common.requestSender.POSTRequestSender;
import ca.ulaval.glo4002.utilities.JSONMessageEncoder;

public class Communicator {

    private static final int CENTRAL_SERVER_PORT = 9001;
    private static final String ADDRESS_KEY = "address";
    private static final String REGISTER_URL = "register/";

    public static enum TargetResource {
        FIRE, POLICE
    };

    private int userID;
    private JSONMessageEncoder messageEncoder = new JSONMessageEncoder();
    private POSTRequestSender postRequestSender = new POSTRequestSender(CENTRAL_SERVER_PORT);
    private GETRequestSender getRequestSender = new GETRequestSender(CENTRAL_SERVER_PORT);

    public Communicator(String houseAddress) {
        requestUserIDFromCentralServer(houseAddress);
    }

    public void requestUserIDFromCentralServer(String houseAddress) {
        HashMap<String, String> attributes = new HashMap<String, String>();

        attributes.put(ADDRESS_KEY, houseAddress);
        sendRegisterRequestToCentralServer(attributes);
    }

    private String generateResourceURL(int userID, TargetResource targetResource) {
        return String.format("client/%d/%s", userID, targetResource.toString().toLowerCase());
    }

    public void sendMessageToCentralServer(TargetResource targetResource) {
        String resourceURL = generateResourceURL(userID, targetResource);
        getRequestSender.sendRequest(resourceURL);
    }

    public void sendMessageToCentralServer(HashMap<String, String> attributes, TargetResource targetResource) {
        String messageToSend = messageEncoder.generateEncodedMessage(attributes);
        String resourceURL = generateResourceURL(userID, targetResource);
        postRequestSender.sendRequest(resourceURL, messageToSend);
    }

    private void sendRegisterRequestToCentralServer(HashMap<String, String> attributes) {
        String messageToSend = messageEncoder.generateEncodedMessage(attributes);
        String resourceURL = REGISTER_URL;
        String response = postRequestSender.sendRequest(resourceURL, messageToSend);
        userID = Integer.parseInt(response);
    }

    // For test purposes only
    protected Communicator(String houseAddress, POSTRequestSender postRequestSender, GETRequestSender getRequestSender,
                           JSONMessageEncoder messageEncoder) {
        this.postRequestSender = postRequestSender;
        this.getRequestSender = getRequestSender;
        this.messageEncoder = messageEncoder;
        requestUserIDFromCentralServer(houseAddress);
    }

}
