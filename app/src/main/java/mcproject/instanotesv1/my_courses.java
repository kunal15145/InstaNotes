package mcproject.instanotesv1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class my_courses extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    List<Book> lstBook ;
    public TextView navUsername,credit,email;
    public CircleImageView dp1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;
    private static final String INSTA_COINS = "InstaCoins";
    boolean doubleBackToExitPressedOnce = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_courses);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        lstBook = new ArrayList<>();

        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));
        lstBook.add(new Book("Mobile Computing","Winter 2018",R.drawable.img4));

        RecyclerView myrv = findViewById(R.id.recyclerview_id);

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook);
        myrv.setLayoutManager(gridLayoutManager);
        myrv.setAdapter(myAdapter);





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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(doubleBackToExitPressedOnce){
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            },2000);
        }
    }

//    @Override
    public void account(View V) {
        Intent intent3;
        intent3 = new Intent(V.getContext(), Account.class);
        startActivity(intent3);
    }

    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), Notifications.class);
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
            //firebaseAuth.signOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
