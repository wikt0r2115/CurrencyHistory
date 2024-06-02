package com.przelicznikwalut;

import java.util.Scanner;

public class UserInput {
    private Scanner scanner = new Scanner(System.in);

    public String getDateFromUser() {
        System.out.println("Podaj datę (YYYY-MM-DD): ");
        return scanner.nextLine();
    }

    public String getCurrencyFromUser() {
        System.out.println("Podaj kod waluty (np. USD, EUR): ");
        return scanner.nextLine();
    }
}
