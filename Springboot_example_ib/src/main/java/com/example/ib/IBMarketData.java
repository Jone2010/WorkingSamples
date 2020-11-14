package com.example.ib;

public class IBMarketData {
	
	private int tickerId;
	private String tickerType;
	private double price;
	private String tickAttribute;

	public IBMarketData(int tickerId, String tickerType, double price, String tickAttribute) {
		this.tickerId = tickerId;
		this.tickerType = tickerType;
		this.price=price;
		this.tickAttribute = tickAttribute;
	}

	public int getTickerId() {
		return tickerId;
	}

	public void setTickerId(int tickerId) {
		this.tickerId = tickerId;
	}

	public String getTickerType() {
		return tickerType;
	}

	public void setTickerType(String tickerType) {
		this.tickerType = tickerType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTickAttribute() {
		return tickAttribute;
	}

	public void setTickAttribute(String tickAttribute) {
		this.tickAttribute = tickAttribute;
	}

}
