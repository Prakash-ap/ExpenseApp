package com.eron.android.expenseapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Fragments.AccountFragment;
import com.eron.android.expenseapp.Fragments.CategoryFragment;
import com.eron.android.expenseapp.Fragments.SpendingFragment;
import com.eron.android.expenseapp.Fragments.TransactionFragment;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
     public  static ImageView add,list;
    private static final String PREFS = "prefs";
    private static final String PHNO_PREFS = "phoneprefs";
    private static final String PASS_PREFS = "passprefs";
    private static final String CHECK_PREFS = "checkprefs";
    public  static ImageView edit,delete;

    TabLayout tabLayout;
    ViewPager viewPager;
    SharedPreferences sharedPreferences;

    static final String[] Months = new String[] { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    public static String spinnervalue="";
    FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        edit=findViewById(R.id.cat_edt);
        delete=findViewById(R.id.cat_delete);

        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashBoardActivity.this,AddnewIncomeCategory1.class);
                startActivity(intent);
            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.monthspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinnertext,Months);
    adapter.setDropDownViewResource(R.layout.spinnertext);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(DashBoardActivity.this);
        add=findViewById(R.id.add);

        add.setVisibility(View.GONE);

        list=findViewById(R.id.list);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(DashBoardActivity.this,list);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.settings){
                            Toast.makeText(DashBoardActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                        }else if(item.getItemId()==R.id.logout){

                            sharedPreferences=getSharedPreferences(PREFS,0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                            Intent intent=new Intent(DashBoardActivity.this,MainActivity.class);
                            startActivity(intent);

                        //    Toast.makeText(DashBoardActivity.this, "logout Clicked", Toast.LENGTH_SHORT).show();


                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });



        tabLayout = findViewById(R.id.maintablayout);
      viewPager = findViewById(R.id.mainviewpager);
        setUpViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if(i==0){
                    add.setVisibility(View.GONE);
                   /// floatingActionButton.setVisibility(View.GONE);

                }else if(i==1){

                    add.setVisibility(View.VISIBLE);
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in=new Intent(DashBoardActivity.this,AddTransaction.class);
                            startActivity(in);
                          //  floatingActionButton.setVisibility(View.GONE);


                        }
                    });

                }else if(i==2){
                   // floatingActionButton.setVisibility(View.VISIBLE);

                    add.setVisibility(View.GONE);


                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent in=new Intent(DashBoardActivity.this,AddnewIncomeCategory1.class);
                            startActivity(in);
                            Toast.makeText(DashBoardActivity.this, "this is Category Screen", Toast.LENGTH_SHORT).show();


                        }
                    });



                }else if(i==3){
//
                    add.setVisibility(View.VISIBLE);
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent in=new Intent(DashBoardActivity.this,AddnewAccountType.class);
                            startActivity(in);
                            Toast.makeText(DashBoardActivity.this, "this is Category Screen", Toast.LENGTH_SHORT).show();



                        }
                    });
                }

            }

            @Override
            public void onPageSelected(int i) {

                if(i==0){
                    add.setVisibility(View.GONE);
                }else if(i==1){

                }else if(i==2){

                }else{

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons();




    }

    private void createTabIcons() {
        TextView tabone=(TextView)LayoutInflater.from(this).inflate(R.layout.customtab_layout,null);
        tabone.setText("Dashboad");

        tabone.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.dashboard,0,0);
        tabLayout.getTabAt(0).setCustomView(tabone);


        TextView tabtwo=(TextView)LayoutInflater.from(this).inflate(R.layout.customtab_layout,null);
        tabtwo.setText("Transc");
        tabtwo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.transaction,0,0);

        tabLayout.getTabAt(1).setCustomView(tabtwo);

        TextView tabthree=(TextView)LayoutInflater.from(this).inflate(R.layout.customtab_layout,null);
        tabthree.setText("Category");
        tabthree.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.catg,0,0);


        tabLayout.getTabAt(2).setCustomView(tabthree);

        TextView tabfourth=(TextView)LayoutInflater.from(this).inflate(R.layout.customtab_layout,null);
        tabfourth.setText("Account");
        tabfourth.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.accounts,0,0);
        tabLayout.getTabAt(3).setCustomView(tabfourth);



    }

    private void setUpViewPager(ViewPager viewPager) {
       ViewAdapter adapter=new ViewAdapter(getSupportFragmentManager());
        adapter.addFragment(new SpendingFragment(), "Dashboard");

        adapter.addFragment(new TransactionFragment(), "Transc");
        adapter.addFragment(new CategoryFragment(), "Category");
        adapter.addFragment(new AccountFragment(), "Account");
        viewPager.setAdapter(adapter);
    }

    /*public void AddFunction(View view) {

    }*/

    static class ViewAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitle = new ArrayList<>();

        public ViewAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        public void addFragment(Fragment fragment, String title) {

            fragmentList.add(fragment);
            fragmentTitle.add(title);

        }


        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        spinnervalue=parent.getItemAtPosition(position).toString();
        Log.d("Dashbord", "onItemSelected: "+spinnervalue);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
