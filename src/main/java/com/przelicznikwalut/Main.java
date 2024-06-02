package com.przelicznikwalut;

import java.util.Scanner;
import com.przelicznikwalut.DataValidation;

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
        int option = scanner.nextInt();
        scanner.nextLine(); // Konsumuj nową linię

        DataValidation validate = new DataValidation();

        String date = "";
        //Menu
        switch(option) {
            case 1:
                // Wprowadź datę z jednego dnia
                System.out.println("Podaj datę (YYYY-MM-DD): ");
                date = scanner.nextLine();
                System.out.println(validate.DateValidation(date));
                break;
            case 2:
                // Wprowadź zakres dat
                System.out.println("Podaj początkową datę (YYYY-MM-DD): ");
                String startDate = scanner.nextLine();
                System.out.println("Podaj końcową datę (YYYY-MM-DD): ");
                String endDate = scanner.nextLine();
                date = startDate + "/" + endDate;
                break;
            default:
                System.out.println("Nie ma takiej opcji w menu");
                System.exit(0);
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
