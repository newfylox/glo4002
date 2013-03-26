package ca.ulaval.glo4002.main;

import java.util.HashMap;

import ca.ulaval.glo4002.communication.RegistrationCommunicator;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class Main {
    
    private static final String ADDRESS = "123 rue ville";
    private static final String ADDRESS_KEY = "address";

    public static void main(String[] arguments) {
        int userID;
        
        userID = requestRegistrationToCentralServer(ADDRESS);
        AlarmSystem alarmSystem = new AlarmSystem(userID);
    }
    
    public static int requestRegistrationToCentralServer(String address) {
        RegistrationCommunicator registrationCommunicator = new RegistrationCommunicator();
        HashMap<String, String> attributes = new HashMap<String, String>();
        int userID;
        
        attributes.put(ADDRESS_KEY, address);
        registrationCommunicator.sendRegistrationRequest(attributes);
        userID = registrationCommunicator.retrieveUserID();
        
        return userID;
    }
    
}
