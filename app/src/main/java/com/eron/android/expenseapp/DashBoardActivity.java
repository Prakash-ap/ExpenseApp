package com.eron.android.expenseapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
    ImageView add,list;
    TabLayout tabLayout;
    ViewPager viewPager;
    static final String[] Months = new String[] { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        final Spinner spinner = (Spinner) findViewById(R.id.monthspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinnertext,Months);
    adapter.setDropDownViewResource(R.layout.spinnertext);
        spinner.setAdapter(adapter);

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

                            Toast.makeText(DashBoardActivity.this, "logout Clicked", Toast.LENGTH_SHORT).show();


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
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons();
        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(DashBoardActivity.this,AddTransaction.class);
                startActivity(in);
            }
        });



    }

    private void createTabIcons() {
        TextView tabone=(TextView)LayoutInflater.from(this).inflate(R.layout.customtab_layout,null);
        tabone.setText("Dashboard");
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
        tabfourth.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.account,0,0);
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
        Bundle bundle=new Bundle();
        bundle.putString("SpinnerValue",view.toString());
        SpendingFragment spendingFragment=new SpendingFragment();
        spendingFragment.setArguments(bundle);
        Toast.makeText(this, "Value"+bundle, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
