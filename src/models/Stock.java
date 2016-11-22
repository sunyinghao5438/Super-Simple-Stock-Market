package models;

public class Stock {

	private String symbol;
	private Double lastDividend;
	private Double parValue;

	/*
	 * constructor
	 */
	public Stock(String symbol, Double lastDividend, Double parValue) {
		this.symbol = symbol;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
	}

	/*
	 * get and set functions
	 */
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}
}
