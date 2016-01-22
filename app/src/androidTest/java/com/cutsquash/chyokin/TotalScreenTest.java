package com.cutsquash.chyokin;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import com.cutsquash.chyokin.total.TotalActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TotalScreenTest {

    @Rule
    public ActivityTestRule<TotalActivity> mActivityTestRule =
            new ActivityTestRule<>(TotalActivity.class);

    @Test
    public void sayHello() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.valueDisplay)).check(matches(isDisplayed())); //line 3
    }

}