package ca.ulaval.glo4002.testAcceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestSendAlarmSignalWhenIntrusion {
    private TestFixture fixture;
    private CentralServer centralServer;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();

        fixture.waitThirtySeconds();

        fixture.createAlarmSystem();
        fixture.initializeAlarmSystem();
        fixture.armSystem();
    }

    @After
    public void tearDown() throws Exception {
        fixture.stopServers();
    }

    @Test
    public void emergenciesCalledThirtySecondsAfterMainDoorIntrusion()
            throws InterruptedException {
        fixture.openMainDoor();

        fixture.verifyPoliceWasCalled();
    }

    @Ignore
    @Test
    public void emergenciesCalledWhenSecondaryDoorIntrusion() {
        fixture.openSecondaryDoor();

        // fixture.verifyPoliceWasCalled();
    }

    @Ignore
    @Test
    public void emergenciesCalledWhenMovementDetected() {
        fixture.triggerMovementDetector();

        // fixture.verifyPoliceWasCalled();
    }

}