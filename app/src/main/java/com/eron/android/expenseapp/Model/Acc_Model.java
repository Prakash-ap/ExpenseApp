package com.eron.android.expenseapp.Model;

public class Acc_Model {
    private int id;
    private String in_acc_type;
    private int imageid=-1;

    public Acc_Model(int id, String in_acc_type, int imageid) {
        this.id = id;
        this.in_acc_type = in_acc_type;
        this.imageid = imageid;
    }

    public Acc_Model(int id, String in_acc_type) {
        this.id = id;
        this.in_acc_type = in_acc_type;
    }

    public Acc_Model() {
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIn_acc_type() {
        return in_acc_type;
    }

    public void setIn_acc_type(String in_acc_type) {
        this.in_acc_type = in_acc_type;
    }
}
