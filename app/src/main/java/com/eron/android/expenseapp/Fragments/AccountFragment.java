package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.Add_New_AccountAdapter;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;


public class AccountFragment extends Fragment {

    RecyclerView recyclerView;
    Add_New_AccountAdapter add_new_accountAdapter;
    Acc_Model acc_model;
    ArrayList<Acc_Model>acc_modelArrayList;
    DataBaseHandler db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);
        recyclerView=view.findViewById(R.id.account_recyclerview);
        db=new DataBaseHandler(getContext());
        acc_modelArrayList=new ArrayList<>();
        acc_modelArrayList=db.getAllAccType();
        add_new_accountAdapter=new Add_New_AccountAdapter(acc_modelArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(add_new_accountAdapter);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        acc_modelArrayList=new ArrayList<>();
        acc_modelArrayList=db.getAllAccType();
        add_new_accountAdapter=new Add_New_AccountAdapter(acc_modelArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(add_new_accountAdapter);
      //  Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

  /*  @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(), "onStat", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getContext(), "Onstop", Toast.LENGTH_SHORT).show();
    }*/
}
