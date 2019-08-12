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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.AccountSpinnerAdapter;
import com.eron.android.expenseapp.Adapter.CopySpinnerCatAdapter1;
import com.eron.android.expenseapp.Adapter.ExpenseSpinnerCatAdapter;
import com.eron.android.expenseapp.Adapter.ExpenseSpinnerCatAdapter1;
import com.eron.android.expenseapp.Adapter.SpinnerExpAdapter;
import com.eron.android.expenseapp.DashBoardActivity;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class TransExpenseFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    Calendar calendar;
    TextView date;
    Spinner category,account;
    EditText amount,notes;
    Button save;
    ArrayList<ExpenseItemData>expenseItemDataArrayList;
    ArrayList<ExpenseItemData>expenseItemDataArrayList1;
    ArrayList<CatItemData>getCatItemDataArrayList1;
    ArrayList<CatItemData>catItemDataArrayList1;
    ArrayList<ExpenseItemData>expenseAccItemDataArrayList;

    ExpenseItemData expenseItemData;
    ExpenseItemData expenseItemData1;
    ExpenseItemData expenseItemData2;
    ExpenseItemData expenseItemData3;
    ExpenseItemData expenseItemData4;
    ExpenseItemData expenseItemData5;
    ExpenseItemData expenseItemData6;

    CatItemData catItemData;
    CatItemData catItemData1;
    CatItemData catItemData2;
    CatItemData catItemData3;
    CatItemData catItemData4;
    CatItemData catItemData5;
    CatItemData catItemData6;
    CatItemData catItemData7;
    ExpenseItemData expenseItemData7;
   // ArrayList<CatItemData>catItemDataArrayList;
    DataBaseHandler db;
    CopySpinnerCatAdapter1 copySpinnerCatAdapter1;
    ExpenseSpinnerCatAdapter1 expenseSpinnerCatAdapter;
    AccountSpinnerAdapter accountSpinnerAdapter;

    Acc_Model acc_model;
    Acc_Model acc_model1;
    Acc_Model acc_model2;
    ArrayList<Acc_Model>acc_modelArrayList;

    ArrayList<Acc_Model>acc_modelArrayList1;

    String adate,acat,aacc,aamnt,anotes;
    int acatimg,aaccimg;
    String type;
    ArrayList<TransModel>transModelArrayList;
    ArrayList<TransModel>transModelArrayList1;
    TransModel transModel;
    String month;
    int dayofmonth;
    int year;
    String selectmonthyear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trans_expense, container, false);

        calendar= Calendar.getInstance();
        date=view.findViewById(R.id.exedtdate);
        category=view.findViewById(R.id.exspinner);
        account=view.findViewById(R.id.exaccount);
        amount=view.findViewById(R.id.edtamount);
        notes=view.findViewById(R.id.edtnote);
        save=view.findViewById(R.id.btnsave);
        db=new DataBaseHandler(getContext());

        save.setOnClickListener(this);
        category.setOnItemSelectedListener(this);
        account.setOnItemSelectedListener(this);

       /* expenseItemDataArrayList=new ArrayList<>();
        expenseAccItemDataArrayList=new ArrayList<>();*/
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

    private void loadCatg() {
        catItemData=new CatItemData();
        catItemDataArrayList1=new ArrayList<>();
        catItemDataArrayList1=db.getAllExpenseCat1();

        if(catItemDataArrayList1.size()==0){

            catItemData.setText("Rent");
            catItemData.setImageId(R.string.Roomrent);
            catItemData.setType("expense");
            db.addExpCatg1(catItemData);

            catItemData1=new CatItemData();
            catItemData1.setText("Food");
            catItemData1.setImageId(R.string.food);
            catItemData1.setType("expense");

            db.addExpCatg1(catItemData1);

            catItemData2=new CatItemData();
            catItemData2.setText("Fuel");
            catItemData2.setImageId(R.string.fa_gas_pump_solid);
            catItemData2.setType("expense");

            db.addExpCatg1(catItemData2);

            catItemData3=new CatItemData();
            catItemData3.setText("Travel");
            catItemData3.setImageId(R.string.Travel);
            catItemData3.setType("expense");

            db.addExpCatg1(catItemData3);

            catItemData4=new CatItemData();
            catItemData4.setText("Shopping");
            catItemData4.setImageId(R.string.Shopping);
            catItemData4.setType("expense");

            db.addExpCatg1(catItemData4);

            catItemData5=new CatItemData();
            catItemData5.setText("Gift");
            catItemData5.setImageId(R.string.gift);
            catItemData5.setType("expense");

            db.addExpCatg1(catItemData5);

            catItemData6=new CatItemData();
            catItemData6.setText("Wifi");
            catItemData6.setImageId(R.string.Wifi);
            catItemData6.setType("expense");

            db.addExpCatg1(catItemData6);


            catItemDataArrayList1=db.getAllExpenseCat1();

            expenseSpinnerCatAdapter=new ExpenseSpinnerCatAdapter1(catItemDataArrayList1,getContext());
            category.setAdapter(expenseSpinnerCatAdapter);


        }else{
            catItemData=new CatItemData();
            catItemDataArrayList1=new ArrayList<>();
            catItemDataArrayList1=db.getAllExpenseCat1();
            expenseSpinnerCatAdapter=new ExpenseSpinnerCatAdapter1(catItemDataArrayList1,getContext());
            category.setAdapter(expenseSpinnerCatAdapter);

       /* expenseItemData=new ExpenseItemData();
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

            expenseSpinnerCatAdapter=new ExpenseSpinnerCatAdapter(expenseItemDataArrayList,getContext());
            category.setAdapter(expenseSpinnerCatAdapter);


        }else{
            expenseItemData=new ExpenseItemData();
            expenseItemDataArrayList=new ArrayList<>();
            expenseItemDataArrayList=db.getAllExpenseCat();
            expenseSpinnerCatAdapter=new ExpenseSpinnerCatAdapter(expenseItemDataArrayList,getContext());
            category.setAdapter(expenseSpinnerCatAdapter);
*/
        }
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

    private void updateLabel() {
        String format="MMMM dd, yyyy";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format, Locale.getDefault());
        date.setText(simpleDateFormat.format(calendar.getTime()));
        dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        month = new SimpleDateFormat("MMMM").format(calendar.getTime());
        year=calendar.get(Calendar.YEAR);
        selectmonthyear=month + " " + String.valueOf(year);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnsave){

            adate=date.getText().toString();
            aamnt=amount.getText().toString();
            anotes=notes.getText().toString();
            type="expense";

            if(adate.equals("")||aamnt.equals("")){
                Toast.makeText(getContext(), "Enter the Credentials", Toast.LENGTH_SHORT).show();
            }else {

                transModelArrayList=new ArrayList<>();
                transModel=new TransModel();
                transModel.setDate(adate);
                transModel.setCategory_name(acat);
                transModel.setCategory_img(acatimg);
                transModel.setAccount_name(aacc);
                transModel.setAccount_img(aaccimg);
                transModel.setAmount(aamnt);
                transModel.setNote(anotes);
                transModel.setType(type);
                transModel.setDay_of_month(String.valueOf(dayofmonth));
                transModel.setMonth(month);
                transModel.setYear(year);
                transModel.setSelectedmonthyear(selectmonthyear);
                transModelArrayList.add(transModel);
                db.addNewIncome(transModel);


                Intent intent=new Intent(getContext(),DashBoardActivity.class);
                startActivity(intent);



            }




        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.exspinner:

                catItemDataArrayList1=db.getAllExpenseCat1();

                acat=catItemDataArrayList1.get(position).getText();
                acatimg= (catItemDataArrayList1.get(position).getImageId());

                break;
            case R.id.exaccount:

                acc_modelArrayList1=db.getAllAccType();
                aacc=acc_modelArrayList1.get(position).getIn_acc_type();
                aaccimg= (acc_modelArrayList1.get(position).getImageid());


                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



