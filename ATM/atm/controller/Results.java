package atm.controller;


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