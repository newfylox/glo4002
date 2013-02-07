package ca.ulaval.glo4002.endToEndTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

public class EndToEndTest {
  private String RESPONSE_TO_POST_REQUEST_FROM_EMERGENCY = "POST request received at emergency server";
  private String RESPONSE_TO_POST_REQUEST_FROM_CENTRAL = "POST request received at central server";
  private String RESPONSE_TO_POST_REQUEST = RESPONSE_TO_POST_REQUEST_FROM_CENTRAL
      + RESPONSE_TO_POST_REQUEST_FROM_EMERGENCY;
  private String A_POST_REQUEST = "simple request";

  @Before
  public void setUp() throws Exception {
    CentralServer.startServer();
    EmergencyServer.startServer();
  }

  @After
  public void tearDown() throws Exception {
    CentralServer.stopServer();
    EmergencyServer.stopServer();
  }

  @Test
  public void endToEndTest() {
    Home home = new Home();
    home.armSystem();
    home.openMainDoor();
    assertEquals(postRequestAnswer, RESPONSE_TO_POST_REQUEST);
  }
}
