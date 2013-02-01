package ca.ulaval.glo4002.client.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.client.Home;

public class HomeTest {

	private Home home;

	@Before
	public void initClient() {
		home = new Home();
	}

	@Test
	public void homeWasInitializedCorrectly() {
		assertTrue(home.getHomeConnectionHandler() != null);
	}

	@Test
	public void initialAlarmStatusIsUnarmed() {
		assertFalse(home.systemIsArmed());
	}

	@Test
	public void systemCanBeArmed() {
		home.armSystem();
		assertTrue(home.systemIsArmed());
	}

	@Test
	public void systemCanDetectMainDoorOpening() {
		home.openMainDoor();
		assertTrue(home.mainDoorIsOpen());
	}

	@Test
	public void systemSendsRequestToServerWhenAnIntrusionIsDetected() {
		home.armSystem();
		home.openMainDoor();
		assertTrue(home.hasSentRequestToServer);
	}

}
