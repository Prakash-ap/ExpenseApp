package com.eron.android.expenseapp.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TransactionFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private static TransactionFragment instance = null;

    private RecyclerView recyclerView;
    private TransAdapter transAdapter;
    private ArrayList<TransModel> transModelArrayList;
    TransModel transModel;
    DataBaseHandler db;
    ImageView previous, next;
    TextView dateselector;
    String monthYearStr;
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MMMM-dd");
    Calendar calendar;
    String currentmonth;
    int currentyear;
    String monthpicker;
    long tempincome = 0;
    long tempexpense = 0;
    long temptotal = 0;
    TextView totalincometext, totalexpensetext, total, tcurrentdate;
    String in, exp, currentdate;
    String selected_month;
    String startmonth;
    NumberFormat formatter;
    String moneyString,moneyString1;
    String totalamount;
    String totalincome,totalexpense;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    public static TransactionFragment getInstance() {
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        db = new DataBaseHandler(getContext());
        previous = view.findViewById(R.id.previous);
        next = view.findViewById(R.id.next);
        recyclerView=view.findViewById(R.id.trecyclerview);
        totalincometext = view.findViewById(R.id.ttotalincometext);
        totalexpensetext = view.findViewById(R.id.ttotalexpensevalue);
        tcurrentdate = view.findViewById(R.id.tcurrentdate);
        total = view.findViewById(R.id.ttotalAmount);
        DashBoardActivity.add.setVisibility(View.VISIBLE);
        dateselector = view.findViewById(R.id.dateselector);
        calendar = Calendar.getInstance();
        monthpicker = new SimpleDateFormat("MMMM yyyy").format(calendar.getTime());
        currentmonth = new SimpleDateFormat("MMMM").format(calendar.getTime());
        currentyear = Integer.parseInt(new SimpleDateFormat("YYYY").format(calendar.getTime()));
        currentdate = new SimpleDateFormat("MMMM dd, YYYY").format(calendar.getTime());
        formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        Date date = calendar.getTime();


        dateselector.setText(monthpicker);

        tcurrentdate.setText(currentdate);
        selected_month = dateselector.getText().toString();
        dateselector.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                transModelArrayList = new ArrayList<>();
                transModelArrayList = db.getCurrentMonthList(s.toString());
                transModel = new TransModel();


                long tempincome = 0;
                long tempexpense = 0;
                long temptotal = 0;

                for (int j = 0; j < transModelArrayList.size(); j++) {

                    transModel = transModelArrayList.get(j);
                    if (transModel.getType().equals("income")) {

                        in = transModel.getAmount();
                        if (in.equals("")) {

                        } else {
                            tempincome += Long.parseLong(in);
                        }

                    } else if (transModel.getType().equals("expense")) {
                        exp = transModel.getAmount();

                        if (exp.equals("")) {

                        } else {
                            tempexpense += Long.parseLong(exp);
                        }
                    } else {
                        Toast.makeText(getContext(), "No Values", Toast.LENGTH_SHORT).show();
                    }

                }
                String dbdate1;
                dbdate1 = transModel.getDate();
                /* String new_dbdate=new SimpleDateFormat("MMMM dd, YYYY").format(dbdate1);*/
                tcurrentdate.setText(dbdate1);
                temptotal = tempincome - tempexpense;
                totalamount=formatter.format(temptotal);
                totalincome=formatter.format(tempincome);
                totalexpense=formatter.format(tempexpense);

                totalincometext.setText(String.valueOf("+"+totalincome));
                totalexpensetext.setText(String.valueOf("-"+totalexpense));
                total.setText(String.valueOf(totalamount));
                transModelArrayList.clear();
                transModelArrayList = new ArrayList<>();
                transModel = new TransModel();
                transModelArrayList = db.getCurrentMonthList(s.toString());
                if (transModelArrayList.size() != 0) {

                    transAdapter = new TransAdapter(getContext(), transModelArrayList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(transAdapter);
                } else {

                    transModelArrayList.clear();
                    transAdapter = new TransAdapter(getContext(), transModelArrayList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
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

                datepicker().show();


               /* DialogClass pickerDialog = new DialogClass();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        dateselector.setText(formatMonthYear(monthYearStr));


                    }
                });
                pickerDialog.show(getChildFragmentManager(), "MonthYearPickerDialog");*/

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String montval = dateselector.getText().toString();

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

                datepicker().show();

/*
                DialogClass pickerDialog = new DialogClass();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        dateselector.setText(formatMonthYear(monthYearStr));


                    }
                });
                pickerDialog.show(getChildFragmentManager(), "MonthYearPickerDialog");*/

            }
        });



        return view;
    }
    String formatMonthYear(String str) {


        // Date date = null;
        Date d1 = null;
        try {


            d1 = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(d1);
    }

    public Dialog datepicker(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT,this,year, month, day){
            @Override
            protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                int day = getContext().getResources().getIdentifier("android:id/day", null, null);
                if(day != 0){
                    View dayPicker = findViewById(day);
                    if(dayPicker != null){
                        //Set Day view visibility Off/Gone
                        dayPicker.setVisibility(View.GONE);
                    }
                }
            }
        };


      //  DatePicker dp = dpd.getDatePicker();

        /*dp.setMinDate(c.getTimeInMillis());
        dp.setMinDate(c.getTimeInMillis());
        dp.setMaxDate(c.getTimeInMillis());*/

        return dpd;
    }



    private void select(int start) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, start);
        SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
//        Date date = calendar.getTimeInMillis();
        String end = format.format(calendar.getTime());
        Log.d("TransactionFragment", "selected30Dates: " + end);


    }

    private String getnextmonth() {
        StringBuilder builder = new StringBuilder();
        builder.append("Previous Month: ");
        builder.append(Calendar.MONTH + 1);

        return builder.toString();
    }

    private String getpreviousmonth() {
        StringBuilder builder = new StringBuilder();
        builder.append("Next Month : ");
        builder.append(Calendar.MONTH - 1);

        return builder.toString();
    }


    @Override
    public void onResume() {
        super.onResume();
        long tempincome = 0;
        long tempexpense = 0;
        transModelArrayList = new ArrayList<>();
        db = new DataBaseHandler(getContext());
        transModelArrayList = db.getCurrentMonthList(selected_month);
        transModel = new TransModel();

        for (int j = 0; j < transModelArrayList.size(); j++) {
            transModel = transModelArrayList.get(j);
            if (transModel.getType().equals("income")) {

                in = transModel.getAmount();
                if (in.equals("")) {

                } else {
                    tempincome += Long.parseLong(in);
                }

            } else if (transModel.getType().equals("expense")) {
                exp = transModel.getAmount();

                if (exp.equals("")) {

                } else {
                    tempexpense += Long.parseLong(exp);
                }
            } else {
                Toast.makeText(getContext(), "No Values", Toast.LENGTH_SHORT).show();
            }

        }
        temptotal = tempincome - tempexpense;
        totalamount=formatter.format(temptotal);
        totalincome=formatter.format(tempincome);
        totalexpense=formatter.format(tempexpense);

        totalincometext.setText(String.valueOf("+"+totalincome));
        totalexpensetext.setText(String.valueOf("-"+totalexpense));
        total.setText(String.valueOf(totalamount));
        transModelArrayList = new ArrayList<>();
        transModel = new TransModel();
        transModelArrayList = db.getCurrentMonthList(monthpicker);


            transAdapter = new TransAdapter(getContext(), transModelArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(transAdapter);


    }


    public void reload() {
        long tempincome = 0;
        long tempexpense = 0;
        transModelArrayList = new ArrayList<>();
        db = new DataBaseHandler(getContext());
        transModelArrayList = db.getCurrentMonthList(selected_month);
        transModel = new TransModel();

        for (int j = 0; j < transModelArrayList.size(); j++) {
            transModel = transModelArrayList.get(j);
            if (transModel.getType().equals("income")) {

                in = transModel.getAmount();
                if (in.equals("")) {

                } else {
                    tempincome += Long.parseLong(in);
                }

            } else if (transModel.getType().equals("expense")) {
                exp = transModel.getAmount();

                if (exp.equals("")) {

                } else {
                    tempexpense += Long.parseLong(exp);
                }
            } else {
                Toast.makeText(getContext(), "No Values", Toast.LENGTH_SHORT).show();
            }

        }
        temptotal = tempincome - tempexpense;
        totalamount=formatter.format(temptotal);
        totalincome=formatter.format(tempincome);
        totalexpense=formatter.format(tempexpense);

        totalincometext.setText(String.valueOf("+"+totalincome));
        totalexpensetext.setText(String.valueOf("-"+totalexpense));
        total.setText(String.valueOf(totalamount));

        transModelArrayList = new ArrayList<>();
        transModelArrayList = db.getCurrentMonthList(selected_month);

        if (transModelArrayList.size() != 0) {
            transAdapter = new TransAdapter(getContext(), transModelArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(transAdapter);
        } else {

            transModelArrayList.clear();
            transAdapter = new TransAdapter(getContext(), transModelArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(transAdapter);
            Toast.makeText(getContext(), "No Values", Toast.LENGTH_SHORT).show();

        }

        //  SpendingFragment.getInstance().reload();

        //SpendingFragment

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
           // reload();

            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("IsRefresh", "Yes");
        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        String dateFormatSymbols;
        dateFormatSymbols=new DateFormatSymbols().getMonths()[month];
        String selecteddate=dateFormatSymbols+" "+year;

        dateselector.setText(selecteddate);



    }
}
