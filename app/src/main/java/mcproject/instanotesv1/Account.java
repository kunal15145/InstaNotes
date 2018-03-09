package mcproject.instanotesv1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends AppCompatActivity {

    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        TextView sname= findViewById(R.id.textView21);
        TextView email= findViewById(R.id.textView23);
        TextView fname= findViewById(R.id.textView13);
        TextView credit= findViewById(R.id.textView22);
        ImageView dp= findViewById(R.id.imageView13);

        if(firebaseUser!=null) {


            Uri uri = firebaseUser.getPhotoUrl();
            String str = firebaseUser.getDisplayName();
            String[] splitStr = str.split("\\s+");
            fname.setText(splitStr[0]);
            sname.setText(splitStr[1]);
//            dp.setImageURI(null);
            dp.setImageURI(uri);

            email.setText(firebaseUser.getEmail());
            dp.setImageDrawable(getResources().getDrawable(R.drawable.person_photo));
            credit.setText("7");
        }
    }
}
