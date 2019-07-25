package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.TransAdapter;
import com.eron.android.expenseapp.AddTransaction;
import com.eron.android.expenseapp.DashBoardActivity;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SpendingFragment extends Fragment {
    String selectedVAlue;
    Button addtrans;
    DataBaseHandler db;
    RecyclerView recyclerView;
    TransAdapter transAdapter;
    ArrayList<TransModel>transModelArrayList;
    TransModel transModel;
    Calendar calendar;
    Date c;
    String date1;
    SimpleDateFormat simpleDateFormat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_spending1, container, false);
        addtrans=view.findViewById(R.id.addtransbtn);
        db=new DataBaseHandler(getContext());
       c=Calendar.getInstance().getTime();
        String format="MM/dd/YYYY";
        simpleDateFormat=new SimpleDateFormat(format);
        date1= simpleDateFormat.format(c);

        recyclerView=view.findViewById(R.id.srecyclerview);
        transModelArrayList=new ArrayList<>();
        transModelArrayList=db.getAllNewIncome();

        transModel=new TransModel();

        for (int i=0;i<transModelArrayList.size();i++){
            transModel=transModelArrayList.get(i);
            String dbdate=transModel.getDate();
            if(dbdate.equals(date1)){
                transModelArrayList=new ArrayList<>();
                transModelArrayList=db.getTodayNewIncome(date1);

                transAdapter=new TransAdapter(getContext(),transModelArrayList);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(transAdapter);


            }else{
               // transModelArrayList.clear();
                Toast.makeText(getContext(), "No Data Available on Todays date", Toast.LENGTH_SHORT).show();
            }
        }






        addtrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddTransaction.class);
                startActivity(intent);
            }
        });

      selectedVAlue=  DashBoardActivity.spinnervalue;

        Log.d("SpendingFragment", "onCreateView: "+selectedVAlue);

        Bundle bundle = getArguments();
 // selectedVAlue = bundle.getString("SpinnerValue");
            Toast.makeText(getContext(), "slected value"+selectedVAlue, Toast.LENGTH_SHORT).show();
        return view;
    }

}
