package atm.model;


import atm.controller.*;
import atm.view.*;

public class ModelEvent {
	
	public enum EventKind {
		BalanceUpdate, AmountTransferredUpdate, AmountWithdrawUpdate
	}
	
	private EventKind kind;
	private double balance;
	private AgentStatus agSt;
	
	
	public ModelEvent(EventKind kind, double balance, AgentStatus agSt){
		this.balance = balance;
		this.kind = kind;
		this.agSt = agSt;
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
}