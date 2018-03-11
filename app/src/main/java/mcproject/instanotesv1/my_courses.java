package mcproject.instanotesv1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.squareup.picasso.Picasso;


import de.hdodenhof.circleimageview.CircleImageView;

public class my_courses extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public TextView navUsername,credit,email;
    public CircleImageView dp1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;
    private static final String INSTA_COINS = "InstaCoins";

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_courses);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), join_courses.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        dp1 = headerView.findViewById(R.id.dp);
        navUsername = headerView.findViewById(R.id.username);

        credit = headerView.findViewById(R.id.credit);

        firestore.collection("users").document(firebaseUser.getEmail()).addSnapshotListener(
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
                            Log.d("Current data",null);
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
    public void account(View V)
    {
        Intent intent3;
        intent3 = new Intent(V.getContext(),Account.class);
        startActivity(intent3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_courses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

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
            intent2 = new Intent(getApplicationContext(),Settings.class);
            startActivity(intent2);

        } else if (id == R.id.nav_logout) {
            //firebaseAuth.signOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
