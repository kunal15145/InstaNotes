package mcproject.instanotesv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

public class Help_2015037 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_help);
        // setting up toolbar
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(R.string.help_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.main, new HelpFragMain_2015037()).commit();
    }

    @Override
    //back button
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
