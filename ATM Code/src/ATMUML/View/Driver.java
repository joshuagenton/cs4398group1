package ATMUML.View;

import java.io.IOException;

import ATMUML.Controller.card_reader;

public class Driver {
	
	
	public static void main(String[] args) {
		card_reader cardRead = null;
		String test = "%T6391480001052388  ^WELLS/CHRISTOPHER         ^4912160?;6391480001052388=4912160?+202=411558900=00?";

		try {
			cardRead = new card_reader(test);
		} catch (IOException e) {
			System.out.println("Card Read Error: " +e.getMessage());
		}
		System.out.println("Cardholder: " + cardRead.getCard_holder());
		System.out.println("CCN: " + cardRead.getCcn());
		System.out.println("EXP: " + cardRead.getExpDateString());
		
	}

}
