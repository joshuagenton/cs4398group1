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
	private SortedMap<Integer, HashMap> accounts;
	private SortedMap<String, AgentModel> agents;
	
	public ModelEvent(Object obj, int id, String message, SortedMap<Integer, HashMap> accts, SortedMap<String, AgentModel> agents, EventKind kind){
		super(obj, id, message);
		this.accounts = accts;
		this.agents = agents;
		this.kind = kind;
		
	}
	
	public SortedMap<Integer, HashMap> getAccounts(){
		return accounts;
	}
	
	public EventKind getKind(){
		return kind;
	}
	
	public SortedMap<String, AgentModel> getAgents(){
		return agents;
	}
	
}
