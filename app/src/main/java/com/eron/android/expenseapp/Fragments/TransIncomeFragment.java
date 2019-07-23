package com.eron.android.expenseapp.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.eron.android.expenseapp.Adapter.SpinnerCatAdapter;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class TransIncomeFragment extends Fragment {
    TextView date;
    EditText amount,notes;
    Spinner category,account;
    Calendar calendar;
    ArrayList<CatItemData>catItemDataArrayList;
    ArrayList<CatItemData>accItemDataArrayList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trans_income, container, false);
        calendar=Calendar.getInstance();
        date=view.findViewById(R.id.edtdate);
        category=view.findViewById(R.id.spinnercat);
        account=view.findViewById(R.id.spinneraccount);
        catItemDataArrayList=new ArrayList<>();
        //accItemDataArrayList=new ArrayList<>();

        catItemDataArrayList.add(new CatItemData("Salary","@string/icon_eye"));
        /*catItemDataArrayList.add(new CatItemData("Bonus",R.drawable.bonus));
        catItemDataArrayList.add(new CatItemData("Other",R.drawable.others));

        accItemDataArrayList.add(new CatItemData("Cash",R.drawable.cash));
        accItemDataArrayList.add(new CatItemData("Card",R.drawable.card));
        accItemDataArrayList.add(new CatItemData("Account",R.drawable.account));*/


        SpinnerCatAdapter adapter=new SpinnerCatAdapter(getContext(),R.id.cat_text,R.layout.child_catlayout,catItemDataArrayList);
        category.setAdapter(adapter);

     /*   SpinnerCatAdapter adapter1=new SpinnerCatAdapter(getContext(),R.id.cat_text,R.layout.child_catlayout,accItemDataArrayList);
        account.setAdapter(adapter1);
*/



        final DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),dateSetListener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        return view;


    }

    private void updateLabel() {
        String format="MM/dd/YYYY";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format, Locale.getDefault());
        date.setText(simpleDateFormat.format(calendar.getTime()));
    }


}
