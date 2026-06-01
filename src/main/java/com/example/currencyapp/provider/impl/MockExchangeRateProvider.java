package com.example.currencyapp.provider.impl;

import com.example.currencyapp.provider.ExchangeRateProvider;

public class MockExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public double getRate(String from, String to) {

        if (from.equals("USD") && to.equals("JOD")) {
            return 0.709;
        }

        if (from.equals("EUR") && to.equals("USD")) {
            return 1.09;
        }

        return 1.0;
    }
}