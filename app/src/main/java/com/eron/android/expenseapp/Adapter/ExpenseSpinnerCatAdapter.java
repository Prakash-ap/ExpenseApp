package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class ExpenseSpinnerCatAdapter extends BaseAdapter{

    private ArrayList<ExpenseItemData>expenseItemDataArrayList;
    private Context context;
    LayoutInflater layoutInflater;

    public ExpenseSpinnerCatAdapter(ArrayList<ExpenseItemData> expenseItemDataArrayList, Context context) {
        this.expenseItemDataArrayList = expenseItemDataArrayList;
        this.context = context;
        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return expenseItemDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=layoutInflater.inflate(R.layout.child_catlayout,null);
        FontTextView fontTextView=(FontTextView)convertView.findViewById(R.id.cat_img);
        TextView textView=(TextView)convertView.findViewById(R.id.cat_text);
        fontTextView.setText(expenseItemDataArrayList.get(position).getImageId());
        textView.setText(expenseItemDataArrayList.get(position).getText());


        return convertView;
    }
}
