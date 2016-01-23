package com.cutsquash.chyokin.total;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;

import com.cutsquash.chyokin.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
public class CalculatorNumericPadLayout extends ViewGroup {
    public CalculatorNumericPadLayout(Context context) {
        this(context, null);
    }
    public CalculatorNumericPadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public CalculatorNumericPadLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void setButtonListener(OnClickListener listener) {
        for (int childIndex = getChildCount() - 1; childIndex >= 0; --childIndex) {
            final View v = getChildAt(childIndex);
            if (v instanceof Button) {
                final Button b = (Button) v;
                b.setOnClickListener(listener);
            }
        }
    }
}

