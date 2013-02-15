package ca.ulaval.glo4002.client;

public abstract class KeypadAdapter {
    protected ArmingSystem armingSystem;

    public KeypadAdapter() {
        armingSystem = new ArmingSystem();
    }

    public abstract void sendNIPtoSystem(String nip);
}
