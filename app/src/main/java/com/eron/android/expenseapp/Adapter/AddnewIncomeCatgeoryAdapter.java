package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.eron.android.expenseapp.Model.NewCategory;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

public class AddnewIncomeCatgeoryAdapter extends RecyclerView.Adapter<AddnewIncomeCatgeoryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<NewCategory>newCategoryArrayList;

    public AddnewIncomeCatgeoryAdapter(Context context, ArrayList<NewCategory> newCategoryArrayList) {
        this.context = context;
        this.newCategoryArrayList = newCategoryArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.activity_addnew_income_category,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        NewCategory newCategory=newCategoryArrayList.get(i);
        myViewHolder.name.setText(newCategory.getName());
        myViewHolder.icon.setImageResource(Integer.parseInt(newCategory.getImage()));


    }

    @Override
    public int getItemCount() {
        return newCategoryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText name;
        ImageView icon;
        Button ok,cancel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.edt_namecat);
            icon=itemView.findViewById(R.id.icon_cat);
            ok=itemView.findViewById(R.id.catincomeok);
            cancel=itemView.findViewById(R.id.catcancel);
        }
    }
}
