/**
 * 
 */
package atm.model;

import java.util.Set;

import javax.swing.SwingUtilities;

import atm.controller.DatabaseController;
import atm.controller.Results;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author CSWells
 */
public class ATMCoreModel extends AbstractModel{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String account_number;
	private String PIN;
	private String name;
	private Results fromAccount = null;
	private Results toAccount = null;
	public TransactionTypes type;
	private int pinTries = 0;
	
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
	public synchronized void start(){
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Start, AgentStatus.Start);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
	}
	
	public synchronized void accountSelect(Results account){
		if (type == TransactionTypes.Balance)
			checkBalance(account);
	}
	
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
	public void reset(){
		account_number = null;
		PIN = null;
		name = null;
		type = null;
		pinTries = 0;
		start();
	}
	/** 
	 * @return the account_number
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getAccount_number() {
		// begin-user-code
		return account_number;
		// end-user-code
	}
	/** 
	 * @return the name
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getName() {
		// begin-user-code
		return name;
		// end-user-code
	}
	/** 
	 * @param account_number the account_number to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public synchronized void setAccount_info(String account_number, String name) {
		// begin-user-code
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
		// end-user-code
	}
	
	public synchronized void waiting(){
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Wait, AgentStatus.Wait);
    	notifyChanged(me);
		notifyAll();
	}
	
	public synchronized void cancel() {
		// begin-user-code
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Cancel, AgentStatus.Cancel);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
		// end-user-code
	}
	
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
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int account_validated;

	/** 
	 * @return the account_validated
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getAccount_validated() {
		// begin-user-code
		return account_validated;
		// end-user-code
	}

	/** 
	 * @param i the account_validated to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
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
		// end-user-code
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private DatabaseController data_baseinterface;

	/** 
	 * @return the data_baseinterface
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public DatabaseController getData_baseinterface() {
		return data_baseinterface;
	}

	/** 
	 * @param data_baseinterface the data_baseinterface to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setData_baseinterface(DatabaseController data_baseinterface) {
		this.data_baseinterface = data_baseinterface;
		data_baseinterface.validate_user(account_number, PIN);
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void start_session() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
	private Set<Results> accounts;
	public void setAccounts(Set<Results> set) {
		this.accounts = set;	
	}
	public Set<Results> getAccounts(){
		return accounts;
	}
}