package simpleAccount.model;

/**
 * This is the Agent class.  This is how we create and setup
 * the agent.
 * 
 * @author Paul Bryson/Stacie Christensen
 * @since 2015-4-26
 */
class Agent implements Runnable {
	String accountID;
	String agentID;
	Double amount;
	Double ops;
	Double transferred = 0.0;
	String type;
	AccountModel accountModel;
	Boolean agentRunning = true;
	String agentStatus = "Stopped";
	//Accounts accounts;

	/**
	 * Agent constructor.
	 * 
	 * @param accountID the accountID that we are interacting with
	 * @param amount the amount we are depositing/withdrawing
	 * @param ops the operation
	 * @param agentID the agentID
	 * @param accountModel the accountModel for the account we are interacting with
	 */
	public Agent (String accountID, double amount, double ops, String agentID, AccountModel accountModel) {
        this.accountID = accountID;
        this.amount = amount;
        this.ops = ops;
        //this.accounts = accounts;
        this.agentID = agentID;
        this.accountModel = accountModel;
    }

    public void run() {
    	while(true) {
	    	while(agentRunning) {
	    		AddAmount();
	    		try {
					Thread.sleep((long) (1/ops*1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}
    }
    
    /**
     * AddAmount() adds the amount to the transferred balance.
     */
    public void AddAmount() {
    	if (accountModel.AddAmount(this)) {
    		transferred = transferred + amount;
    	}
    		
    }
}