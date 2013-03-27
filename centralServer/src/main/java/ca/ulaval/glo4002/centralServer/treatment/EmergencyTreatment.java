package ca.ulaval.glo4002.centralServer.treatment;

import java.util.Date;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.user.Alarm;
import ca.ulaval.glo4002.centralServer.user.Alarm.Type;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;
import ca.ulaval.glo4002.centralServer.user.UserDirectoryLocator;

public abstract class EmergencyTreatment {

    protected Communicator communicator;
    protected UserDirectory userDirectory = UserDirectoryLocator.getInstance().getUserDirectory();

    public EmergencyTreatment() {

    }

    // for test purpose only
    protected EmergencyTreatment(Communicator communicator, UserDirectory userDirectory) {
        this.communicator = communicator;
        this.userDirectory = userDirectory;
    }

    protected void addAlarmToUserList(int userId, Type type) {
        Date currentDate = new Date();
        Alarm alarm = new Alarm(type, currentDate);
        userDirectory.obtainUser(userId).addAlarm(alarm);
    }

}
