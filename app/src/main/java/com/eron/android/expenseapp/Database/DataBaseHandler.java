package com.eron.android.expenseapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eron.android.expenseapp.Model.User;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="expenseapp";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="expenses_signup";
    private static final String KEY_ID="id";
    private static final String KEY_PHONE_NO="phone_no";
    private static final String KEY_USERNAME="username";
    private static final String KEY_PASSWORD="password";
    private static final String KEY_PHONE_MODEL="phone_model";

    public DataBaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+"("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_USERNAME+" TEXT,"
                +KEY_PHONE_NO+ " TEXT,"+KEY_PASSWORD+" TEXT,"+KEY_PHONE_MODEL+" TEXT"+")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);

    }

    public void addUserDetails(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_USERNAME,user.getName());
        contentValues.put(KEY_PHONE_NO,user.getPhone_no());
        contentValues.put(KEY_PASSWORD,user.getPassword());
        contentValues.put(KEY_PHONE_MODEL,user.getPhone_model());

        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public ArrayList<User>getAllUser(){
        ArrayList<User>userArrayList=new ArrayList<>();

        String selectAllQuery=" SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectAllQuery,null);

        if(cursor.moveToFirst()){
            do{
                User user=new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setPhone_no(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setPhone_model(cursor.getString(4));
                userArrayList.add(user);
            }while (cursor.moveToNext());
        }
        return userArrayList;
    }

    public void clearTable(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
    }


}
