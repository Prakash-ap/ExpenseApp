package com.eron.android.expenseapp.Model;

public class User {
    private int id;
    private String name;
    private String phone_no;
    private String password;
    private String phone_model;
    private String registered_date;
    private String signedin_date;

    public User(int id, String name, String phone_no, String password, String phone_model, String registered_date, String signedin_date) {
        this.id = id;
        this.name = name;
        this.phone_no = phone_no;
        this.password = password;
        this.phone_model = phone_model;
        this.registered_date = registered_date;
        this.signedin_date = signedin_date;
    }

    public User(int id, String name, String phone_no, String password, String phone_model) {
        this.id = id;
        this.name = name;
        this.phone_no = phone_no;
        this.password = password;
        this.phone_model = phone_model;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }

    public String getSignedin_date() {
        return signedin_date;
    }

    public void setSignedin_date(String signedin_date) {
        this.signedin_date = signedin_date;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_model() {
        return phone_model;
    }

    public void setPhone_model(String phone_model) {
        this.phone_model = phone_model;
    }
}
