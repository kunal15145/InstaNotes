package mcproject.instanotesv1;

import android.app.Notification;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notifications extends AppCompatActivity{

    private RecyclerView recyclerView;
    private NotifAdapter adapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private String courseName;
    private ArrayList<String> courses;
    private ArrayList<String> notifCourses;
    private ArrayList<String> c;

    List<Notif> notifList;
    // Main activity for notifications

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notifications);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.notifications_title);
        notifList=new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        notifCourses = new ArrayList<>();
        db.collection("notifications")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                notifCourses.add(document.getId());
                            }
                        } else {
                            Log.d("document", "error getting documents: ", task.getException());
                        }
                    }
                });

        courses = new ArrayList<>();
        db.collection("users").document(firebaseAuth.getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()) {
                                c = (ArrayList<String>) document.getData().get("Courses"); //course from user
                                db.collection("courses")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (DocumentSnapshot document : task.getResult()) {
                                                        Log.d("name", String.valueOf(document.getId()));
                                                        for(int i=0;i<c.size();++i){
                                                            if(document.getId().equals(c.get(i))){
                                                                courses.add(String.valueOf(document.get("CourseName")));
                                                                Log.d("name1",String.valueOf(document.getId()));
                                                            }
                                                        }
                                                    }
                                                    for (int i=0;i<c.size();++i) {
                                                        DocumentReference docRef = db.collection("notifications").document(courses.get(i));
                                                        Log.d("c",c.get(i));
                                                        docRef.get()
                                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                        if (task.isSuccessful()) {
                                                                            DocumentSnapshot document = task.getResult();
                                                                            if (document.exists()) {
                                                                                Log.d("here", "here");
                                                                                String desc = String.valueOf(document.getData().get("Date"));
                                                                                String courseName = String.valueOf(document.getData().get("CourseName"));
                                                                                String user = String.valueOf(document.getData().get("User"));
                                                                                desc = desc.substring(0, 10);
                                                                                Log.d("desc", desc);
                                                                                notifList.add(new Notif(courseName + " notes requested by " + user, desc, R.drawable.person_photo));
                                                                                Log.d("desc", String.valueOf(notifList.size()));
                                                                                adapter = new NotifAdapter(Notifications.this, notifList);
                                                                                recyclerView.setAdapter(adapter);
                                                                            } else {
                                                                                Log.d("desc", "No such document exist");
                                                                            }
                                                                        } else {
                                                                            Log.d("DateTab", "Error getting documents: ", task.getException());
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                } else {
                                                    Log.d("document", "error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                                Log.d("c", String.valueOf(c.size()));
                            } else{
                                Log.d("desc","No such document exist");
                            }
                        } else {
                            Log.d("DateTab", "Error getting documents: ", task.getException());
                        }
                    }
                });

//        db.collection("notifications").document(coursename).collection(String.valueOf(count[0])).document(coursename).set(notification);

        // Hard coded default values

        //setting adapter to recyclerview
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
