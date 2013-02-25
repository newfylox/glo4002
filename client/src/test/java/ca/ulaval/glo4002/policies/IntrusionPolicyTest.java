package ca.ulaval.glo4002.policies;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.communication.CommunicationUnit;
import ca.ulaval.glo4002.devices.AlarmSystem;

public class IntrusionPolicyTest {

    private IntrusionPolicy policy;
    private final int A_ZONE = 1;

    @Mock
    private AlarmSystem alarmSystem;
    @Mock
    private CommunicationUnit communicationUnit;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        policy = new IntrusionPolicy(alarmSystem, communicationUnit);
    }

    @Test
    public void whenSystemIsNotArmedThereIsNoMessageSent() {
        doReturn(false).when(alarmSystem).isArmed();
        policy.execute(A_ZONE);
        verify(communicationUnit, never()).send();
    }

    @Test
    public void whenSystemIsArmedThereIsAMessageSent() {
        doReturn(true).when(alarmSystem).isArmed();
        policy.execute(A_ZONE);
        verify(communicationUnit).send();
    }

}
