package com.eron.android.expenseapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

public class SpinnerCatAdapter extends ArrayAdapter<CatItemData>{

    int groupid;
    Context context;
    ArrayList<CatItemData>catItemDataArrayList;
    LayoutInflater layoutInflater;

    public SpinnerCatAdapter( Context context,int groupid,int id, ArrayList<CatItemData> catItemDataArrayList) {
        super(context,id,catItemDataArrayList);
        this.groupid = groupid;
        this.context = context;
        this.catItemDataArrayList = catItemDataArrayList;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemview=layoutInflater.inflate(R.layout.child_catlayout,parent,false);
        TextView imageView=(TextView) itemview.findViewById(R.id.cat_img);
        imageView.setText(catItemDataArrayList.get(position).getImageId());
        TextView textView=(TextView)itemview.findViewById(R.id.cat_text);
        textView.setText(catItemDataArrayList.get(position).getText());
        return itemview;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}
