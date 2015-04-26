package test;


import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import atm.controller.DatabaseController;
import atm.controller.Results;

public class DatabaseControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetAccounts() throws SQLException, Exception {
		DatabaseController db = new DatabaseController();
		db.validate_user("6391480001052388", "1234");
		List<Results> results = db.getAccounts();
		for (Results r : results){
			assertNotNull(r);
		}
		assertEquals(4,results.size());
	}

	@Test
	public final void testGetPicture() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetPicture() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testValidate_user() throws SQLException, Exception {
		DatabaseController db = new DatabaseController();
		int test1= db.validate_user("6391480001052388", "1234");
		assertEquals(1, test1);
		test1= db.validate_user("6391480001052388", "0000");
		assertEquals(0, test1);
		test1= db.validate_user("63914800010523", "9876");
		assertEquals(0, test1);
	}

	@Test
	public final void testDeposit() throws SQLException, Exception {
		DatabaseController db = new DatabaseController();
		db.validate_user("6391480001052388", "1234");
		List<Results> results = db.getAccounts();
		for (Results r : results){
			double balance = db.deposit(r.getAccountNum(), 100);
			assertEquals(r.getBalance(), balance, 100);
		}
		
	}

	@Test
	public final void testWithdrawl() throws SQLException, Exception {
		DatabaseController db = new DatabaseController();
		db.validate_user("6391480001052388", "1234");
		List<Results> results = db.getAccounts();
		for (Results r : results){
			boolean balance = db.withdrawl(r.getAccountNum(), 100);
			assertEquals(true, balance);
		}
	}

	@Test
	public final void testTransfer() throws SQLException, Exception {
		DatabaseController db = new DatabaseController();
		db.validate_user("6391480001052388", "1234");
		List<Results> results = db.getAccounts();
		Object[] account = results.toArray();
		boolean test = db.transfer(((Results) account[0]).getAccountNum(), ((Results) account[1]).getAccountNum(), 100);
		assertEquals(true,test);
	}

	@Test
	public final void testTransaction_history() {
		//fail("Not yet implemented"); // TODO
	}

}
