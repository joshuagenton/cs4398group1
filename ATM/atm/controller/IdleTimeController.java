package atm.controller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the IdelTimeController that monitors the idle time of
 * the user.  If the user is idle for too long they are automatically
 * logged out of the system.
 * 
 * @author Stacie Christensen
 * @since 2015-03-25
 */
public class IdleTimeController extends TimerTask {
	
	public IdleTimeController() {}
	
	static Timer timer;
	static TimerTask timerTask;
	static ATMController controller;

	/**
	 * The runTimer allows the sytem to run the timer for a set amount of time.
	 * 
	 * @param cont the ATM Controller
	 */
	public static void runTimer(ATMController cont) {
		controller = cont;
		if(timer != null)
			cancelTimer();
		timerTask = new IdleTimeController();
		timer = new Timer();
		timer.schedule(timerTask, 45000);
	}

	/**
	 * The cancelTimer allows the system the ability to cancel the timer if another
	 * action is performed.
	 */
	public static void cancelTimer() {
		timer.cancel();
	}
	
	/**
	 * Run allows runTimer to initiate the timer.
	 */
  	public void run() {
  		controller.operation("Logout");
  		timer.cancel();
    }
 }


