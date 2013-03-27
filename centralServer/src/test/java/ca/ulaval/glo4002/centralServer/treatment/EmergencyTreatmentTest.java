package ca.ulaval.glo4002.centralServer.treatment;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.centralServer.communication.Communicator;
import ca.ulaval.glo4002.centralServer.user.Alarm;
import ca.ulaval.glo4002.centralServer.user.Alarm.Type;
import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.centralServer.user.UserDirectory;

public class EmergencyTreatmentTest {

    private static final String A_GOOD_URL_ID = "20";

    private User mockedUser;

    @Mock
    private Communicator communicator;

    @Mock
    private UserDirectory userDirectory;

    private EmergencyTreatment emergencyTreatment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        emergencyTreatment = createEmergencyTreatment();
        mockedUser = mock(User.class);
    }

    @Test
    public void toto() {
        int aGoodID = Integer.parseInt(A_GOOD_URL_ID);
        doReturn(mockedUser).when(userDirectory).obtainUser(aGoodID);

        emergencyTreatment.addAlarmToUserLog(aGoodID, any(Type.class));

        verify(mockedUser).addAlarm(any(Alarm.class));
    }

    private EmergencyTreatment createEmergencyTreatment() {
        EmergencyTreatment abstractEmergencyTreatment = new EmergencyTreatment(communicator, userDirectory) {

        };
        return abstractEmergencyTreatment;
    }

}
