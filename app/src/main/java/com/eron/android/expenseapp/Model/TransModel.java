package com.eron.android.expenseapp.Model;

import android.print.PrinterId;

public class TransModel {
    private int id;
    private String category_name;
    private int category_img;
    private String type;
    private String date;
    private String note;
    private String amount;
    private String account_name;
    private int account_img;
    private String day_of_month;
    private String month;
    private int year;
    private  String selectedmonthyear;



    public TransModel() {
    }

    public TransModel(int id, String category_name, int category_img, String type, String date, String note, String amount, String account_name, int account_img, String day_of_month, String month, int year, String selectedmonthyear) {
        this.id = id;
        this.category_name = category_name;
        this.category_img = category_img;
        this.type = type;
        this.date = date;
        this.note = note;
        this.amount = amount;
        this.account_name = account_name;
        this.account_img = account_img;
        this.day_of_month = day_of_month;
        this.month = month;
        this.year = year;
        this.selectedmonthyear = selectedmonthyear;
    }

    public TransModel(int id, String category_name, int category_img, String type, String date, String note, String amount, String account_name, int account_img, String day_of_month, String month) {
        this.id = id;
        this.category_name = category_name;
        this.category_img = category_img;
        this.type = type;
        this.date = date;
        this.note = note;
        this.amount = amount;
        this.account_name = account_name;
        this.account_img = account_img;
        this.day_of_month = day_of_month;
        this.month = month;
    }

    public String getSelectedmonthyear() {
        return selectedmonthyear;
    }

    public void setSelectedmonthyear(String selectedmonthyear) {
        this.selectedmonthyear = selectedmonthyear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getCategory_img() {
        return category_img;
    }

    public void setCategory_img(int category_img) {
        this.category_img = category_img;
    }

    public String getDay_of_month() {
        return day_of_month;
    }

    public void setDay_of_month(String day_of_month) {
        this.day_of_month = day_of_month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public int getAccount_img() {
        return account_img;
    }

    public void setAccount_img(int account_img) {
        this.account_img = account_img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
