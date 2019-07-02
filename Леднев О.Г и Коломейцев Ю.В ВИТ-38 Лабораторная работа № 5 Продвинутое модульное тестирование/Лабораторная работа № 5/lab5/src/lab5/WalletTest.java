package lab5;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


class BankStub extends Bank {
	private Map<String, Integer> rates = new HashMap<String, Integer>();

	public BankStub() {
		rates.put("RUB", 1);
		rates.put("USD", 60);
		rates.put("EUR", 70);
	}
	
	public double convert(int amount, String currIn, String currOut) throws Exception {
		
		if (rates.containsKey(currIn) && rates.containsKey(currOut)) {
			double rateIn = rates.get(currIn);
			double rateOut = rates.get(currOut);
			
			double resultRate = rateIn / rateOut;
			
			return resultRate * amount;
		} else {
			throw new Exception();
		}
	}
}

public class WalletTest{
	
	private Wallet wallet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		wallet = new Wallet(new BankStub());
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAddMoney() {
		wallet.addMoney("RUB", 300);
		assertEquals(wallet.getMoney("RUB"), 300);
		wallet.addMoney("RUB", 300);
		assertEquals(wallet.getMoney("RUB"), 600);
	}
	
	@Test
	public void testRemoveMoney() throws Exception {
		wallet.addMoney("RUB", 100);
		wallet.removeMoney("RUB", 20);
		assertEquals(wallet.getMoney("RUB"), 80);
	}
	
	@Test(expected=Exception.class)
	public void testRemoveMoneyException() throws Exception {
		wallet.addMoney("RUB", 20);
		wallet.removeMoney("RUB", 100);
	}
	
	@Test
	public void testSize() throws Exception {
		assertEquals(0, wallet.size());
		wallet.addMoney("RUB", 100);
		assertEquals(1, wallet.size());
		wallet.removeMoney("RUB", 100);
		assertEquals(0, wallet.size());
	}
	
	@Test
	public void testToString() {
		assertEquals("{}", wallet.toString());
		
		wallet.addMoney("GBP", 500);
		wallet.addMoney("RUB", 300);
		wallet.addMoney("USD", 100);
		
 
		assertEquals("{ 500 GBP, 100 USD, 300 RUB }", wallet.toString());
	}
	
	
	@Test
	public void testGetTotalMoney() throws Exception {
		wallet.addMoney("RUB", 300);
		wallet.addMoney("USD", 100);
		
		assertEquals((int)6300, (int)wallet.getTotalMoney("RUB"));
	}
	
	
	@Test
	public void test() {
		
		//fail("Not yet implemented"); // TODO
	}

}
