package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.communication.Communicator.CommunicationType;
import ca.ulaval.glo4002.centralServer.user.Alarm;
import ca.ulaval.glo4002.centralServer.user.Alarm.Type;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class PoliceTreatment extends EmergencyTreatment {

    private Type ALARM_TYPE = Alarm.Type.INTRUSION;

    public PoliceTreatment() {
        communicator = new Communicator(CommunicationType.POLICE);
    }

    public void processRequest(String userIdPassedByGetRequest) throws UserNotFoundException {
        int userId = Integer.parseInt(userIdPassedByGetRequest);
        if (userDirectory.userExists(userId)) {
            communicator.sendMessageToEmergencyServer(userDirectory.obtainUser(userId));
        } else {
            throw new UserNotFoundException("The ID " + userIdPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }

        addAlarmToUserList(userId, ALARM_TYPE);
    }

    // for test purpose only
    protected PoliceTreatment(Communicator communicator, UserDirectory userDirectory) {
        super(communicator, userDirectory);
    }

}
