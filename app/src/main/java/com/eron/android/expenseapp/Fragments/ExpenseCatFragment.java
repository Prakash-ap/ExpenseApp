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

import com.eron.android.expenseapp.Adapter.Add_New_ExpenseCatAdapter;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;


public class ExpenseCatFragment extends Fragment {
    RecyclerView recyclerView;
    Add_New_ExpenseCatAdapter expenseCatAdapter;
    ExpenseItemData expenseItemData;
    ArrayList<ExpenseItemData>expenseItemDataArrayList;
    DataBaseHandler db;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_expense_cat, container, false);
        db=new DataBaseHandler(getContext());
        expenseItemDataArrayList=new ArrayList<>();
        expenseItemDataArrayList=db.getAllExpenseCat();

        recyclerView=view.findViewById(R.id.expensecatrecyclerview);
        expenseCatAdapter=new Add_New_ExpenseCatAdapter(expenseItemDataArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(expenseCatAdapter);



        return view;
    }

}
