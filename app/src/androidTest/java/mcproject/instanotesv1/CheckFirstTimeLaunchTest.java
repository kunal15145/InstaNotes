package mcproject.instanotesv1;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

import static org.junit.Assert.*;

public class CheckFirstTimeLaunchTest {
    @Rule
    public ActivityTestRule<CheckFirstTimeLaunch> activityActivityTestRule = new ActivityTestRule<>(CheckFirstTimeLaunch.class, true, true);

}