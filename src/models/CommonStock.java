package models;

public class CommonStock extends Stock {

	public CommonStock(String symbol, Double lastDividend, Double parValue) {
		super(symbol, lastDividend, parValue);
	}

	public String toString(){
		return this.getSymbol()+"\tCommon\t"+this.getLastDividend()+"\t\t\t\t"+this.getParValue();
	}
}
