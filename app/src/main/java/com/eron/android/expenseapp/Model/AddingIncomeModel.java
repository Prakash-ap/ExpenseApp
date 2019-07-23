package com.eron.android.expenseapp.Model;

public class AddingIncomeModel {
    private String date;
    private String incategory;
    private String inaccount;
    private String amount;
    private String note;

    public AddingIncomeModel(String date, String incategory, String inaccount, String amount, String note) {
        this.date = date;
        this.incategory = incategory;
        this.inaccount = inaccount;
        this.amount = amount;
        this.note = note;
    }

    public AddingIncomeModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIncategory() {
        return incategory;
    }

    public void setIncategory(String incategory) {
        this.incategory = incategory;
    }

    public String getInaccount() {
        return inaccount;
    }

    public void setInaccount(String inaccount) {
        this.inaccount = inaccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
