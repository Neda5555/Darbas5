package com.example.darbas5;

public class Currency {
    private String currencyCode;
    private String exchangeRate;

    public Currency(String currencyCode, String exchangeRate) {
        this.currencyCode = currencyCode;
        this.exchangeRate = exchangeRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    // Additional setter methods if needed
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}