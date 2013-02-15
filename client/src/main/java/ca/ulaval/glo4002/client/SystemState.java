package ca.ulaval.glo4002.client;

public class SystemState {

    private enum SystemStatus {
        ARMED, EXIT_DELAY, DISARMED
    };

    private boolean ready = true;

    private SystemStatus armed = SystemStatus.DISARMED;

    public boolean isArmed() {
        return armed == SystemStatus.ARMED;
    }

    public void changeStatusToArmed() {
        armed = SystemStatus.ARMED;
    }

    public void changeStatusToDisarmed() {
        armed = SystemStatus.DISARMED;
    }

    public void changeStatusToExitDelay() {
        armed = SystemStatus.EXIT_DELAY;
    }

    public boolean isReady() {
        return ready;
    }

}
