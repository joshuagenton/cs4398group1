package atm.controller;

import java.util.Timer;
import java.util.TimerTask;

public class IdleTimeController extends TimerTask {
	
	public IdleTimeController() {}
	
	//static Toolkit toolkit;
	static Timer timer;
	static TimerTask timerTask;

	// If no events happen trigger timer.  
	//TODO When we are idle, call runTimer(15) to start.
	public static void runTimer(int seconds) {
		timerTask = new IdleTimeController();
		timer = new Timer();
		timer.schedule(timerTask, seconds * 1000);
	}

	// If there is an action we need to cancel the timer.
	public void cancelTimer() {
		timer.cancel();
	}
	
  	public void run() {
  		// Log user out of the system and save changes.
  		MainController mc = new MainController();
  		mc.logout();
    }
 }


