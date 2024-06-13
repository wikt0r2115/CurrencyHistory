package com.przelicznikwalut;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MenuJava {
    private static volatile MenuJava instance;

    public static MenuJava getInstance() {
        if (instance == null) {
            synchronized (MenuJava.class) {
                if (instance == null) {
                    instance = new MenuJava();
                }
            }
        }
        return instance;
    }

    public String ChooseCurrency(Scanner scanner) {
        // Wybór waluty
        System.out.println("Wybierz walutę (np. USD, EUR): ");
        return scanner.nextLine();
    }

    public Integer ChooseDateType(Scanner scanner) {
        // Wybór opcji wprowadzenia daty
        System.out.println("Wybierz opcję wprowadzenia daty:");
        System.out.println("1. Data z jednego dnia");
        System.out.println("2. Zakres dat");
        return scanner.nextInt();
    }

    public void Menu(Scanner scanner, int option, DataValidation validate, String currency) {
        String date = "";
        // Menu
        switch (option) {
            case 1:
                // Wprowadź datę z jednego dnia
                System.out.println("Podaj datę (YYYY-MM-DD): ");
                scanner.nextLine();
                date = scanner.nextLine();
                if (!validate.DateValidation(date)) {
                    System.out.println("Niepoprawny format daty");
                    System.exit(0);
                }

                // Pobierz dane z API
                NBPApiClient apiClient = new NBPApiClient();
                try {
                    String jsonResponse = apiClient.fetchData(date, currency);
                    // Przetwórz i wyświetl dane
                    DataProcessor dataProcessor = new DataProcessor();
                    ProcessedData processedData = dataProcessor.processData(jsonResponse);
                    System.out.println("Wynik:");
                    System.out.println(processedData + " PLN");
                    saveDataToCSV(scanner, processedData);
                } catch (IOException e) {
                    System.err.println("Wystąpił błąd podczas pobierania danych: " + e.getMessage());
                }
                break;

            case 2:
                // Wprowadź zakres dat
                System.out.println("Podaj początkową datę (YYYY-MM-DD): ");
                scanner.nextLine();
                String startDate = scanner.nextLine();
                if (!validate.DateValidation(startDate)) {
                    System.out.println("Niepoprawny format daty");
                    System.exit(0);
                }

                System.out.println("Podaj końcową datę (YYYY-MM-DD): ");
                String endDate = scanner.nextLine();
                if (!validate.DateValidation(endDate)) {
                    System.out.println("Niepoprawny format daty");
                    System.exit(0);
                }

                NBPApiClient apiClientRange = new NBPApiClient();
                try {
                    String jsonResponse = apiClientRange.fetchDataForDateRange(startDate, endDate, currency);
                    saveDataToJSON(jsonResponse);
                    System.out.println("Dane zapisane w formacie JSON.");
                } catch (IOException e) {
                    System.err.println("Wystąpił błąd podczas pobierania danych: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Nie ma takiej opcji w menu");
                System.exit(0);
        }
    }

    private void saveDataToCSV(Scanner scanner, ProcessedData processedData) {
        System.out.println("Czy chcesz zapisać dane do pliku csv?(T/N)");
        Character c = scanner.nextLine().charAt(0);
        if (c == 'T') {
            // Zapisz dane do pliku CSV
            CsvWriter csvWriter = new CsvWriter();
            csvWriter.writeDataToCsv(processedData, "output data" + "/data_" + System.currentTimeMillis() + ".csv");
        }
    }

    private void saveDataToJSON(String data) {
        try (FileWriter fileWriter = new FileWriter("output data" + "/data_" + System.currentTimeMillis() + ".json")) {
            fileWriter.write(data);
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas zapisywania danych do pliku JSON: " + e.getMessage());
        }
    }

    public void ClearMenu() throws IOException {
        Runtime.getRuntime().exec("cls");
    }
}
