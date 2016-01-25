package com.cutsquash.chyokin.utils;

import android.view.View;
import android.view.animation.Animation;

import com.cutsquash.chyokin.R;

/**
 * Created by Justin on 25/01/2016.
 */
public class AnimUtils {

    public interface AnimationCallback {
        void callback();
    }

    public static Animation animationWithCallback(Animation a,
                                                  final AnimationCallback callback) {

        a.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                callback.callback();
            }
        });

        return a;

    }
}
