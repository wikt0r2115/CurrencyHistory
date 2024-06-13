package com.przelicznikwalut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NBPApiClient {
    private static final String API_URL = "https://api.nbp.pl/api/exchangerates/rates/A/";

    public String fetchData(String date, String currency) throws IOException {
        String urlString = API_URL + currency + "/" + date;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();

            return content.toString();
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            throw new IOException("Error 404: Not Found");
        } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
            throw new IOException("Error 400: Bad Request");
        } else {
            throw new IOException("Unexpected response code: " + responseCode);
        }
    }

    public String fetchDataForDateRange(String startDate, String endDate, String currency) throws IOException {
        String urlString = API_URL + currency + "/" + startDate + "/" + endDate;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();

            return content.toString();
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            throw new IOException("Error 404: Not Found");
        } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
            throw new IOException("Error 400: Bad Request");
        } else {
            throw new IOException("Unexpected response code: " + responseCode);
        }
    }
}
