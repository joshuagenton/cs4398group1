package atm.controller;

import java.util.Timer;
import java.util.TimerTask;

public class IdleTimeController extends TimerTask {
	
	public IdleTimeController() {}
	
	static Timer timer;
	static TimerTask timerTask;
	static ATMController controller;

	// Any time an action is performed, reset the timer to 15 seconds
	public static void runTimer(ATMController cont) {
		controller = cont;
		if(timer != null)
			cancelTimer();
		timerTask = new IdleTimeController();
		timer = new Timer();
		timer.schedule(timerTask, 15000);
	}

	// If there is an action we need to cancel the timer.
	public static void cancelTimer() {
		timer.cancel();
	}
	
	//  This function is automatically run by the timer when it runs out
  	public void run() {
  		controller.operation("Logout");
    }
 }


