package views;

import java.util.Date;
import java.util.Scanner;

import controllers.StockController;
import models.CommonStock;
import models.PreferredStock;
import models.Stock;
import models.Trade;
import models.Trade.Indicator;

public class Solution {
	static Scanner in = new Scanner(System.in);
	static StockController stockController;

	public static void main(String[] args) {
		Stock tea = new CommonStock("TEA", 0.0, 100.0);
		Stock pop = new CommonStock("POP", 8.0, 100.0);
		Stock ale = new CommonStock("ALE", 23.0, 60.0);
		Stock gin = new PreferredStock("GIN", 8.0, 0.02, 100.0);
		Stock joe = new CommonStock("JOE", 13.0, 250.0);
		stockController = new StockController();
		stockController.addStock(tea);
		stockController.addStock(pop);
		stockController.addStock(ale);
		stockController.addStock(gin);
		stockController.addStock(joe);
		print();
		while (in.hasNextInt()) {
			int operation = in.nextInt();
			Trade trade;
			Double result;
			switch (operation) {
			case 1:
				result = calDividendYield();
				if (result == Double.MIN_VALUE) {
					System.out.println("Error!");
					break;
				} else {
					System.out.println("The dividend yield is: " + result);
					break;
				}
			case 2:
				result = calPERatio();
				if (calPERatio() == Double.MIN_VALUE) {
					System.out.println("Error!");
					break;
				} else {
					System.out.println("The P/E Ratio is: " + calPERatio());
					break;
				}
			case 3:
				trade = buyTrade();
				if (trade.getStock() == null) {
					System.out.println("Error!");
					break;
				} else {
					stockController.addTrade(trade);
					break;
				}
			case 4:
				trade = buyTrade();
				if (trade.getStock() == null) {
					System.out.println("Error!");
					break;
				} else {
					stockController.addTrade(trade);
					break;
				}
			case 5:
				result = calWeightedStockPrice();
				if (result == Double.MIN_VALUE) {
					System.out.println("Error!");
					break;
				} else {
					System.out.println("The Weighted Stock Price is: " + result);
					break;
				}
			case 6:
				System.out.println("GBCE is: " + stockController.calGBCE());
				break;
			default:
				System.out.println("Please input right numebr!!");
				break;
			}
			print();
		}
	}

	public static Double calDividendYield() {
		System.out.println("Input the stock name:");
		in.nextLine();
		String name = in.nextLine();
		System.out.println("Input the price (price cannot be 0):");
		Double price = in.nextDouble();
		Stock stock = stockController.getStockByStockName(name);
		return StockController.calDividendYieldByStock(stock, price);
	}

	public static Double calPERatio() {
		System.out.println("Input the stock name");
		in.nextLine();
		String name = in.nextLine();
		System.out.println("Input the price(price cannot be 0):");
		Double price = in.nextDouble();
		Stock stock = stockController.getStockByStockName(name);
		return StockController.calPERatioByStock(stock, price);
	}

	public static Trade buyTrade() {
		System.out.println("Input the stock name");
		in.nextLine();
		String name = in.nextLine();
		System.out.println("Input the price:");
		Double price = in.nextDouble();
		System.out.println("Input the quantity:");
		Double quantity = in.nextDouble();
		Stock stock = stockController.getStockByStockName(name);
		Trade trade = new Trade(stock, quantity, Indicator.BUY, price);
		return trade;
	}

	public static Trade sellTrade() {
		System.out.println("Input the stock name");
		in.nextLine();
		String name = in.nextLine();
		System.out.println("Input the price:");
		Double price = in.nextDouble();
		System.out.println("Input the quantity:");
		Double quantity = in.nextDouble();
		Stock stock = stockController.getStockByStockName(name);
		Trade trade = new Trade(stock, quantity, Indicator.SELL, price);
		return trade;
	}

	public static Double calWeightedStockPrice() {
		System.out.println("Input the stock name");
		in.nextLine();
		String name = in.nextLine();
		Stock stock = stockController.getStockByStockName(name);
		return StockController.calVolumeWeightedByStock(stock);
	}

	public static void print() {
		System.out.println("*************************");
		System.out.println("Super Simple Stock Market");
		System.out.println("*************************");
		System.out.println("Stocks");
		System.out.println("*************************");
		System.out.println("Stock\tType\tLast\t\tFixed\t\tPar");
		System.out.println("Symbol\t\tDividend\tDividend\tValue");
		for (Stock stock : stockController.getStockList()) {
			if (stock instanceof CommonStock) {
				stock = (CommonStock) stock;
				System.out.println(stock.toString());
			} else if (stock instanceof PreferredStock) {
				stock = (PreferredStock) stock;
				System.out.println(((PreferredStock) stock).toString());
			}
		}
		System.out.println("1. Calculate the dividend yield");
		System.out.println("2. Calculate the P/E Ratio");
		System.out.println("3. Buy Stocks");
		System.out.println("4. Sell Stocks");
		System.out.println("5. Get Volume Weighted Stock Price");
		System.out.println("6. Calculate GBCE");
		System.out.println("");
	}
}
