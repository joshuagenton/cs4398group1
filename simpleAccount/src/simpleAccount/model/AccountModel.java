package simpleAccount.model;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class AccountModel extends AbstractModel {
	private SortedMap<Integer, HashMap> accounts = new TreeMap<Integer, HashMap>();
	private SortedMap<String, Agent> agents = new TreeMap<String, Agent>();

	private int total = 0;
	private int current = 0;
	private String state = "add";
	
	public void clear(){total = 0; store(0);}
	
	public void store(int value){
		current = value;
		ModelEvent me = new ModelEvent(this, 1, "", current, accounts, agents);
		notifyChanged(me);
	}
	
	public void refresh(){
		ModelEvent me = new ModelEvent(this, 1, "", current, accounts, agents);
		notifyChanged(me);
	}
	
	public void add()throws Exception
	{if(current == 5) throw new Exception("method add class CalculatorModel: current is 5");
		state = "add"; total = current;}
	
	public void subtract(){state = "subtract"; total = current;}
	
	public void equals(){
		if(state == "add"){
			total += current;
		}
		else {
			total -= current;
		}
		current = total;
		ModelEvent me = new ModelEvent(this, 1, "", total, accounts, agents);
		notifyChanged(me);
	}

	public void AddAccount(HashMap<String, Object> account) {
		try {
			this.accounts.put(Integer.parseInt((String)(account.get("id"))), account);
			
		} catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public void PrintAccounts() {
		for (Integer key : accounts.keySet()) {
			System.out.println(accounts.get(key).get("id") + " " + accounts.get(key).get("name") + " " + String.format("%.2f", accounts.get(key).get("amount")));
		}
	}
	
	public void deposit (String id, String currency, Double amount) throws Exception {
		HashMap<String, Object> account = accounts.get(Integer.parseInt(id));
		if (((Double) account.get("amount")) + amount < 0) {
			throw new Exception("Insufficient funds: amount to withdraw is " + (-1 * (((Double) account.get("amount")) + amount)) + " greater than available funds: " + ((Double) account.get("amount")));
		}
		else {
			account.put("amount", (((Double) account.get("amount")) + amount));
			accounts.put(Integer.parseInt(id), account);
			refresh();
		}
	}
	
	public synchronized Boolean AddAmount (Agent agent) {
		while ((Double) accounts.get(agent.accountID).get("amount") + agent.amount < 0.0 && agent.agentRunning) {
			try {
				if (!agent.agentStatus.equals("Blocked")) {
					agent.agentStatus = "Blocked";
					refresh();
				}
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!agent.agentRunning)
			return false;
		
		agent.agentStatus = "Running";
		Double amount = (Double) accounts.get(agent.accountID).get("amount");
		accounts.get(agent.accountID).put("amount", ((Double) accounts.get(agent.accountID).get("amount") + agent.amount));
		if ((Double) accounts.get(agent.accountID).get("amount") >= 0.0) {
			System.out.println("Added amount "+ agent.amount + " for " + ((Double) accounts.get(agent.accountID).get("amount")) + " to " + accounts.get(agent.accountID).get("id"));
			refresh();
			notifyAll();
			return true;
		}
		return false;
	}
	
	public void StartAgent(String accountID, double amount, double ops, String agentID) {
		if (!agents.containsKey(agentID)) {
			//Thread T1 = new Thread(new Agent(accountID, amount, ops, agentID, this));
			//agents.put(agentID, T1);
		}
	}

	public SortedMap<Integer, HashMap> getAccounts() {
		return accounts;
	}

}
