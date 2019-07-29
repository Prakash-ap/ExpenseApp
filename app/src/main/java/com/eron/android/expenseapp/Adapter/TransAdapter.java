package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TransModel>transModelArrayList;


    public TransAdapter(Context context, ArrayList<TransModel> transModelArrayList) {
        this.context = context;
        this.transModelArrayList = transModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(context).inflate(R.layout.transchild_layout,viewGroup,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        TransModel transModel=transModelArrayList.get(i);
        myViewHolder.categoryname.setText(transModel.getCategory_name());
//        myViewHolder.catimg.setText(transModel.getCategory_img());
        myViewHolder.accountname.setText(transModel.getAccount_name());
    //    myViewHolder.accimg.setText(transModel.getAccount_img());
        myViewHolder.dayofmonth.setText(transModel.getDay_of_month());
        myViewHolder.note.setText(transModel.getNote());
        myViewHolder.amount.setText(transModel.getAmount());
        myViewHolder.month.setText(transModel.getMonth());



    }

    @Override
    public int getItemCount() {
        return transModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryname,accountname,dayofmonth,amount,note,month;
        FontTextView catimg;
        FontTextView accimg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryname=itemView.findViewById(R.id.child_cat_name);
            catimg=itemView.findViewById(R.id.child_cat_img);
            accountname=itemView.findViewById(R.id.child_type);
            accimg=itemView.findViewById(R.id.child_acc_img);
            dayofmonth=itemView.findViewById(R.id.child_dat);
            month=itemView.findViewById(R.id.child_month);
            amount=itemView.findViewById(R.id.child_amount);
            note=itemView.findViewById(R.id.child_note);

        }
    }
}
