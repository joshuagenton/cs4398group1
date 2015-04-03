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
}