package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import models.CommonStock;
import models.PreferredStock;
import models.Stock;
import models.Trade;

public class StockController {

	HashMap<String, Stock> stocks;
	private static List<Trade> tradeRecords;

	/*
	 * constructor
	 */
	public StockController() {
		stocks = new HashMap<String, Stock>();
		tradeRecords = new ArrayList<Trade>();
	}

	/*
	 * add stock
	 */
	public void addStock(Stock stock) {
		stocks.put(stock.getSymbol(), stock);
	}

	/*
	 * delete stock
	 */
	public void deleteStock(Stock stock) {
		stocks.remove(stock.getSymbol());
	}

	/*
	 * get stock by name
	 */
	public Stock getStockByStockName(String name) {
		Stock stock = stocks.get(name);
		return stock;
	}

	/*
	 * add trade buy or sell
	 */
	public void addTrade(Trade trade) {
		tradeRecords.add(trade);
	}

	/*
	 * calculate Dividend Yield
	 */
	public static Double calDividendYieldByStock(Stock stock, Double price) {
		Double result = Double.MIN_VALUE;
		if (stock instanceof CommonStock) {
			stock = (CommonStock) stock;
			result = stock.getLastDividend() / price;
		} else if (stock instanceof PreferredStock) {
			stock = (PreferredStock) stock;
			result = ((PreferredStock) stock).getFixeddividend() * stock.getParValue() / price;
		}
		return result;
	}

	/*
	 * calculate P/E Ratio By Stock
	 */
	public static Double calPERatioByStock(Stock stock, Double price) {
		Double result = Double.MIN_VALUE;
		result = price / stock.getLastDividend();
		return result;
	}

	/*
	 * calculate Volum Weighted By Stock
	 */
	public static Double calVolumeWeightedByStock(Stock stock) {
		Double result = Double.MIN_VALUE;
		Double numerator = 0.0;
		Double denominator = 0.0;
		List<Trade> records = getTradeRecordsByTime(stock, 15);
		for (Trade trade : records) {
			numerator += trade.getPrice() * trade.getQuantity();
			denominator += trade.getQuantity();
		}
		result = numerator / denominator;
		return result;
	}

	/*
	 * calculate GBCE
	 */
	public Double calGBCE() {
		Double result = 1.0;
		for (Stock stock : stocks.values()) {
			result *= calVolumeWeightedByStock(stock);
		}
		result = Math.pow(result, 1.0 / stocks.size());
		return result;
	}

	/*
	 * get trade records in last 15 mins
	 */
	private static List<Trade> getTradeRecordsByTime(Stock stock, int mins) {
		List<Trade> records = new ArrayList<Trade>();
		Date currentTime = new Date();
		for (Trade trade : tradeRecords) {
			if (currentTime.getTime() - trade.getTradeTime().getTime() <= 15 * 60 * 1000) {
				records.add(trade);
			}
		}
		return records;
	}

	/*
	 * get stock list
	 */
	public List<Stock> getStockList() {
		List<Stock> stockList = new ArrayList<Stock>();
		for (Stock stock : stocks.values()) {
			stockList.add(stock);
		}
		return stockList;
	}
}
