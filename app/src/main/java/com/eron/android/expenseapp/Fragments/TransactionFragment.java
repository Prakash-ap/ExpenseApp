package com.eron.android.expenseapp.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_transaction, container, false);
        db=new DataBaseHandler(getContext());
        previous=view.findViewById(R.id.previous);
        next=view.findViewById(R.id.next);
        dateselector=view.findViewById(R.id.dateselector);
        calendar=Calendar.getInstance();
        currentmonth=new SimpleDateFormat("MMMM yyyy").format(calendar.getTime());
        dateselector.setText(currentmonth);

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
        transModel=new TransModel();
        transModelArrayList=new ArrayList<>();
        DashBoardActivity.add.setVisibility(View.VISIBLE);


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
