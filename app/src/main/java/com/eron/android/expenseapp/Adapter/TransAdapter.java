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
        myViewHolder.category.setText(transModel.getCategory());
        myViewHolder.account.setText(transModel.getAccount());
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

        TextView category,account,dayofmonth,amount,note,month;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            category=itemView.findViewById(R.id.child_cat);
            account=itemView.findViewById(R.id.child_type);
            dayofmonth=itemView.findViewById(R.id.child_dat);
            month=itemView.findViewById(R.id.child_month);
            amount=itemView.findViewById(R.id.child_amount);
            note=itemView.findViewById(R.id.child_note);

        }
    }
}
