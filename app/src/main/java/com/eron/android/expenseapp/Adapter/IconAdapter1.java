package com.eron.android.expenseapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.R;
import com.maltaisn.icondialog.Icon;
import com.maltaisn.icondialog.IconView;
import com.maltaisn.icondialog.Label;
import com.maltaisn.icondialog.LabelValue;

import java.text.MessageFormat;
import java.util.zip.Inflater;

public class IconAdapter1 extends  RecyclerView.Adapter<IconAdapter1.IconViewHolder> {

    private MessageFormat idFmt;
    private MessageFormat infoFmt;
    private Toast infoToast;
    private Context context;
    private Icon[] selectedIcons;

    IconAdapter1() {
        setHasStableIds(true);

       /* idFmt = new MessageFormat(getString(R.string.selected_icon_id_fmt));
        infoFmt = new MessageFormat(getString(R.string.icon_info_fmt));*/
    }

    class IconViewHolder extends RecyclerView.ViewHolder {

        private IconView iconImv;
        private TextView idTxv;

        IconViewHolder(View view) {
            super(view);
            iconImv = view.findViewById(R.id.icon_cat);
           // idTxv = view.findViewById(R.id.txv_id);
        }

        void bindViewHolder(final Icon icon) {
            iconImv.setIcon(icon);
            iconImv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Print detailed info about the icon
                    StringBuilder labelsSb = new StringBuilder();
                    for (Label label : icon.getLabels()) {
                        LabelValue[] aliases = label.getAliases();
                        if (aliases != null) {
                            labelsSb.append('{');
                            for (LabelValue alias : aliases) {
                                labelsSb.append(alias);
                                labelsSb.append(", ");
                            }
                            labelsSb.delete(labelsSb.length() - 2, labelsSb.length());
                            labelsSb.append("}, ");
                        } else if (label.getValue() != null) {
                            labelsSb.append(label.getValue());
                            labelsSb.append(", ");
                        }
                    }
                    labelsSb.delete(labelsSb.length() - 2, labelsSb.length());

                    // ID: 1000
                    // Category: Transport
                    // Labels: Car, Fuel, Vehicle
                    if (infoToast != null) infoToast.cancel();
                   /* infoToast = Toast.makeText(MainActivity.this,
                            infoFmt.format(new Object[]{icon.getId(),
                                    icon.getCategory().getName(MainActivity.this),
                                    labelsSb.toString()}), Toast.LENGTH_LONG);
                    infoToast.show();*/
                }
            });

         //   idTxv.setText(idFmt.format(new Object[]{icon.getId()}));
        }
    }

    @Override
    public @NonNull
    IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(context).inflate(R.layout.activity_addnew_income_category,parent,false);
       return new IconViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final IconViewHolder holder, final int position) {
        holder.bindViewHolder(selectedIcons[position]);
    }

    @Override
    public int getItemCount() {
        return selectedIcons.length;
    }

    @Override
    public long getItemId(int position) {
        return selectedIcons[position].getId();
    }

}



