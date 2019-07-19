package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eron.android.expenseapp.R;

public class SpendingFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_spending1, container, false);

        if(getArguments()!=null){
            String param=getArguments().getString("SpinnerValue");
        }
        return view;
    }

}
