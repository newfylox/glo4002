package ca.ulaval.glo4002.centralServer.treatment;

import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserDirectoryLocator;
import ca.ulaval.glo4002.centralServer.user.UserNotFoundException;

public class AlarmListTreatment {

    protected UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    public String retrieveLogFromUser(String userIdPassedByGetRequest) {
        int userId = Integer.parseInt(userIdPassedByGetRequest);
        String log;
        if (userDirectory.userExists(userId)) {
            log = userDirectory.getAlarmsForUser(userId);
        } else {
            throw new UserNotFoundException("The ID " + userIdPassedByGetRequest
                                            + " was not found in the UsersDirectory.");
        }

        return log;
    }
}
