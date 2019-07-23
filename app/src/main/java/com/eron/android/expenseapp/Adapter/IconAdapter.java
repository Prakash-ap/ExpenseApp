package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.eron.android.expenseapp.R;

public class IconAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private Integer[] iconList;

    public IconAdapter(Context context,Integer[] iconList) {
        layoutInflater = LayoutInflater.from(context);
       this.iconList=iconList;

    }

    @Override
    public int getCount() {
        return iconList.length;
    }

    @Override
    public Object getItem(int position) {
        return iconList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlertDialogViewHolder alertDialogViewHolder;


        if (convertView == null) {


            // This is an alertDialog, therefore it has no root
            convertView = layoutInflater.inflate(R.layout.icon_layout, null);

            DisplayMetrics metrics = convertView.getResources().getDisplayMetrics();
            int screenWidth = metrics.widthPixels;

            convertView.setLayoutParams(new GridView.LayoutParams(screenWidth / 6, screenWidth / 6));
            alertDialogViewHolder = new AlertDialogViewHolder();
            alertDialogViewHolder.icon = convertView.findViewById(R.id.image_choose_icon_entry);
            convertView.setTag(alertDialogViewHolder);
        } else {
            alertDialogViewHolder = (AlertDialogViewHolder) convertView.getTag();
        }
        alertDialogViewHolder.icon.setAdjustViewBounds(true);
        alertDialogViewHolder.icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        alertDialogViewHolder.icon.setPadding(8, 8, 8, 8);
        alertDialogViewHolder.icon.setImageResource(iconList[position]);
        return convertView;
    }

    private class AlertDialogViewHolder {
        ImageView icon;
    }



}
