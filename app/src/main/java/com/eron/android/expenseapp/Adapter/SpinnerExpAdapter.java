package com.eron.android.expenseapp.Adapter;

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
import com.eron.android.expenseapp.Model.ExpenseItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;

public class SpinnerExpAdapter extends ArrayAdapter<ExpenseItemData>{

    int groupid;
    Context context;
    ArrayList<ExpenseItemData>expenseItemDataArrayList;
    LayoutInflater layoutInflater;

    public SpinnerExpAdapter(Context context, int groupid, int id, ArrayList<ExpenseItemData> expenseItemDataArrayList) {
        super(context,id,expenseItemDataArrayList);
        this.groupid = groupid;
        this.context = context;
        this.expenseItemDataArrayList = expenseItemDataArrayList;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemview=layoutInflater.inflate(R.layout.child_expenselayout,parent,false);
        ImageView imageView=(ImageView)itemview.findViewById(R.id.exp_cat_img);
        imageView.setImageResource(expenseItemDataArrayList.get(position).getImageId());
        TextView textView=(TextView)itemview.findViewById(R.id.exp_cat_text);
        textView.setText(expenseItemDataArrayList.get(position).getText());
        return itemview;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}
