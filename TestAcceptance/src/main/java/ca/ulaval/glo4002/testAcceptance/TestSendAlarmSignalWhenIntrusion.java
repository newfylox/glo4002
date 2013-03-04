package ca.ulaval.glo4002.testAcceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestSendAlarmSignalWhenIntrusion {
    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.initServers();

        fixture.createAlarmSystem();
        fixture.initializeAlarmSystem();
        fixture.armSystem();
    }

    @After
    public void tearDown() throws Exception {
        fixture.stopServers();
    }

    @Ignore
    @Test
    public void emergenciesCalledThirtySecondsAfterMainDoorIntrusion() {
        fixture.triggerMainDoorIntrusion();

        fixture.waitThirtySeconds();

        fixture.verifyPoliceWasCalled();
    }

    @Ignore
    @Test
    public void emergenciesCalledWhenSecondaryDoorIntrusion() {
        fixture.triggerSecondaryDoorIntrusion();

        fixture.verifyPoliceWasCalled();
    }

    @Ignore
    @Test
    public void emergenciesCalledWhenMovementDetected() {
        fixture.triggerMovementDetector();

        fixture.verifyPoliceWasCalled();
    }

}