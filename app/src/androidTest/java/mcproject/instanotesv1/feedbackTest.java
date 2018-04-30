package mcproject.instanotesv1;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

import static org.junit.Assert.*;

public class feedbackTest {

    @Rule
    public ActivityTestRule<feedback> activityActivityTestRule = new ActivityTestRule<>(feedback.class, true, true);

}