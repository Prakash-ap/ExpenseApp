package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class Add_New_AccountAdapter extends RecyclerView.Adapter<Add_New_AccountAdapter.MyViewHolder> {

    ArrayList<Acc_Model>acc_modelArrayList;
    private Context context;

    public Add_New_AccountAdapter(ArrayList<Acc_Model> acc_modelArrayList, Context context) {
        this.acc_modelArrayList = acc_modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Add_New_AccountAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.child_add_new_acc_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Add_New_AccountAdapter.MyViewHolder myViewHolder, int i) {
        Acc_Model acc_model=acc_modelArrayList.get(i);
        myViewHolder.catname.setText(acc_model.getIn_acc_type());
        myViewHolder.image.setText(acc_model.getImageid());



    }

    @Override
    public int getItemCount() {
        return acc_modelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextView image;
        TextView catname;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catname=itemView.findViewById(R.id.add_acc_text);
            image=itemView.findViewById(R.id.add_acc_img);

        }
    }
}
