package com.eron.android.expenseapp.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar;
    String currentmonth;
    String monthpicker;
    long tempincome=0;
    long tempexpense=0;
    long temptotal=0;
    TextView totalincometext,totalexpensetext,total,tcurrentdate;
    String in,exp,currentdate;




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
        currentdate=new SimpleDateFormat("MMMM dd, YYYY").format(calendar.getTime());

        dateselector.setText(monthpicker);
        tcurrentdate.setText(currentdate);

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
        transModelArrayList=db.getAllNewIncome();
        transModel=new TransModel();

        for(int j=0;j<transModelArrayList.size();j++){
            transModel=transModelArrayList.get(j);
            if(transModel.getType().equals("income")){

                in=transModel.getAmount();
                if(in.equals("")){

                }else{
                    tempincome +=Long.parseLong(in);
                }

            }else if(transModel.getType().equals("expenses")){
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

        for(int i=0;i<transModelArrayList.size();i++){
            transModel=transModelArrayList.get(i);
            String dbmonth=transModel.getMonth();
            if(dbmonth.equals(currentmonth)){
                transModelArrayList=new ArrayList<>();
                transModelArrayList=db.getCurrentMonthList(currentmonth);
                transAdapter=new TransAdapter(getContext(),transModelArrayList);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(transAdapter);
            }else {
                Toast.makeText(getContext(), "No Data Available for this month", Toast.LENGTH_SHORT).show();
            }
        }





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


}
