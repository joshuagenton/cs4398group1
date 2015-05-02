package simpleAccount;

import java.awt.EventQueue;
import simpleAccount.controller.AccountController;


/**
 * The Main class is where main exists.  It also contains the call to the 
 * scanner which prompts the user to enter the file path for the data they 
 * would like to interact with.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public class Main {

	public static void main(String[] args) {
		String datafile = "testFileHW4.txt";
		if (!(args.length == 0))
			datafile = args[0];
		
		AccountController controller = new AccountController();
		controller.ReadFile(datafile);

	}

}
