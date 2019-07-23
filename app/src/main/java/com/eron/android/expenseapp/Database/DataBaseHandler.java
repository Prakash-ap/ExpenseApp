package com.eron.android.expenseapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eron.android.expenseapp.Model.User;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="kakeibo_db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="kakeibo_signup";
    private static final String ADD_INCOME_TABLE_NAME="add_new_income";
    private static final String KEY_ID="sno";
    private static final String KEY_PHONE_NO="user_mobile_no";
    private static final String KEY_USERNAME="user_name";
    private static final String KEY_PASSWORD="user_pass";
    private static final String KEY_PHONE_MODEL="user_device_info";
    private static final String KEY_USER_REGISTERED="user_registered";
  //  private static final String KEY_USER_SIGNIN="user_signedin";

    private static final String KEY_NEWINCOME_DATE="newincome_date";
    private static final String KEY_NEWINCOME_CATG="newincome_catg";
    private static final String KEY_NEWINCOME_ACC="newincome_acc";
    private static final String KEY_NEWINCOME_AMNT="newincome_amnt";
    private static final String KEY_NEWINCOME_NOTE="newincome_note";




    public DataBaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+"("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_USERNAME+" TEXT,"
                +KEY_PHONE_NO+ " TEXT,"+KEY_PASSWORD+" TEXT,"+KEY_PHONE_MODEL+" TEXT,"+KEY_USER_REGISTERED+ " TEXT" +")";

        String CREATE_NEWICOME_TABLE=" CREATE TABLE "+ADD_INCOME_TABLE_NAME+"("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NEWINCOME_DATE + " TEXT," +KEY_NEWINCOME_CATG+ " TEXT," +KEY_NEWINCOME_ACC+ " TEXT,"+KEY_NEWINCOME_AMNT+
                " TEXT,"+KEY_NEWINCOME_NOTE+ " TEXT"+")";

         db.execSQL(CREATE_TABLE);
         db.execSQL(CREATE_NEWICOME_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+ADD_INCOME_TABLE_NAME);

    }

    public void addUserDetails(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_USERNAME,user.getName());
        contentValues.put(KEY_PHONE_NO,user.getPhone_no());
        contentValues.put(KEY_PASSWORD,user.getPassword());
        contentValues.put(KEY_PHONE_MODEL,user.getPhone_model());
        contentValues.put(KEY_USER_REGISTERED,user.getRegistered_date());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

   /* public void addUserDetails(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_USERNAME,user.getName());
        contentValues.put(KEY_PHONE_NO,user.getPhone_no());
        contentValues.put(KEY_PASSWORD,user.getPassword());
        contentValues.put(KEY_PHONE_MODEL,user.getPhone_model());
        contentValues.put(KEY_USER_REGISTERED,user.getRegistered_date());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }
*/
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
                user.setRegistered_date(cursor.getString(5));
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
