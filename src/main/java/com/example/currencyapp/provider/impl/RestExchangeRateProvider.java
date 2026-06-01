package com.example.currencyapp.provider.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.currencyapp.provider.ExchangeRateProvider;

public class RestExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public double getRate(String from, String to) {

        try {
            String urlStr =
                    "https://open.er-api.com/v6/latest/" + from;

            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HTTP Error Code: " + responseCode);
            }

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            String json = response.toString();

            String search = "\"" + to.toUpperCase() + "\":";

            int index = json.indexOf(search);

            if (index == -1) {
                throw new RuntimeException("Currency not found in response");
            }

            String sub = json.substring(index + search.length());
            String number = sub.split(",")[0].replace("}", "").trim();

            return Double.parseDouble(number);

        } catch (Exception e) {
            throw new RuntimeException("REST call failed: " + e.getMessage(), e);
        }
    }
}