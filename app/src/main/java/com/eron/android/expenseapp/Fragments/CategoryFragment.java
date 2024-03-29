package com.eron.android.expenseapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eron.android.expenseapp.Adapter.IconAdapter;
import com.eron.android.expenseapp.DashBoardActivity;
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
    public static final String mypreference = "mypref";
    public static final String catType = "typeKey";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DashBoardActivity dashBoardActivity;
    public  static ImageView edit,delete;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_category, container, false);
        db=new DataBaseHandler(getContext());
        sharedPreferences = this.getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        dashBoardActivity=new DashBoardActivity();


        viewPager=view.findViewById(R.id.catviewpager);

        setUpViewPager(viewPager);
        tabLayout=view.findViewById(R.id.cattablayout);


        tabLayout.setupWithViewPager(viewPager);


     /*   viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if(i==0) {
                    editor.putString(catType, "income");
                    editor.commit();

                }else if(i==1){

                    editor.putString(catType, "expense");
                    editor.commit();



                }else{

                }
            }

            @Override
            public void onPageSelected(int i) {
                if(i==0) {
                    editor.putString(catType, "income");
                    editor.commit();

                }else if(i==1){

                    editor.putString(catType, "expense");
                    editor.commit();



                }else{

                }

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
           /* Fragment fragment=null;
            if(i==0){
                fragment=new IncomecatFragment();
            }else if(i==1){
                fragment=new ExpenseCatFragment();
            }

            return fragment;*/
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
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
