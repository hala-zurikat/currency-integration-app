package com.example.currencyapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.currencyapp.model.ConversionResult;
import com.example.currencyapp.provider.ExchangeRateProvider;
import com.example.currencyapp.util.Logger;

public class CurrencyService {

    private ExchangeRateProvider provider;

    private static final Set<String> allowedCurrencies
            = Set.of("USD", "JOD", "EUR", "GBP");

    private List<ConversionResult> history = new ArrayList<>();

    public CurrencyService(ExchangeRateProvider provider) {
        this.provider = provider;
    }

    public void validate(String from, String to, double amount) {

        if (from == null || from.trim().isEmpty()
                || to == null || to.trim().isEmpty()) {
            Logger.error("Validation failed: empty currency");
            throw new IllegalArgumentException("Currency cannot be empty");
        }
        if (from.equalsIgnoreCase(to)) {
            Logger.error("Validation failed: same currency");
            throw new IllegalArgumentException("From and To currencies cannot be the same");
        }
        if (!allowedCurrencies.contains(from) || !allowedCurrencies.contains(to)) {
            Logger.error("Validation failed: unsupported currency");
            throw new IllegalArgumentException("Unsupported currency");
        }

        if (amount <= 0) {
            Logger.error("Validation failed: invalid amount");

            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }

    private double getRateWithRetry(String from, String to) {

        int attempts = 3;

        for (int i = 1; i <= attempts; i++) {
            try {
                return provider.getRate(from, to);
            } catch (Exception e) {
                System.out.println("Attempt " + i + " failed");
            }
        }

        throw new RuntimeException("All attempts failed to fetch exchange rate");
    }

    public ConversionResult convert(String from, String to, double amount) {

        validate(from, to, amount);

        Logger.info("Starting conversion: " + from + " -> " + to + " | amount = " + amount);

        Logger.info("Using provider: " + provider.getClass().getSimpleName());

        double rate = getRateWithRetry(from, to);

        Logger.info("Received rate: " + rate);

        double convertedAmount = amount * rate;

        ConversionResult result = new ConversionResult(
                from,
                to,
                amount,
                rate,
                convertedAmount,
                provider.getClass().getSimpleName(),
                LocalDateTime.now().toString()
        );
        Logger.info("Conversion successful. Converted = " + convertedAmount);

        history.add(result);

        Logger.info("Result stored in history. Total records = " + history.size());

        return result;
    }

    public List<ConversionResult> getHistory() {
        return history;
    }
}
