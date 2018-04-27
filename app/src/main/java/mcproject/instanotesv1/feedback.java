package mcproject.instanotesv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class feedback extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    public RatingBar ratingBar;
    private static final String UserID = "UserID";
    private static final String Rating = "Rating";
    private static final String Comment = "Comment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore=FirebaseFirestore.getInstance();
        // Setting Toolbar
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(R.string.Feedback_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Initialize RatingBar
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Display rating by calling getRating() method.
     *
     * @param view
     */
    public void rateMe(View view) {

        Toast.makeText(getApplicationContext(),
                String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
        EditText comment =(EditText)findViewById(R.id.editText3);
        Map<String,Object> NewFeedback = new HashMap<>();
        NewFeedback.put(UserID, firebaseUser.getUid());
        NewFeedback.put(Rating, ratingBar.getRating());
        NewFeedback.put(Comment, comment.getText().toString());
        firebaseFirestore.collection("feedbacks")
                .add(NewFeedback)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("akjsdgajk", "Comment successful");
                    }
                });

    }
}
