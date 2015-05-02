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
	 * Testing the AgentModel constructor.
	 */
	@Test
	public void testAgentModel() {
		
		try {
			AgentModel agent2 = new AgentModel("078910", 10.0, 1, "0", true, "Blocked", account);
		} catch (Exception e) {
			fail("Exception " + e.getMessage());
		}
	}

	/**
	 * Testing the Run method in AgentModel.
	 */
	@Test
	public void testRun() {

		try {
			agent.run();
			fail("Should not be able to run this from here directly.");
		} catch (Exception e) {
			
		}
		
	}

	/** 
	 * Testing the AddAmount method in AgentModel.
	 */
	@Test
	public void testAddAmount() {

		try {
			agent.AddAmount();
			fail("Should not be able to run this from here directly.");
		} catch (Exception e) {
			
		}
	}

}
