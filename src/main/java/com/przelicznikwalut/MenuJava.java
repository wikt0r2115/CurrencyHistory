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
    public void ClearMenu() throws IOException {
        Runtime.getRuntime().exec("cls");
    }
}
