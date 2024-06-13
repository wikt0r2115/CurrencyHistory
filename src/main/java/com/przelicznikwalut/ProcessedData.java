package com.przelicznikwalut;

public class ProcessedData {
    private String code;
    private double rate;

    public ProcessedData(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return  "Kod waluty: " + code + ", Licznik: " + rate;
    }
}
