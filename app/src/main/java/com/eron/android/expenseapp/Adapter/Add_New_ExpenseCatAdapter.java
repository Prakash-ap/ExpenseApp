package com.eron.android.expenseapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Fragments.ExpenseCatFragment;
import com.eron.android.expenseapp.Fragments.IncomecatFragment;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class Add_New_ExpenseCatAdapter extends RecyclerView.Adapter<Add_New_ExpenseCatAdapter.MyViewHolder> {

    ArrayList<ExpenseItemData>expenseItemDataArrayList;
    private Context context;
    DataBaseHandler db;
    int IconsPosition=0;
    Fragment fragment;
    AlertDialog.Builder builder;

    public Add_New_ExpenseCatAdapter(ArrayList<ExpenseItemData> expenseItemDataArrayList, Context context,Fragment fragment) {
        this.expenseItemDataArrayList = expenseItemDataArrayList;
        this.context = context;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public Add_New_ExpenseCatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.child_add_new_cat_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Add_New_ExpenseCatAdapter.MyViewHolder myViewHolder, final int i) {
        db=new DataBaseHandler(context);
        builder=new AlertDialog.Builder(context);
        final ExpenseItemData expenseItemData=expenseItemDataArrayList.get(i);
        myViewHolder.catname.setText(expenseItemData.getText());
        myViewHolder.image.setText(expenseItemData.getImageId());
        myViewHolder.catdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewHolder.catdel.setVisibility(View.GONE);
                myViewHolder.catedt.setVisibility(View.GONE);

                builder.setMessage("Are you sure,You want to delete it?");
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteExpenseCatEntry(String.valueOf(expenseItemData.getId()));
                        delete(i);
                        dialog.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });

        myViewHolder.catedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myViewHolder.catdel.setVisibility(View.GONE);
                myViewHolder.catedt.setVisibility(View.GONE);


                final int[] iconList = {
                        R.string.icon_eye,R.string.fa_eye_dropper_solid,R.string.fa_address_book,
                        R.string.Account,R.string.Bank,R.string.card,R.string.cash,R.string.Education,R.string.Entertainment,
                        R.string.Travel,R.string.Wifi,R.string.food,R.string.Shopping,R.string.gift};


                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_addnew_expense_category);
                dialog.setTitle("Edit");
                final EditText catname=dialog.findViewById(R.id.edt_namecat);
                final FontTextView catimg=dialog.findViewById(R.id.icon_cat);

                Button ok=dialog.findViewById(R.id.catincomeok);
                final Button cancel=dialog.findViewById(R.id.catcancel);

                catname.setText(expenseItemDataArrayList.get(i).getText());
                catimg.setText(expenseItemDataArrayList.get(i).getImageId());
                dialog.show();

                catimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showselectIconDialog(context);

                    }

                    public void showselectIconDialog(Context context) {

                        final GridView gridView = new GridView(context);
                        gridView.setAdapter(new IconAdapter(context,iconList));
                        gridView.setNumColumns(4);
                        gridView.setGravity(Gravity.CENTER);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                IconsPosition =position;
                                catimg.setText(iconList[position]);
                                // TODO: Implement
                            }
                        });

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setView(gridView);
                        builder.setTitle("Choose Icon");

                        builder.setPositiveButton("SELECT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();

                    }

                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nam = catname.getText().toString();
                        expenseItemData.setId(expenseItemDataArrayList.get(i).getId());
                        expenseItemData.setText(nam);
                        if(IconsPosition!=0) {
                            expenseItemData.setImageId(iconList[IconsPosition]);
                        }else{
                            expenseItemData.setImageId(expenseItemDataArrayList.get(i).getImageId());
                        }

                        expenseItemData.setType("expense");

                        db.updateExpenseCatEntry(expenseItemData);

                        ((ExpenseCatFragment)fragment).updateList();
                       /* SpendingFragment spendingFragment=new SpendingFragment();
                        spendingFragment.reload();
*/
                        dialog.dismiss();

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return expenseItemDataArrayList.size();
    }

    public void delete(int position){
        expenseItemDataArrayList.remove(position);
        notifyItemRemoved(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextView image;
        TextView catname;
        ImageView catedt, catdel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catname = itemView.findViewById(R.id.add_cat_text);
            image = itemView.findViewById(R.id.add_cat_img);
            catedt = itemView.findViewById(R.id.cat_edt);
            catdel = itemView.findViewById(R.id.cat_delete);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    catedt.setVisibility(View.VISIBLE);
                    catdel.setVisibility(View.VISIBLE);
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    catedt.setVisibility(View.GONE);
                    catdel.setVisibility(View.GONE);


                }
            });


        }

    }
}
