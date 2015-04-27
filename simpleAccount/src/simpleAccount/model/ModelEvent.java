package simpleAccount.model;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.SortedMap;

public class ModelEvent extends ActionEvent {
	private int amount;
	private SortedMap<Integer, HashMap> accounts;
	public ModelEvent(Object obj, int id, String message, int amount, SortedMap<Integer, HashMap> accts, SortedMap<String, Agent> agents){
		super(obj, id, message);
		this.amount = amount;
		this.accounts = accts;
		
	}
	public int getAmount(){return amount;}
	public SortedMap<Integer, HashMap> getAccounts(){return accounts;}
}
