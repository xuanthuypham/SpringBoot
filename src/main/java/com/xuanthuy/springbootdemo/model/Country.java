package com.xuanthuy.springbootdemo.model;

public class Country {
	private int countruyCode;
	private String countryName;
	
	public Country(int countruyCode, String countryName) {
		this.countruyCode = countruyCode;
		this.countryName = countryName;
	}
	public int getCountruyCode() {
		return countruyCode;
	}
	public void setCountruyCode(int countruyCode) {
		this.countruyCode = countruyCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}
