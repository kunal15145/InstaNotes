package mcproject.instanotesv1;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class main_activity_join_course_test {

    @Rule
    public ActivityTestRule<my_courses> activityActivityTestRule = new ActivityTestRule<>(my_courses.class, true, true);

    @Test
    public void onclickjoin()
    {
        onView(withId(R.id.fab)).check((ViewAssertion) isClickable());
        onView(withId(R.id.fab)).perform(click()).check(matches(isDisplayed()));
    }

    public void onclicknotif()
    {
        onView(withId(R.id.notificon)).check((ViewAssertion)isClickable());
        onView(withId(R.id.notificon)).perform(click()).check(matches(isDisplayed()));
    }


}
