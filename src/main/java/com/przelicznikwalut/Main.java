package com.przelicznikwalut;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Wybór waluty
        System.out.println("Wybierz walutę (np. USD, EUR): ");
        String currency = scanner.nextLine();

        // Wybór opcji wprowadzenia daty
        System.out.println("Wybierz opcję wprowadzenia daty:");
        System.out.println("1. Data z jednego dnia");
        System.out.println("2. Zakres dat");
        String option = scanner.nextLine();

        String date;
        if (option.equals("1")) {
            // Wprowadź datę z jednego dnia
            System.out.println("Podaj datę (YYYY-MM-DD): ");
            date = scanner.nextLine();
        } else {
            // Wprowadź zakres dat
            System.out.println("Podaj początkową datę (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();
            System.out.println("Podaj końcową datę (YYYY-MM-DD): ");
            String endDate = scanner.nextLine();
            date = startDate + "/" + endDate;
        }

        // Pobierz dane z API
        NBPApiClient apiClient = new NBPApiClient();
        String jsonResponse = apiClient.fetchData(date, currency);

        // Przetwórz i wyświetl dane
        DataProcessor dataProcessor = new DataProcessor();
        ProcessedData processedData = dataProcessor.processData(jsonResponse);
        System.out.println("Wynik:");
        System.out.println(processedData);

        // Zapisz dane do pliku CSV
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.writeDataToCsv(processedData, "data_" + System.currentTimeMillis() + ".csv");

        scanner.close();
    }
}
