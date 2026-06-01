package com.example.currencyapp.provider.impl;

import java.math.BigInteger;

import com.example.currencyapp.provider.ExchangeRateProvider;
import com.example.currencyapp.soap.client.NumberConversion;
import com.example.currencyapp.soap.client.NumberConversionSoapType;

public class SoapExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public double getRate(String from, String to) {

        try {
            System.out.println("[SOAP] calling service...");

            NumberConversion service = new NumberConversion();
            NumberConversionSoapType soap = service.getNumberConversionSoap();

            BigInteger value = BigInteger.valueOf(123);

            String response = soap.numberToWords(value);

            System.out.println("[SOAP RESPONSE] " + response);

            // dummy mapping (لأن service مش currency)
            if (from.equals("USD") && to.equals("JOD")) return 0.709;
            if (from.equals("EUR") && to.equals("USD")) return 1.08;

            return response.length() * 0.01;

        } catch (Exception e) {
            throw new RuntimeException("SOAP failed", e);
        }
    }
}