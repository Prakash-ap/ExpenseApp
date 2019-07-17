package com.eron.android.expenseapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.User;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    EditText name,phone_no,password;
    Button register_btn;
    TextView link;
    String sname,sphone,spass,smodel;
    User user;
    ArrayList<User>userArrayList;
    DataBaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name=findViewById(R.id.input_signup_name);
        phone_no=findViewById(R.id.input_signup_phonenumber);
        password=findViewById(R.id.input_password);

        register_btn=findViewById(R.id.btn_signup);
        link=findViewById(R.id.link_login);

        db=new DataBaseHandler(this);


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.clearTable();

                user=new User();
                userArrayList=new ArrayList<>();
                sname=name.getText().toString();
                sphone=phone_no.getText().toString();
                spass=password.getText().toString();
                smodel= Build.MODEL;

                if(sname.equals("")||sphone.equals("")||spass.equals("")){
                    Toast.makeText(SignUpActivity.this, "Enter All the Credentials", Toast.LENGTH_SHORT).show();
                }

                user.setName(sname);
                user.setPhone_no(sphone);
                user.setPassword(spass);
                user.setPhone_model(smodel);



                db.addUserDetails(user);

                Toast.makeText(SignUpActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                name.setText("");
                phone_no.setText("");
                password.setText("");


            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


}
