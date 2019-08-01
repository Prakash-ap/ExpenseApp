package com.eron.android.expenseapp.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.AccountSpinnerAdapter;
import com.eron.android.expenseapp.Adapter.CopySpinnerCatAdapter1;
import com.eron.android.expenseapp.DashBoardActivity;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class TransIncomeFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trans_income, container, false);
        calendar=Calendar.getInstance();
        date=view.findViewById(R.id.edtdate);
        category=view.findViewById(R.id.spinnercat);
        account=view.findViewById(R.id.spinneraccount);
        save=view.findViewById(R.id.btnsave);
        cancel=view.findViewById(R.id.btn_cancel);
        amount=view.findViewById(R.id.edtamount);
        notes=view.findViewById(R.id.edtnote);
        db=new DataBaseHandler(getContext());

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
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
                new DatePickerDialog(getContext(),dateSetListener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;


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

            accountSpinnerAdapter=new AccountSpinnerAdapter(acc_modelArrayList,getContext());
            account.setAdapter(accountSpinnerAdapter);

            }else {

            acc_model=new Acc_Model();
            acc_modelArrayList=new ArrayList<>();
            acc_modelArrayList=db.getAllAccType();


            accountSpinnerAdapter=new AccountSpinnerAdapter(acc_modelArrayList,getContext());
            account.setAdapter(accountSpinnerAdapter);

        }
    }

    private void loadCatg() {

        catItemData=new CatItemData();
        catItemDataArrayList=new ArrayList<>();
        catItemDataArrayList=db.getAllCatg();

        if(catItemDataArrayList.size()==0){

            catItemData.setText("Salary");
            catItemData.setImageId(R.string.salary);
            catItemData.setType("income");
            db.addCatg(catItemData);

            catItemData1=new CatItemData();
            catItemData1.setText("Allowance");
            catItemData1.setImageId(R.string.Allowance);
            catItemData1.setType("income");

            db.addCatg(catItemData1);

            catItemData2=new CatItemData();
            catItemData2.setText("Bonus");
            catItemData2.setImageId(R.string.cash);
            catItemData2.setType("income");

            db.addCatg(catItemData2);


            catItemDataArrayList=db.getAllCatg();


           copySpinnerCatAdapter1=new CopySpinnerCatAdapter1(catItemDataArrayList,getContext());
           category.setAdapter(copySpinnerCatAdapter1);


        }else{
            catItemData=new CatItemData();
            catItemDataArrayList=new ArrayList<>();
            catItemDataArrayList=db.getAllCatg();
            copySpinnerCatAdapter1=new CopySpinnerCatAdapter1(catItemDataArrayList,getContext());
            category.setAdapter(copySpinnerCatAdapter1);

        }
    }

    private void updateLabel() {
        String format="MM/dd/YYYY";
      simpleDateFormat=new SimpleDateFormat(format, Locale.getDefault());
        date.setText(simpleDateFormat.format(calendar.getTime()));
        dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        month = new SimpleDateFormat("MMMM").format(calendar.getTime());
    }




    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnsave){

     dateval=date.getText().toString();

     amountval=amount.getText().toString();
     noteval=notes.getText().toString();
     type="income";

     if(dateval.equals("") ||amountval.equals("") ){
         Toast.makeText(getContext(), "Enter the Credentials", Toast.LENGTH_SHORT).show();
     }else{
         transModelArrayList=new ArrayList<>();
         transModel=new TransModel();
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
         transModelArrayList.add(transModel);
         db.addNewIncome(transModel);
         Log.d("Insert", "Inserting from Income: " + transModel);

         Intent intent=new Intent(getContext(),DashBoardActivity.class);
         startActivity(intent);

     }


        }else{

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinnercat:

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
}
