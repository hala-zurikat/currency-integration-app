package com.example.currencyapp.model;

public class ConversionResult {
    
    private String fromCurrency;
    private String toCurrency;

    private double amount;
    private double exchangeRate;
    private double convertedAmount;

    private String provider;

    private String timestamp;

    public ConversionResult(String fromCurrency,
                        String toCurrency,
                        double amount,
                        double exchangeRate,
                        double convertedAmount,
                        String provider,
                        String timestamp) {

    this.fromCurrency = fromCurrency;
    this.toCurrency = toCurrency;
    this.amount = amount;
    this.exchangeRate = exchangeRate;
    this.convertedAmount = convertedAmount;
    this.provider = provider;
    this.timestamp = timestamp;
}
public String getFromCurrency() {
    return fromCurrency;
}

public String getToCurrency() {
    return toCurrency;
}

public double getAmount() {
    return amount;
}

public double getExchangeRate() {
    return exchangeRate;
}

public double getConvertedAmount() {
    return convertedAmount;
}

public String getProvider() {
    return provider;
}

public String getTimestamp() {
    return timestamp;
}
}
