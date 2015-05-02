package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import simpleAccount.model.Account;
import simpleAccount.model.AgentModel;


public class Test_AccountModel {
	
	private AgentModel agent = null;
	private Account account = null;
	
	/**
	 * Setup Account and AgentModel before running tests.
	 */
	@Before
	public void setupEach() {
		try {
			agent = new AgentModel("078910", 10.0, 1, "0", true, "Running", account);
			account = new Account();
		
		} catch (Exception e) {
			fail("Didn't set up the @Before.");
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	/**
	 * Testing the AddAgent method in Account.
	 */
	@Test
	public void testAddAgent() {
		try {
			AgentModel agent2 = new AgentModel("078910", 10.0, 1, "0", true, "Blocked", account);
			
			account.AddAgent(agent2);
			
			
		} catch (Exception e) {
			fail("Exception " + e.getMessage());
		}
	}

	/**
	 * Testing the RemoveAgent method in Account.
	 */
	@Test
	public void testRemoveAgent() {
		
		agent.setAgentID("1");
		
		try{
			account.RemoveAgent(agent);
		} catch (Exception e) {
			fail("Exception " + e.getMessage());
		}
	}

	/**
	 * Testing the SetAgent method in Account.
	 */
	@Test
	public void testSetAgent() {
		
		agent.setAgentID("1");
		
		try {
			account.SetAgent("078910", 10.0, 1, "1", true, "Running");
		} catch (Exception e) {
			fail("Exception " + e.getMessage());
		}
	}
	
	/**
	 * Testing the Amount method in Account.
	 */
	@Test
	public void testAmount() {
		try {
			agent.setAmount(0);
			
		} catch (Exception e) {
			fail("Valid, should have been successful.");
		}
		try {
			agent.setAmount(0.5);
			
		} catch (Exception e) {
			fail("Valid, should have been successful.");
		}
		try {
			agent.setAmount(-10);
			
		} catch (Exception e) {
			fail("Valid, should have been successful.");
		}	
		try {
			agent.setAmount(123456);
		} catch (Exception e) {
			fail("Valid, should have been successful.");
		}	
	}
	
	@Test
	public void testAddAmount() {
		try {
			account.AddAmount(agent);
			fail("This should fail.");
		} catch (Exception e) {
			//fail("Valid, should have been successful.");
		}
	}

}
