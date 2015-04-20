package test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import atm.controller.CardReaderController;
import atm.model.CardReaderModel;

public class CardReaderModelTest {

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
	public final void testReadCont() {
		CardReaderModel card = new CardReaderModel();
		
		String input = "%B6391480100325586  ^WATERS/PHILIP T           ^4912120?;6391480100325586=4912120?+202=034903800=00?";
		try {
			card.readcont(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String Ccn = card.getCcn();
		String ExpDate = card.getExpDateString();
		String Name = card.getCard_holder();
		
		assertEquals("6391480100325586", Ccn);
		assertEquals("WATERS/PHILIP T", Name);
		assertEquals("12/49", ExpDate);
	}

}
