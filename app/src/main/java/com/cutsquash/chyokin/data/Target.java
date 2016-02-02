package com.cutsquash.chyokin.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.cutsquash.chyokin.R;

/**
 * Created by jnp1 on 01/02/2016.
 */
public class Target implements ModelContract.Target {

    public static final String TARGET_KEY = "target";
    public static final int TARGET_DEFAULT = 0;
    // The activity with which the shared preferences are associated
    private Activity mActivity;

    public Target(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public int getTarget() {
        SharedPreferences sharedPref = mActivity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getInt(TARGET_KEY, TARGET_DEFAULT);
    }

    @Override
    public void setTarget(int target) {
        SharedPreferences sharedPref = mActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(TARGET_KEY, target);
        editor.commit();
    }
}
