package com.eron.android.expenseapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.TransModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class EditIncomeActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
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
    CatItemData catItemData;
    CatItemData catItemData1;
    CatItemData catItemData2;
    CatItemData catItemData3;
    CatItemData catItemData4;
    String ocat,oacc;
    int ocatimg,oaccimg;

    Acc_Model acc_model1;
    Acc_Model acc_model2;
    Acc_Model acc_model3;
    CopySpinnerCatAdapter1 copySpinnerCatAdapter1;
    ArrayList<CatItemData>catItemDataArrayList1=null;
    Acc_Model acc_model;
    ArrayList<Acc_Model>acc_modelArrayList;
    AccountSpinnerAdapter accountSpinnerAdapter;
    ArrayList<Acc_Model>acc_modelArrayList1;
    int year;

    Fragment fragment;

    String cattext;
    String catimage;




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
    String intentval;
    String idate,icatname,icatimg,iaccname,iaccimg,iamnt,inote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trans_income);
        // Inflate the layout for this fragment

        RelativeLayout relativeLayout=findViewById(R.id.relativelayout);
        calendar=Calendar.getInstance();
        date=findViewById(R.id.edtdate);
        category=findViewById(R.id.spinnercatupdate);
        account=findViewById(R.id.spinneraccount);
        save=findViewById(R.id.btnsave);
        cancel=findViewById(R.id.btn_cancel);
        amount=findViewById(R.id.edtamount);
        notes=findViewById(R.id.edtnote);
        intentval=getIntent().getStringExtra("KEY_ID");
        idate=getIntent().getStringExtra("KEY_DATE");
        icatname=getIntent().getStringExtra("KEY_CATNAME");
        icatimg=getIntent().getStringExtra("KEY_CATIMG");
        iaccname=getIntent().getStringExtra("KEY_ACCNAME");
        iaccimg=getIntent().getStringExtra("KEY_ACCIMG");
        iamnt=getIntent().getStringExtra("KEY_AMNT");
        inote=getIntent().getStringExtra("KEY_NOTE");



        date.setText(idate);
        amount.setText(iamnt);
        notes.setText(inote);



        db=new DataBaseHandler(this);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        category.setOnItemSelectedListener(this);
        account.setOnItemSelectedListener(this);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });


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
                new DatePickerDialog(EditIncomeActivity.this,dateSetListener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }

    private void loadAct() {

        acc_model=new Acc_Model();
        acc_modelArrayList=new ArrayList<>();
        acc_modelArrayList=db.getAllAccType();
        accountSpinnerAdapter=new AccountSpinnerAdapter(acc_modelArrayList,getApplicationContext());
            account.setAdapter(accountSpinnerAdapter);
            for(int i=0;i<acc_modelArrayList.size();i++){
                acc_model=acc_modelArrayList.get(i);
                String acc1=acc_model.getIn_acc_type();
                if(acc1.equals(iaccname)){
                    account.setSelection(i);
                    Log.d("spinner", "loadAcc: " + i);
                }else{

                }
            }
         //   account.setSelection(acc_modelArrayList.indexOf(iaccname));

        }


    private void loadCatg() {

        catItemData = new CatItemData();
        catItemDataArrayList = new ArrayList<>();
        catItemDataArrayList = db.getAllCatg();
        copySpinnerCatAdapter1 = new CopySpinnerCatAdapter1(catItemDataArrayList, getApplicationContext());
        category.setAdapter(copySpinnerCatAdapter1);

        for (int i = 0; i < catItemDataArrayList.size(); i++) {
            catItemData = catItemDataArrayList.get(i);
            String cat1 = catItemData.getText();
            if (cat1.equals(icatname)) {
                category.setSelection(i);
                Log.d("spinner", "loadCatg: " + i);

            }

          /*  if(catItemDataArrayList.contains(icatname)){
                category.setSelection(i);
                Log.d("spinner", "loadCatg: "+i);

            }
        }
        */





       /* for (int i=0;i<catItemDataArrayList.size();i++){

            if(catItemDataArrayList.contains(icatname)){
                category.setSelection(i);
                Log.d("spinner", "loadCatg: "+i);

            }
        }*/

            //  category.setSelection(catItemDataArrayList.indexOf(icatname));
          //  int pos = catItemDataArrayList.indexOf(icatname);
            // Log.d("spinner", "loadCatg: "+pos);

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
            dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
            month = new SimpleDateFormat("MMMM").format(calendar.getTime());
            year=calendar.get(Calendar.YEAR);
            selectmonthyear=month + " " + String.valueOf(year);

     amountval=amount.getText().toString();
     noteval=notes.getText().toString();
     type="income";

     if(dateval.equals("") ||amountval.equals("") ){
         Toast.makeText(this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
     }else{
         transModelArrayList=new ArrayList<>();
         transModel=new TransModel();

//         transModel.setId(Integer.parseInt(intentval));
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


         db.getAllNewIncome();
         Log.d("Insert", "Inserting from Income: " + transModel);

         Intent intent=new Intent(this,DashBoardActivity.class);
         startActivity(intent);

     }


        }else{

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinnercatupdate:

                catItemDataArrayList1=db.getAllCatg();

               ocat=catItemDataArrayList1.get(position).getText();
               ocatimg= (catItemDataArrayList1.get(position).getImageId());



             //   Log.d("CatValue", "onItemSelected: "+catval);


                break;
            case R.id.spinneraccount:

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
