package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import simpleAccount.model.Account;
import simpleAccount.model.AgentModel;


public class Test_AgentModel {
	
	private AgentModel agent = null;
	private Account account = null;
	
	@Before
	public void setupEach() {
		try {
			agent = new AgentModel("078910", 10.0, 1, "0", true, "Running", account);
			account = new Account();
			//agent.setAccountModel(account);
		
		} catch (Exception e) {
			fail("Didn't set up the @Before.");
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Test
	public void testAddAmount() {

		try {
			account.AddAmount(agent);
		} catch (Exception e) {
			fail("Exception " + e.getMessage());
		}
	}

	@Test
	public void testAddAgent() {
		try {
			account.AddAgent(agent);
		} catch (Exception e) {
			fail("Exception " + e.getMessage());
		}
	}

	@Test
	public void testRemoveAgent() {
		
		agent.setAgentID("1");
		
		try{
			account.RemoveAgent(agent);
		} catch (Exception e) {
			fail("Exception " + e.getMessage());
		}
	}

	@Test
	public void testSetAgent() {
		
		agent.setAgentID("1");
		
		try {
			account.SetAgent("078910", 10.0, 1, "1", true, "Running");
		} catch (Exception e) {
			fail("Exception " + e.getMessage());
		}
	}
	
	@Test
	public void testDuplicateAgentIDs() {
		try {
			agent.setAgentID("1");
			
			//Account account2 = new Account();
			AgentModel agent2 = new AgentModel("078910", 10.0, 1, "0", true, "Running", account);
			
			agent2.setAgentID("1");
			
			fail("Should have thrown exception");
		} catch (Exception e) {
			//fail("Exception " + e.getMessage());
		} 
	}
	
	@Test
	public void testAmount() {
		try {
			agent.setAmount(0);
			
		} catch (Exception e) {
			fail("Should have thrown exception setting to 0");
		}
		try {
			agent.setAmount(0.5);
			
		} catch (Exception e) {
			fail("Should have thrown exception setting to 0.5");
		}
		try {
			agent.setAmount(-10);
			
		} catch (Exception e) {
			fail("Should have thrown exception setting to -10");
		}	
		try {
			agent.setAmount(123456);
			fail("Should have thrown exception setting to A");
		} catch (Exception e) {
			
		}		
	}
}
