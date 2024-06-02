package com.przelicznikwalut;

public class DataProcessor {
    public ProcessedData processData(String jsonResponse) {
        String currency = extractValue(jsonResponse, "currency");
        String code = extractValue(jsonResponse, "code");
        double rate = Double.parseDouble(extractValueFromRates(jsonResponse, "mid"));

        return new ProcessedData(currency, code, rate);
    }

    private String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\":\"";
        int startIndex = json.indexOf(searchKey) + searchKey.length();
        int endIndex = json.indexOf("\"", startIndex);
        return json.substring(startIndex, endIndex);
    }

    private String extractValueFromRates(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int startIndex = json.indexOf(searchKey) + searchKey.length();
        int endIndex = json.indexOf("}", startIndex);
        return json.substring(startIndex, endIndex).replace("]", "").trim();
    }
}
