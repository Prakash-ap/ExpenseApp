package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.Add_New_ExpenseCatAdapter;
import com.eron.android.expenseapp.Adapter.Add_New_IncomeCatAdapter;
import com.eron.android.expenseapp.Adapter.IconAdapter;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;


public class ExpenseCatFragment extends Fragment {
    RecyclerView recyclerView;
    Add_New_ExpenseCatAdapter expenseCatAdapter;
    ExpenseItemData expenseItemData;
    ArrayList<ExpenseItemData>expenseItemDataArrayList;
    DataBaseHandler db;
    FloatingActionButton floatingActionButton;
    EditText name;
    TextView icon;
    Button ok,cancel;
    int IconsPosition=0;
    String nam;
    private int[] iconList = {
            R.string.icon_eye,R.string.fa_eye_dropper_solid,R.string.fa_address_book,
            R.string.Account,R.string.Bank,R.string.card,R.string.cash,R.string.Education,R.string.Entertainment,
            R.string.Travel,R.string.Wifi,R.string.food,R.string.Shopping,R.string.gift,R.string.Birthday,R.string.Ambulance,R.string.Barchart,R.string.Barcode,
            R.string.mobile,R.string.lap,R.string.book,R.string.coffee,R.string.desktop,R.string.dollar,R.string.Drinks,R.string.Drinks,R.string.fa_address_card_solid,R.string.fa_air_freshener_solid,R.string.fa_baby_carriage_solid,R.string.fa_baseball_ball_solid,R.string.fa_camera_solid,R.string.fa_broom_solid,R.string.fa_church_solid,R.string.fa_coins_solid,
            R.string.fa_file_image_solid};







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_expense_cat, container, false);
        floatingActionButton =view.findViewById(R.id.fab);

        db=new DataBaseHandler(getContext());
        expenseItemDataArrayList=new ArrayList<>();
        expenseItemDataArrayList=db.getAllExpenseCat();

        recyclerView=view.findViewById(R.id.expensecatrecyclerview);
        expenseCatAdapter=new Add_New_ExpenseCatAdapter(expenseItemDataArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(expenseCatAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.activity_addnew_expense_category, null);
                name = alertLayout.findViewById(R.id.edt_namecat);
                icon = alertLayout.findViewById(R.id.icon_cat);
                ok = alertLayout.findViewById(R.id.catincomeok);
                cancel = alertLayout.findViewById(R.id.catcancel);

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setView(alertLayout);
                final AlertDialog dialog = alert.create();


                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showselectIconDialog(getContext());

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (IconsPosition != 0) {
                            nam = name.getText().toString();
                            // img=icon.getText().toString();
                            expenseItemDataArrayList = new ArrayList<>();
                            expenseItemData = new ExpenseItemData();
                            expenseItemData.setText(nam);
                            expenseItemData.setImageId(iconList[IconsPosition]);
                            expenseItemData.setType("expense");


                            db.addExpCatg(expenseItemData);
                          //  Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                            expenseItemDataArrayList=new ArrayList<>();
                            expenseItemDataArrayList=db.getAllExpenseCat();

                            expenseCatAdapter=new Add_New_ExpenseCatAdapter(expenseItemDataArrayList,getContext());
                            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(expenseCatAdapter);


                            dialog.dismiss();

                        }


                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }


            public void showselectIconDialog(Context context) {
                final GridView gridView = new GridView(context);
                gridView.setAdapter(new IconAdapter(context, iconList));
                gridView.setNumColumns(4);
                gridView.setGravity(Gravity.CENTER);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        //   icon.setImageIcon(iconList.length);
                        IconsPosition = position;
                        icon.setText(iconList[position]);
                        // TODO: Implement
                        Log.d("icon", "onItemClick: " + icon);
                      //  Toast.makeText(view.getContext(), "Clicked position is: " + icon, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        expenseItemDataArrayList=new ArrayList<>();
        expenseItemDataArrayList=db.getAllExpenseCat();

        expenseCatAdapter=new Add_New_ExpenseCatAdapter(expenseItemDataArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(expenseCatAdapter);

    }


}
