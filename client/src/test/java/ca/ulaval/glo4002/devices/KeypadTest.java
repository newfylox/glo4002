package ca.ulaval.glo4002.devices;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class KeypadTest {

    private static final String NEW_PIN = "98765";
    private static final String VALID_PIN = "12345";
    private static final String INVALID_PIN = "54321";
    private static final String RAPID_PIN = "#0";

    private Keypad keypad;

    @Mock
    private AlarmSystem alarmSystem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        keypad = new Keypad(alarmSystem);

        doReturn(true).when(alarmSystem).validatePIN(VALID_PIN);
        doReturn(true).when(alarmSystem).validatePIN(RAPID_PIN);
        doReturn(false).when(alarmSystem).validatePIN(INVALID_PIN);
    }

    @Test
    public void canArmSystemWhenPINIsValid() {
        keypad.armSystem(VALID_PIN);
        verify(alarmSystem).armWithThirtySecondsDelay();
    }

    @Test
    public void canArmSystemWithRapidPIN() {
        keypad.armSystem(RAPID_PIN);
        verify(alarmSystem).armWithThirtySecondsDelay();
    }

    @Test(expected = InvalidPINException.class)
    public void whenArmingSystemIfPINIsNotValidThrowAnException() {
        keypad.armSystem(INVALID_PIN);
    }

    @Test
    public void canDisarmSystemWhenPINIsValid() {
        keypad.disarmSystem(VALID_PIN);
        verify(alarmSystem).disarm();
    }

    @Test(expected = InvalidPINException.class)
    public void whenDisarmingSystemIfPINIsNotValidThrowAnException() {
        keypad.disarmSystem(INVALID_PIN);
    }

    @Test
    public void canSendRequestToChangePIN() {
        keypad.requestPINChange(VALID_PIN, NEW_PIN);
        verify(alarmSystem).changePIN(VALID_PIN, NEW_PIN);
    }

}
