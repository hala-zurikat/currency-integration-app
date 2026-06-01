package com.example.currencyapp.provider;


    public interface ExchangeRateProvider {
        double getRate(String from,String to);
    }

