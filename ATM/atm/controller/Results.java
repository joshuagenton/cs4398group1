package atm.controller;

import java.util.Comparator;

/**
 * This is the Results controller.  It is where the results of a user's
 * transactions come from.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class Results{
	
	private String name;
	private Integer accountNum;
	private Double balance;
	
	/**
	 * Default constructor.
	 */
	public Results(){}
	
	/**
	 * Constructor.
	 * @param n the name of the user
	 * @param num the account number
	 * @param b the balance of the account
	 */
	public Results(String n, Integer num, Double b){
		name = n;
		accountNum = num;
		balance = b;
	}
	
	
	// Getters/Setters
	
	public String getName(){
		return name;
	}
	
	public Integer getAccountNum(){
		return accountNum;
	}
	public Double getBalance(){
		return balance;
	}
}

/**
 * The Comparator allows the system the avbility to compare two results.
 * 
 * @author Chris Wells
 */
class AccountCompare implements Comparator<Results> {
	@Override
	public int compare(Results o1, Results o2) {
		return o1.getAccountNum().compareTo(o2.getAccountNum());
	}
	
}