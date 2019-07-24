package com.eron.android.expenseapp.Model;

import android.print.PrinterId;

public class TransModel {
    private int id;
    private String category;
    private String type;
    private String date;
    private String note;
    private String amount;
    private String account;
    private String day_of_month;
    private String month;

    public TransModel() {
    }


    public TransModel(int id, String category, String type, String date, String note, String amount, String account, String day_of_month, String month) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.date = date;
        this.note = note;
        this.amount = amount;
        this.account = account;
        this.day_of_month = day_of_month;
        this.month = month;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
