package atm.model;


public interface Agent extends Model {
	public double getTransferred();
	public String getName();
	public AccountModel getAccount();
	public AgentStatus getStatus();
	
	public void onPause();
	public void onResume();
	public void setName(String name);
	public void setStatus(AgentStatus agSt);
	public void finish();
}