package com.eron.android.expenseapp.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.EditExpenseActivity;
import com.eron.android.expenseapp.EditIncomeActivity;
import com.eron.android.expenseapp.Fragments.SpendingFragment;
import com.eron.android.expenseapp.Fragments.TransactionFragment;
import com.eron.android.expenseapp.Model.TransModel;
import com.eron.android.expenseapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import info.androidhive.fontawesome.FontTextView;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TransModel>transModelArrayList;
    DataBaseHandler db;
    AlertDialog.Builder builder;
    private Fragment fragment;


    public TransAdapter(Context context, ArrayList<TransModel> transModelArrayList,Fragment fragment) {
        this.context = context;
        this.transModelArrayList = transModelArrayList;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(context).inflate(R.layout.transchild_layout,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        builder=new AlertDialog.Builder(context);
        db=new DataBaseHandler(context);

        final TransModel transModel = transModelArrayList.get(i);

        myViewHolder.categoryname.setText(transModel.getCategory_name());
        myViewHolder.catimg.setText(transModel.getCategory_img());
        myViewHolder.accountname.setText(transModel.getAccount_name());
        myViewHolder.accimg.setText(transModel.getAccount_img());
        myViewHolder.dayofmonth.setText(transModel.getDay_of_month());
        myViewHolder.note.setText(transModel.getNote());
        // myViewHolder.amount.setText(transModel.getAmount());
        myViewHolder.month.setText(transModel.getMonth());


        if (transModel.getType().equals("income")) {
            myViewHolder.amount.setTextColor(Color.BLUE);
            myViewHolder.amount.setText(transModel.getAmount());

        } else if (transModel.getType().equals("expense")) {
            myViewHolder.amount.setTextColor(Color.RED);
            myViewHolder.amount.setText(transModel.getAmount());

        } else {

        }

        myViewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewHolder.del.setVisibility(View.INVISIBLE);
                myViewHolder.edt.setVisibility(View.INVISIBLE);
                builder.setMessage("Are you sure,You want to delete it?");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        db.deleteTransEntry(String.valueOf(transModel.getId()));
                        delete(i);
                       // ((SpendingFragment)fragment).reload();
                       // ((TransactionFragment)fragment).reload();
                      //  db.getAllNewIncome();

                        dialog.dismiss();

                    }

                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

        myViewHolder.edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.del.setVisibility(View.INVISIBLE);
                myViewHolder.edt.setVisibility(View.INVISIBLE);
               if(transModel.getType().equals("income")){
                   Intent intent=new Intent(context, EditIncomeActivity.class);
                   intent.putExtra("KEY_ID", String.valueOf(transModelArrayList.get(i).getId()));
                   intent.putExtra("KEY_DATE",String.valueOf(transModelArrayList.get(i).getDate()));
                   intent.putExtra("KEY_CATNAME",String.valueOf(transModelArrayList.get(i).getCategory_name()));
                   intent.putExtra("KEY_CATIMG",String.valueOf(transModelArrayList.get(i).getCategory_img()));
                   intent.putExtra("KEY_ACCNAME",String.valueOf(transModelArrayList.get(i).getAccount_name()));
                   intent.putExtra("KEY_ACCIMG",String.valueOf(transModelArrayList.get(i).getAccount_img()));
                   intent.putExtra("KEY_AMNT",String.valueOf(transModelArrayList.get(i).getAmount()));
                   intent.putExtra("KEY_NOTE",String.valueOf(transModelArrayList.get(i).getNote()));
                   context.startActivity(intent);

               }else if(transModelArrayList.get(i).getType().equals("expense")){
                   Intent intent=new Intent(context, EditExpenseActivity.class);
                   intent.putExtra("KEY_EID", String.valueOf(transModelArrayList.get(i).getId()));
                   intent.putExtra("KEY_EDATE",String.valueOf(transModelArrayList.get(i).getDate()));
                   intent.putExtra("KEY_ECATNAME",String.valueOf(transModelArrayList.get(i).getCategory_name()));
                   intent.putExtra("KEY_ECATIMG",String.valueOf(transModelArrayList.get(i).getCategory_img()));
                   intent.putExtra("KEY_EACCNAME",String.valueOf(transModelArrayList.get(i).getAccount_name()));
                   intent.putExtra("KEY_EACCIMG",String.valueOf(transModelArrayList.get(i).getAccount_img()));
                   intent.putExtra("KEY_EAMNT",String.valueOf(transModelArrayList.get(i).getAmount()));
                   intent.putExtra("KEY_ENOTE",String.valueOf(transModelArrayList.get(i).getNote()));
                   context.startActivity(intent);

               }








            }
        });
    }


    @Override
    public int getItemCount() {
        return transModelArrayList.size();
    }

    public void delete(int position){
        transModelArrayList.remove(position);
        notifyItemRemoved(position);
    }


public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryname,accountname,dayofmonth,amount,note,month;
        FontTextView catimg;
        FontTextView accimg;
        ImageView edt,del;
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
            edt=itemView.findViewById(R.id.trans_edt);
            del=itemView.findViewById(R.id.trans_delete);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    edt.setVisibility(View.VISIBLE);
                    del.setVisibility(View.VISIBLE);
                    return true;
                }

            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    edt.setVisibility(View.INVISIBLE);
                    del.setVisibility(View.INVISIBLE);

                }
            });

        }
    }
}
