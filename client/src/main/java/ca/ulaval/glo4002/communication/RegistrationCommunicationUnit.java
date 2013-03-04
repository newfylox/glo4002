package ca.ulaval.glo4002.communication;

import java.net.URISyntaxException;
import java.util.HashMap;

public class RegistrationCommunicationUnit extends CommunicationUnit {

    private static final String RESOURCE = "register/";

    private String response;

    public RegistrationCommunicationUnit() {
        super();
        resource = RESOURCE;
    }

    public void sendRegistrationRequest(HashMap<String, String> attributes) {
        String message = messageEncoder.generateEncodedMessage(attributes);
        try {
            response = postRequestSender.sendRequest(resource, message);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int retrieveUserID() {
        int userID = Integer.parseInt(response);
        return userID;
    }
}
