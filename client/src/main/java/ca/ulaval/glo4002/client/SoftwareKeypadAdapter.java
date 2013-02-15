package ca.ulaval.glo4002.client;

public class SoftwareKeypadAdapter extends KeypadAdapter {

    @Override
    public void sendNIPtoSystem(String nip) {
        armingSystem.handleKeypadEntry(nip);
    }
}
