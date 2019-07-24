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

import com.eron.android.expenseapp.Adapter.SpinnerCatAdapter;
import com.eron.android.expenseapp.DashBoardActivity;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    String dateval,catval,accval,amountval,noteval,type;

    SimpleDateFormat simpleDateFormat;
    String month;
    int dayofmonth;


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

        catItemDataArrayList=new ArrayList<>();


        catItemDataArrayList.add(new CatItemData("Salary",R.string.salary));
        catItemDataArrayList.add(new CatItemData("Lease",R.string.home));
      //  catItemDataArrayList.add(new CatItemData("Salary",R.string.cash));
     //   catItemDataArrayList.add(new CatItemData("Other",R.drawable.others));

        SpinnerCatAdapter adapter=new SpinnerCatAdapter(getContext(),R.id.cat_text,R.layout.child_catlayout,catItemDataArrayList);
        category.setAdapter(adapter);

        accItemDataArrayList=new ArrayList<>();

        accItemDataArrayList.add(new CatItemData("Cash",R.string.cash));
        accItemDataArrayList.add(new CatItemData("Card",R.string.card));
        accItemDataArrayList.add(new CatItemData("Account",R.string.Account));

       // accItemDataArrayList.add(new CatItemData("Account",R.drawable.account));



        SpinnerCatAdapter adapter1=new SpinnerCatAdapter(getContext(),R.id.cat_text,R.layout.child_catlayout,accItemDataArrayList);
        account.setAdapter(adapter1);



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
         transModel.setCategory(catval);
         transModel.setAccount(accval);
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
                catval=parent.getItemAtPosition(position).toString();


                break;
            case R.id.spinneraccount:
                accval=parent.getItemAtPosition(position).toString();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
