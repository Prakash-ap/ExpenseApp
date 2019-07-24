package com.eron.android.expenseapp.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.eron.android.expenseapp.Adapter.SpinnerExpAdapter;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class TransExpenseFragment extends Fragment {
    Calendar calendar;
    TextView date;
    Spinner category,account;
    EditText amount,notes;
    ArrayList<ExpenseItemData>expenseItemDataArrayList;
    ArrayList<ExpenseItemData>expenseAccItemDataArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trans_expense, container, false);

        calendar= Calendar.getInstance();
        date=view.findViewById(R.id.exedtdate);
        category=view.findViewById(R.id.exspinner);
        account=view.findViewById(R.id.exaccount);
        expenseItemDataArrayList=new ArrayList<>();
        expenseAccItemDataArrayList=new ArrayList<>();

        expenseItemDataArrayList.add(new ExpenseItemData("Food",R.string.food));
        expenseItemDataArrayList.add(new ExpenseItemData("Travel",R.string.Travel));
        expenseItemDataArrayList.add(new ExpenseItemData("Shopping",R.string.Shopping));
        expenseItemDataArrayList.add(new ExpenseItemData("Gift",R.string.gift));
        expenseItemDataArrayList.add(new ExpenseItemData("Holiday",R.string.Holidays));
        expenseItemDataArrayList.add(new ExpenseItemData("Wifi",R.string.Wifi));

        expenseAccItemDataArrayList.add(new ExpenseItemData("Cash",R.string.cash));
        expenseAccItemDataArrayList.add(new ExpenseItemData("Card",R.string.card));
        expenseAccItemDataArrayList.add(new ExpenseItemData("Account",R.string.Account));


        SpinnerExpAdapter adapter=new SpinnerExpAdapter(getContext(),R.id.exp_cat_text,R.layout.child_expenselayout,expenseItemDataArrayList);
        category.setAdapter(adapter);

        SpinnerExpAdapter adapter1=new SpinnerExpAdapter(getContext(),R.id.exp_cat_text,R.layout.child_expenselayout,expenseAccItemDataArrayList);
        account.setAdapter(adapter1);


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



