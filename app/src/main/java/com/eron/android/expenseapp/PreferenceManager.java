package com.eron.android.expenseapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE=0;
    private static final String PREF_NAME="intro_slider";
    private static final String IS_FIRST_TIME_LAUNCH="Is_first_time_launch";

    public PreferenceManager( Context _context) {
       pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
       editor = pref.edit();
        this._context = _context;
    }

    public void setFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimelaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
}
