package com.eron.android.expenseapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.User;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView btn_login;
    EditText phone_number, password;
    CheckBox remember_me;
    ImageView pswdimage;
    TextView mlink;
    DataBaseHandler db;
    String dbphone_no, dbpass;
    String uphone_no, upass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREFS = "prefs";
    private static final String PHNO_PREFS = "phoneprefs";
    private static final String PASS_PREFS = "passprefs";
    private static final String CHECK_PREFS = "checkprefs";
    ArrayList<User> userArrayList;
    String signdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);


        phone_number = findViewById(R.id.input_phonenumber);
        password = findViewById(R.id.input_password);
        remember_me = findViewById(R.id.ch_rememberme);
        pswdimage=findViewById(R.id.eye);


        pswdimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                }
        });


        mlink = findViewById(R.id.link_signup);

        db = new DataBaseHandler(this);


        sharedPreferences = getSharedPreferences(PREFS, 0);
        editor = sharedPreferences.edit();
        btn_login = findViewById(R.id.btn_login);



       String spphone=(sharedPreferences.getString(PHNO_PREFS, ""));
       String sppass=(sharedPreferences.getString(PASS_PREFS, ""));
       Boolean sboolean=(sharedPreferences.getBoolean(CHECK_PREFS,false));

 if(sboolean){
           Intent intent=new Intent(MainActivity.this,DashBoardActivity.class);
           startActivity(intent);
     phone_number.setText(spphone);
     password.setText(sppass);
     remember_me.setChecked(sboolean);
       }
        btn_login.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {


                                             User user = new User();
                                          userArrayList = new ArrayList<>();



                                             uphone_no = phone_number.getText().toString();
                                             upass = password.getText().toString();

                                             if (uphone_no.equals("") || upass.equals("")) {
                                                 Toast.makeText(MainActivity.this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
                                             }

                                             userArrayList = db.getAllUser();
                                             for (int i = 0; i < userArrayList.size(); i++) {

                                                 user = userArrayList.get(i);
                                                 dbphone_no = user.getPhone_no();
                                                 dbpass = user.getPassword();

                                             }
                                             if (dbphone_no==null || dbpass==null) {
                                                 Toast.makeText(MainActivity.this, "No Account is found", Toast.LENGTH_SHORT).show();
                                             }else if (uphone_no.matches(dbphone_no) && upass.matches(dbpass)) {

                                                 Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                                                 startActivity(intent);

                                                 Toast.makeText(MainActivity.this, "Signed In Successfully" + Build.MODEL, Toast.LENGTH_SHORT).show();
                                             } else {
                                                 Toast.makeText(MainActivity.this, "Autherization Error!", Toast.LENGTH_SHORT).show();
                                             }


                                             if (remember_me.isChecked()) {
                                                 editor.putString(PHNO_PREFS, uphone_no);
                                                 editor.putString(PASS_PREFS, upass);
                                                 editor.putBoolean(CHECK_PREFS, true);
                                                 editor.apply();

                                             } else {
                                                 editor.putBoolean(CHECK_PREFS, false);
                                                 editor.remove(PHNO_PREFS);
                                                 editor.remove(PASS_PREFS);
                                                 editor.apply();
                                             }


                                             phone_number.setText("");
                                             password.setText("");

                                         }
                                     });



        mlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent1);

                phone_number.setText("");
                password.setText("");
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}

