package lab5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {
	
	private Random random = new Random();
	
	private Map<String, Integer> rates = new HashMap<String, Integer>();

	public Bank() {
		rates.put("RUB", 1);
		rates.put("USD", 60);
		rates.put("EUR", 70);
	}
	
	public int rand(int min, int max) {
	    int randomNum = random.nextInt((max - min) + 1) + min;

	    return randomNum;
	}	
	
	public double convert(int amount, String currIn, String currOut) throws Exception {
		
		if (rates.containsKey(currIn) && rates.containsKey(currOut)) {
			double rateIn = rates.get(currIn);
			double rateOut = rates.get(currOut);

			rateIn = (rateIn / 100) * (rand(-20, 20) + 100);

			
			double resultRate = rateIn / rateOut;
			
			return resultRate * amount;
		} else {
			throw new Exception();
		}
	}
}
