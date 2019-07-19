package com.eron.android.expenseapp.Model;

public class TransModel {

    private String category;
    private String type;
    private String date;
    private String note;
    private String amount;

    public TransModel() {
    }

    public TransModel(String category, String type, String date, String note, String amount) {
        this.category = category;
        this.type = type;
        this.date = date;
        this.note = note;
        this.amount = amount;
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
