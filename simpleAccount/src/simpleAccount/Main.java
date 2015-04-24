package simpleAccount;

import java.awt.EventQueue;
import simpleAccount.controller.AccountController;
import simpleAccount.view.TestView;

public class Main {

	public static void main(String[] args) {
		String datafile = "testfileHW4.txt";
		if (!(args.length == 0))
			datafile = args[0];
		
		AccountController controller = new AccountController();
		controller.ReadFile(datafile);
		
		// TODO Auto-generated method stub
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestView window = new TestView();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		*/

	}

}
