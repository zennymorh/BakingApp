package com.kehinde.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kehinde.bakingapp.activities.RecipeListActivity;
import com.kehinde.bakingapp.util.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by kehinde on 6/7/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    public static final String RECIPE_EXTRA = "RECIPE_EXTRA";
    private IdlingResource mIdlingResource;


    @Rule
    public ActivityTestRule<RecipeListActivity> activityTestRule= new ActivityTestRule<>(RecipeListActivity.class);

    // Registers any resource that needs to be synchronized with Espresso before the test is run.
    @Before
    public void registerIdlingResource() {
        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        // Register Idling Resources
        Espresso.registerIdlingResources(mIdlingResource);

    }

    /**
     * Checks if the Recipe List RecyclerView is displayed
     * */
    @Test
    public void RecipeListRecyclerViewIsDisplayed(){

        // Check that the Recycler View is  displayed
        onView(withId(R.id.recipe_list_recycler)).check(matches(isDisplayed()));
    }

    /**
     * Clicks on a RecyclerViewItem and checks it opens the {@link com.kehinde.bakingapp.activities.RecipeStepListActivity}
     */
    @Test
    public void clickRecyclerViewItemOpensRecipeStepListActivity(){

        // Click on the RecyclerView item at position 1
        onView(withId(R.id.recipe_list_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


        onView(withId(R.id.step_recycler)).check(matches(isDisplayed()));


    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
