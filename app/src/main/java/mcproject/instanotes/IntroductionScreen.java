package mcproject.instanotes;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class IntroductionScreen extends AppCompatActivity {

    private LinearLayout dots;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private CheckFirstTimeLaunch checkFirstTimeLaunch;
    private Button SignInButton;
    private int[] WelcomeScreensLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkFirstTimeLaunch = new CheckFirstTimeLaunch(this);
        if(!checkFirstTimeLaunch.checkFirstLaunch()){
            launchCoursesScreen();
            finish();
        }
        else getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_introduction_screen);
    }

    private void launchCoursesScreen(){
        checkFirstTimeLaunch.setFirstLaunch(false);
        startActivity(new Intent(this,my_courses.class));
        finish();
    }




}
