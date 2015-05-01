package simpleAccount.model;


import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.SwingUtilities;

import simpleAccount.model.ModelEvent.EventKind;

public class DepositAgent extends AbstractModel implements Runnable, Agent {
	private Object pauseLock;
	private boolean paused;
	public volatile boolean active;
	private Account account;
	private double amount;
	private double transferred;
	private String name = new String("Default");
	private AgentStatus status;
	private int acctID;
	
	private SortedMap<Integer, HashMap> accounts = new TreeMap<Integer, HashMap>();
	private SortedMap<String, AgentModel> agents = new TreeMap<String, AgentModel>();
	
	public DepositAgent(Account account, double amount, int acctID){
		this.account = account;
		this.amount = amount;
		this.transferred = 0;
		this.status = AgentStatus.Running;
		this.active = true;
		this.paused = false;
		this.pauseLock = new Object();
		this.acctID = acctID;
	}
	public void run() {
		while(active) {
			synchronized (pauseLock) {
                while (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                    	System.out.println("Thread " + Thread.currentThread().getName() + " interrupted");
                    }
                }
            }
			account.agentDeposit(amount);
			this.transferred += amount;
			
			int iTrans = (int) transferred;
			
			final ModelEvent me = new ModelEvent(account, acctID, "", iTrans, accounts, agents, AgentStatus.NA, ModelEvent.EventKind.AmountTransferredUpdate);
					//ModelEvent.EventKind.AmountTransferredUpdate, transferred, AgentStatus.NA);
			SwingUtilities.invokeLater(
					new Runnable() {
					    public void run() {
					    	notifyChanged(me);
					    }
					});
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException ex){
				System.out.println("Thread " + Thread.currentThread().getName() + " interrupted");
			}
		}
	}
	public double getTransferred(){return transferred;}
	public void onPause() {
        synchronized (pauseLock) {
            paused = true;
            setStatus(AgentStatus.Paused);
        }
    }

    public void onResume() {
        synchronized (pauseLock) {
            paused = false;
            setStatus(AgentStatus.Running);
            pauseLock.notify();
        }
    }
    public void setStatus(AgentStatus agSt) {
    	status = agSt;
    	final ModelEvent me = new ModelEvent(account, acctID, "", 0, accounts, agents, agSt, ModelEvent.EventKind.AgentStatusUpdate);
    	SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
    }
    public AgentStatus getStatus(){return status;}
    public void setName(String name) {this.name = name;}
    public String getName(){return name;}
    public Account getAccount(){return account;}
    public void finish(){
    	active = false;
    }
}
