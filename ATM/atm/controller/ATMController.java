package atm.controller;

import atm.model.ATMCoreModel;
import atm.view.SelectionView;

public class ATMController extends AbstractController{

	private Integer userID;
	private Integer accountNo;
	private Double amount;
	private String selection;
	private IdleTimeController timer = new IdleTimeController();
	
	public ATMController() {}

	// 
	public void operation(String opt) {
		
		IdleTimeController.runTimer(15, this);
		
		if (opt == SelectionView.Start){
			((ATMCoreModel)getModel()).start();
		}
		else if (opt == SelectionView.Transfer) {

			
		}
		else if (opt == SelectionView.Withdraw) {
			
			
		}
		else if (opt == SelectionView.Balance) {
			
			
		}
		else if (opt == SelectionView.Cancel) {
			((ATMCoreModel)getModel()).cancel();
		}
		else if (opt == SelectionView.Enter){

		}
		else if (opt == SelectionView.Logout){
			timer.cancelTimer();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			((ATMCoreModel)getModel()).reset();
		}
		else {
			System.out.println("Operation isn't defined");
			System.out.println(opt);
		}
		
	}
	
	public void start(){
		
	}
	public boolean login(char[] cs){
		((ATMCoreModel)getModel()).waiting();
		DatabaseController db = new DatabaseController();
		((ATMCoreModel)getModel()).setAccount_validated(db.validate_user(((ATMCoreModel)getModel()).getAccount_number(), String.valueOf(cs)));
		System.out.println("LOGIN");
		System.out.println("PIN: " + String.valueOf(cs));
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



	public Integer getAccountNo() {
		return accountNo;
	}



	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
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



	public void setSelection(String selection) {
		this.selection = selection;
	}
	
	
}
