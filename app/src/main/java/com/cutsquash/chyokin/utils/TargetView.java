package com.cutsquash.chyokin.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.cutsquash.chyokin.R;

/**
 * Created by Justin on 13/02/2016.
 */
public class TargetView {

    ViewGroup targetView;
    Context context;

    public TargetView(Context context, ViewGroup targetView) {
        this.targetView = targetView;
        this.context = context;
    }

    public void setProgress(float progress) {
        // TODO deal with progress > 1
        int nChildren = targetView.getChildCount();
        int childrenToFill = (int) Math.floor(progress*nChildren);
        for (int iChild = 0; iChild < nChildren; iChild++) {
            View child = targetView.getChildAt(iChild);
            if (iChild < childrenToFill) {
                child.setBackground(
                        ContextCompat.getDrawable(context, R.drawable.target_full));
            } else {
                child.setBackground(
                        ContextCompat.getDrawable(context, R.drawable.target_blank));
            }

        }
    }
}
