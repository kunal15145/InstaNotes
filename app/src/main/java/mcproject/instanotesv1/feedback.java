package mcproject.instanotesv1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        // Get the ActionBar here to configure the way it behaves.
//        final ActionBar ab = getSupportActionBar();
//        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
//        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
//        ab.setDisplayShowTitleEnabled(false); // disable the defaul
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
