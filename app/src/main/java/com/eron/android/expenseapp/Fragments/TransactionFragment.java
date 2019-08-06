package com.eron.android.expenseapp.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.DialogClass;
import com.eron.android.expenseapp.Adapter.TransAdapter;
import com.eron.android.expenseapp.DashBoardActivity;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class TransactionFragment extends Fragment {

    private RecyclerView recyclerView;
    private TransAdapter transAdapter;
    private ArrayList<TransModel>transModelArrayList;
    TransModel transModel;
    DataBaseHandler db;
    ImageView previous,next;
    TextView dateselector;
    String monthYearStr;
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar;
    String currentmonth;
    int currentyear;
    String monthpicker;
    long tempincome=0;
    long tempexpense=0;
    long temptotal=0;
    TextView totalincometext,totalexpensetext,total,tcurrentdate;
    String in,exp,currentdate;
    String selected_month;
    String startmonth;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_transaction, container, false);
        db=new DataBaseHandler(getContext());
        previous=view.findViewById(R.id.previous);
        next=view.findViewById(R.id.next);
        totalincometext=view.findViewById(R.id.ttotalincometext);
        totalexpensetext=view.findViewById(R.id.ttotalexpensevalue);
        tcurrentdate=view.findViewById(R.id.tcurrentdate);
        total=view.findViewById(R.id.ttotalAmount);
        DashBoardActivity.add.setVisibility(View.VISIBLE);
        dateselector=view.findViewById(R.id.dateselector);
        calendar=Calendar.getInstance();
        monthpicker=new SimpleDateFormat("MMMM yyyy").format(calendar.getTime());
        currentmonth=new SimpleDateFormat("MMMM").format(calendar.getTime());
        currentyear= Integer.parseInt(new SimpleDateFormat("YYYY").format(calendar.getTime()));
        currentdate=new SimpleDateFormat("MMMM dd, YYYY").format(calendar.getTime());

        dateselector.setText(monthpicker);

        tcurrentdate.setText(currentdate);
        selected_month=dateselector.getText().toString();
        dateselector.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                transModelArrayList=new ArrayList<>();
                transModelArrayList=db.getCurrentMonthList(s.toString());
                transModel=new TransModel();


                long tempincome=0;
                long tempexpense=0;
                long temptotal=0;

                for(int j=0;j<transModelArrayList.size();j++){

                    transModel=transModelArrayList.get(j);
                    if(transModel.getType().equals("income")){

                        in=transModel.getAmount();
                        if(in.equals("")){

                        }else{
                            tempincome +=Long.parseLong(in);
                        }

                    }else if(transModel.getType().equals("expense")){
                        exp=transModel.getAmount();

                        if(exp.equals("")){

                        }else {
                            tempexpense +=Long.parseLong(exp);
                        }
                    }else {
                        Toast.makeText(getContext(), "No Values", Toast.LENGTH_SHORT).show();
                    }

                }
                String dbdate1;
                dbdate1=transModel.getDate();
               /* String new_dbdate=new SimpleDateFormat("MMMM dd, YYYY").format(dbdate1);*/
                tcurrentdate.setText(dbdate1);
                temptotal=tempincome-tempexpense;
                totalincometext.setText(String.valueOf(tempincome));
                totalexpensetext.setText(String.valueOf(tempexpense));
                total.setText(String.valueOf(temptotal));
                transModelArrayList.clear();
                transModelArrayList=new ArrayList<>();
                transModel=new TransModel();
                transModelArrayList=db.getCurrentMonthList(s.toString());
                if(transModelArrayList.size()!=0) {

                    transAdapter = new TransAdapter(getContext(), transModelArrayList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(transAdapter);
                }else{

                    transModelArrayList.clear();
                    transAdapter=new TransAdapter(getContext(),transModelArrayList);
                    RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(transAdapter);

                    Toast.makeText(getContext(), "No Data Available for this month", Toast.LENGTH_SHORT).show();

                }




            }


            @Override
            public void afterTextChanged(Editable s) {


            }
        });



        dateselector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogClass pickerDialog = new DialogClass();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        dateselector.setText(formatMonthYear(monthYearStr));


                    }
                });
                pickerDialog.show(getChildFragmentManager(), "MonthYearPickerDialog");

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String montval=dateselector.getText().toString();

/*

              //
                // dateselector.setText(getpreviousmonth());

                Calendar calendar=Calendar.getInstance();
                calendar.add(Calendar.MONTH,-1);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMMM yyyy");

               String mon=simpleDateFormat.format(calendar.getTime());
               dateselector.setText(mon);
*/



                //calendar.add(Calendar.MONTH);
               // SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
//        Date date = calendar.getTimeInMillis();
              //  String end = format.format(monthpicker);
               // dateselector.setText(monthpicker);
                DialogClass pickerDialog = new DialogClass();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        dateselector.setText(formatMonthYear(monthYearStr));


                    }
                });
                pickerDialog.show(getChildFragmentManager(), "MonthYearPickerDialog");

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Calendar calendar=Calendar.getInstance();
                calendar.add(Calendar.MONTH,+1);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMMM yyyy");

                String mon=simpleDateFormat.format(calendar.getTime());
                dateselector.setText(mon);
*/
              /*  Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(calendar.getTimeInMillis());
                calendar.add(Calendar.MONTH, 0);
                SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
//        Date date = calendar.getTimeInMillis();
                String end = format.format(calendar.getTime());
                dateselector.setText(end);*/

                DialogClass pickerDialog = new DialogClass();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        dateselector.setText(formatMonthYear(monthYearStr));


                    }
                });
                pickerDialog.show(getChildFragmentManager(), "MonthYearPickerDialog");

            }
        });

        recyclerView=view.findViewById(R.id.trecyclerview);
        transModelArrayList=new ArrayList<>();
        transModelArrayList=db.getCurrentMonthList(selected_month);
        transModel=new TransModel();

        for(int j=0;j<transModelArrayList.size();j++){
            transModel=transModelArrayList.get(j);
            if(transModel.getType().equals("income")){

                in=transModel.getAmount();
                if(in.equals("")){

                }else{
                    tempincome +=Long.parseLong(in);
                }

            }else if(transModel.getType().equals("expense")){
                exp=transModel.getAmount();

                if(exp.equals("")){

                }else {
                    tempexpense +=Long.parseLong(exp);
                }
            }else {
                Toast.makeText(getContext(), "No Values", Toast.LENGTH_SHORT).show();
            }

        }
        temptotal=tempincome-tempexpense;
        totalincometext.setText(String.valueOf(tempincome));
        totalexpensetext.setText(String.valueOf(tempexpense));
        total.setText(String.valueOf(temptotal));

        transModelArrayList=new ArrayList<>();
        transModelArrayList=db.getCurrentMonthList(selected_month);

        if(transModelArrayList.size()!=0) {
            transAdapter = new TransAdapter(getContext(), transModelArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(transAdapter);
        }else {

            transModelArrayList.clear();
            transAdapter=new TransAdapter(getContext(),transModelArrayList);
            RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(transAdapter);
            Toast.makeText(getContext(), "No Values", Toast.LENGTH_SHORT).show();

        }


      /*  for(int i=0;i<transModelArrayList.size();i++){
            transModel=transModelArrayList.get(i);
            String dbmonth=transModel.getMonth();
            int dbyear=transModel.getYear();
            String comparemonth=dbmonth+" "+dbyear;
            if((comparemonth).equals(selected_month)){
                transModelArrayList=new ArrayList<>();
                transModelArrayList=db.getCurrentMonthList(selected_month);
                transAdapter=new TransAdapter(getContext(),transModelArrayList);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(transAdapter);
            }else {

                Toast.makeText(getContext(), "No Data Available for this month", Toast.LENGTH_SHORT).show();
            }
        }
*/




        return view;
    }
    String formatMonthYear(String str) {
        Date date = null;
        try {
            date = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    private void select(int start) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, start);
        SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
//        Date date = calendar.getTimeInMillis();
        String end = format.format(calendar.getTime());
        Log.d("TransactionFragment", "selected30Dates: " + end );


    }

    private String getnextmonth() {
        StringBuilder builder=new StringBuilder();
        builder.append("Previous Month: ");
        builder.append(Calendar.MONTH+1);

        return builder.toString();
    }

    private String getpreviousmonth() {
        StringBuilder builder=new StringBuilder();
        builder.append("Next Month : ");
        builder.append(Calendar.MONTH-1);

        return builder.toString();
    }


    @Override
    public void onResume() {
        super.onResume();
        transModelArrayList=new ArrayList<>();
        transModel=new TransModel();
        transModelArrayList=db.getCurrentMonthList(monthpicker);
        if(transModelArrayList.size()!=0) {

            transAdapter = new TransAdapter(getContext(), transModelArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(transAdapter);
        }else{

            transModelArrayList.clear();
            transAdapter=new TransAdapter(getContext(),transModelArrayList);
            RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(transAdapter);

            Toast.makeText(getContext(), "No Data Available for this month", Toast.LENGTH_SHORT).show();

        }


    }
}
