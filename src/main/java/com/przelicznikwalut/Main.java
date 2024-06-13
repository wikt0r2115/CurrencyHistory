package com.przelicznikwalut;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuJava menu = new MenuJava();
        DataModel model = new DataModel();
        DataValidation validate = new DataValidation();
        DataController controller = new DataController(model,menu,scanner,validate);
        controller.AppStart();
        controller.ChooseDateType();







        scanner.close();
    }
}
