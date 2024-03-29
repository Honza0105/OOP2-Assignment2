package util;

import java.math.BigDecimal;

/**
 * Class describing a Till containing all the Money of zoo, in this case.
 */
public class Till {
	private BigDecimal funds;
	private static Till instance;

	private Till(BigDecimal initialFunds) {
		this.funds = initialFunds;
	}

	public static Till getInstance(BigDecimal initialFunds){
		if(instance == null) {
			instance = new Till(initialFunds);
		}
		return instance;
	}
	public static Till getInstance(){
		BigDecimal initialFunds = BigDecimal.ZERO;
		if(instance == null) {
			instance = new Till(initialFunds);
		}
		return instance;
	}

	
	public boolean withdraw(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) < 0 ) {
			throw new IllegalArgumentException("You cannot withdraw a negative amount from the till.");
		}
		if (funds.compareTo(amount) < 0) {
			return false;
		}
		else {
			funds = funds.subtract(amount);
			return true;
		}
	}
	
	public void deposit(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) < 0 ) {
			throw new IllegalArgumentException("You cannot deposit a negative amount in the till.");
		}
		funds = funds.add(amount);
		System.out.println(amount + "€ has been been deposited!");
	}
	
	public BigDecimal getFunds() {
		return funds;
	}
}
