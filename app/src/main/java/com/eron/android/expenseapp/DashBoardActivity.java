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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.eron.android.expenseapp.Fragments.AccountFragment;
import com.eron.android.expenseapp.Fragments.CategoryFragment;
import com.eron.android.expenseapp.Fragments.SpendingFragment;
import com.eron.android.expenseapp.Fragments.TransactionFragment;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {
    ImageView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        TabLayout tabLayout = findViewById(R.id.maintablayout);
        ViewPager viewPager = findViewById(R.id.mainviewpager);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(DashBoardActivity.this,AddTransaction.class);
                startActivity(in);
            }
        });


    }

    private void setUpViewPager(ViewPager viewPager) {
       ViewAdapter adapter=new ViewAdapter(getSupportFragmentManager());
        adapter.addFragment(new SpendingFragment(), "Spending");
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
}
