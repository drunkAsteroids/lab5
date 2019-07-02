package lab5;

import java.util.HashMap;
import java.util.Map;




public class Wallet {
	private Map<String, Integer> wallet = new HashMap<String, Integer>();
	private Bank bank;
	private MoneyPrinter printer = new MoneyPrinter();

	public Wallet(Bank bank) {
		this.bank = bank;
	}
	
	public int size() {
		return wallet.size();
	}
	
	public void addMoney(String curr, int amount) {
		wallet.put(curr, this.getMoney(curr) + amount);
		printer.print("Пополнение баланса", curr, amount);
	}
	
	public void removeMoney(String curr, int amount) throws Exception {
		if (wallet.containsKey(curr)) {
			int inWallet = this.getMoney(curr);
			if (inWallet >= amount) {
				printer.print("Снятие", curr, amount);
				inWallet = inWallet - amount;
				
				if (inWallet > 0) {
					wallet.put(curr, inWallet);
				} else {
					wallet.remove(curr);
				}
			} else {
				throw new Exception();
			}
		}
	}
	

	
	public int getMoney(String curr) {
		
		
		if (wallet.containsKey(curr)) {
			printer.print("Запрос баланса", curr, wallet.get(curr));
			return wallet.get(curr);
		} else {
			return 0;
		}
	}
	
	public double getTotalMoney(String curr) throws Exception {
		double totalAmount = 0;
		for (Map.Entry<String, Integer> entry: wallet.entrySet()) {
			totalAmount += bank.convert(entry.getValue(), entry.getKey(), curr);
		}		
		printer.print("Запрос баланса в валюте", curr, (int)totalAmount);
		return totalAmount;
	}
	
	
	public String toString() {
		String result = "{";
		
		for (Map.Entry<String, Integer> entry: wallet.entrySet()) {
			result += " " + String.valueOf(entry.getValue()) + " " + entry.getKey() + ",";
		}
		
		if (!result.equals("{")) {
			result = result.substring(0, result.length() - 1) + " ";
		}
		
		result += "}";
		
		return result;
	}
}
