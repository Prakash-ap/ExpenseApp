package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eron.android.expenseapp.Adapter.TransAdapter;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;


public class TransactionFragment extends Fragment {

    private RecyclerView recyclerView;
    private TransAdapter transAdapter;
    private ArrayList<TransModel>transModelArrayList;
    TransModel transModel;
    DataBaseHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_transaction, container, false);
        db=new DataBaseHandler(getContext());

        recyclerView=view.findViewById(R.id.trecyclerview);
        transModel=new TransModel();
        transModelArrayList=new ArrayList<>();


        return view;
    }


}
