package ca.ulaval.glo4002.testAcceptance;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.devices.InvalidPINException;
import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestArmViaKeypad {

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.createAlarmSystem();
    }

    @Test
    public void systemIsArmedWithFastPIN() {
        fixture.armSystemWithFastPIN();
        fixture.assertAlarmSystemIsArmed();
    }

    @Test
    public void systemIsArmedWithGoodPIN() {
        fixture.armSystemWithDefaultPIN();

        fixture.assertAlarmSystemIsArmed();
    }

    @Test
    public void systemIsNotArmedWithWrongPIN() {
        try {
            fixture.armSystemWithWrongPIN();
            fail("InvalidPINException expected.");
        } catch (InvalidPINException e) {
            fixture.assertAlarmSystemIsNotArmed();
        }

    }

    @Test
    public void thirtySecondsDelayAfterArmingSystemBeforeArming() throws InterruptedException {
        fixture.armSystemWithDefaultPIN();

        fixture.openSecondaryDoor();

        fixture.waitThirtySeconds();

        fixture.verifyPoliceWasCalled();
    }
}