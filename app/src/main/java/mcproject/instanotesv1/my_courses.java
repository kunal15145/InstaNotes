package mcproject.instanotesv1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class my_courses extends AppCompatActivity{


    public TextView credit,email;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;
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

        dialog = new ProgressDialog(this);
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
                Intent intent = new Intent(getApplicationContext(), join_courses.class);
                startActivity(intent);
            }
        });

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }


        });

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.img3)
                .addProfiles(
                        new ProfileDrawerItem().withName(firebaseUser.getDisplayName()).withEmail(firebaseUser.getEmail()).withIcon(firebaseUser.getPhotoUrl())
                )
                .build();


        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mActionBarToolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Transaction").withIcon(android.R.drawable.ic_menu_recent_history).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Account Info").withIcon(android.R.drawable.ic_dialog_info).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Help").withIcon(android.R.drawable.ic_menu_help).withIdentifier(3),
                        new PrimaryDrawerItem().withName("FeedBack").withIcon(android.R.drawable.ic_menu_report_image).withIdentifier(4),
                        new PrimaryDrawerItem().withName("Settings").withIcon(android.R.drawable.ic_menu_manage).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Logout").withIcon(android.R.drawable.btn_dialog).withIdentifier(6)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Intent intent2;
                            if(((Nameable) drawerItem).getName().getText(view.getContext()).equals("Settings")){
                                intent2 = new Intent(getApplicationContext(),settings.class);
                                startActivity(intent2);
                            }
                            else if(((Nameable) drawerItem).getName().getText(view.getContext()).equals("Account Info")){
                                intent2 = new Intent(view.getContext(),Account.class);
                                startActivity(intent2);
                            }
                            else if(((Nameable) drawerItem).getName().getText(view.getContext()).equals("Transaction")){
                                intent2 = new Intent(view.getContext(),Transactions.class);
                                startActivity(intent2);
                            }
                            else if(((Nameable) drawerItem).getName().getText(view.getContext()).equals("Help")){
                                intent2 = new Intent(view.getContext(),Help.class);
                                startActivity(intent2);
                            }
                            else if(((Nameable) drawerItem).getName().getText(view.getContext()).equals("FeedBack")){
                                intent2 = new Intent(view.getContext(),feedback.class);
                                startActivity(intent2);
                            }
                            else if(((Nameable) drawerItem).getName().getText(view.getContext()).equals("Logout")){
                                AuthUI.getInstance().signOut(view.getContext())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            startActivity(new Intent(my_courses.this,IntroductionScreen.class));
                                            finish();
                                        }
                                    });
                            }
                        }

                        return false;
                    }
                })
                .build();

            drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

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
        MyCoursesAdapter myAdapter = new MyCoursesAdapter(this,temp);
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
}
