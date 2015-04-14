package atm.controller;

import java.util.Comparator;


public class Results{
	
	private String name;
	private Integer accountNum;
	private Double balance;
	
	public Results(){}
	public Results(String n, Integer num, Double b){
		name = n;
		accountNum = num;
		balance = b;
	}
	
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
class AccountCompare implements Comparator<Results> {
	@Override
	public int compare(Results o1, Results o2) {
		return o1.getAccountNum().compareTo(o2.getAccountNum());
	}
	
}