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

import com.eron.android.expenseapp.Adapter.CopySpinnerAccAdapter;
import com.eron.android.expenseapp.Adapter.CopySpinnerCatAdapter;
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
    CatItemData catItemData;
    CatItemData catItemData1;
    CatItemData catItemData2;
    CatItemData catItemData3;
    CatItemData catItemData4;


    String cattext;
    String catimage;

    String dateval,catval,accval,amountval,noteval,type;
    String accname;
    int accimg;

    SimpleDateFormat simpleDateFormat;
    String month;
    int dayofmonth;
    String catvalues,accvalues;
    int catimg;
    String[] catnames={"Salary","Lease"};
    int[] caticons={R.string.salary,R.string.home};
    String[] accnames={"Cash","Card","Account"};
    int[] accicons={R.string.cash,R.string.card,R.string.Account};
    CopySpinnerCatAdapter copySpinnerCatAdapter;
    CopySpinnerAccAdapter copySpinnerAccAdapter;
    ArrayAdapter<String>catadapter;


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

       // loadCatg();

        catItemDataArrayList=new ArrayList<>();

        copySpinnerCatAdapter=new CopySpinnerCatAdapter(catnames,caticons,getContext());
        category.setAdapter(copySpinnerCatAdapter);

        copySpinnerAccAdapter=new CopySpinnerAccAdapter(accnames,accicons,getContext());
        account.setAdapter(copySpinnerAccAdapter);





       /* catItemDataArrayList.add(new CatItemData("Salary",R.string.salary));
        catItemDataArrayList.add(new CatItemData("Lease",R.string.home));*/
      //  catItemDataArrayList.add(new CatItemData("Salary",R.string.cash));
     //   catItemDataArrayList.add(new CatItemData("Other",R.drawable.others));

       /* SpinnerCatAdapter adapter=new SpinnerCatAdapter(getContext(),R.id.cat_text,R.layout.child_catlayout,catItemDataArrayList);
        category.setAdapter(adapter);
        accItemDataArrayList=new ArrayList<>();

        accItemDataArrayList.add(new CatItemData("Cash",R.string.cash));
        accItemDataArrayList.add(new CatItemData("Card",R.string.card));
        accItemDataArrayList.add(new CatItemData("Account",R.string.Account));
*/
       // accItemDataArrayList.add(new CatItemData("Account",R.drawable.account));



      /*  SpinnerCatAdapter adapter1=new SpinnerCatAdapter(getContext(),R.id.cat_text,R.layout.child_catlayout,accItemDataArrayList);
        account.setAdapter(adapter1);
*/


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
        categoryList=new ArrayList<String>();
        catItemData=new CatItemData();
        catItemDataArrayList=new ArrayList<>();
        catItemDataArrayList=db.getAllCatg();

        if(catItemDataArrayList.size()==0){

            catItemDataArrayList.add(new CatItemData("Salary",R.string.salary));

            db.addCatg(catItemData);

            catItemData1=new CatItemData();
            catItemData1.setText("Allowance");
            catItemData1.setImageId(R.string.Allowance);
            db.addCatg(catItemData1);

            catItemData2=new CatItemData();
            catItemData2.setText("Bonus");
            catItemData2.setImageId(R.string.Bonus);
            db.addCatg(catItemData2);


            catItemDataArrayList=db.getAllCatg();
            for (int i=0;i<catItemDataArrayList.size();i++){
                catItemData=catItemDataArrayList.get(i);
                cattext=catItemData.getText();
                catimage= String.valueOf(catItemData.getImageId());
                categoryList.add(cattext);
                categoryList.add(catimage);

            }

            catadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,categoryList);
            catadapter.setDropDownViewResource(R.layout.child_catlayout);
            category.setAdapter(catadapter);



        }else{
            catItemData=new CatItemData();
            catItemDataArrayList=new ArrayList<>();
            catItemDataArrayList=db.getAllCatg();
            for(int i=0;i<catItemDataArrayList.size();i++){
                catItemData=catItemDataArrayList.get(i);
                cattext=catItemData.getText();
                catimage= String.valueOf(catItemData.getImageId());
                categoryList.add(cattext);
                categoryList.add(catimage);
            }
            catadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,categoryList);
            catadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            category.setAdapter(catadapter);

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
         transModel.setCategory_name(catval);
         transModel.setCategory_img(catimg);
         transModel.setAccount_name(accval);
         transModel.setAccount_img(accimg);
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
               /* catval=catItemData.getText().toString();
                catimg=catItemData.getImageId();*/
                catval=catnames[position];
                catimg=caticons[position];

                Log.d("CatValue", "onItemSelected: "+catval);


                break;
            case R.id.spinneraccount:
              //  accval=parent.getItemAtPosition(position).toString();

                accname=accnames[position];
                accimg=accicons[position];



                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
