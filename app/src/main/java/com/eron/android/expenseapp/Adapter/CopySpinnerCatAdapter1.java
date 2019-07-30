package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

import info.androidhive.fontawesome.FontTextView;

public class CopySpinnerCatAdapter1 extends BaseAdapter{

    private ArrayList<CatItemData>catItemDataArrayList;
    private Context context;
    LayoutInflater layoutInflater;

    public CopySpinnerCatAdapter1(ArrayList<CatItemData> catItemDataArrayList, Context context) {
        this.catItemDataArrayList = catItemDataArrayList;
        this.context = context;
        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return catItemDataArrayList.size();
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
        fontTextView.setText(catItemDataArrayList.get(position).getImageId());
        textView.setText(catItemDataArrayList.get(position).getText());


        return convertView;
    }
}
