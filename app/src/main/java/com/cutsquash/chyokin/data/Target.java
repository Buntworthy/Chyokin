package com.cutsquash.chyokin.data;

import android.content.Context;

/**
 * Created by jnp1 on 01/02/2016.
 */
public class Target implements ModelContract.Target {

    private Context mContext;

    public Target(Context context) {
        this.mContext = context;
    }

    @Override
    public int getTarget() {
        // TODO get the target from shared preferences
        return 100;
    }

    @Override
    public void setTarget(int target) {
        // TODO set the target in shared preferences
    }
}
