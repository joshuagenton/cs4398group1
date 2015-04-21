package atm.model;

public class ModelEvent {
	
	public enum EventKind {
		BalanceUpdate, AmountTransferredUpdate, AmountWithdrawUpdate, Login, Cancel, Wait,Start, SelectAccount, Verified, CheckBalance, invalidPIN
	}
	
	private EventKind kind;
	private AgentStatus agSt;
	
	
	public ModelEvent(EventKind kind, AgentStatus agSt){
		this.kind = kind;
		this.agSt = agSt;
	}
	
	public EventKind getKind(){
		return kind;
	}
}