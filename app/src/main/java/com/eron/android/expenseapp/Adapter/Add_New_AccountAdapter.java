package com.eron.android.expenseapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
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

import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Fragments.AccountFragment;
import com.eron.android.expenseapp.Fragments.IncomecatFragment;
import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class Add_New_AccountAdapter extends RecyclerView.Adapter<Add_New_AccountAdapter.MyViewHolder> {

    ArrayList<Acc_Model>acc_modelArrayList;
    private Context context;
    DataBaseHandler db;
    int IconsPosition=0;
    Fragment fragment;
    AlertDialog.Builder builder;

    public Add_New_AccountAdapter(ArrayList<Acc_Model> acc_modelArrayList, Context context,Fragment fragment) {
        this.acc_modelArrayList = acc_modelArrayList;
        this.context = context;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public Add_New_AccountAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.child_add_new_acc_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Add_New_AccountAdapter.MyViewHolder myViewHolder, final int i) {
        db=new DataBaseHandler(context);
        builder=new AlertDialog.Builder(context);

        final Acc_Model acc_model=acc_modelArrayList.get(i);
        myViewHolder.catname.setText(acc_model.getIn_acc_type());
        myViewHolder.image.setText(acc_model.getImageid());
        myViewHolder.accdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.accedt.setVisibility(View.GONE);
                myViewHolder.accdel.setVisibility(View.GONE);
                builder.setMessage("Are you sure,You want to delete it?");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteAccountEntry(String.valueOf(acc_model.getId()));
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

        myViewHolder.accedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewHolder.accedt.setVisibility(View.GONE);
                myViewHolder.accdel.setVisibility(View.GONE);
                final int[] iconList = {
                      R.string.fa_address_book,
                        R.string.Account,R.string.Bank,R.string.card,R.string.cash,R.string.Education,R.string.Entertainment,
                        R.string.Travel};


                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_addnew_account);
                dialog.setTitle("Edit");
                final EditText catname=dialog.findViewById(R.id.edt_nameacc);
                final FontTextView catimg=dialog.findViewById(R.id.icon_acc);

                Button ok=dialog.findViewById(R.id.accincomeok);
                final Button cancel=dialog.findViewById(R.id.acccancel);

                catname.setText(acc_modelArrayList.get(i).getIn_acc_type());
                catimg.setText(acc_modelArrayList.get(i).getImageid());
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
                        acc_model.setId(acc_modelArrayList.get(i).getId());
                        acc_model.setIn_acc_type(nam);
                        if(IconsPosition!=0) {
                            acc_model.setImageid(iconList[IconsPosition]);
                        }else{
                            acc_model.setImageid(acc_modelArrayList.get(i).getImageid());
                        }


                        db.updateAccountEntry(acc_model);

                        ((AccountFragment)fragment).updateList();
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
        return acc_modelArrayList.size();
    }
    public void delete(int position){
        acc_modelArrayList.remove(position);
        notifyItemRemoved(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextView image;
        TextView catname;
        ImageView accedt,accdel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catname=itemView.findViewById(R.id.add_acc_text);
            image=itemView.findViewById(R.id.add_acc_img);
            accedt=itemView.findViewById(R.id.acccat_edt);
            accdel=itemView.findViewById(R.id.acccat_delete);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    accedt.setVisibility(View.VISIBLE);
                    accdel.setVisibility(View.VISIBLE);
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accedt.setVisibility(View.GONE);
                    accdel.setVisibility(View.GONE);


                }
            });

        }
    }
}
