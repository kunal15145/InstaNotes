package mcproject.instanotesv1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        TextView sname=(TextView)findViewById(R.id.textView21);
        TextView email=(TextView)findViewById(R.id.textView23);
        TextView fname=(TextView)findViewById(R.id.textView13);
        TextView credit=(TextView)findViewById(R.id.textView22);
        ImageView dp=(ImageView)findViewById(R.id.imageView13);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null) {


            Uri uri = acct.getPhotoUrl();
            String str = acct.getDisplayName();
            String[] splitStr = str.split("\\s+");
            fname.setText(splitStr[0]);
            sname.setText(splitStr[1]);
//            dp.setImageURI(null);
//            dp.setImageURI(uri);

            email.setText(acct.getEmail());
            dp.setImageDrawable(getResources().getDrawable(R.drawable.person_photo));
            credit.setText("7");
        }
    }
}
