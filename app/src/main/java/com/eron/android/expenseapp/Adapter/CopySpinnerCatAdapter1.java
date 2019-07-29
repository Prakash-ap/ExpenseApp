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

public class CopySpinnerCatAdapter1 extends ArrayAdapter<CatItemData>{

    ArrayList<CatItemData>catItemDataArrayList;

    public CopySpinnerCatAdapter1(Context context, ArrayList<CatItemData> catItemDataArrayList) {
        super(context,R.layout.child_catlayout,R.id.cat_text,catItemDataArrayList);
        this.catItemDataArrayList = catItemDataArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, null);
    }

    private View initView(int position,View convertView,ViewGroup parent){
        CatItemData catItemData=getItem(position);
        if(convertView==null){
            if(parent==null){
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.child_catlayout,null);
            }else{
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.child_catlayout,null,false);

            }

            TextView catname=(TextView)convertView.findViewById(R.id.cat_text);
            FontTextView catimg=(FontTextView)convertView.findViewById(R.id.cat_img);
            if(catname!=null){
                catname.setText(catItemData.getText());
            }
            if(catimg !=null){
                catimg.setText(catItemData.getImageId());
            }
        }
        return convertView;
    }
}
