package mcproject.instanotesv1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class join_courses extends AppCompatActivity {

    private HashMap<Integer,ArrayList<SingleItemModel>> allSampleData = new HashMap<>();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;

    private static int CSE = 0;
    private static int ECE = 1;
    private static int DES = 2;
    private static int HSS = 3;
    private static int MTH = 4;
    private static int BIO = 5;
    private static int OTHERS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_join_courses);
//        setContentView(R.layout.activity_feedback);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        add();

        sub();

    }

    public void onStart(){
        super.onStart();
        addData();
    }

    public void onResume(){
        super.onResume();
        addData();
    }

    public void add(){
        Log.d("asdkjfhasdf", "yagami gandu");
    }

    public void sub(){
        Log.d("asdkjfhasdf", "yagami loda");
    }

    public void addData(){
        firebaseFirestore.collection("courses")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot documentSnapshot:task.getResult()){
                            if(documentSnapshot.get("CourseID").toString().contains("CSE")){
                                if(!join_courses.this.allSampleData.containsKey(CSE)){
                                    join_courses.this.allSampleData.put(CSE,new ArrayList<SingleItemModel>());
                                }
                                join_courses.this.allSampleData.get(CSE).add(new SingleItemModel(documentSnapshot.get("CourseName").toString(),"url",documentSnapshot.get("Semester").toString(),"JOIN"));
                            }
                            else if(documentSnapshot.get("CourseID").toString().contains("ECE")){
                                if(!join_courses.this.allSampleData.containsKey(ECE)){
                                    join_courses.this.allSampleData.put(ECE,new ArrayList<SingleItemModel>());
                                }
                                join_courses.this.allSampleData.get(ECE).add(new SingleItemModel(documentSnapshot.get("CourseName").toString(),"url",documentSnapshot.get("Semester").toString(),"JOIN"));

                            }
                            else if(documentSnapshot.get("CourseID").toString().contains("DES")){
                                if(!join_courses.this.allSampleData.containsKey(DES)){
                                    join_courses.this.allSampleData.put(DES,new ArrayList<SingleItemModel>());
                                }
                                join_courses.this.allSampleData.get(DES).add(new SingleItemModel(documentSnapshot.get("CourseName").toString(),"url",documentSnapshot.get("Semester").toString(),"JOIN"));
                            }
                            else if(documentSnapshot.get("CourseID").toString().contains("HSS")){
                                if(!join_courses.this.allSampleData.containsKey(HSS)){
                                    join_courses.this.allSampleData.put(HSS,new ArrayList<SingleItemModel>());
                                }
                                join_courses.this.allSampleData.get(HSS).add(new SingleItemModel(documentSnapshot.get("CourseName").toString(),"url",documentSnapshot.get("Semester").toString(),"JOIN"));
                            }
                            else if(documentSnapshot.get("CourseID").toString().contains("MTH")){
                                if(!join_courses.this.allSampleData.containsKey(MTH)){
                                    join_courses.this.allSampleData.put(MTH,new ArrayList<SingleItemModel>());
                                }
                                join_courses.this.allSampleData.get(MTH).add(new SingleItemModel(documentSnapshot.get("CourseName").toString(),"url",documentSnapshot.get("Semester").toString(),"JOIN"));
                            }
                            else if(documentSnapshot.get("CourseID").toString().contains("BIO")){
                                if(!join_courses.this.allSampleData.containsKey(BIO)){
                                    join_courses.this.allSampleData.put(BIO,new ArrayList<SingleItemModel>());
                                }
                                join_courses.this.allSampleData.get(BIO).add(new SingleItemModel(documentSnapshot.get("CourseName").toString(),"url",documentSnapshot.get("Semester").toString(),"JOIN"));
                            }
                            else {
                                if(!join_courses.this.allSampleData.containsKey(OTHERS)){
                                    join_courses.this.allSampleData.put(OTHERS,new ArrayList<SingleItemModel>());
                                }
                                join_courses.this.allSampleData.get(OTHERS).add(new SingleItemModel(documentSnapshot.get("CourseName").toString(),"url",documentSnapshot.get("Semester").toString(),"JOIN"));
                            }
                        }
                       Log.d("this", String.valueOf(allSampleData.size()));
                    }
                    else {
                        Log.d("Couldn't","Try Again");
                    }
                }
            });
        Log.d("sadasd", String.valueOf(this.allSampleData.size()));
        RecyclerView my_recycler_view = findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this,this.allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
