package models;

import java.rmi.server.Operation;
import java.util.Date;

public class Trade {

	public enum Indicator {
		BUY, SELL
	}

	private Date tradeTime;
	private Stock stock;
	private Double quantity;
	private Indicator indicator;
	private Double price;

	/**
	 * Constructor.
	 */
	public Trade(Stock stock, Double quantity, Indicator indicator, Double price) {
		Date currentTime = new Date();
		this.tradeTime = currentTime;
		this.quantity = quantity;
		this.indicator = indicator;
		this.stock = stock;
		this.price = price;
	}

	/*
	 * get and set functions
	 */
	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
