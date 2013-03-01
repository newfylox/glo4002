package ca.ulaval.glo4002.devices;

public class Keypad {

    private int validPIN = 12345;

    private AlarmSystem alarmSystem;

    public Keypad(final AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void armSystem(final int submittedPIN) throws BadStateException, InvalidPINException {
        validatePIN(submittedPIN);
        alarmSystem.arm();
    }

    private void validatePIN(final int submittedPIN) throws InvalidPINException {
        if (submittedPIN != validPIN) {
            throw new InvalidPINException();
        }
    }

    public void disarmSystem(final int submittedPIN) throws InvalidPINException {
        validatePIN(submittedPIN);
        alarmSystem.disarm();
    }

    public void changePIN(final int submittedPIN, final int newPIN) throws InvalidPINException {
        validatePIN(submittedPIN);
        validPIN = newPIN;
    }

    protected boolean isPINValid(final int submittedPIN) {
        return validPIN == submittedPIN;
    }
}
