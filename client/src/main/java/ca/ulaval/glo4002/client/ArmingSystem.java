package ca.ulaval.glo4002.client;

public class ArmingSystem implements DelayTimerDelegate {

    private static final int DELAY = 30;
    private static final String CORRECT_NIP = "1234";
    private static final String RAPID_NIP = "00";
    private SystemState systemState;
    private DelayTimer delayTimer;

    public ArmingSystem() {
        systemState = new SystemState();
        delayTimer = new DelayTimer(this);
    }

    protected ArmingSystem(SystemState systemState, DelayTimer delayTimer) {
        this.systemState = systemState;
        this.delayTimer = delayTimer;
    }

    private void armSystem() {

        if (systemState.isReady()) {
            beginExitDelay();
        }
    }

    private void disarmSystem() {
        systemState.changeStatusToDisarmed();
    }

    private void beginExitDelay() {
        systemState.changeStatusToExitDelay();
        delayTimer.startDelay(DELAY, null); // FIXME Corriger le delay manager
                                            // pour
                                            // ne plus prendre d'argument
    }

    private boolean isNIPValid(String nip) {
        if (systemState.isArmed())
            return nip.equals(CORRECT_NIP);
        else
            return nip.equals(CORRECT_NIP) || nip.equals(RAPID_NIP);
    }

    public void performNIPValidation(String nip) {
        if (isNIPValid(nip)) {
            if (!systemState.isArmed())
                armSystem();
            else
                disarmSystem();
        }
    }

    @Override
    public void delayExpired(Object identifier) {
        systemState.changeStatusToArmed();
    }

}
