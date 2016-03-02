package com.cutsquash.chyokin.total;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cutsquash.chyokin.about.OnBoardingActivity;
import com.cutsquash.chyokin.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class TotalActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if we need to start onboarding
        // Get the shared preferences
        SharedPreferences preferences =  getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE);

        // Check if onboarding_complete is false
        if(!preferences.getBoolean(getString(R.string.onBoarding_key),false)) {
            // Start the onboarding Activity
            Intent onboarding = new Intent(this, OnBoardingActivity.class);
            startActivity(onboarding);

            // Close the main Activity
            finish();
            return;
        }

        setContentView(R.layout.activity_total);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Run admob with a delay to prevent slowdown in app startup
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mAdView = (AdView) findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice(getString(R.string.testDevice_id))
                        .build();
                mAdView.loadAd(adRequest);

            }
        }, 2000);

    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        TotalFragment fragment = (TotalFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (fragment.getView().findViewById(R.id.addView).getVisibility() == View.VISIBLE) {
            // If the add view is visible, switch to the total view
            fragment.showTotalView(true);
        } else {
            super.onBackPressed();
        }
    }
}
