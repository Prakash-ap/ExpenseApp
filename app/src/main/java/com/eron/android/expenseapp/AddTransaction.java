package com.eron.android.expenseapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.eron.android.expenseapp.Fragments.TransExpenseFragment;
import com.eron.android.expenseapp.Fragments.TransIncomeFragment;

import java.util.ArrayList;
import java.util.List;

public class AddTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        TabLayout tabLayout=findViewById(R.id.transtablayout);
        ViewPager viewPager=findViewById(R.id.transviewpager);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new TransIncomeFragment(),"Income");
        viewPagerAdapter.addFragment(new TransExpenseFragment(),"Expense");
        viewPager.setAdapter(viewPagerAdapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment>fragmentList=new ArrayList<>();
        private final List<String>fragmenttitle=new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmenttitle.size();
        }

        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            fragmenttitle.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return fragmenttitle.get(position);
        }

    }
}
