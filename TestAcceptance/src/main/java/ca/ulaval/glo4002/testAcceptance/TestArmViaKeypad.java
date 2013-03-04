package ca.ulaval.glo4002.testAcceptance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.devices.InvalidPINException;
import ca.ulaval.glo4002.testFixtures.TestFixture;

import com.sun.jersey.api.client.Client;

public class TestArmViaKeypad {

    private static final int THIRTY_SECONDS = 30000;
    private static final int FIFTEEN_SECONDS = 15000;

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.createAlarmSystem();
    }

    @After
    public void teardown() throws Exception {}

    @Test
    public void systemIsArmedWithFastPIN() {
        fixture.armSystemWithFastPIN();
        fixture.assertAlarmSystemIsArmed();
    }

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
    public void thirtySecondsDelayAfterArmingSystemBeforeArming() throws Exception {
        fixture.initServers();

        fixture.waitSeconds(FIFTEEN_SECONDS);

        Client client = Client.create();

        // WebResource webResource =
        // client.resource("http://localhost:8080/inscription/");
        //
        // String input = "GODO";
        //
        // ClientResponse response =
        // webResource.type("application/json").post(ClientResponse.class,
        // input);
        //
        // if (response.getStatus() != 200) {
        // throw new RuntimeException("Failed : HTTP error code : " +
        // response.getStatus());
        // }

        fixture.initializeAlarmSystem();

        fixture.armSystemWithDefaultPIN();

        fixture.openSecondaryDoor();
        fixture.waitSeconds(THIRTY_SECONDS);

        fixture.verifyPoliceWasNotCalled();
        fixture.stopServers();
    }
}