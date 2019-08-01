package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eron.android.expenseapp.Model.Acc_Model;
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class AccountSpinnerAdapter extends BaseAdapter{

    private ArrayList<Acc_Model>acc_modelArrayList;
    private Context context;
    LayoutInflater layoutInflater;

    public AccountSpinnerAdapter(ArrayList<Acc_Model> acc_modelArrayList, Context context) {
        this.acc_modelArrayList = acc_modelArrayList;
        this.context = context;
        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return acc_modelArrayList.size();
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
        fontTextView.setText(acc_modelArrayList.get(position).getImageid());
        textView.setText(acc_modelArrayList.get(position).getIn_acc_type());


        return convertView;
    }
}
