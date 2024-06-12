package com.przelicznikwalut;

import java.util.Date;

public class DataModel {
    private Date Date;
    private Date Startdate;
    private Date Enddate;
    private String Currency;

    public Date getDate() {
        return Date;
    }
    public void setDate(Date date){
        this.Date = date;
    }

    public Date getStartdate() {
        return Startdate;
    }
    public void setStartdate(Date startdate){
        this.Startdate = startdate;
    }

    public Date getEnddate() {
        return Enddate;
    }
    public void setEnddate(Date enddate){
        this.Enddate = enddate;
    }

    public String getCurrency(){
        return Currency;
    }
    public void setCurrency(String currency){
        this.Currency = currency;
    }
}
