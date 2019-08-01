package com.eron.android.expenseapp.Model;

public class ExpenseItemData {

    String text;
    int imageId;
    String type;
    int id;

    public ExpenseItemData(String text, int imageId, String type, int id) {
        this.text = text;
        this.imageId = imageId;
        this.type = type;
        this.id = id;
    }

    public ExpenseItemData(String text, int imageId) {
        this.text = text;
        this.imageId = imageId;
    }

    public ExpenseItemData(String text, int imageId, String type) {
        this.text = text;
        this.imageId = imageId;
        this.type = type;
    }


    public ExpenseItemData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
