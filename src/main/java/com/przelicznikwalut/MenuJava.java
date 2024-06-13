package com.przelicznikwalut;

import java.io.IOException;
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

    public String ChooseCurrency(Scanner scanner){
        // Wybór waluty
        System.out.println("Wybierz walutę (np. USD, EUR): ");
        return scanner.nextLine();
    }
    public Integer ChooseDateType(Scanner scanner){
        // Wybór opcji wprowadzenia daty
        System.out.println("Wybierz opcję wprowadzenia daty:");
        System.out.println("1. Data z jednego dnia");
        System.out.println("2. Zakres dat");
        return scanner.nextInt();
    }
    public void Menu(Scanner scanner,int option,DataValidation validate, String currency){
        String date = "";
        //Menu
        switch(option) {
            case 1:
                // Wprowadź datę z jednego dnia
                System.out.println("Podaj datę (YYYY-MM-DD): ");
                scanner.nextLine();
                date = scanner.nextLine();
                if(validate.DateValidation(date) == Boolean.FALSE){
                    System.out.println("Nie poprawny format daty");
                    System.exit(0);
                }
                break;
            case 2:
                // Wprowadź zakres dat
                System.out.println("Podaj początkową datę (YYYY-MM-DD): ");
                scanner.nextLine();
                String startDate = scanner.nextLine();

                if(validate.DateValidation(startDate) == Boolean.FALSE){
                    System.out.println("Nie poprawny format daty");
                    System.exit(0);
                }

                System.out.println("Podaj końcową datę (YYYY-MM-DD): ");
                scanner.nextLine();
                String endDate = scanner.nextLine();

                if(validate.DateValidation(endDate) == Boolean.FALSE){
                    System.out.println("Nie poprawny format daty");
                    System.exit(0);
                }

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
        System.out.println(processedData + " PLN");
        System.out.println("Czy chcesz zapisać dane do pliku csv?(T/N)");
        Character c = scanner.nextLine().charAt(0);
        if(c == 'T') {
            // Zapisz dane do pliku CSV
            CsvWriter csvWriter = new CsvWriter();
            csvWriter.writeDataToCsv(processedData, "data_" + System.currentTimeMillis() + ".csv");
        }else
            System.exit(0);
    }
    public void ClearMenu() throws IOException {
        Runtime.getRuntime().exec("cls");
    }
}
