package com.cutsquash.chyokin;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.cutsquash.chyokin.total.TotalActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TotalScreenTest {

    @Rule
    public ActivityTestRule<TotalActivity> mActivityTestRule =
            new ActivityTestRule<>(TotalActivity.class);

    @Before
    public void setUp() {
        // Delete any existing data
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.action_delete)).perform(click());

    }


    @Test
    public void deleteData_zeroTotal() {

        // Click delete menu
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.action_delete)).perform(click());

        // Check total is zero
        onView(withId(R.id.totalValue)).check(matches(withText("0p")));

    }

    @Test
    public void enterSaving_showValue() {

        // Click add button
        onView(withId(R.id.button_add)).perform(click());

        // Check add view is displayed
        onView(withId(R.id.valueDisplay)).check(matches(isDisplayed()));

        // Enter saving of £23.00
        onView(withId(R.id.digit_2)).perform(click());
        onView(withId(R.id.digit_3)).perform(click());
        onView(withId(R.id.digit_0)).perform(click());
        onView(withId(R.id.digit_0)).perform(click());

        // Check value is displayed
        onView(withId(R.id.valueDisplay)).check(matches(withText("£23.00")));

        // Press submit
        onView(withId(R.id.submit)).perform(click());

        // Check add view is displayed
        onView(withId(R.id.totalValue)).check(matches(isDisplayed()));
        onView(withId(R.id.totalValue)).check(matches(withText("£23.00")));

    }

    @Test
    public void cancelSaving_noValue() {

        // Click add button
        onView(withId(R.id.button_add)).perform(click());

        // Check add view is displayed
        onView(withId(R.id.valueDisplay)).check(matches(isDisplayed()));

        // Enter saving of £23.00
        onView(withId(R.id.digit_2)).perform(click());
        onView(withId(R.id.digit_3)).perform(click());
        onView(withId(R.id.digit_0)).perform(click());
        onView(withId(R.id.digit_0)).perform(click());
        onView(withId(R.id.cancel)).perform(click());

        onView(withId(R.id.totalValue)).check(matches(withText("0p")));

    }


}