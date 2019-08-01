package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.IconAdapter;
import com.eron.android.expenseapp.Database.DataBaseHandler;
import com.eron.android.expenseapp.Model.CatItemData;
import com.eron.android.expenseapp.R;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    TextView text;
    TabLayout tabLayout;
    ViewPager viewPager;
    DataBaseHandler db;
    CatItemData catItemData;
    ArrayList<CatItemData>catItemDataArrayList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_category, container, false);
        db=new DataBaseHandler(getContext());


        viewPager=view.findViewById(R.id.catviewpager);

        setUpViewPager(viewPager);
        tabLayout=view.findViewById(R.id.cattablayout);


        tabLayout.setupWithViewPager(viewPager);


      /*  viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if(i==0){
                    catItemDataArrayList=new ArrayList<>();
                    catItemDataArrayList=db.getAllCatg();



                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });*/

        return view;
    }

    private void setUpViewPager(ViewPager viewPager) {
      ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager());
      viewPagerAdapter.addFragment(new IncomecatFragment(),"Income");
      viewPagerAdapter.addFragment(new ExpenseCatFragment(),"Expense");
      viewPager.setAdapter(viewPagerAdapter);



    }

    static class ViewPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment>fragmentList=new ArrayList<>();
        private final List<String>fragmenttitle=new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment=null;
            if(i==0){
                fragment=new IncomecatFragment();
            }else if(i==1){
                fragment=new ExpenseCatFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
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
   /* private void showselectIconDialog(Context context){
        GridView gridView = new GridView(context);
        gridView.setAdapter(new IconAdapter(context));
        gridView.setNumColumns(4);
        gridView.setGravity(Gravity.CENTER);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: Implement
                Toast.makeText(view.getContext(), "Clicked position is: " + position, Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(gridView);
        builder.setTitle("Choose Icon");
        builder.show();
    }
*/




}
