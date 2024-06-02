package com.przelicznikwalut;

public class ProcessedData {
    private String currency;
    private String code;
    private double rate;

    public ProcessedData(String currency, String code, double rate) {
        this.currency = currency;
        this.code = code;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Currency: " + currency + ", Code: " + code + ", Rate: " + rate;
    }
}
