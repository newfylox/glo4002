package ca.ulaval.glo4002.devices;

public class Keypad {

    private static final String DEFAULT_PIN = "12345";
    private static final String RAPID_PIN = "00";

    private String validPIN;
    private AlarmSystem alarmSystem;

    public Keypad(final AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
        this.validPIN = DEFAULT_PIN;
    }

    public void armSystem(final String PIN) throws BadStateException,
            InvalidPINException {
        if (isPINValid(PIN)) {
            alarmSystem.arm();
        } else {
            throw new InvalidPINException();
        }
    }

    public void disarmSystem(final String PIN) throws InvalidPINException {
        if (isPINValid(PIN)) {
            alarmSystem.disarm();
        } else {
            throw new InvalidPINException();
        }
    }

    public void changePIN(final String PIN, final String newPIN)
            throws InvalidPINException {
        if (isPINValid(PIN)) {
            checkPINFormat(newPIN);
            validPIN = newPIN;
        } else {
            throw new InvalidPINException();
        }
    }

    // Protected for testing purposes
    protected boolean isPINValid(final String newPIN) {
        return (validPIN == newPIN || RAPID_PIN == newPIN);
    }

    private void checkPINFormat(final String PIN) {
        if (!PIN.matches("^[0-9]{5}$")) {
            throw new PINFormatForbiddenException();
        }
    }
}
