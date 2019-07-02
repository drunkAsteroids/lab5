package lab5;

public class MoneyPrinter {
	public MoneyPrinter() {
		
	}
	
	public void print(String operation, String currency, int amount) {
		System.out.println(operation + " (" + amount + " " + currency + ")");
	}
}
