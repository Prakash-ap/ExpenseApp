package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.eron.android.expenseapp.Adapter.Add_New_IncomeCatAdapter;
import com.eron.android.expenseapp.Adapter.AddnewIncomeCatgeoryAdapter;
import com.eron.android.expenseapp.Adapter.CopySpinnerCatAdapter1;
import com.eron.android.expenseapp.Adapter.IconAdapter;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.NewCategory;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

public class IncomecatFragment extends Fragment {

    RecyclerView recyclerView;
    EditText name;
    TextView icon;
    Button ok,cancel;
    int IconsPosition=0;
    String nam;
    TextView textView;
    AddnewIncomeCatgeoryAdapter addnewIncomeCatgeoryAdapter;
    private ArrayList<NewCategory>newCategoryArrayList;
    DataBaseHandler db;
    CatItemData catItemData;
    ArrayList<CatItemData>catItemDataArrayList;
    CopySpinnerCatAdapter1 copySpinnerCatAdapter1;
   Add_New_IncomeCatAdapter add_new_incomeCatAdapter;
   FloatingActionButton floatingActionButton;

    private int[] iconList = {
            R.string.icon_eye,R.string.fa_eye_dropper_solid,R.string.fa_eye,R.string.fa_address_book,
            R.string.Account,R.string.Bank,R.string.card,R.string.cash,R.string.Education,R.string.Entertainment,
            R.string.Transport,R.string.Travel,R.string.Wifi,R.string.food,R.string.Shopping,R.string.gift};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_incomecat, container, false);


        recyclerView=view.findViewById(R.id.incomecatrecyclerview);
        floatingActionButton=view.findViewById(R.id.fab);

        db=new DataBaseHandler(getContext());
        catItemDataArrayList=new ArrayList<>();
        catItemDataArrayList=db.getAllCatg();

        add_new_incomeCatAdapter=new Add_New_IncomeCatAdapter(catItemDataArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(add_new_incomeCatAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        LayoutInflater inflater = getLayoutInflater();
                                                        View alertLayout = inflater.inflate(R.layout.activity_addnew_income_category, null);
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
                                                                    catItemDataArrayList = new ArrayList<>();
                                                                    catItemData = new CatItemData();
                                                                    catItemData.setText(nam);
                                                                    catItemData.setImageId(iconList[IconsPosition]);
                                                                    catItemData.setType("income");


                                                                    db.addCatg(catItemData);
                                                                    Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                                                                    catItemDataArrayList=new ArrayList<>();
                                                                    catItemDataArrayList=db.getAllCatg();

                                                                    add_new_incomeCatAdapter=new Add_New_IncomeCatAdapter(catItemDataArrayList,getContext());
                                                                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
                                                                    recyclerView.setLayoutManager(layoutManager);
                                                                    recyclerView.setAdapter(add_new_incomeCatAdapter);


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
                                                                Toast.makeText(view.getContext(), "Clicked position is: " + icon, Toast.LENGTH_LONG).show();
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










       // textView=view.findViewById(R.id.text);
      /*  textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddnewIncomeCategory.class);
                startActivity(intent);
            }
        });
*/



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        catItemDataArrayList=new ArrayList<>();
        catItemDataArrayList=db.getAllCatg();

        add_new_incomeCatAdapter=new Add_New_IncomeCatAdapter(catItemDataArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(add_new_incomeCatAdapter);
    }
}
