package mcproject.instanotesv1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Account extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account);


        TextView sname=(TextView)findViewById(R.id.textView21);
        TextView email=(TextView)findViewById(R.id.textView23);
        TextView fname=(TextView)findViewById(R.id.textView13);
        TextView credit=(TextView)findViewById(R.id.textView22);
        CircleImageView dp=findViewById(R.id.imageView13);
//        dp = findViewById(R.id.dp);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if(acct!=null) {
//
//
////            Uri uri = acct.getPhotoUrl();

////            dp.setImageURI(null);
////            dp.setImageURI(uri);
//
//            email.setText(acct.getEmail());
////            dp.setImageDrawable(getResources().getDrawable(R.drawable.person_photo));
//            credit.setText("7");
//
//        }
        Uri uri = firebaseUser.getPhotoUrl();
        String str = firebaseUser.getDisplayName();
        String[] splitStr = str.split("\\s+");
        fname.setText(splitStr[0]);
        sname.setText(splitStr[1]);
        email.setText(firebaseUser.getEmail());
        credit.setText("7 Credits");
        String TAG="url";
        Log.d(TAG, String.valueOf(uri));

//
        Picasso.with(getApplicationContext())
                .load(uri)
                .error(android.R.drawable.sym_def_app_icon)
                .into(dp);
    }
}
