package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.eron.android.expenseapp.AddTransaction;
import com.eron.android.expenseapp.DashBoardActivity;
import com.eron.android.expenseapp.R;

public class SpendingFragment extends Fragment {
    String selectedVAlue;
    Button addtrans;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_spending1, container, false);
        addtrans=view.findViewById(R.id.addtransbtn);
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
