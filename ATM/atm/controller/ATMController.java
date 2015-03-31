package atm.controller;

import atm.view.SelectionView;

public class ATMController extends AbstractController{

	private Integer userID;
	private Integer accountNo;
	private Double amount;
	private String selection;
	
	
	
	
	public ATMController() {}
	
	// 
	public void operation(String opt) {
		if (opt == SelectionView.Transfer) {

			
		}
		else if (opt == SelectionView.Withdraw) {
			
			
		}
		else if (opt == SelectionView.Balance) {
			
			
		}
		else {
			// invalid selection
		}
	
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
