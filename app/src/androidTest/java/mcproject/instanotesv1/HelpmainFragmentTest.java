package mcproject.instanotesv1;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class HelpmainFragmentTest {

    @Rule
    public CLTest<HelpFragMain_2015037> mFragmentTestRule = new CLTest<>(HelpFragMain_2015037.class);

    @Test
    public void fragment_can_be_instantiated() {

        // Launch the activity to make the fragment visible
        mFragmentTestRule.launchActivity(null);

        // Then use Espresso to test the Fragment
        Espresso.onView(ViewMatchers.withId(R.id.help1)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help2)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help3)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help4)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help5)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help6)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help7)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help8)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help9)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help10)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help11)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help12)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.help13)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.view2)).check(matches(isDisplayed()));
    }
}
