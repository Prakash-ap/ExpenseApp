package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class Add_New_IncomeCatAdapter extends RecyclerView.Adapter<Add_New_IncomeCatAdapter.MyViewHolder> {

    ArrayList<CatItemData>catItemDataArrayList;
    private Context context;

    public Add_New_IncomeCatAdapter(ArrayList<CatItemData> catItemDataArrayList, Context context) {
        this.catItemDataArrayList = catItemDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Add_New_IncomeCatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.child_add_new_cat_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Add_New_IncomeCatAdapter.MyViewHolder myViewHolder, int i) {
        CatItemData catItemData=catItemDataArrayList.get(i);
        myViewHolder.catname.setText(catItemData.getText());
        myViewHolder.image.setText(catItemData.getImageId());

        }

    @Override
    public int getItemCount() {
        return catItemDataArrayList.size();
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
