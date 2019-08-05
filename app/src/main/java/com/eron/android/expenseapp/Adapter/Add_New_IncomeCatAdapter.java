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
   // private EventListener eventListener;
    Fragment fragment;


   /* public Add_New_IncomeCatAdapter(EventListener eventListener) {
        this.eventListener = eventListener;
    }
*/
    public interface EventListener{
        void updateList();

    }
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
    public void onBindViewHolder(@NonNull Add_New_IncomeCatAdapter.MyViewHolder myViewHolder, int i) {
        CatItemData catItemData=catItemDataArrayList.get(i);
        myViewHolder.catname.setText(catItemData.getText());
        myViewHolder.image.setText(catItemData.getImageId());

        }



    @Override
    public int getItemCount() {
        return catItemDataArrayList.size();
    }


    public void delete(int position){
        catItemDataArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void update(int pos){
        catItemDataArrayList.add(pos,catItemData);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

            catdel.setOnClickListener(this);
            catedt.setOnClickListener(this);


        }



        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.cat_delete) {
                delete(getAdapterPosition());
               // CatItemData catItemData = catItemDataArrayList.get(getAdapterPosition() + 1);
                db = new DataBaseHandler(context);
                db.deleteIncomeCatEntry(String.valueOf(catItemData.getId()));
         //   CatItemData catItemData=new CatItemData();
          //  catItemDataArrayList=db.getAllCatg();
          //  catItemData=catItemDataArrayList.get(getPosition());
           // catItemData.getId();*/
          //      Integer deletedRows = db.deleteIncomeCatEntry(String.valueOf(catItemData.getId()));
           //     if (deletedRows > 0) {
           //         Toast.makeText(context, "Data deleted", Toast.LENGTH_SHORT).show();
           //     } else {
           //         Toast.makeText(context, "Data  Not deleted", Toast.LENGTH_SHORT).show();


             //   }

                Log.d("deleted", "onClick: " + db.getAllCatg());

            } else if(v.getId()==R.id.cat_edt){

                final int[] iconList = {
                        R.string.icon_eye,R.string.fa_eye_dropper_solid,R.string.fa_address_book,
                        R.string.Account,R.string.Bank,R.string.card,R.string.cash,R.string.Education,R.string.Entertainment,
                        R.string.Travel,R.string.Wifi,R.string.food,R.string.Shopping,R.string.gift};


                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_addnew_income_category);
                dialog.setTitle("Edit");
                final EditText catname=dialog.findViewById(R.id.edt_namecat);
                final FontTextView catimg=dialog.findViewById(R.id.icon_cat);

                Button ok=dialog.findViewById(R.id.catincomeok);
                 final Button cancel=dialog.findViewById(R.id.catcancel);

                 catname.setText(catItemDataArrayList.get(getAdapterPosition()).getText());
                 catimg.setText(catItemDataArrayList.get(getAdapterPosition()).getImageId());
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



                                //   icon.setImageIcon(iconList.length);
                                IconsPosition =position;
                                catimg.setText(iconList[position]);
                                // TODO: Implement
                               // Log.d("icon", "onItemClick: "+icon);
                             //   Toast.makeText(view.getContext(), "Clicked position is: " + icon, Toast.LENGTH_LONG).show();
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
                        // img=icon.getText().toString();
                        catItemDataArrayList = new ArrayList<>();
                        catItemData = new CatItemData();
                        catItemData=catItemDataArrayList.get(getAdapterPosition());
                        catItemData.setId(catItemData.getId());
                        catItemData.setText(nam);
                        catItemData.setImageId(iconList[IconsPosition]);
                        catItemData.setType("income");

                        //catItemData.setType("income");


                        db.updateIncomeCatEntry(catItemData);

                        ((IncomecatFragment)fragment).updateList();

                        //eventListener.updateList();


                       // db.getAllCatg();
                        /*catItemDataArrayList = new ArrayList<>();
                        catItemData = new CatItemData();
                        catItemDataArrayList=db.getAllCatg();
                        for(int i=0;i<catItemDataArrayList.size();i++){
                            catItemData=catItemDataArrayList.get(i);
                            catItemData.setText(catItemData.getText());
                            catItemData.setImageId(catItemData.getImageId());

                        }
                        catItemData.setText(nam);
                        catItemData.setImageId(iconList[getAdapterPosition()]);*/

                        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
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
        }




    }
}
