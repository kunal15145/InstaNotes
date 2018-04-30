package mcproject.instanotesv1;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    @Rule
    public ActivityTestRule<Account> mActivityTestRule = new ActivityTestRule<Account>(Account.class);
    private Account mAcitvity = null;

    @Before
    public void setUp() throws Exception{
        mAcitvity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mAcitvity.findViewById(R.id.nav_view);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception{
        mAcitvity = null;
    }

}



