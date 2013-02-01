package ca.ulaval.glo4002.client;

public class Home {

	private HomeConnectionHandler homeConnectionHandler;
	private boolean isArmed = false;
	private boolean mainDoorIsOpened = false;
	public boolean hasSentRequestToServer = false;

	public Home() {
		homeConnectionHandler = new HomeConnectionHandler();
	}

	public HomeConnectionHandler getHomeConnectionHandler() {
		return homeConnectionHandler;
	}

	public boolean systemIsArmed() {
		return isArmed;
	}

	public void armSystem() {
		isArmed = true;
	}

	public void openMainDoor() {
		mainDoorIsOpened = true;
		if (systemIsArmed()) {
			detectIntrusion("Main door is open");
		}
	}

	public boolean mainDoorIsOpen() {
		return mainDoorIsOpened;
	}

	private boolean detectIntrusion(String info) {
		if (sendRequestToServer(info)) {
			return true;
		}
		return false;
	}

	private boolean sendRequestToServer(String info) {
		getHomeConnectionHandler().sendPostRequest(info);
		hasSentRequestToServer = true;
		return true;
	}
}
