package mcproject.instanotesv1;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class join_courses extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private static FirebaseFirestore firebaseFirestore;
    private ProgressDialog dialog;
    EditText editTextSearch;

    // List of all categories
    private static int CSE = 0;
    private static int ECE = 1;
    private static int DES = 2;
    private static int HSS = 3;
    private static int MTH = 4;
    private static int BIO = 5;
    private static int OTHERS = 6;
    RecyclerViewDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_join_courses);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        dialog=new ProgressDialog(join_courses.this);
        dialog.setMessage("Retrieving data, please wait.");
        dialog.show();
        addData();

        editTextSearch=findViewById(R.id.editMobileNo);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });

        /*AutoCompleteTextView autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.editMobileNo);

        final ArrayList<String> stringlist = new ArrayList<>();
        firebaseFirestore.collection("courses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot:task.getResult()){
                                String s = (String) documentSnapshot.get("CourseName");
                                stringlist.add((String) documentSnapshot.get("CourseName"));
                            }
                        }
                    }
                });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,stringlist);

        autoCompleteTextView1.setAdapter(adapter);*/


    }

    private void filter(final String text) {
        //new array list that will hold the filtered data
        final ArrayList<ArrayList<SingleItemModel>> filterdNames = new ArrayList<>();
        filterdNames.add(new ArrayList<SingleItemModel>());
        filterdNames.get(0).add(new SingleItemModel("Courses",null,null,null));

        //looping through existing elements
        firebaseFirestore.collection("courses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot:task.getResult()){
                                String s = (String) documentSnapshot.get("CourseName");
                                if(s.toLowerCase().contains(text.toLowerCase())){
                                    filterdNames.get(0).add(new SingleItemModel((String)documentSnapshot.get("CourseName"),null,(String)documentSnapshot.get("Semester"),"JOIN"));
                                }
                            }
                        }
                    }
                });
        adapter.filterList(filterdNames);

    }



    // Adding a course
    public void addData(){
         firebaseFirestore.collection("users")
                 .document(firebaseUser.getUid())
                 .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                     @Override
                     public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        if(e!=null){
                            return;
                        }
                        else if(documentSnapshot!=null && documentSnapshot.exists()){
                            final ArrayList<String> Courses;
                            Courses = (ArrayList<String>) documentSnapshot.get("Courses");
                            for(String s:Courses){
                                Log.d("dsadas",s);
                            }
                            firebaseFirestore.collection("courses")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            ArrayList<ArrayList<SingleItemModel>> allSampleData = new ArrayList<>();
                                            for(int i=0;i<7;i++){
                                                allSampleData.add(new ArrayList<SingleItemModel>());
                                            }
                                            if(task.isSuccessful()){
                                                for(DocumentSnapshot documentSnapshot:task.getResult()) {
                                                    int count = 0;
                                                    if (documentSnapshot.get("CourseID").toString().contains("CSE")) {
                                                        if (allSampleData.get(CSE).isEmpty()) {
                                                            allSampleData.get(CSE).add(new SingleItemModel("CSE", "url", null, null));
                                                        }
                                                        String coursname;
                                                        coursname = documentSnapshot.get("CourseName").toString().replaceAll("\\(.*?\\) ?", "");
                                                        if(!Courses.contains(documentSnapshot.getId())){
                                                            allSampleData.get(CSE).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                                        }
                                                        count++;
                                                    }
                                                    if (documentSnapshot.get("CourseID").toString().contains("ECE")) {
                                                        if (allSampleData.get(ECE).isEmpty()) {
                                                            allSampleData.get(ECE).add(new SingleItemModel("ECE", "url", null, null));
                                                        }
                                                        String coursname;
                                                        coursname = documentSnapshot.get("CourseName").toString().replaceAll("\\(.*?\\) ?", "");
                                                        if(!Courses.contains(documentSnapshot.getId())){
                                                            allSampleData.get(ECE).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                                        }
                                                        count++;
                                                    }
                                                    if (documentSnapshot.get("CourseID").toString().contains("DES")) {
                                                        if (allSampleData.get(DES).isEmpty()) {
                                                            allSampleData.get(DES).add(new SingleItemModel("DESIGN", "url", null, null));
                                                        }
                                                        String coursname = "";
                                                        coursname = documentSnapshot.get("CourseName").toString().replaceAll("\\(.*?\\) ?", "");
                                                        if(!Courses.contains(documentSnapshot.getId())){
                                                            allSampleData.get(DES).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                                        }
                                                        count++;
                                                    }
                                                    if (documentSnapshot.get("CourseID").toString().contains("HSS")) {
                                                        if (allSampleData.get(HSS).isEmpty()) {
                                                            allSampleData.get(HSS).add(new SingleItemModel("HSS", "url", null, null));
                                                        }
                                                        String coursname = "";
                                                        coursname = documentSnapshot.get("CourseName").toString().replaceAll("\\(.*?\\) ?", "");
                                                        if(!Courses.contains(documentSnapshot.getId())){
                                                            allSampleData.get(HSS).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                                        }
                                                        count++;
                                                    }
                                                    if (documentSnapshot.get("CourseID").toString().contains("MTH")) {
                                                        if (allSampleData.get(MTH).isEmpty()) {
                                                            allSampleData.get(MTH).add(new SingleItemModel("MATHS", "url", null, null));
                                                        }
                                                        String coursname = "";
                                                        coursname = documentSnapshot.get("CourseName").toString().replaceAll("\\(.*?\\) ?", "");
                                                        if(!Courses.contains(documentSnapshot.getId())){
                                                            allSampleData.get(MTH).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                                        }
                                                        count++;
                                                    }
                                                    if (documentSnapshot.get("CourseID").toString().contains("BIO")) {
                                                        if (allSampleData.get(BIO).isEmpty()) {
                                                            allSampleData.get(BIO).add(new SingleItemModel("BIO", "url", null, null));
                                                        }
                                                        String coursname = "";
                                                        coursname = documentSnapshot.get("CourseName").toString().replaceAll("\\(.*?\\) ?", "");
                                                        if(!Courses.contains(documentSnapshot.getId())){
                                                            allSampleData.get(BIO).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                                        }
                                                        count++;
                                                    }
                                                    if (count == 0) {
                                                        if (allSampleData.get(OTHERS).isEmpty()) {
                                                            allSampleData.get(OTHERS).add(new SingleItemModel("OTHERS", "url", null, null));
                                                        }
                                                        String coursname = "";
                                                        coursname = documentSnapshot.get("CourseName").toString().replaceAll("\\(.*?\\) ?", "");
                                                        if(!Courses.contains(documentSnapshot.getId())){
                                                            allSampleData.get(OTHERS).add(new SingleItemModel(coursname, "url", documentSnapshot.get("Semester").toString(), "JOIN"));
                                                        }
                                                    }
                                                }
                                                set_join_courses(allSampleData);
                                            }
                                            else {
                                                Log.d("Couldn't","Try Again");
                                            }
                                        }
                                    });
                        }
                        else {
                            Log.d("Don't Care","");
                        }
                     }
                 });
    }
    // Back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(0, R.anim.slide_out);
        return true;
    }

    public void set_join_courses(ArrayList<ArrayList<SingleItemModel>> allSampleData){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        Log.d("this", String.valueOf(allSampleData.size()));
        RecyclerView my_recycler_view = findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        adapter = new RecyclerViewDataAdapter(this,allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }
}
