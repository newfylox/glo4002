package ca.ulaval.glo4002.policies;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.communication.IntrusionCommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class IntrusionPolicy extends Policy {

    public IntrusionPolicy(AlarmSystem alarmSystem) {
        super(alarmSystem);
        communicationUnit = new IntrusionCommunicationUnit();
    }

    // for test purpose only
    protected IntrusionPolicy(AlarmSystem alarmSystem,
            CommunicationUnit communicationUnit) {
        super(alarmSystem);
        this.communicationUnit = communicationUnit;
    }

    @Override
    public void execute(int zone) {
        if (alarmSystem.isArmed()) {
            sendMessage();
        }
    }

    private void sendMessage() {
        communicationUnit.send();
    }

}
