package mcproject.instanotesv1;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

import static org.junit.Assert.*;

public class NotificationsTest
{

    @Rule
    public ActivityTestRule<Notifications> activityActivityTestRule = new ActivityTestRule<>(Notifications.class, true, true);

}