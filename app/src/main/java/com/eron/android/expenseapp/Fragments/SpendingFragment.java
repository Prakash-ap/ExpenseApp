package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.TransAdapter;
import com.eron.android.expenseapp.AddTransaction;
import com.eron.android.expenseapp.DashBoardActivity;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SpendingFragment extends Fragment {
    private static SpendingFragment instance = null;
    String selectedVAlue;
    Button addtrans;
    DataBaseHandler db;
    public static RecyclerView recyclerView;
    public static TransAdapter transAdapter;
    ArrayList<TransModel> transModelArrayList;
    TransModel transModel;
    Calendar calendar;
    Date c;
    String date1;
    SimpleDateFormat simpleDateFormat;
    long tempincome = 0;
    long tempexpense = 0;
    TextView totalincome, totalexpense;
    String in, exp;
    NumberFormat formatter;
    String moneyString,moneyString1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    public static SpendingFragment getInstance() {
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spending1, container, false);
        addtrans = view.findViewById(R.id.addtransbtn);
        totalincome = view.findViewById(R.id.sincomevalue);
        totalexpense = view.findViewById(R.id.sexpensevalue);

        db = new DataBaseHandler(getContext());
        c = Calendar.getInstance().getTime();
        String format = "MMMM dd, YYYY";
        simpleDateFormat = new SimpleDateFormat(format);
        date1 = simpleDateFormat.format(c);
        formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));


        recyclerView = view.findViewById(R.id.srecyclerview);
        transModelArrayList = new ArrayList<>();
        transModelArrayList = db.getTodayNewList(date1);
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
       // Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        moneyString = formatter.format(tempincome);
        moneyString1 = formatter.format(tempexpense);

        totalincome.setText(moneyString);
        totalincome.setTextColor(Color.WHITE);
        totalexpense.setText(moneyString1);
        totalexpense.setTextColor(Color.WHITE);

        transModelArrayList = new ArrayList<>();
        transModelArrayList = db.getAllNewIncome();
        transModel = new TransModel();

        for (int i = 0; i < transModelArrayList.size(); i++) {
            transModel = transModelArrayList.get(i);
            String dbdate = transModel.getDate();
            if (dbdate.equals(date1)) {
                transModelArrayList = new ArrayList<>();
                transModelArrayList = db.getTodayNewList(date1);

                transAdapter = new TransAdapter(getContext(), transModelArrayList, SpendingFragment.this);


                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(transAdapter);


            } else {
                // transModelArrayList.clear();
                // Toast.makeText(getContext(), "No Data Available on Todays date", Toast.LENGTH_SHORT).show();
            }
        }


        addtrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddTransaction.class);
                startActivity(intent);
            }
        });

        selectedVAlue = DashBoardActivity.spinnervalue;

        Log.d("SpendingFragment", "onCreateView: " + selectedVAlue);


       /* SpendingFragment test = (SpendingFragment) getChildFragmentManager().findFragmentByTag("testID");
        if (test != null && test.isVisible()) {
            //DO STUFF
            reload();
        }
        else {
            //Whatever
        }
        */


        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // onResume();

            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("IsRefresh", "Yes");
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        long tempincome = 0;
        long tempexpense = 0;
        transModelArrayList = new ArrayList<>();
        transModelArrayList = db.getTodayNewList(date1);
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

        moneyString = formatter.format(tempincome);
        moneyString1 = formatter.format(tempexpense);
        totalincome.setText(moneyString);
        totalincome.setTextColor(Color.WHITE);
        totalexpense.setText(moneyString1);
        totalexpense.setTextColor(Color.WHITE);


        //  reload();
    }

    public void reload() {

        ProgressBar progressBar;

       /* c = Calendar.getInstance().getTime();
        String format = "MMMM dd, YYYY";
        simpleDateFormat = new SimpleDateFormat(format);

        db=new DataBaseHandler(getContext());
*/
        //  String date2 = simpleDateFormat.format(c);
        long tempincome = 0;
        long tempexpense = 0;
        //   TextView totalincome, totalexpense;


        transModelArrayList = new ArrayList<>();
        transModel = new TransModel();
        transModelArrayList = db.getTodayNewList(date1);

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
        moneyString = formatter.format(tempincome);
        moneyString1 = formatter.format(tempexpense);
        totalincome.setText(moneyString);
        totalincome.setTextColor(Color.WHITE);
        totalexpense.setText(moneyString1);
        totalexpense.setTextColor(Color.WHITE);

        /*transModelArrayList = new ArrayList<>();
        transModelArrayList = db.getAllNewIncome();
        transModel = new TransModel();

        for (int i = 0; i < transModelArrayList.size(); i++) {
            transModel = transModelArrayList.get(i);
            String dbdate = transModel.getDate();
            if (dbdate.equals(date1)) {*/
                /*transModelArrayList = new ArrayList<>();
                transModelArrayList = db.getTodayNewList(date1);*/

        transAdapter = new TransAdapter(getContext(), transModelArrayList, SpendingFragment.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(transAdapter);


            /*} else {
                // transModelArrayList.clear();
                // Toast.makeText(getContext(), "No Data Available on Todays date", Toast.LENGTH_SHORT).show();
            }*/
    }
}








