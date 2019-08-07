package com.eron.android.expenseapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.AccountSpinnerAdapter;
import com.eron.android.expenseapp.Adapter.CopySpinnerCatAdapter1;
import com.eron.android.expenseapp.Adapter.ExpenseSpinnerCatAdapter;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.Model.TransModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class EditExpenseActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    TextView date;
    EditText amount,notes;
    Button save,cancel;
    Spinner category,account;
    Calendar calendar;
    ArrayList<CatItemData>catItemDataArrayList;
    ArrayList<CatItemData>accItemDataArrayList;
    DataBaseHandler db;
    TransModel transModel;
    ArrayList<TransModel>transModelArrayList;
    List<String> categoryList;
    List<String> accountList;
    List<Integer> caticon;
    List<Integer> accicon;
    ExpenseItemData expenseItemData;
    ExpenseItemData expenseItemData1;
    ExpenseItemData expenseItemData2;
    ExpenseItemData expenseItemData3;
    ExpenseItemData expenseItemData4;
    ExpenseItemData expenseItemData5;
    ExpenseItemData expenseItemData6;
    ExpenseItemData expenseItemData7;

    String ocat,oacc;
    int ocatimg,oaccimg;

    Acc_Model acc_model1;
    Acc_Model acc_model2;
    Acc_Model acc_model3;
    ExpenseSpinnerCatAdapter expenseSpinnerCatAdapter;
    ArrayList<ExpenseItemData>expenseItemDataArrayList=null;
    Acc_Model acc_model;
    ArrayList<Acc_Model>acc_modelArrayList;
    AccountSpinnerAdapter accountSpinnerAdapter;
    ArrayList<Acc_Model>acc_modelArrayList1;
    int year;


    String cattext;
    String catimage;
    String intentval;
    String idate,icatname,icatimg,iaccname,iaccimg,iamnt,inote;




    String dateval,catval,accval,amountval,noteval,type;
    String acctype;
    String accimg;
    String sort=null;

    SimpleDateFormat simpleDateFormat;
    String month;
    int dayofmonth;
    String catvalues,accvalues;
    int catimg;
    String selectmonthyear;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trans_expense);
        // Inflate the layout for this fragment

        calendar=Calendar.getInstance();
        calendar= Calendar.getInstance();
        date=findViewById(R.id.exedtdate);
        category=findViewById(R.id.exspinner);
        account=findViewById(R.id.exaccount);
        amount=findViewById(R.id.edtamount);
        notes=findViewById(R.id.edtnote);
        save=findViewById(R.id.btnsave);
        relativeLayout=findViewById(R.id.relative);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });



        intentval=getIntent().getStringExtra("KEY_EID");
        idate=getIntent().getStringExtra("KEY_EDATE");
        icatname=getIntent().getStringExtra("KEY_ECATNAME");
        icatimg=getIntent().getStringExtra("KEY_ECATIMG");
        iaccname=getIntent().getStringExtra("KEY_EACCNAME");
        iaccimg=getIntent().getStringExtra("KEY_EACCIMG");
        iamnt=getIntent().getStringExtra("KEY_EAMNT");
        inote=getIntent().getStringExtra("KEY_ENOTE");

        date.setText(idate);
        amount.setText(iamnt);
        notes.setText(inote);

        db=new DataBaseHandler(this);

        save.setOnClickListener(this);
      //  cancel.setOnClickListener(this);
        category.setOnItemSelectedListener(this);
        account.setOnItemSelectedListener(this);


       loadCatg();
       loadAct();



        final DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditExpenseActivity.this,dateSetListener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }

    private void loadAct() {

        acc_model=new Acc_Model();
        acc_modelArrayList=new ArrayList<>();
        acc_modelArrayList=db.getAllAccType();
        if(acc_modelArrayList.size()==0){
            acc_model.setIn_acc_type("Cash");
            acc_model.setImageid(R.string.cash);
            db.addAcc(acc_model);


            acc_model1=new Acc_Model();
            acc_model1.setIn_acc_type("Card");
            acc_model1.setImageid(R.string.card);
            db.addAcc(acc_model1);

            acc_model2=new Acc_Model();
            acc_model2.setIn_acc_type("Account");
            acc_model2.setImageid(R.string.Account);
            db.addAcc(acc_model2);

            acc_modelArrayList=db.getAllAccType();

            accountSpinnerAdapter=new AccountSpinnerAdapter(acc_modelArrayList,getApplicationContext());
            account.setAdapter(accountSpinnerAdapter);

            }else {

            acc_model=new Acc_Model();
            acc_modelArrayList=new ArrayList<>();
            acc_modelArrayList=db.getAllAccType();


            accountSpinnerAdapter=new AccountSpinnerAdapter(acc_modelArrayList,getApplicationContext());
            account.setAdapter(accountSpinnerAdapter);

        }
    }

    private void loadCatg() {

        expenseItemData=new ExpenseItemData();
        expenseItemDataArrayList=new ArrayList<>();
        expenseItemDataArrayList=db.getAllExpenseCat();

        if(expenseItemDataArrayList.size()==0){

            expenseItemData.setText("Rent");
            expenseItemData.setImageId(R.string.Roomrent);
            expenseItemData.setType("expense");
            db.addExpCatg(expenseItemData);

            expenseItemData1=new ExpenseItemData();
            expenseItemData1.setText("Food");
            expenseItemData1.setImageId(R.string.food);
            expenseItemData1.setType("expense");

            db.addExpCatg(expenseItemData1);

            expenseItemData2=new ExpenseItemData();
            expenseItemData2.setText("Fuel");
            expenseItemData2.setImageId(R.string.fa_gas_pump_solid);
            expenseItemData2.setType("expense");

            db.addExpCatg(expenseItemData2);

            expenseItemData3=new ExpenseItemData();
            expenseItemData3.setText("Travel");
            expenseItemData3.setImageId(R.string.Travel);
            expenseItemData3.setType("expense");

            db.addExpCatg(expenseItemData3);

            expenseItemData4=new ExpenseItemData();
            expenseItemData4.setText("Shopping");
            expenseItemData4.setImageId(R.string.Shopping);
            expenseItemData4.setType("expense");

            db.addExpCatg(expenseItemData4);

            expenseItemData5=new ExpenseItemData();
            expenseItemData5.setText("Gift");
            expenseItemData5.setImageId(R.string.gift);
            expenseItemData5.setType("expense");

            db.addExpCatg(expenseItemData5);

            expenseItemData6=new ExpenseItemData();
            expenseItemData6.setText("Wifi");
            expenseItemData6.setImageId(R.string.Wifi);
            expenseItemData6.setType("expense");

            db.addExpCatg(expenseItemData6);



            expenseItemDataArrayList=db.getAllExpenseCat();


           expenseSpinnerCatAdapter=new ExpenseSpinnerCatAdapter(expenseItemDataArrayList,getApplicationContext());
           category.setAdapter(expenseSpinnerCatAdapter);


        }else{
            expenseItemData=new ExpenseItemData();
            expenseItemDataArrayList=new ArrayList<>();
            expenseItemDataArrayList=db.getAllExpenseCat();
            expenseSpinnerCatAdapter=new ExpenseSpinnerCatAdapter(expenseItemDataArrayList,getApplicationContext());
            category.setAdapter(expenseSpinnerCatAdapter);

        }
    }

    private void updateLabel() {
        String format="MMMM dd, yyyy";
      simpleDateFormat=new SimpleDateFormat(format, Locale.getDefault());
        date.setText(simpleDateFormat.format(calendar.getTime()));
        dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        month = new SimpleDateFormat("MMMM").format(calendar.getTime());
        year=calendar.get(Calendar.YEAR);
        selectmonthyear=month + " " + String.valueOf(year);
    }




    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnsave){

     dateval=date.getText().toString();

     amountval=amount.getText().toString();
     noteval=notes.getText().toString();
     type="expense";

     if(dateval.equals("") ||amountval.equals("") ){
         Toast.makeText(this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
     }else{
         transModelArrayList=new ArrayList<>();
         transModel=new TransModel();
         transModel.setId(Integer.parseInt(intentval));
         transModel.setDate(dateval);
         transModel.setCategory_name(ocat);
         transModel.setAccount_name(oacc);
         transModel.setCategory_img(ocatimg);
         transModel.setAccount_img(oaccimg);
         transModel.setAmount(amountval);
         transModel.setNote(noteval);
         transModel.setType(type);
         transModel.setDay_of_month(String.valueOf(dayofmonth));
         transModel.setMonth(month);
         transModel.setYear(year);
         transModel.setSelectedmonthyear(selectmonthyear);
         transModelArrayList.add(transModel);
         db.updateTransList(transModel);
       //  db.getAllNewIncome();
         Log.d("Insert", "Inserting from Income: " + transModel);
    //     Log.d("Insert", "Inserting from Income: " + db);

         Intent intent=new Intent(this,DashBoardActivity.class);
         startActivity(intent);

     }


        }else{

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.exspinner:

                expenseItemDataArrayList=db.getAllExpenseCat();

               ocat=expenseItemDataArrayList.get(position).getText();
               ocatimg= (expenseItemDataArrayList.get(position).getImageId());


             //   Log.d("CatValue", "onItemSelected: "+catval);


                break;
            case R.id.exaccount:

                acc_modelArrayList1=db.getAllAccType();
                oacc=acc_modelArrayList1.get(position).getIn_acc_type();
                oaccimg= (acc_modelArrayList1.get(position).getImageid());


                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    protected void hideKeyboard(View view){
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }


}
