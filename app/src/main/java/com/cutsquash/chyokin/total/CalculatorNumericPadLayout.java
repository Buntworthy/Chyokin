package com.cutsquash.chyokin.total;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.view.View;

import com.cutsquash.chyokin.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
public class CalculatorNumericPadLayout extends CalculatorPadLayout {
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
    public void onFinishInflate() {
        super.onFinishInflate();
        for (int childIndex = getChildCount() - 1; childIndex >= 0; --childIndex) {
            final View v = getChildAt(childIndex);
            if (v instanceof Button) {
                final Button b = (Button) v;
                switch (b.getId()) {
                    case R.id.digit_0:
                        b.setText(Integer.toString(0));
                        break;
                    case R.id.digit_1:
                        b.setText(Integer.toString(1));
                        break;
                    case R.id.digit_2:
                        b.setText(Integer.toString(2));
                        break;
                    case R.id.digit_3:
                        b.setText(Integer.toString(3));
                        break;
                    case R.id.digit_4:
                        b.setText(Integer.toString(4));
                        break;
                    case R.id.digit_5:
                        b.setText(Integer.toString(5));
                        break;
                    case R.id.digit_6:
                        b.setText(Integer.toString(6));
                        break;
                    case R.id.digit_7:
                        b.setText(Integer.toString(7));
                        break;
                    case R.id.digit_8:
                        b.setText(Integer.toString(8));
                        break;
                    case R.id.digit_9:
                        b.setText(Integer.toString(9));
                        break;
                    case R.id.digit_00:
                        b.setText("00");
                        break;
                }
            }
        }
    }
}

