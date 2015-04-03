package atm.controller;

import java.util.Map;
import java.util.Map.Entry;

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
		else if (opt == SelectionView.Withdraw) {
			
			
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
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			((ATMCoreModel)getModel()).reset();
		}
		else if (opt == SelectionView.SelectAccount){
			
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
			operation("Logout");
		}
		return false;
		
	}
	// TRANSFER
	public boolean transferFunds(Double amount) {
		return false;
		
		
	}
	
	// BALANCE
	public void showBalance(Integer accountNo) {
		
		
	}
	
	// WITHDRAW
	public boolean withdrawFunds(Double amount) {
		return false;
		
		
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
