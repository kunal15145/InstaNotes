package mcproject.instanotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView previewEmailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        previewEmailid = findViewById(R.id.emailid);
        Intent intent = getIntent();
        String email = intent.getStringExtra(MainActivity.kl);
        previewEmailid.setText(email);
    }
}
