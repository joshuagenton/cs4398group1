package atm.controller;

import java.io.IOException;

import atm.model.Model;
import atm.model.atm_core;
import atm.model.card_reader;

public class CardReaderController extends AbstractController {

	private Integer cardNo;
	private card_reader card = new card_reader();

	public CardReaderController(){}
	
	public CardReaderController(Model model) {
		this.setModel(model);
	}

	// PARSE
	public Integer readCard(String input) {
		try {
			card.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((atm_core)getModel()).setAccount_info(card.getCcn(),card.getCard_holder());
		return null;
	}

}
