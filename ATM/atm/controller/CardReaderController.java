package atm.controller;

import java.io.IOException;

import atm.model.Model;
import atm.model.ATMCoreModel;
import atm.model.CardReaderModel;

public class CardReaderController extends AbstractController {

	//private Integer cardNo;
	private CardReaderModel card = new CardReaderModel();

	public CardReaderController(){}
	
	public CardReaderController(Model model) {
		this.setModel(model);
	}

	// PARSE
	public Integer readCard(String input) {
		try {
			card.readcont(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ATMCoreModel)getModel()).setAccount_info(card.getCcn(),card.getCard_holder());
		return null;
	}

}
