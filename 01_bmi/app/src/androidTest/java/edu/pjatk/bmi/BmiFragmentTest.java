package edu.pjatk.bmi;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

@RunWith(AndroidJUnit4.class)
public class BmiFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testBmiCalculation_normalWeight() {
        onView(withId(R.id.navigation_dashboard))
                .perform(click());

        onView(withId(R.id.editTextNumberDecimalHeight))
                .perform(typeText("175"), closeSoftKeyboard());

        onView(withId(R.id.editTextNumberDecimalWeight))
                .perform(typeText("70"), closeSoftKeyboard());

        onView(withId(R.id.textViewBMI))
                .check(matches(withText("22.86")));

        onView(withId(R.id.textViewDescription))
                .check(matches(withText("Optimal")));
    }
}
