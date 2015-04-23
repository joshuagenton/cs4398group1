/**
 * 
 */
package atm.model;

import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.SwingUtilities;

import atm.controller.DatabaseController;
import atm.controller.Results;

/**
 * The ATMCoreModel houses all the core functionality for the ATM.
 * 
 * @author Chris Wells
 *
 */
public class ATMCoreModel extends AbstractModel{

	private String account_number;
	private String PIN;
	private String name;
	private Results fromAccount = null;
	private Results toAccount = null;
	public TransactionTypes type;
	private int pinTries = 0;
	private BufferedImage picture = null;
	private int account_validated;
	private DatabaseController data_baseinterface;
	

	
	
	public synchronized void dbError(){
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Wait, AgentStatus.DBCommError);
		reset();
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}
	/**
	 * The withdrawComplete is a synchronized function that refreshes the view
	 * when the action is complete.
	 */
	public synchronized void withdrawComplete(){
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountWithdrawUpdate, AgentStatus.WithdrawComplete);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}
	
	/**
	 * The insufficientFunds is a synchronized function that refreshes the view
	 * when insufficients funds are found.  This is an error to the user.
	 */
	public synchronized void insufficientFunds(){
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountWithdrawUpdate, AgentStatus.InsufFunds);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}
	
	/**
	 * The withdraw is a synchronized function that refreshes the view
	 * when the the withdraw action is fired.
	 */
	public synchronized void withdraw(){
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountWithdrawUpdate, AgentStatus.Withdraw);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}
	
	/**
	 * The doTransType is a synchronized function that refreshes the view
	 * when the the withdraw action is fired.
	 */
	public synchronized void doTransType(){
		if(type == TransactionTypes.Withdraw){
			final ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountWithdrawUpdate, AgentStatus.Withdraw);
			SwingUtilities.invokeLater(
					new Runnable() {
					    public void run() {
					    	notifyChanged(me);
					    }
					});
			}
			
		else if (type == TransactionTypes.Transfer && toAccount == null){
			final ModelEvent me = new ModelEvent(ModelEvent.EventKind.SelectAccount, AgentStatus.SelectToAccount);
			SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						notifyChanged(me);
						}
					});
			}
		else if (type == TransactionTypes.Transfer){
			final ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountTransferredUpdate, AgentStatus.Transfer);
			SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						notifyChanged(me);
						}
					});
			}
		notifyAll();
	}
	
	/**
	 * The setTranType is a synchronized function that refreshes the view
	 * when the transaction type is selected.
	 * 
	 * @param incoming the selection made by the user
	 */
	public synchronized void setTranType(TransactionTypes incoming){
		type = incoming;
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.SelectAccount, AgentStatus.SelectFromAccount);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}
	
	/**
	 * The TransactionTypes is a synchronized function that refreshes the view.
	 * 
	 * @return type the transaction type
	 */
	public synchronized TransactionTypes getTransType(){
		return type;
	}
	
	/**
	 * The invalidPIN is a synchronized function that refreshes the view
	 * when an invalid PIN is entered.
	 */
	public synchronized void invalidPIN(){
		if (pinTries < 3){
			pinTries ++;
			final ModelEvent me = new ModelEvent(ModelEvent.EventKind.invalidPIN, AgentStatus.InvalidPIN);
			SwingUtilities.invokeLater(
					new Runnable() {
					    public void run() {
					    	notifyChanged(me);
					    }
					});
		}
		else cancel();
		notifyAll();
				
	}
	
	/**
	 * The newTransaction is a synchronized function that refreshes the view
	 * when another transaction is requested by the user.
	 */
	public synchronized void newTransaction(){
		PIN = null;
		fromAccount = null;
		toAccount = null;
		type = null;
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Login, AgentStatus.NeedPIN);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
				
	}
	
	/**
	 * The start is a synchronized function that refreshes the view
	 * when the user starts a session.
	 */
	public synchronized void start(){
		System.out.println("Start: Model");
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Start, AgentStatus.Start);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
	}
	
	/**
	 * The accountSelect is a synchronized function that refreshes the view
	 * when the user selects an account to interact with.
	 * 
	 * @param account the account the user selected
	 */
	public synchronized void accountSelect(Results account){
		if (type == TransactionTypes.Balance)
			checkBalance(account);
	}
	
	/**
	 * The checkBalance is a synchronized function that refreshes the view
	 * when the user selects an account to view the balance of.
	 * 
	 * @param account the account the user selected
	 */
	public synchronized void checkBalance(Results account){
		fromAccount = account;

		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.CheckBalance, AgentStatus.CheckBalance);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}

	/**
	 * The reset functions resets the user information including the name, account, etc.
	 * 
	 */
	public void reset(){
		account_number = null;
		PIN = null;
		name = null;
		type = null;
		fromAccount = null;
		toAccount = null;
		pinTries = 0;
		start();
	}
	
	/**
	 * The waiting function is a synchronized function that allows the system
	 * to wait for the user to make an action.
	 * 
	 */
	public synchronized void waiting(){
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Wait, AgentStatus.Wait);
    	notifyChanged(me);
		notifyAll();
	}
	
	/**
	 * The cancel is a synchronized function that allows the user the ability to cancel their 
	 * interaction with the system.
	 */
	public synchronized void cancel() {
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Cancel, AgentStatus.Cancel);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}
	
	/**
	 * The transfer is a synchronized function that allows the user the ability to transfer
	 * funds from one account to another.
	 */
	public synchronized void transfer(){
		type = TransactionTypes.Transfer;
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.SelectAccount, AgentStatus.SelectFromAccount);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}

	
	// Getters/Setters

	public void setPicture(BufferedImage picture) {
		this.picture = picture;
	}
	
	public BufferedImage getPicture() {
		return this.picture;
	}
	
	private Set<Results> accounts;
	
	public void setAccounts(Set<Results> set) {
		this.accounts = set;	
	}
	public Set<Results> getAccounts(){
		return accounts;
	}
	
	public DatabaseController getData_baseinterface() {
		return data_baseinterface;
	}

	public void setData_baseinterface(DatabaseController data_baseinterface) {
		this.data_baseinterface = data_baseinterface;
		data_baseinterface.validate_user(account_number, PIN);
	}
	
	public int getAccount_validated() {
		return account_validated;
	}

	public synchronized void setAccount_validated(int i) {
		this.account_validated = i;
		
		if (i > 0){
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Verified, AgentStatus.Verified);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		}
		notifyAll();
	}
	
	public String getAccount_number() {
		return account_number;
	}

	public String getName() {
		return name;
	}
	
	public synchronized void setFromAccount(Results a){
		if (fromAccount == null)
			fromAccount = a;
		else toAccount = a;
		if (type == TransactionTypes.Balance){
			final ModelEvent me = new ModelEvent(ModelEvent.EventKind.CheckBalance, AgentStatus.CheckBalance);
			SwingUtilities.invokeLater(
					new Runnable() {
					    public void run() {
					    	notifyChanged(me);
					    }
					});
		}
		
		notifyAll();
	}
	
	public Results getFromAccount(){
		return fromAccount;
	}
	
	public Results getToAccount(){
		return toAccount;
	}
	
	public synchronized void setAccount_info(String account_number, String name) {
		this.account_number = account_number;
		this.name = name;
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Login, AgentStatus.NeedPIN);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}
}