package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eron.android.expenseapp.Adapter.AddnewIncomeCatgeoryAdapter;
import com.eron.android.expenseapp.AddnewIncomeCategory;
import com.eron.android.expenseapp.Model.NewCategory;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

public class IncomecatFragment extends Fragment {

    RecyclerView recyclerView;
    TextView textView;
    AddnewIncomeCatgeoryAdapter addnewIncomeCatgeoryAdapter;
    private ArrayList<NewCategory>newCategoryArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_incomecat, container, false);

        recyclerView=view.findViewById(R.id.incomecatrecyclerview);
       // textView=view.findViewById(R.id.text);
      /*  textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddnewIncomeCategory.class);
                startActivity(intent);
            }
        });
*/
        NewCategory newCategory=new NewCategory();
        newCategory.setName(newCategory.getName());


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

}
