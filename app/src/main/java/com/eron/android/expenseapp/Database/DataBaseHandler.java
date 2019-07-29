package com.eron.android.expenseapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.Model.User;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="kakeibo_db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="kakeibo_signup";
    private static final String ADD_INCOME_TABLE_NAME="add_new_income";
    private static final String ADD_INCOME_CAT_TABLE="add_cat_table";
    private static final String ADD_ACC_TABLE="add_acc_table";
    private static final String KEY_ID="sno";
    private static final String KEY_PHONE_NO="user_mobile_no";
    private static final String KEY_USERNAME="user_name";
    private static final String KEY_PASSWORD="user_pass";
    private static final String KEY_PHONE_MODEL="user_device_info";
    private static final String KEY_USER_REGISTERED="user_registered";
  //  private static final String KEY_USER_SIGNIN="user_signedin";

    private static final String KEY_NEWINCOME_DATE="newincome_date";
    private static final String KEY_NEWINCOME_CATG_NAME="newincome_catg_name";
    private static final String KEY_NEWINCOME_CATG_IMG="newincome_catg_img";
    private static final String KEY_NEWINCOME_ACC_NAME="newincome_acc_name";
    private static final String KEY_NEWINCOME_ACC_IMG="newincome_acc_img";
    private static final String KEY_NEWINCOME_AMNT="newincome_amnt";
    private static final String KEY_NEWINCOME_NOTE="newincome_note";
    private static final String KEY_NEWINCOME_TYPE="newincome_type";
    private static final String KEY_NEWINCOME_DAY_OF_MONTH="newincome_day_of_month";
    private static final String KEY_NEWINCOME_MONTH="newincome_month";



    private static final String KEY_ADD_CAT_NAME="cat_name";
    private static final String KEY_ADD_CAT_IMG="cat_img";

    private static final String KEY_ADD_ACC_NAME="acc_name";
    private static final String KEY_ADD_ACC_ICON="acc_img";






    public DataBaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+"("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_USERNAME+" TEXT,"
                +KEY_PHONE_NO+ " TEXT,"+KEY_PASSWORD+" TEXT,"+KEY_PHONE_MODEL+" TEXT,"+KEY_USER_REGISTERED+ " TEXT" +")";

        String CREATE_NEWICOME_TABLE=" CREATE TABLE "+ADD_INCOME_TABLE_NAME+"("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NEWINCOME_DATE + " TEXT," +KEY_NEWINCOME_CATG_NAME+ " TEXT,"+KEY_NEWINCOME_CATG_IMG+" TEXT," +KEY_NEWINCOME_ACC_NAME+ " TEXT,"+KEY_NEWINCOME_ACC_IMG +" TEXT,"+KEY_NEWINCOME_AMNT+
                " TEXT,"+KEY_NEWINCOME_NOTE+ " TEXT,"+ KEY_NEWINCOME_TYPE +" TEXT,"+ KEY_NEWINCOME_DAY_OF_MONTH + " TEXT,"+ KEY_NEWINCOME_MONTH +" TEXT"+")";

        String CREATE_NEW_CAT_TABLE=" CREATE TABLE "+ADD_INCOME_CAT_TABLE+ "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_ADD_CAT_NAME+" TEXT,"+KEY_ADD_CAT_IMG+" TEXT,"+KEY_NEWINCOME_TYPE+ " TEXT "+")";

        String CREATE_NEW_ACC_TABLE=" CREATE TABLE "+ADD_ACC_TABLE+ "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_ADD_ACC_NAME+" TEXT,"+KEY_ADD_ACC_ICON+" TEXT"+")";

         db.execSQL(CREATE_TABLE);
         db.execSQL(CREATE_NEWICOME_TABLE);
         db.execSQL(CREATE_NEW_CAT_TABLE);
         db.execSQL(CREATE_NEW_ACC_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+ADD_INCOME_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+ADD_INCOME_CAT_TABLE);
        db.execSQL(" DROP TABLE IF EXISTS "+ADD_ACC_TABLE);

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

    public void addCatg(CatItemData catItemData){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
       contentValues.put(KEY_ADD_CAT_NAME,catItemData.getText());
       contentValues.put(KEY_ADD_CAT_IMG,catItemData.getImageId());
       contentValues.put(KEY_NEWINCOME_TYPE,catItemData.getType());
        db.insert(ADD_INCOME_CAT_TABLE,null,contentValues);
        db.close();
    }

    public void addAcc(Acc_Model acc_model){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_ADD_ACC_NAME,acc_model.getIn_acc_type());
        contentValues.put(KEY_ADD_ACC_ICON,acc_model.getImageid());
        db.insert(ADD_ACC_TABLE,null,contentValues);
        db.close();
    }

    public void addNewIncome(TransModel transModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NEWINCOME_DATE,transModel.getDate());
        contentValues.put(KEY_NEWINCOME_CATG_NAME,transModel.getCategory_name());
        contentValues.put(KEY_NEWINCOME_CATG_IMG,transModel.getCategory_img());
        contentValues.put(KEY_NEWINCOME_ACC_NAME,transModel.getAccount_name());
        contentValues.put(KEY_NEWINCOME_ACC_IMG,transModel.getAccount_img());
        contentValues.put(KEY_NEWINCOME_AMNT,transModel.getAmount());
        contentValues.put(KEY_NEWINCOME_NOTE,transModel.getNote());
        contentValues.put(KEY_NEWINCOME_TYPE,transModel.getType());
        contentValues.put(KEY_NEWINCOME_DAY_OF_MONTH,transModel.getDay_of_month());
        contentValues.put(KEY_NEWINCOME_MONTH,transModel.getMonth());

        db.insert(ADD_INCOME_TABLE_NAME,null,contentValues);
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
                user.setRegistered_date(cursor.getString(5));
                userArrayList.add(user);
            }while (cursor.moveToNext());
        }
        return userArrayList;
    }

    public ArrayList<CatItemData>getAllCatg(){
        ArrayList<CatItemData>catItemDataArrayList=new ArrayList<>();

        String selectAllQuery=" SELECT * FROM "+ADD_INCOME_CAT_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectAllQuery,null);

        if(cursor.moveToFirst()){
            do{
               CatItemData catItemData=new CatItemData();
               catItemData.setId(Integer.parseInt(cursor.getString(0)));
               catItemData.setText(cursor.getString(1));
               catItemData.setImageId(Integer.parseInt(cursor.getString(2)));
                catItemData.setType(cursor.getString(3));
               catItemDataArrayList.add(catItemData);
            }while (cursor.moveToNext());
        }
        return catItemDataArrayList;
    }

    public ArrayList<Acc_Model>getAllAccType(){
        ArrayList<Acc_Model>acc_modelArrayList=new ArrayList<>();

        String selectAllQuery=" SELECT * FROM "+ADD_ACC_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectAllQuery,null);

        if(cursor.moveToFirst()){
            do{
                Acc_Model acc_model=new Acc_Model();
                acc_model.setId(Integer.parseInt(cursor.getString(0)));
                acc_model.setIn_acc_type(cursor.getString(1));
                acc_model.setImageid(Integer.parseInt(cursor.getString(2)));
                acc_modelArrayList.add(acc_model);
            }while (cursor.moveToNext());
        }
        return acc_modelArrayList;
    }

    public ArrayList<TransModel>getAllNewIncome(){
        ArrayList<TransModel>transModelArrayList=new ArrayList<>();

        String selectAllQuery=" SELECT * FROM "+ADD_INCOME_TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();


        Cursor cursor=db.rawQuery(selectAllQuery,null);

        if(cursor.moveToFirst()){
            do{
                TransModel transModel=new TransModel();
                transModel.setId(Integer.parseInt(cursor.getString(0)));
                transModel.setDate(cursor.getString(1));
                transModel.setCategory_name(cursor.getString(2));
                transModel.setCategory_img(Integer.parseInt(cursor.getString(3)));
                transModel.setAccount_name(cursor.getString(4));
                transModel.setAccount_img(Integer.parseInt((cursor.getString(5))));
                transModel.setAmount(cursor.getString(6));
                transModel.setNote(cursor.getString(7));
                transModel.setType(cursor.getString(8));
                transModel.setDay_of_month(cursor.getString(9));
                transModel.setMonth(cursor.getString(10));
                transModelArrayList.add(transModel);
            }while (cursor.moveToNext());
        }
        return transModelArrayList;
    }


    public void clearTable(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
    }


    public ArrayList<TransModel>getTodayNewList(String dates){
        ArrayList<TransModel>transModelArrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(ADD_INCOME_TABLE_NAME,new String[]{KEY_ID,KEY_NEWINCOME_DATE,KEY_NEWINCOME_CATG_NAME,KEY_NEWINCOME_CATG_IMG,KEY_NEWINCOME_ACC_NAME,KEY_NEWINCOME_ACC_IMG,KEY_NEWINCOME_AMNT,KEY_NEWINCOME_NOTE,KEY_NEWINCOME_TYPE,KEY_NEWINCOME_DAY_OF_MONTH,KEY_NEWINCOME_MONTH},
                KEY_NEWINCOME_DATE+ "=?",new String[]{String.valueOf(dates)},null,null,null,null);


        if(cursor.moveToFirst()){
            do{
                TransModel transModel=new TransModel();
                transModel.setId(Integer.parseInt(cursor.getString(0)));
                transModel.setDate(cursor.getString(1));
                transModel.setCategory_name(cursor.getString(2));
                transModel.setCategory_img(Integer.parseInt(cursor.getString(3)));
                transModel.setAccount_name(cursor.getString(4));
                transModel.setAccount_img(Integer.parseInt(cursor.getString(5)));
                transModel.setAmount(cursor.getString(6));
                transModel.setNote(cursor.getString(7));
                transModel.setType(cursor.getString(8));
                transModel.setDay_of_month(cursor.getString(9));
                transModel.setMonth(cursor.getString(10));
                transModelArrayList.add(transModel);
            }while (cursor.moveToNext());
        }
        return transModelArrayList;


    }

    public ArrayList<TransModel>getCurrentMonthList(String month){
        ArrayList<TransModel>transModelArrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(ADD_INCOME_TABLE_NAME,new String[]{KEY_ID,KEY_NEWINCOME_DATE,KEY_NEWINCOME_CATG_NAME,KEY_NEWINCOME_CATG_IMG,KEY_NEWINCOME_ACC_NAME,KEY_NEWINCOME_ACC_IMG,KEY_NEWINCOME_AMNT,KEY_NEWINCOME_NOTE,KEY_NEWINCOME_TYPE,KEY_NEWINCOME_DAY_OF_MONTH,KEY_NEWINCOME_MONTH},
                KEY_NEWINCOME_MONTH+ "=?",new String[]{String.valueOf(month)},null,null,null,null);


        if(cursor.moveToFirst()){
            do{
                TransModel transModel=new TransModel();
                transModel.setId(Integer.parseInt(cursor.getString(0)));
                transModel.setDate(cursor.getString(1));
                transModel.setCategory_name(cursor.getString(2));
                transModel.setCategory_img(Integer.parseInt(cursor.getString(3)));
                transModel.setAccount_name(cursor.getString(4));
                transModel.setAccount_img(Integer.parseInt(cursor.getString(5)));
                transModel.setAmount(cursor.getString(6));
                transModel.setNote(cursor.getString(7));
                transModel.setType(cursor.getString(8));
                transModel.setDay_of_month(cursor.getString(9));
                transModel.setMonth(cursor.getString(10));
                transModelArrayList.add(transModel);
            }while (cursor.moveToNext());
        }
        return transModelArrayList;


    }



}
