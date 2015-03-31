package atm.model;

import javax.swing.SwingUtilities;


public class AccountModel extends AbstractModel {

	private double balance;
	
	public AccountModel(double balance){
		this.balance = balance;
	}
	
	
	public double getBalance(){
		return balance;
	}
	
	
	
	
	public synchronized void withdraw(double amount) throws OverdrawException {
		double newB = balance - amount;
		
		if(newB < 0.0) throw new OverdrawException(newB);
		
		balance = newB;
		
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, balance, AgentStatus.NA);
		
		SwingUtilities.invokeLater(
			new Runnable() {
			    public void run() {
			    	notifyChanged(me);
			    }
			});
	}
}
