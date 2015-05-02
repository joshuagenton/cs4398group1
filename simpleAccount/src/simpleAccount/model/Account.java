package simpleAccount.model;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.SwingUtilities;


/**
 * This is the AccountModel which is the model for the selected account
 * which we are interacting with.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public class Account extends AbstractModel {
	private SortedMap<Integer, HashMap> accounts = new TreeMap<Integer, HashMap>();
	private SortedMap<String, AgentModel> agents = new TreeMap<String, AgentModel>();

	private int total = 0;
	private int current = 0;
	private String state = "add";
	
	public void clear(){total = 0; store(0);}
	
	/**
	 * This stores the intial value before updating.
	 * @param value the value we are storing.
	 */
	public void store(int value){
		current = value;
		ModelEvent me = new ModelEvent(this, 1, "", current, accounts, agents, ModelEvent.EventKind.BalanceUpdate);
		notifyChanged(me);
	}
	
	/**
	 * Refresh the values in the view.
	 */
	public void refresh(){
		ModelEvent me = new ModelEvent(this, 1, "", current, accounts, agents, ModelEvent.EventKind.BalanceUpdate);
		notifyChanged(me);
	}
	
	/**
	 * 
	 * @throws Exception method add class CalculatorModel: current is 5
	 */
	public void add()throws Exception
	{if(current == 5) throw new Exception("method add class CalculatorModel: current is 5");
		state = "add"; total = current;}
	
	/**
	 * 
	 */
	public void subtract(){state = "subtract"; total = current;}
	
	public void equals(){
		if(state == "add"){
			total += current;
		}
		else {
			total -= current;
		}
		current = total;
		ModelEvent me = new ModelEvent(this, 1, "", total, accounts, agents, ModelEvent.EventKind.BalanceUpdate);
		notifyChanged(me);
	}

	/**
	 * Adds account to the HashMap.
	 * @param account the account we wish to add
	 */
	public void AddAccount(HashMap<String, Object> account) {
		try {
			this.accounts.put(Integer.parseInt((String)(account.get("id"))), account);
			
		} catch (Exception e) {
            System.out.println(e);
        }
	}
	
	/**
	 * Prints out all the accounts.
	 */
	public void PrintAccounts() {
		for (Integer key : accounts.keySet()) {
			System.out.println(accounts.get(key).get("id") + " " + accounts.get(key).get("name") + " " 
					+ String.format("%.2f", accounts.get(key).get("amount")));
		}
	}
	
	/**
	 * The synchronized deposit() function allows users to withdraw (pass in negative value) or deposit an
	 * amount into the selected account.
	 * 
	 * @param id the accountID
	 * @param currency the type of currency selected
	 * @param amount the amount the user wants to deposit/withdraw
	 * @throws Exception for the parsing of the ID
	 */
	public synchronized void deposit (String id, String currency, Double amount) throws Exception {
		HashMap<String, Object> account = accounts.get(Integer.parseInt(id));
		
		System.out.println("I AM HERE.");
		System.out.println(amount);
		System.out.println(id);
		
		if (((Double) account.get("amount")) + amount < 0) {
			throw new Exception("Insufficient funds: amount to withdraw is " + (-1 * (((Double) account.get("amount")) + amount)) 
					+ " greater than available funds: " + ((Double) account.get("amount")));
		}
		else {
			account.put("amount", (((Double) account.get("amount")) + amount));
			accounts.put(Integer.parseInt(id), account);
			refresh();
		}
	}
	
	/**
	 * The synchronized (for the agents) AddAmount() function which allows us to add
	 * to the balance of the selected account.
	 * @param agent the agent that is running
	 * @return boolean for if the add was successful
	 */
	public synchronized boolean AddAmount (AgentModel agent) {
		while ((Double) accounts.get(Integer.parseInt(agent.accountID)).get("amount") + agent.amount < 0.0 && agent.agentRunning) {
			try {
				if (!agent.agentStatus.equals("Blocked")) {
					agent.agentStatus = "Blocked";
					refresh();
				}
				System.out.println("Agent waiting " + agent.agentID);
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!agent.agentRunning)
			return false;
		
		agent.agentStatus = "Running";
		Double amount = (Double) accounts.get(Integer.parseInt(agent.accountID)).get("amount");
		accounts.get(Integer.parseInt(agent.accountID)).put("amount", ((Double) accounts.get(Integer.parseInt(agent.accountID)).get("amount") + agent.amount));
		if ((Double) accounts.get(Integer.parseInt(agent.accountID)).get("amount") >= 0.0) {
			System.out.println("Modified account " + accounts.get(Integer.parseInt(agent.accountID)).get("id") + " by "+ agent.amount + " to a total of " + ((Double) accounts.get(Integer.parseInt(agent.accountID)).get("amount")));
			refresh();
			notifyAll();
			return true;
		}
		return false;
	}
	
	public synchronized void AddAgent (AgentModel agent) {
		agents.put(agent.agentID, agent);
		refresh();
	}
	
	public synchronized void RemoveAgent (AgentModel agent) {
		agents.remove(agent.agentID);
	}
	
	public synchronized void SetAgent (String accountID, double amount, double ops, String agentID, Boolean agentRunning, String agentStatus) {
		if (!agents.containsKey(agentID)) {
			System.out.println("Created new agent " + agentID);
			AgentModel agent = new AgentModel(accountID, amount, ops, agentID, agentRunning, agentStatus, this);
			Thread agentThread = new Thread(agent);
			agents.put(agentID, agent);
			agentThread.start();
		} else {
			agents.get(agentID).amount = amount;
			agents.get(agentID).ops = ops;
			agents.get(agentID).agentRunning = agentRunning;
			agents.get(agentID).agentStatus = agentStatus;
			refresh();
		}
	}
	
	/**
	 * getAccounts() returns a list of the accounts from the text file.
	 * @return StortedMap of the accounts.
	 */
	public SortedMap<Integer, HashMap> getAccounts() {
		return accounts;
	}
	
	
	
}
