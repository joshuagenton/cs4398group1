package atm.model;


/**
 * These are the different agent statuses we are monitoring.  
 * 
 * @author Chris Wells
 * @since 2015-03-05
 */
public enum AgentStatus {
	Running, Blocked, Paused, NeedPIN, SelectFromAccount, SelectToAccount, Verified, VerifyFailed, Finished, NA, Cancel, Wait, Start, Transfer, 
	CheckBalance, InvalidPIN, Withdraw, WithdrawComplete, InsufFunds, DBCommError, CardReadError
}
