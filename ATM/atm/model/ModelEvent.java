package atm.model;

/**
 * This is the ModelEvent class which classifies the type of
 * event that is occurring.  
 * 
 * @author Stacie.Christensen
 * @since 2015-03-05
 */
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