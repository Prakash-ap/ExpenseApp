package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class Add_New_ExpenseCatAdapter extends RecyclerView.Adapter<Add_New_ExpenseCatAdapter.MyViewHolder> {

    ArrayList<ExpenseItemData>expenseItemDataArrayList;
    private Context context;
    DataBaseHandler db;

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

    public void delete(int position){
        expenseItemDataArrayList.remove(position);
        notifyItemRemoved(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        FontTextView image;
        TextView catname;
        ImageView catedt,catdel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catname=itemView.findViewById(R.id.add_cat_text);
            image=itemView.findViewById(R.id.add_cat_img);
            catedt=itemView.findViewById(R.id.cat_edt);
            catdel=itemView.findViewById(R.id.cat_delete);

            catedt.setOnClickListener(this);
            catdel.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(v.getId()==R.id.cat_delete){

                delete(getAdapterPosition());
                ExpenseItemData expenseItemData = expenseItemDataArrayList.get(getAdapterPosition() + 1);
                db = new DataBaseHandler(context);
           /* CatItemData catItemData=new CatItemData();
            catItemData.getId();*/
                Integer deletedRows = db.deleteExpenseCatEntry(String.valueOf(expenseItemData.getId()));
                if (deletedRows > 0) {
                    Toast.makeText(context, "Data deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Data  Not deleted", Toast.LENGTH_SHORT).show();


                }

                Log.d("deleted", "onClick: " + db.getAllCatg());

            }

        }
    }
}
