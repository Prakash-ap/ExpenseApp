package com.eron.android.expenseapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
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
import com.eron.android.expenseapp.Fragments.IncomecatFragment;
import com.eron.android.expenseapp.Fragments.SpendingFragment;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;
import java.util.EventListener;

import info.androidhive.fontawesome.FontTextView;

public class Add_New_IncomeCatAdapter extends Adapter<Add_New_IncomeCatAdapter.MyViewHolder> {

    ArrayList<CatItemData>catItemDataArrayList;
    private Context context;
    DataBaseHandler db;
    CatItemData catItemData;
    int IconsPosition=0;
    Fragment fragment;

    AlertDialog.Builder builder;

    public Add_New_IncomeCatAdapter(ArrayList<CatItemData> catItemDataArrayList, Context context, Fragment fragment) {
        this.catItemDataArrayList = catItemDataArrayList;
        this.context = context;
        this.fragment=fragment;


    }

    @NonNull
    @Override
    public Add_New_IncomeCatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.child_add_new_cat_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Add_New_IncomeCatAdapter.MyViewHolder myViewHolder, final int i) {

        builder=new AlertDialog.Builder(context);
        catItemData=catItemDataArrayList.get(i);
        myViewHolder.catname.setText(catItemData.getText());
        myViewHolder.image.setText(catItemData.getImageId());



        myViewHolder.catdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewHolder.catdel.setVisibility(View.GONE);
                myViewHolder.catedt.setVisibility(View.GONE);
                builder.setMessage("Are you sure,You want to delete it?");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        db.deleteIncomeCatEntry(String.valueOf(catItemData.getId()));
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
                        R.string.Travel,R.string.Wifi,R.string.food,R.string.Shopping,R.string.gift,R.string.Birthday,R.string.Ambulance,R.string.Barchart,R.string.Barcode,
                        R.string.mobile,R.string.lap,R.string.book,R.string.coffee,R.string.desktop,R.string.dollar,R.string.Drinks,R.string.Drinks,R.string.fa_address_card_solid,R.string.fa_air_freshener_solid,R.string.fa_baby_carriage_solid,R.string.fa_baseball_ball_solid,R.string.fa_camera_solid,R.string.fa_broom_solid,R.string.fa_church_solid,R.string.fa_coins_solid,
                        R.string.fa_file_image_solid};


                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_addnew_income_category);
                dialog.setTitle("Edit");
                final EditText catname=dialog.findViewById(R.id.edt_namecat);
                final FontTextView catimg=dialog.findViewById(R.id.icon_cat);

                Button ok=dialog.findViewById(R.id.catincomeok);
                final Button cancel=dialog.findViewById(R.id.catcancel);

                catname.setText(catItemDataArrayList.get(i).getText());
                catimg.setText(catItemDataArrayList.get(i).getImageId());
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
                        catItemData.setId(catItemDataArrayList.get(i).getId());
                        catItemData.setText(nam);
                        if(IconsPosition!=0) {
                            catItemData.setImageId(iconList[IconsPosition]);
                        }else{
                            catItemData.setImageId(catItemDataArrayList.get(i).getImageId());
                        }
                        catItemData.setType("income");

                        db.updateIncomeCatEntry(catItemData);

                        ((IncomecatFragment)fragment).updateList();
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
        return catItemDataArrayList.size();
    }


    public void delete(int position){
        catItemDataArrayList.remove(position);
        notifyItemRemoved(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        FontTextView image;
        TextView catname;
        ImageView catedt, catdel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            db = new DataBaseHandler(context);
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
