package ca.ulaval.glo4002.devices;

public class Keypad {

    private static final String CORRECT_NIP = "1234";
    private static final String RAPID_NIP = "00";
    private AlarmSystem alarmSystem;

    public void sendNIPtoSystem(String nip) {
        alarmSystem.handleKeypadEntry(nip);
    }
    
    public void handleKeypadEntry(String nip) {
        if (isNIPValid(nip)) {
            if (!isArmed()) {
                armSystem();
            } else {
                disarmSystem();
            }
        }
    }
    
    private boolean isNIPValid(String nip) {
        if (isArmed()) {
            return nip.equals(CORRECT_NIP);
        } else {
            return nip.equals(CORRECT_NIP) || nip.equals(RAPID_NIP);
        }
    }
}
