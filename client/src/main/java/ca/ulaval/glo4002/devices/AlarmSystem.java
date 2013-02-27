package ca.ulaval.glo4002.devices;

import ca.ulaval.glo4002.utilities.DelayTimer;
import ca.ulaval.glo4002.utilities.DelayTimerDelegate;

public class AlarmSystem implements DelayTimerDelegate{

	private static final int DELAY = 30;
    
	private boolean isArmed;
    private boolean isReady;
    private DelayTimer delayTimer;
    
    public AlarmSystem() {
    	isReady = true;
    	isArmed = false;
    	delayTimer = new DelayTimer(this);
    }

    public boolean isArmed() {
        return isArmed == true;
    }

	public void arm() throws BadStateException {
		if(!isReady){
			throw new BadStateException();
		} else {
			startDelay();
		}
    }
	
    public void disarm() {
    	isArmed =false;
    }

	public void setNotReady() {
	    isReady = false;
    }

	public void setReady() {
	    isReady = true;
    }

	private void startDelay() {
		delayTimer.startDelay(DELAY, this);
    }

	@Override
    public void delayExpired(Object identifier) {
	    isArmed = true;
    }
}