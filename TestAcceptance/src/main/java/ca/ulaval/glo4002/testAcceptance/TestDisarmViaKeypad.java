package ca.ulaval.glo4002.testAcceptance;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ca.ulaval.glo4002.testFixtures.TestFixture;

public class TestDisarmViaKeypad {
    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();

        fixture.createAlarmSystem();
        fixture.armSystem();
    }

    @Test
    public void systemIsDisarmedWhenDisarmedWithGoodNIP() {
        fixture.disarmSystemWithGoodNIP();

        fixture.assertAlarmSystemIsNotArmed();
    }

    @Ignore
    @Test
    public void systemIsArmedWhenDisarmedWithWrongNIP() {
        fixture.disarmSystemWithWrongNIP();

        fixture.assertAlarmSystemIsArmed();
    }

}