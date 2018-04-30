package mcproject.instanotesv1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

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
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class PreviewNotes extends AppCompatActivity {

    private List<Notes> previewNotesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PreviewNotesAdapter previewNotesAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    private static final String OWN_TAG = "OWN";
    private static final String DATE_TAG = "DATE";
    private static final String Course_TAG="Course";
    String date;
    String coursnema;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_notes);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        date = (String) getIntent().getExtras().get("Date");
        coursnema = (String) getIntent().getExtras().get("coursename");
        type = (int) getIntent().getExtras().get("Flag");

        recyclerView = findViewById(R.id.recycler_view);
        previewNotesAdapter = new PreviewNotesAdapter(previewNotesList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(previewNotesAdapter);

        prepareList();
    }

    private void prepareList() {
        previewNotesList.clear();
//        Notes previewNotes = new Notes("Test","Rajan Girsa",5,FALSE,"www.google.com",4,FALSE);
//        previewNotesList.add(previewNotes);
//
//        previewNotes = new Notes("Test1","Rajan Girsa",1,TRUE,"www.google.com",1,TRUE);
//        previewNotesList.add(previewNotes);
//
//        previewNotes = new Notes("Test2","Rajan Girsa",4,FALSE,"www.google.com",7,FALSE);
//        previewNotesList.add(previewNotes);
//
//        previewNotes = new Notes("Test3","Rajan Girsa",2,TRUE,"www.google.com",3,TRUE);
//        previewNotesList.add(previewNotes);
        if(type==0) {
            firebaseFirestore.collection("uploads")
                    .whereEqualTo(DATE_TAG, date)
                    .whereEqualTo(Course_TAG, coursnema)
                    .whereEqualTo(OWN_TAG, "0")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {

                                final ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) documentSnapshot.get("User_uploads");
                                for (int i = 0; i < list.size(); i++) {

                                    final ArrayList<String> list1 = (ArrayList<String>) list.get(i).get("Images");
                                    String s = (String) list.get(i).get("Dislikes");
                                    final String s1 = (String) list.get(i).get("Likes");
                                    String userid = (String) list.get(i).get("UserID");
                                    firebaseFirestore.collection("users")
                                            .document(userid)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    String name = (String) task.getResult().get("Name");
                                                    previewNotesList.add(new Notes(date, name, Integer.parseInt(s1), FALSE, "www.google.com", list1.size(), FALSE, list1));
                                                }
                                            });

                                    previewNotesAdapter.notifyDataSetChanged();
                                }

                            }

                        }
                    });
        }
        else if(type==1){
            firebaseFirestore.collection("uploads")
                    .whereEqualTo(DATE_TAG, date)
                    .whereEqualTo(Course_TAG, coursnema)
                    .whereEqualTo(OWN_TAG, "1")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {

                                final ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) documentSnapshot.get("User_uploads");
                                for (int i = 0; i < list.size(); i++) {

                                    final ArrayList<String> list1 = (ArrayList<String>) list.get(i).get("Images");
                                    String s = (String) list.get(i).get("Dislikes");
                                    final String s1 = (String) list.get(i).get("Likes");
                                    final String userid = (String) list.get(i).get("UserID");
                                    firebaseFirestore.collection("users")
                                            .document(userid)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    String name = (String) task.getResult().get("Name");
                                                    if(firebaseUser.getUid().equals(userid)){
                                                        previewNotesList.add(new Notes(date, name, Integer.parseInt(s1), FALSE, "www.google.com", list1.size(), FALSE, list1));
                                                    }
                                                }
                                            });

                                    previewNotesAdapter.notifyDataSetChanged();
                                }

                            }

                        }
                    });
        }

    }
}