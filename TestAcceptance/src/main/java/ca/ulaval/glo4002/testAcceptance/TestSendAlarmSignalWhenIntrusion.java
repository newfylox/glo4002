package ca.ulaval.glo4002.testAcceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestSendAlarmSignalWhenIntrusion {

    private static final int THIRTY_SECONDS = 30000;
    private TestFixture fixture;
    private CentralServer centralServer;

    private final int FIFTEEN_SECONDS = 15000;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();

        fixture.waitSeconds(FIFTEEN_SECONDS);

        fixture.createAlarmSystem();
        fixture.initializeAlarmSystem();
        fixture.armSystem();
    }

    @After
    public void tearDown() throws Exception {
        fixture.stopServers();
    }

    @Test
    public void emergenciesCalledThirtySecondsAfterMainDoorIntrusion() throws InterruptedException {
        fixture.openMainDoor();

        fixture.waitSeconds(THIRTY_SECONDS);

        fixture.verifyPoliceWasCalled();
    }

    @Test
    public void emergenciesCalledWhenSecondaryDoorIntrusion() {
        fixture.openSecondaryDoor();

        fixture.verifyPoliceWasCalled();
    }

    @Test
    public void emergenciesCalledWhenMovementDetected() {
        fixture.triggerMovementDetector();

        fixture.verifyPoliceWasCalled();
    }

}