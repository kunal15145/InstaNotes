package mcproject.instanotesv1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class join_courses extends AppCompatActivity{

    private ArrayList<ArrayList<SingleItemModel>> allSampleData = new ArrayList<>();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    private ProgressDialog dialog;

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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        for(int i=0;i<7;i++){
            allSampleData.add(new ArrayList<SingleItemModel>());
        }
        dialog=new ProgressDialog(join_courses.this);
        dialog.setMessage("Retrieving data, please wait.");
        dialog.show();
        addData();
    }

    public void addData(){
        firebaseFirestore.collection("courses")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot documentSnapshot:task.getResult()) {
                            int count = 0;
                            if (documentSnapshot.get("CourseID").toString().contains("CSE")) {
                                if (join_courses.this.allSampleData.get(CSE).isEmpty()) {
                                    join_courses.this.allSampleData.get(CSE).add(new SingleItemModel("CSE", "url", null, null));
                                }
                                String coursname = "";
                                if(documentSnapshot.get("CourseName").toString().contains("(new)")){
                                    coursname = documentSnapshot.get("CourseName").toString().replaceAll("(new)","");
                                }
                                else coursname = documentSnapshot.get("CourseName").toString();
                                join_courses.this.allSampleData.get(CSE).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                count++;
                            }
                            if (documentSnapshot.get("CourseID").toString().contains("ECE")) {
                                if (join_courses.this.allSampleData.get(ECE).isEmpty()) {
                                    join_courses.this.allSampleData.get(ECE).add(new SingleItemModel("ECE", "url", null, null));
                                }
                                String coursname = "";
                                if(documentSnapshot.get("CourseName").toString().contains("(new)")){
                                    coursname = documentSnapshot.get("CourseName").toString().replaceAll("(new)","");
                                }
                                else coursname = documentSnapshot.get("CourseName").toString();
                                join_courses.this.allSampleData.get(ECE).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                count++;
                            }
                            if (documentSnapshot.get("CourseID").toString().contains("DES")) {
                                if (join_courses.this.allSampleData.get(DES).isEmpty()) {
                                    join_courses.this.allSampleData.get(DES).add(new SingleItemModel("DESIGN", "url", null, null));
                                }
                                String coursname = "";
                                if(documentSnapshot.get("CourseName").toString().contains("(new)")){
                                    coursname = documentSnapshot.get("CourseName").toString().replaceAll("(new)","");
                                }
                                else coursname = documentSnapshot.get("CourseName").toString();
                                join_courses.this.allSampleData.get(DES).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                count++;
                            }
                            if (documentSnapshot.get("CourseID").toString().contains("HSS")) {
                                if (join_courses.this.allSampleData.get(HSS).isEmpty()) {
                                    join_courses.this.allSampleData.get(HSS).add(new SingleItemModel("HSS", "url", null, null));
                                }
                                String coursname = "";
                                if(documentSnapshot.get("CourseName").toString().contains("(new)")){
                                    coursname = documentSnapshot.get("CourseName").toString().replaceAll("(new)","");
                                }
                                else coursname = documentSnapshot.get("CourseName").toString();
                                join_courses.this.allSampleData.get(HSS).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                count++;
                            }
                            if (documentSnapshot.get("CourseID").toString().contains("MTH")) {
                                if (join_courses.this.allSampleData.get(MTH).isEmpty()) {
                                    join_courses.this.allSampleData.get(MTH).add(new SingleItemModel("MATHS", "url", null, null));
                                }
                                String coursname = "";
                                if(documentSnapshot.get("CourseName").toString().contains("(new)")){
                                    coursname = documentSnapshot.get("CourseName").toString().replaceAll("(new)","");
                                }
                                else coursname = documentSnapshot.get("CourseName").toString();
                                join_courses.this.allSampleData.get(MTH).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                count++;
                            }
                            if (documentSnapshot.get("CourseID").toString().contains("BIO")) {
                                if (join_courses.this.allSampleData.get(BIO).isEmpty()) {
                                    join_courses.this.allSampleData.get(BIO).add(new SingleItemModel("BIO", "url", null, null));
                                }
                                String coursname = "";
                                if(documentSnapshot.get("CourseName").toString().contains("(new)")){
                                    coursname = documentSnapshot.get("CourseName").toString().replaceAll("(new)","");
                                }
                                else coursname = documentSnapshot.get("CourseName").toString();
                                join_courses.this.allSampleData.get(BIO).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                count++;
                            }
                            if (count == 0) {
                                if (join_courses.this.allSampleData.get(OTHERS).isEmpty()) {
                                    join_courses.this.allSampleData.get(OTHERS).add(new SingleItemModel("OTHERS", "url", null, null));
                                }
                                String coursname = "";
                                if(documentSnapshot.get("CourseName").toString().contains("(new)")){
                                    coursname = documentSnapshot.get("CourseName").toString().replaceAll("(new)","");
                                }
                                else coursname = documentSnapshot.get("CourseName").toString();
                                join_courses.this.allSampleData.get(OTHERS).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                            }
                        }
                        set_join_courses();
                    }
                    else {
                        Log.d("Couldn't","Try Again");
                    }

                }
            });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void set_join_courses(){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        Log.d("this", String.valueOf(allSampleData.size()));
        RecyclerView my_recycler_view = findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this,allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }
}
