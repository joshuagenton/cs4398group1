package atm.controller;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class IdleTimeController extends AbstractController {
	
	public IdleTimeController() {}
	
	Toolkit toolkit;
	Timer timer;

	// If no events happen trigger timer.  
	//TODO When we are idle, call runTimer(15) to start.
	public void runTimer(int seconds) {
	  
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		//timer.schedule(idleLogout(), seconds * 1000);
	}

	// If there is an action we need to cancel the timer.
	public void cancelTimer() {
		timer.cancel();
	
	}
	
  	public TimerTask idleLogout() {
		
  		// Log user out of the system and save changes.
  		MainController mc = new MainController();
  		mc.logout();
  		
  		// Not sure what we are supposed to return.
  		return null;
    }
 }


