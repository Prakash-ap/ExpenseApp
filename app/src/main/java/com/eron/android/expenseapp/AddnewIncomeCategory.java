package com.eron.android.expenseapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.IconAdapter;
import com.eron.android.expenseapp.Fragments.CategoryFragment;
import com.maltaisn.icondialog.Icon;
import com.maltaisn.icondialog.IconDialog;
import com.maltaisn.icondialog.IconFilter;
import com.maltaisn.icondialog.IconView;

import java.util.Arrays;
import java.util.Comparator;

import info.androidhive.fontawesome.FontDrawable;

public class AddnewIncomeCategory extends Activity {

    EditText name;
    TextView icon;
    Button ok,cancel;
    String nam;
    String img;
    private int[] selectedIconIds;
    private int disabledCatg;

    private int[] iconList = {
            R.string.icon_eye,R.string.fa_eye_dropper_solid,R.string.fa_eye,R.string.fa_address_book,
    R.string.Account,R.string.Bank,R.string.card,R.string.cash,R.string.Education,R.string.Entertainment,
    R.string.Transport,R.string.Travel,R.string.Wifi,R.string.food,R.string.Shopping,R.string.gift};
           /* android.R.drawable.ic_media_play, android.R.drawable.ic_media_pause,
            android.R.drawable.ic_delete, android.R.drawable.ic_btn_speak_now,
            android.R.drawable.ic_media_previous, android.R.drawable.ic_media_next,
            android.R.drawable.ic_menu_my_calendar, android.R.drawable.ic_menu_agenda,
            android.R.drawable.ic_media_play, android.R.drawable.ic_media_pause,
            android.R.drawable.ic_delete, android.R.drawable.ic_btn_speak_now,
            android.R.drawable.ic_media_previous, android.R.drawable.ic_media_next,
            android.R.drawable.ic_menu_my_calendar, android.R.drawable.ic_menu_agenda,
            android.R.drawable.ic_media_play, android.R.drawable.ic_media_pause,
            android.R.drawable.ic_delete, android.R.drawable.ic_btn_speak_now,
            android.R.drawable.ic_media_previous, android.R.drawable.ic_media_next,
            android.R.drawable.ic_menu_my_calendar, android.R.drawable.ic_menu_agenda,
            android.R.drawable.ic_menu_gallery,R.drawable.next
*/




    private Icon[] selectedIcons;
    private IconAdapter iconAdapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew_income_category);
        name=findViewById(R.id.edt_namecat);
        icon=findViewById(R.id.icon_cat);
        ok=findViewById(R.id.catincomeok);
        cancel=findViewById(R.id.catcancel);
        context=getApplicationContext();

        FontDrawable drawable=new FontDrawable(this,R.string.cash,true,false);
        drawable.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        drawable.setTextSize(50);
       // icon.setText(drawable);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showselectIconDialog(getApplicationContext());

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nam=name.getText().toString();
                img=icon.getText().toString();



            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showselectIconDialog(Context context){
        final GridView gridView = new GridView(context);
        gridView.setAdapter(new IconAdapter(context,iconList));
        gridView.setNumColumns(4);
        gridView.setGravity(Gravity.CENTER);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            //   icon.setImageIcon(iconList.length);

                icon.setText(iconList[position]);





                // TODO: Implement
                Toast.makeText(view.getContext(), "Clicked position is: " + position, Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(gridView);
        builder.setTitle("Choose Icon");

        builder.setPositiveButton("SELECT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }


}

