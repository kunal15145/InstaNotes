package mcproject.instanotesv1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class my_courses extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public TextView navUsername,credit,email;
    public CircleImageView dp1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;
    private static final String INSTA_COINS = "InstaCoins";
    private ProgressDialog dialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_courses);


        // authenticate
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        dialog=new ProgressDialog(this);
        dialog.setMessage("Retrieving data, please wait.");
        dialog.show();
        addCourse();



        Toolbar mActionBarToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(R.string.mycourses_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // Join courses
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),join_courses.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mActionBarToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        dp1 = headerView.findViewById(R.id.dp);
        dp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3;
                intent3 = new Intent(view.getContext(),Account.class);
                startActivity(intent3);
            }
        });
        navUsername = headerView.findViewById(R.id.username);

        credit = headerView.findViewById(R.id.credit);

        // Setting content
        firestore.collection("users").document(firebaseUser.getUid()).addSnapshotListener(
                new EventListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d("Failure Listining","ok");
                            return;
                        }
                        else if(documentSnapshot!=null && documentSnapshot.exists()){
                            Log.d("dasd", (String) documentSnapshot.get(INSTA_COINS));
                            credit.setText(documentSnapshot.get(INSTA_COINS) +" credits");
                        }
                        else {
                            Log.d("Current data","");
                        }
                    }
                }
        );
        email = headerView.findViewById(R.id.email);

        firebaseUser = firebaseAuth.getCurrentUser();

        navigationView.setNavigationItemSelectedListener(this);
                navUsername.setText(firebaseUser.getDisplayName());
                email.setText(firebaseUser.getEmail());
                Uri uri = firebaseUser.getPhotoUrl();
                String TAG="url";
                Log.d(TAG, String.valueOf(uri));


                Picasso.with(getApplicationContext())
                        .load(uri)
                        .error(android.R.drawable.sym_def_app_icon)
                        .into(dp1);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void addCourse() {
        firestore.collection("users").document(firebaseUser.getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        final ArrayList<String> UsersCourses;
                        if(e!=null){
                            Log.d("Failure Listining","ok");
                            return;
                        }
                        else if(documentSnapshot!=null && documentSnapshot.exists()){
                            UsersCourses = (ArrayList<String>) documentSnapshot.get("Courses");
                            final ArrayList<mycourse> CurrentCourses = new ArrayList<>();
                            if(UsersCourses.isEmpty()){
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                                addMyCourses(CurrentCourses);
                            }
                            for (String s : UsersCourses) {
                                firestore.collection("courses").document(s)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot documentSnapshot = task.getResult();
                                                    String coursename = (String) documentSnapshot.get("CourseName");
                                                    String temp = coursename.replaceAll("\\(.*?\\) ?", "");
                                                    CurrentCourses.add(new mycourse(temp, documentSnapshot.get("Semester").toString(),R.drawable.umb));
                                                }
                                                addMyCourses(CurrentCourses);
                                            }
                                        });
                            }
                        }
                        else {
                            Log.d("Current data","");
                        }
                    }
                });
    }


    public void addMyCourses(ArrayList<mycourse> temp){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        RecyclerView myrv = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,temp);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View v){
        Intent intent=new Intent(v.getContext(),Notifications.class);
        startActivity(intent);
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent2;
        if (id == R.id.transac) {
            intent2=new Intent(getApplicationContext(),Transactions.class);
            startActivity(intent2);
        } else if (id == R.id.help) {
//
            intent2 = new Intent(getApplicationContext(),Help.class);
            startActivity(intent2);
        } else if (id == R.id.feedback) {
            intent2 = new Intent(getApplicationContext(),feedback.class);
            startActivity(intent2);

        } else if (id == R.id.settings) {
            intent2 = new Intent(getApplicationContext(),settings.class);
            startActivity(intent2);

        } else if (id == R.id.nav_logout) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(my_courses.this,IntroductionScreen.class));
                            finish();
                        }
                    });
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
