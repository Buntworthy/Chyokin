package com.cutsquash.chyokin.about;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.cutsquash.chyokin.R;
import com.cutsquash.chyokin.total.TotalActivity;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class OnBoardingActivity extends AppIntro2 {

    // Please DO NOT override onCreate. Use init.
    @Override
    public void init(Bundle savedInstanceState) {


        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment
                .newInstance("test", "hello", R.drawable.logo, Color.WHITE));
        addSlide(AppIntroFragment
                .newInstance("test2", "hello again", R.drawable.logo,
                        ContextCompat.getColor(this, R.color.colorPrimarySave)));

        setProgressButtonEnabled(true);

    }


    @Override
    public void onDonePressed() {
        // Get the shared preferences
        SharedPreferences preferences =
                getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE);

        // Set onboarding_complete to true
        preferences.edit()
                .putBoolean(getString(R.string.onBoarding_key), true).apply();

        // Launch the main Activity, called MainActivity
        Intent main = new Intent(this, TotalActivity.class);
        startActivity(main);

        // Close the OnboardingActivity
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when the slide changes.
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

}