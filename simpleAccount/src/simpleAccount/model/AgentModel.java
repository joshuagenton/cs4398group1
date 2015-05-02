package simpleAccount.model;

/**
 * This is the Agent class.  This is how we create and setup
 * the agent.
 * 
 * @author Paul Bryson/Stacie Christensen
 * @since 2015-4-26
 */
public class AgentModel implements Runnable {

	String accountID;
	String agentID;
	Double amount;
	Double ops;
	Double transferred = 0.0;
	String type;
	Account accountModel;
	Boolean agentRunning = true;
	String agentStatus;
	Integer opsCompleted = 0;


	/**
	 * Agent constructor.
	 * 
	 * @param accountID the accountID that we are interacting with
	 * @param amount the amount we are depositing/withdrawing
	 * @param ops the operation
	 * @param agentID the agentID
	 * @param accountModel the accountModel for the account we are interacting with
	 */
	public AgentModel (String accountID, double amount, double ops, String agentID, Boolean agentRunning, String agentStatus, Account accountModel) {
        this.accountID = accountID;
        this.amount = amount;
        this.ops = ops;
        this.agentID = agentID;
        this.accountModel = accountModel;
        this.agentStatus = agentStatus;
        this.agentRunning = agentRunning;
    }

    public void run() {
    	accountModel.AddAgent(this);
    	while(!agentStatus.equals("Dismiss")) {
	    	while(agentRunning && ops > 0.0) {
	    		AddAmount();
	    		try {
					Thread.sleep((long) (1/ops*1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
	    	
	    	//  For some reason if the thread doesn't do anything here, it dies
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
		accountModel.RemoveAgent(this);
    }
    
    /**
     * AddAmount() adds the amount to the transferred balance.
     */
    public void AddAmount() {
    	if (accountModel.AddAmount(this)) {
    		transferred += amount;
    		opsCompleted++;
    	}
    }
    
    public Double GetTransferred() {
    	return transferred;
    }
    
    public String GetAgentStatus() {
    	return agentStatus;
    }
    
    public Integer GetOpsCompleted () {
    	return opsCompleted;
    }
    
    public void setAgentID(String AgentID) {
    	this.agentID = AgentID;
    }
    
    public void setAmount(double amount) {
    	this.amount = amount;
    }
}