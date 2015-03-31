package atm.controller;

import java.util.Arrays;
import java.util.Date;

public class MainController extends AbstractController {

	private Integer cardNo;
	private String pin;
	private Date timeDate;
	private Arrays actions;
	
	public MainController() {}
	
	public void operation(String opt) {
		
		
	}
	
	// LOGIN
	public boolean login(Integer cardNo, String pin) {
		return false;
		
		
	}
	
	// LOGOUT
	public void logout() {
		
		
		
	}
	
	
	// SESSION
	public void sesssion(Date timeDate, Arrays actions) {
		
	}

}
