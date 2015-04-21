package atm.controller;

import java.awt.image.BufferedImage;
import com.github.sarxos.webcam.Webcam;
import atm.model.ATMCoreModel;
import atm.model.TransactionTypes;
import atm.view.SelectionView;


/**
 * This is the ATMController for the views.  This contains the actions that the user
 * can perform once they have logged into their account.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */

public class ATMController extends AbstractController{

	private Webcam webcam;
	DatabaseController db = new DatabaseController();
	
	public ATMController() {
		this.webcam = Webcam.getDefault();
		if (this.webcam != null) {
			this.webcam.open();
			System.out.println("Camera initialized");
		}
		else {
			System.out.println("No camera detected");
		}
	}

	/**
	 * The function operation initiates what function the user has selected
	 * to perform (withdraw, cancel, etc.)
	 * 
	 * @param opt String that is passed from the view
	 */
	public void operation(String opt) {
		
		IdleTimeController.runTimer(this);
		
		if (opt == SelectionView.Start){
			System.out.println("Start");
			((ATMCoreModel)getModel()).start();
		}
		else if (opt == "newTransaction"){
			((ATMCoreModel)getModel()).newTransaction();
		}
		else if (opt == "InvalidPIN"){
			((ATMCoreModel)getModel()).invalidPIN();
		}
		else if (opt == "PIN"){
			
		}
		else if (opt == SelectionView.Withdraw) {
			((ATMCoreModel)getModel()).setTranType(TransactionTypes.Withdraw);
		}
		else if (opt == SelectionView.Balance) {
			((ATMCoreModel)getModel()).setTranType(TransactionTypes.Balance);
			
		}
		else if (opt == SelectionView.Cancel) {
			((ATMCoreModel)getModel()).cancel();
		}
		else if (opt == SelectionView.Enter){

		}
		else if (opt == SelectionView.Logout){
			IdleTimeController.cancelTimer();
			((ATMCoreModel)getModel()).waiting();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			((ATMCoreModel)getModel()).reset();
		}
		else if (opt == SelectionView.SelectAccount){
			((ATMCoreModel)getModel()).doTransType();
		}
		else if (opt == SelectionView.Transfer){
			((ATMCoreModel)getModel()).transfer();
		}
		else {
			System.out.println("Operation isn't defined");
			System.out.println(opt);
		}
		
		//  Reset timer in case the view change takes too long.
		if (opt != SelectionView.Logout)
			IdleTimeController.runTimer(this);
		
	}
	
	
	/**
	 * The login function allows the user to login to their account based on a 
	 * card swipe and the correct PIN entered into the system.  The card number
	 * and PIN are validated against the database to look for a match.
	 * 
	 * @param cs the card swipe information
	 * @return boolean if the login was a success or not
	 */
	public boolean login(char[] cs){
		((ATMCoreModel)getModel()).waiting();
		int num = db.validate_user(((ATMCoreModel)getModel()).getAccount_number(), String.valueOf(cs));
		if (num > 0){
			((ATMCoreModel)getModel()).setAccount_validated(num);
			getAccounts();
			//  Do camera stuff
			BufferedImage picture = db.getPicture(((ATMCoreModel)getModel()).getAccount_number(), String.valueOf(cs));
			((ATMCoreModel)getModel()).setPicture(picture);
			if (this.webcam != null) {
				System.out.println("Webcam not null");
				picture = null;
				picture = this.webcam.getImage();
				if (picture != null) {
					db.setPicture(((ATMCoreModel)getModel()).getAccount_number(), String.valueOf(cs), picture);
				}
			}
			else {
				System.out.println("Webcam is null");
			}
		}
		else {
			operation("InvalidPIN");
		}
		return false;	
	}
	
	/** 
	 * The transferFunds function allows the user to transfer funds from one account
	 * to another.  The user must select two accounts (to, from) and pass in the
	 * amount they'd like to transfer.
	 * 
	 * @param account1 The from account
	 * @param account2 The to account
	 * @param amount The amount they'd like to transfer
	 */
	public void transferFunds(Results account1, Results account2, Double amount) {
		((ATMCoreModel)getModel()).waiting();
		if(db.transfer(account1.getAccountNum(), account2.getAccountNum(), amount))		
			((ATMCoreModel)getModel()).withdrawComplete();
		else
			((ATMCoreModel)getModel()).insufficientFunds();
		System.out.println("transferred");
		
	}
	
	/**
	 * The withdrawFunds function allows the user to withdraw funds from one account
	 * they select from their available accounts.  
	 * 
	 * @param account The account that the money will be withdrawn from
	 * @param amount The amount that will be withdrawn
	 */
	public void withdrawFunds(Results account, Double amount) {
		((ATMCoreModel)getModel()).waiting();
		if(db.withdrawl(account.getAccountNum(), amount))		
			((ATMCoreModel)getModel()).withdrawComplete();
		else
			((ATMCoreModel)getModel()).insufficientFunds();
	}
	

	//  Getters/Setters

	public void getAccounts() {
		((ATMCoreModel)getModel()).setAccounts(db.getAccounts());
	}


	public void SetFromAccount(Results a){
		((ATMCoreModel)getModel()).setFromAccount(a);
	}

	public void setSelection(String selection) {
	}	
}
