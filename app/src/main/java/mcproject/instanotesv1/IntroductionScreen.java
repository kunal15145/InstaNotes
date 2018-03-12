package mcproject.instanotesv1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
public class IntroductionScreen extends AppCompatActivity {


    // Introduction Screen
    private ViewPager viewPager;
    private PagerAdapter ViewPagerAdapter;
    private LinearLayout AllDots;
    private TextView[] dots;
    private int[] FeatureScreenLayouts;

    // Sign In Screen
    private ProgressDialog progressDialog;
    private static final String LOADING = "Loading";
    private SignInButton signInButton;
    private FirebaseAuth firebaseAuth;
    private static final int SIGN_IN = 9001;
    private GoogleSignInClient googleSignInClient;

    //Firestore
    private FirebaseFirestore db;


    private static final String EMAIL_TAG = "Email";
    private static final String NAME_TAG = "Name";
    private static final String INSTA_COINS = "InstaCoins";
    private static final String PIC_URI = "PicUri";
    private static final String Courses_TAG="Courses";


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            my_course_list(firebaseUser);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthorization(account);
            } catch (ApiException e) {
                e.printStackTrace();
                my_course_list(null);
            }
        }
    }

    private void firebaseAuthorization(GoogleSignInAccount account) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(LOADING);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    Add_CheckUser(firebaseUser);
                    my_course_list(firebaseUser);
                }
                else{
                    //my_course_list(null);
                }
                if(progressDialog.isShowing() && progressDialog!=null){
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void Add_CheckUser(FirebaseUser firebaseUser) {
//        BufferedReader br=null;
//        String line;
//        try{
//            br=new BufferedReader(new InputStreamReader(getAssets().open("a.csv")));
//            int i=0;
//            while((line=br.readLine())!=null){
//                i++;
//                final String course[]=line.split(",");
//                Map<String,Object> NewCourseInfo = new HashMap<>();
//                NewCourseInfo.put(CourseName_TAG,course[1]);
//                NewCourseInfo.put(CourseID_TAG,course[0]);
//                NewCourseInfo.put(InstructorName_TAG,course[2]);
//                NewCourseInfo.put(Semester_TAG,"Winter 2018");
//                db.collection("courses").document("Course"+i)
//                        .set(NewCourseInfo)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Log.d("ok", "course added");
//                            }
//                        });
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        final FirebaseUser[] users=new FirebaseUser[1];
        users[0]=firebaseUser;
        Map<String,Object> NewUserInfo = new HashMap<>();
        NewUserInfo.put(NAME_TAG,users[0].getDisplayName());
        NewUserInfo.put(EMAIL_TAG,users[0].getEmail());
        NewUserInfo.put(INSTA_COINS,"5");
        NewUserInfo.put(Courses_TAG, new ArrayList<String>());
        NewUserInfo.put(PIC_URI,users[0].getPhotoUrl().toString());
        db.collection("users").document(users[0].getUid()).set(NewUserInfo)
          .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("sdfjkhsdkjfha", "written");
                }
          }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("sdjkfsahdkjfh", "Error writing document");
                }
        });
    }

    private void my_course_list(FirebaseUser firebaseUser) {
            if(firebaseUser!=null){
                Intent intent = new Intent(IntroductionScreen.this,my_courses.class);
                startActivity(intent);
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_introduction_screen);
        // Login

        signInButton = findViewById(R.id.signin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent,SIGN_IN);
            }
        });

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.Auth_KEY))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Introduction Screen
        viewPager = findViewById(R.id.viewPageIntro);
        AllDots = findViewById(R.id.Dots);
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
            else dots[j].setTextColor(getResources().getColor(R.color.cardview_light_background));
            AllDots.addView(dots[j]);
        }
    }
}
