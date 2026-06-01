package com.example.currencyapp;

import java.util.Scanner;

import com.example.currencyapp.model.ConversionResult;
import com.example.currencyapp.provider.ExchangeRateProvider;
import com.example.currencyapp.provider.impl.MockExchangeRateProvider;
import com.example.currencyapp.provider.impl.RestExchangeRateProvider;
import com.example.currencyapp.provider.impl.SoapExchangeRateProvider;
import com.example.currencyapp.service.CurrencyService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose Provider:");
        System.out.println("1. Mock");
        System.out.println("2. SOAP");
        System.out.println("3. REST");
        System.out.print("Your choice: ");

        int providerChoice = scanner.nextInt();
        scanner.nextLine();

        ExchangeRateProvider provider;

        switch (providerChoice) {
            case 1:
                provider = new MockExchangeRateProvider();
                break;

            case 2:
                provider = new SoapExchangeRateProvider();
                break;

            case 3:
                provider = new RestExchangeRateProvider();
                break;

            default:
                System.out.println("Invalid choice, defaulting to Mock");
                provider = new MockExchangeRateProvider();
        }

        CurrencyService service = new CurrencyService(provider);

        while (true) {

            System.out.println("=== Currency App ===");
            System.out.println("1. Convert Currency");
            System.out.println("2. View History");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            }

            if (choice == 2) {
                System.out.println("\n--- History ---");

                if (service.getHistory().isEmpty()) {
                    System.out.println("No conversions yet.");
                } else {
                    for (ConversionResult r : service.getHistory()) {
                        System.out.println(
                                r.getFromCurrency() + " -> "
                                + r.getToCurrency() + " | "
                                + r.getAmount() + " = "
                                + r.getConvertedAmount()
                                + " | " + r.getProvider()
                        );
                    }
                }
                continue;
            }

            if (choice != 1) {
                System.out.println("Invalid option");
                continue;
            }

            System.out.print("From: ");
            String from = scanner.nextLine().trim().toUpperCase();

            System.out.print("To: ");
            String to = scanner.nextLine().trim().toUpperCase();

            System.out.print("Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            try {
                ConversionResult result
                        = service.convert(from, to, amount);

                System.out.println("\n--- Result ---");
                System.out.println("From Currency: " + result.getFromCurrency());
                System.out.println("To Currency: " + result.getToCurrency());
                System.out.println("Amount: " + result.getAmount());
                System.out.println("Exchange Rate: " + result.getExchangeRate());
                System.out.println("Converted Amount: " + result.getConvertedAmount());
                System.out.println("Provider: " + result.getProvider());
                System.out.println("Timestamp: " + result.getTimestamp());

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error occurred");
                // e.printStackTrace();
            }
        }
    }
}
