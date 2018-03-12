package mcproject.instanotesv1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Account extends AppCompatActivity {

    // Account activity
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;
    private static final String INSTA_COINS = "InstaCoins";
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account);
        // Adding Toolbar
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(R.string.account_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Initialising variables
        TextView sname= findViewById(R.id.textView21);
        TextView email= findViewById(R.id.textView23);
        TextView fname= findViewById(R.id.textView13);
        final TextView credit= findViewById(R.id.textView22);
        CircleImageView dp=findViewById(R.id.imageView13);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        // Assigning values from firebase

        Uri uri = firebaseUser.getPhotoUrl();
        String str = firebaseUser.getDisplayName();
        String[] splitStr = str.split("\\s+");
        fname.setText(splitStr[0]);
        sname.setText(splitStr[1]);
        email.setText(firebaseUser.getEmail());
        db.collection("users")
                .document(firebaseUser.getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d("Failure Listining","ok");
                            return;
                        }
                        else if(documentSnapshot!=null && documentSnapshot.exists()){
                            Log.d("dasd", (String) documentSnapshot.get(INSTA_COINS));
                            credit.setText((CharSequence) documentSnapshot.get(INSTA_COINS)+" credits");
                        }
                        else {
                            Log.d("Current data",null);
                        }
                    }
                });
        String TAG="url";
        Log.d(TAG, String.valueOf(uri));
        // Setting profile pic
        Picasso.with(getApplicationContext())
                .load(uri)
                .error(android.R.drawable.sym_def_app_icon)
                .into(dp);
    }

    // Back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
