package mcproject.instanotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class IntroductionScreen extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter ViewPagerAdapter;
    private LinearLayout AllDots;
    private TextView[] dots;
    private int[] FeatureScreenLayouts;
    private CheckFirstTimeLaunch checkFirstTimeLaunch;
    private Button SignInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private final int SIGN_IN = 9001;
    static final String kl = "dasdsa";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account;
            try {
                account = task.getResult(ApiException.class);
                updateUI(account);
            } catch (ApiException e) {
                Log.w("TAG","Sign-In Failed");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkFirstTimeLaunch = new CheckFirstTimeLaunch(this);
        if(!checkFirstTimeLaunch.checkFirstLaunch()){
            launchMyCourses();
            finish();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_introduction_screen);
        viewPager = findViewById(R.id.viewPageIntro);
        AllDots = findViewById(R.id.Dots);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,signInOptions);
        SignInButton = findViewById(R.id.signin);
        FeatureScreenLayouts = new int[]{
                R.layout.feature_screen1,
                R.layout.feature_screen2,
                R.layout.feature_screen3,
                R.layout.feature_screen4
        };
        makeDotVisible(0);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        ViewPagerAdapter = new PagerAdapter() {
            LayoutInflater FeatureScreenlayoutInflater;
            @Override
            public int getCount() {
                return FeatureScreenLayouts.length;
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                FeatureScreenlayoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = FeatureScreenlayoutInflater.inflate(FeatureScreenLayouts[position],container,false);
                container.addView(view);
                return view;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {container.removeView((View)object);}
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return (view==object);
            }
        };
        viewPager.setAdapter(ViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}            @Override
            public void onPageSelected(int position) {makeDotVisible(position);}            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null) {
            updateUI(account);
        }
    }

    private void makeDotVisible(int i) {
        dots = new TextView[FeatureScreenLayouts.length];
        AllDots.removeAllViews();
        for(int j=0;j<dots.length;j++){
            dots[j] = new TextView(this);
            dots[j].setText(Html.fromHtml("&#8226;"));
            dots[j].setTextSize(35);
            if(j==i){
                dots[j].setTextColor(getResources().getColor(R.color.newred));
            }
            else dots[j].setTextColor(getResources().getColor(R.color.newgray));
            AllDots.addView(dots[j]);
        }
    }

    private void launchMyCourses() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null) {
            updateUI(account);
        }
        checkFirstTimeLaunch.setFirstLaunch(false);
        startActivity(new Intent(IntroductionScreen.this,my_courses.class));
        finish();
    }

    private void updateUI(GoogleSignInAccount account) {
        Intent sendIntent = new Intent(this,my_courses.class);
        sendIntent.putExtra(kl,account.getEmail());
        startActivity(sendIntent);
    }

    public void my_course(View view) {
        Intent signin = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signin,SIGN_IN);
    }

}
