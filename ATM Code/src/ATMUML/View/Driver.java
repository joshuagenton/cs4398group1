package ATMUML.View;

import ATMUML.Controller.data_basecontroller;
import ATMUML.Model.atm_core;

public class Driver {

	public static void main(String[] args) {
		
//		atm_core atm = new atm_core();
//		atm.start_login(); 		
		
		data_basecontroller db = new data_basecontroller();
		db.validate_user("6391480001052388","1234");
	}
		
}
