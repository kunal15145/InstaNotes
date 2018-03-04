package mcproject.instanotes;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class IntroductionScreen extends AppCompatActivity {

    private LinearLayout dots;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private CheckFirstTimeLaunch checkFirstTimeLaunch;
    private int[] WelcomeScreensLayouts;
    private GoogleSignInClient mGoogleSignInClient;
    private Button SignInButton;
    private final int SIGN_IN = 9001;
    static final String kl = "email";

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
                updateUI(null);
            }
        }
    }

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
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,signInOptions);
        SignInButton = findViewById(R.id.signin);
    }

    private void launchCoursesScreen(){
        checkFirstTimeLaunch.setFirstLaunch(false);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Intent sendIntent = new Intent(this,my_courses.class);
        sendIntent.putExtra(kl,account.getEmail());
        startActivity(sendIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null) {
            updateUI(account);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        Intent sendIntent = new Intent(this,my_courses.class);
        sendIntent.putExtra(kl,account.getEmail());
        startActivity(sendIntent);
    }

    public void my_course(View v)
    {
        Intent signin = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signin,SIGN_IN);
    }



}
