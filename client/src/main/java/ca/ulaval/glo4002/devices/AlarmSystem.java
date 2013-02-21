package ca.ulaval.glo4002.devices;

import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class AlarmSystem implements DelayTimerDelegate{

    private enum SystemStatus {
        ARMED, DISARMED
    };

    private boolean isReady;
    private SystemStatus status;
    private DelayTimer delayTimer;
    
    public AlarmSystem() {
    	isReady = true;
    	status = SystemStatus.DISARMED;
    	delayTimer = new DelayTimer(this);
    }

    public boolean isArmed() {
        return status == SystemStatus.ARMED;
    }

	public void arm() throws BadStateException {
		if(!isReady){
			throw new BadStateException();
		} else {
			status = SystemStatus.ARMED;
		}
    }
	
    public void disarm() {
    	status = SystemStatus.DISARMED;
    }

	public void setNotReady() {
	    isReady = false;
    }

	public void setReady() {
	    isReady = true;
    }

	public void startDelay() {
		delayTimer.startDelay(30, this);
    }

	@Override
    public void delayExpired(Object identifier) {
	    try {
	        arm();
        } catch (BadStateException e) {
	        e.printStackTrace();
        }
    }
}