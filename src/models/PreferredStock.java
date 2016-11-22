package models;

public class PreferredStock extends CommonStock {

	private Double fixeddividend;

	/*
	 * constructor
	 */
	public PreferredStock(String symbol, Double lastDividend, Double parValue) {
		super(symbol, lastDividend, parValue);
	}

	public PreferredStock(String symbol, Double lastDividend, Double fixeddividend, Double parValue) {
		super(symbol, lastDividend, parValue);
		this.fixeddividend = fixeddividend;
	}

	public Double getFixeddividend() {
		return fixeddividend;
	}

	public void setFixeddividend(Double fixeddividend) {
		this.fixeddividend = fixeddividend;
	}

	public String toString() {
		return this.getSymbol() + "\tCommon\t" + this.getLastDividend() + "\t\t" + this.getFixeddividend() + "\t\t"
				+ this.getParValue();
	}
}
