package simpleAccount.model;


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
    
    public void AddAmount() {
    	if (accountModel.AddAmount(this)) {
    		transferred = transferred + amount;
    	}
    		
    }
}