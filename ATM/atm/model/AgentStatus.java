package atm.model;

public enum AgentStatus {
	Running, Blocked, Paused, NeedPIN, SelectFromAccount, SelectToAccount, Verified, VerifyFailed, Finished, NA, Cancel, Wait, Start, Transfer, 
	CheckBalance, InvalidPIN, Withdraw, WithdrawComplete, InsufFunds
}
