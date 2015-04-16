package atm.controller;

import atm.model.ATMCoreModel;
import atm.model.TransactionTypes;
import atm.view.SelectionView;

public class ATMController extends AbstractController{

	private Integer userID;
	private String accountNo;
	private Double amount;
	private String selection;
	
	public ATMController() {}

	// 
	public void operation(String opt) {
		
		IdleTimeController.runTimer(this);
		
		if (opt == SelectionView.Start){
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
	
	public void start(){
		
	}
	DatabaseController db;
	public boolean login(char[] cs){
		((ATMCoreModel)getModel()).waiting();
		db = new DatabaseController();
		int num = db.validate_user(((ATMCoreModel)getModel()).getAccount_number(), String.valueOf(cs));
		if (num > 0){
			((ATMCoreModel)getModel()).setAccount_validated(num);
			getAccounts();
		}
		else {
			operation("InvalidPIN");
		}
		return false;	
	}
	// TRANSFER
	public void transferFunds(Results account1, Results account2, Double amount) {
		((ATMCoreModel)getModel()).waiting();
		db = new DatabaseController();
		if(db.transfer(account1.getAccountNum(), account2.getAccountNum(), amount))		
			((ATMCoreModel)getModel()).withdrawComplete();
		else
			((ATMCoreModel)getModel()).insufficientFunds();
		
		
	}
	
	// BALANCE
	public void showBalance(Integer accountNo) {
		
		
	}
	
	// WITHDRAW
	public void withdrawFunds(Results account, Double amount) {
		((ATMCoreModel)getModel()).waiting();
		db = new DatabaseController();
		if(db.withdrawl(account.getAccountNum(), amount))		
			((ATMCoreModel)getModel()).withdrawComplete();
		else
			((ATMCoreModel)getModel()).insufficientFunds();
	}
	
	// SUFFICIENT FUNDS
	public boolean sufficientFunds(Double amount, Integer accountNo) {
		return false;
		
		
	}


	

	public Integer getUserID() {
		return userID;
	}



	public void setUserID(Integer userID) {
		this.userID = userID;
	}



	public String getAccountNo() {
		return accountNo;
	}



	public void getAccounts() {
		((ATMCoreModel)getModel()).setAccounts(db.getAccounts());
	}



	public Double getAmount() {
		return amount;
	}



	public void setAmount(Double amount) {
		this.amount = amount;
	}



	public String getSelection() {
		return selection;
	}

	public void SetFromAccount(Results a){
		((ATMCoreModel)getModel()).setFromAccount(a);
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}	
}
