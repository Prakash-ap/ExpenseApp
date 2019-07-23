package com.eron.android.expenseapp;

import android.content.Context;
import android.graphics.Typeface;
import android.provider.FontRequest;
import android.support.v4.provider.FontsContractCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class IconTextView  extends android.support.v7.widget.AppCompatTextView {
    private Context context;

    public IconTextView(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

    private void createView() {
        setGravity(Gravity.CENTER);
        //setTypeface(,"Font Awesome 5 Brands-Regular-400.otf", context));
    }
}
