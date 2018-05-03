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

@RunWith(AndroidJUnit4.class)
public class SettingsTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("mcproject.instanotesv1", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<settings_2015037> activityActivityTestRule = new ActivityTestRule<>(settings_2015037.class);

    @Before
    public void setup()
    {
        activityActivityTestRule.getActivity();
    }

    @Test
    public void test1()throws Exception {
        onView(withId(R.id.toolbar_actionbar)).check(matches(isDisplayed()));

    }

    @Test
    public void test2()throws Exception {
        onView(withId(R.id.device_notification)).check(matches(isDisplayed()));

    }


    @Test
    public void test3() throws Exception
    {
        onView(withId(R.id.switch1_settings))
                .check(matches((isDisplayed())));
    }
    @Test
    public void test4() throws Exception
    {
        onView(withId(R.id.device_downloadoverwifi))
                .check(matches((isDisplayed())));
    }
    @Test
    public void test5() throws Exception
    {
        onView(withId(R.id.switch2_settings))
                .check(matches((isDisplayed())));
    }

}
