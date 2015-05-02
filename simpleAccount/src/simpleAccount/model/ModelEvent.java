package simpleAccount.model;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.SortedMap;


/**
 * This is the ModelEvent class which classifies the type of
 * event that is occurring.  
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public class ModelEvent extends ActionEvent {
	
	public enum EventKind {
		BalanceUpdate, AgentStatusUpdate, AmountTransferredUpdate
	}
	
	private EventKind kind;
	private double balance;
	private AgentStatus agSt;
	
	
	private int amount;
	private SortedMap<Integer, HashMap> accounts;
	private SortedMap<String, AgentModel> agents;
	
	public ModelEvent(Object obj, int id, String message, int amount, SortedMap<Integer, HashMap> accts, SortedMap<String, AgentModel> agents, AgentStatus agSt, EventKind kind){
		super(obj, id, message);
		this.amount = amount;
		this.accounts = accts;
		this.agents = agents;
		this.kind = kind;
		this.agSt = agSt;
		
	}
	
	public int getAmount(){return amount;}
	public SortedMap<Integer, HashMap> getAccounts(){
		return accounts;
	}
	public EventKind getKind(){
		return kind;
	}
	public double getBalance(){
		return balance;
	}
	public AgentStatus getAgStatus(){
		return agSt;
	}
	public SortedMap<String, AgentModel> getAgents(){
		return agents;
	}
	
}
