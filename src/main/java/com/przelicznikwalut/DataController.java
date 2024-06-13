package com.przelicznikwalut;

import java.util.Scanner;

public class DataController {
    private DataModel model;
    private MenuJava view;
    private Scanner scanner;
    private DataValidation validate;

    public DataController(DataModel model, MenuJava view,Scanner scanner, DataValidation validate){
        this.model = model;
        this.view = view;
        this.scanner = scanner;
        this.validate = validate;
    }
    public void AppStart(){
        model.setCurrency(view.ChooseCurrency(this.scanner));
    }
    public void ChooseDateType(){
        model.setOption(view.ChooseDateType(this.scanner));
        view.Menu(this.scanner, model.getOption(),this.validate,model.getCurrency());
    }

}
