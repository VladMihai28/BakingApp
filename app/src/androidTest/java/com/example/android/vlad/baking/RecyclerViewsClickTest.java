package com.example.android.vlad.baking;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;

/**
 * Created by Vlad
 */

@RunWith(AndroidJUnit4.class)
public class RecyclerViewsClickTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
        new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickMainActivity() {
        onView(withId(R.id.master_list_fragment)).perform(click());
    }

    @Test
    public void clickRecyclerView() {
        onView(withId(R.id.recyclerview_recipe)).perform(click());
    }

    @Test
    public void clickRecyclerViewItem0() {
        onView(withId(R.id.recyclerview_recipe))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(0, click())
                );
    }

    @Test
    public void clickRecyclerViewItem1() {
        onView(withId(R.id.recyclerview_recipe))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(1, click())
                );
    }

    @Test
    public void clickStepsRecyclerViewItem0() {
        onView(withId(R.id.recyclerview_recipe))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(0, click())
                );
        onView(withId(R.id.recyclerview_recipe_steps))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(0, click())
                );
    }
    @Test
    public void clickStepsRecyclerViewItem1() {
        onView(withId(R.id.recyclerview_recipe))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(0, click())
                );
        onView(withId(R.id.recyclerview_recipe_steps))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(1, click())
                );
    }

}
