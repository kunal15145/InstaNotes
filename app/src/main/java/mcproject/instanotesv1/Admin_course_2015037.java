package mcproject.instanotesv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Admin_course_2015037 extends AppCompatActivity {

    // Accesing courses
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course);

        //Setting up toolbar
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(Admin_course_2015037.this, Admin_addcourses_2015037.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_viewfeedback, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId()==R.id.viewfeedback){
//            Intent intent = new Intent(.this, .class);
//            intent.putExtra("", );
//            startActivity(intent);
//            finish();
            return true;
        }
        return  super.onOptionsItemSelected(menuItem);
    }
}
