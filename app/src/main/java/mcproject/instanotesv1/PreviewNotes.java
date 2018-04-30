package mcproject.instanotesv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class PreviewNotes extends AppCompatActivity {

    private List<Notes> previewNotesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PreviewNotesAdapter previewNotesAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    String date = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_notes);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        date = (String) getIntent().getExtras().get("Date");
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




    }
}