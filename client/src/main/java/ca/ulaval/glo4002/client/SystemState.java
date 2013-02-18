package ca.ulaval.glo4002.client;

public class SystemState {

    private enum SystemStatus {
        ARMED, EXIT_DELAY, DISARMED
    };

    private boolean ready = true;

    private SystemStatus status = SystemStatus.DISARMED;

    public boolean isArmed() {
        return status == SystemStatus.ARMED;
    }

    public void changeStatusToArmed() {
        status = SystemStatus.ARMED;
    }

    public void changeStatusToDisarmed() {
        status = SystemStatus.DISARMED;
    }

    public void changeStatusToExitDelay() {
        status = SystemStatus.EXIT_DELAY;
    }

    public boolean isReady() {
        return ready;
    }

}
