package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class Add_New_ExpenseCatAdapter extends RecyclerView.Adapter<Add_New_ExpenseCatAdapter.MyViewHolder> {

    ArrayList<ExpenseItemData>expenseItemDataArrayList;
    private Context context;

    public Add_New_ExpenseCatAdapter(ArrayList<ExpenseItemData> expenseItemDataArrayList, Context context) {
        this.expenseItemDataArrayList = expenseItemDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Add_New_ExpenseCatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.child_add_new_cat_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Add_New_ExpenseCatAdapter.MyViewHolder myViewHolder, int i) {
        ExpenseItemData expenseItemData=expenseItemDataArrayList.get(i);
        myViewHolder.catname.setText(expenseItemData.getText());
        myViewHolder.image.setText(expenseItemData.getImageId());



    }

    @Override
    public int getItemCount() {
        return expenseItemDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextView image;
        TextView catname;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catname=itemView.findViewById(R.id.add_cat_text);
            image=itemView.findViewById(R.id.add_cat_img);

        }
    }
}
