package mcproject.instanotesv1;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

//TEST ONLY PASSES WHEN NOT SIGNED IN
@RunWith(AndroidJUnit4.class)
public class StartscreenTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("mcproject.instanotesv1", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<StartScreen_2015037> activityActivityTestRule = new ActivityTestRule<>(StartScreen_2015037.class);

    @Before
    public void setup()
    {
        activityActivityTestRule.getActivity();
    }

    @Test
    public void test1()throws Exception {
        onView(withId(R.id.imageView20)).check(matches(isDisplayed()));

    }

    @Test
    public void test2()throws Exception {
        onView(withId(R.id.textView26)).check(matches(isDisplayed()));

    }


    @Test
    public void test3() throws Exception
    {
        onView(withId(R.id.button7))
                .check(matches((isDisplayed())));

    }

}
