package simpleAccount.model;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class AccountModel extends AbstractModel {
	private SortedMap<Integer, HashMap> accounts = new TreeMap<Integer, HashMap>();

	private int total = 0;
	private int current = 0;
	private String state = "add";
	
	public void clear(){total = 0; store(0);}
	
	public void store(int value){
		current = value;
		ModelEvent me = new ModelEvent(this, 1, "", current, accounts);
		notifyChanged(me);
	}
	
	public void refresh(){
		ModelEvent me = new ModelEvent(this, 1, "", current, accounts);
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
		ModelEvent me = new ModelEvent(this, 1, "", total, accounts);
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

	public SortedMap<Integer, HashMap> getAccounts() {
		return accounts;
	}

}
