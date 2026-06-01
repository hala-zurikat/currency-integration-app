# 💱 Currency Integration App

A Java-based mini integration system that demonstrates how different external services (Mock, REST, SOAP) can be integrated using a clean and extensible architecture.

---

## 📌 Project Overview

This project simulates a currency conversion system where exchange rates are retrieved from multiple providers:

- Mock Provider (hardcoded values)
- REST API Provider (real HTTP API integration)
- SOAP Service Provider (generated SOAP client)

The system allows switching between providers dynamically at runtime using Dependency Injection.

---

## 🏗️ Architecture

The project is designed using OOP principles:

- Interface-based design (`ExchangeRateProvider`)
- Dependency Injection via constructor
- Separation of concerns (Service vs Provider vs Model)
- Encapsulation of business logic inside `CurrencyService`

---

## ⚙️ Technologies Used

- Java
- Maven
- REST API (HttpURLConnection)
- SOAP Web Service (Generated Client)
- OOP Principles
- Exception Handling
- Logging (custom logger)

---

## 🔄 Features

- Currency conversion between supported currencies
- Multiple exchange rate providers (Mock / REST / SOAP)
- Retry mechanism for external API calls
- Input validation
- Conversion history tracking
- Runtime provider switching

---

## 📂 Project Structure


src/
├── model
├── provider
│ ├── impl
├── service
├── util
└── Main.java


---

## ▶️ How to Run

1. Clone the repository
```bash
git clone https://github.com/hala-zurikat/currency-integration-app.git
Open in any Java IDE (IntelliJ / VS Code)

Run:
Main.java

📊 Key Concepts Demonstrated
Dependency Injection
Polymorphism via interfaces
External system integration (REST & SOAP)
Retry pattern for fault tolerance
Clean architecture separation

👩‍💻 Author:
Hala Zurikat
