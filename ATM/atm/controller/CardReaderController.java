package atm.controller;

import java.io.IOException;

import atm.model.Model;
import atm.model.ATMCoreModel;
import atm.model.CardReaderModel;


/**
 * This is the ATMController for the views.  This contains the actions that the user
 * can perform once they have logged into their account.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class CardReaderController extends AbstractController {
	
	private CardReaderModel card = new CardReaderModel();
	public CardReaderController(){}
	
	/** 
	 * Constructor
	 * 
	 * @param model the ATMCoreModel
	 */
	public CardReaderController(Model model) {
		this.setModel(model);
	}

	/**
	 * The readCard function parses the information that we receive when the user
	 * swipes their card.  It will parse out the CC number.
	 * 
	 * @param input the parsed information
	 */
	public void readCard(String input) {
		try {
			card.readcont(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		((ATMCoreModel)getModel()).setAccount_info(card.getCcn(),card.getCard_holder());
	}

}
