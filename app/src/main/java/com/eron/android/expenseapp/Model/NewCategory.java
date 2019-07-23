package com.eron.android.expenseapp.Model;

public class NewCategory {
    private String name;
    private String image;

    public NewCategory(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public NewCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
