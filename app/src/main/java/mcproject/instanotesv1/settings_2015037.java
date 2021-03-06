package mcproject.instanotesv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class settings_2015037 extends AppCompatActivity {

    Switch switch1, switch2;

    // Wifi and Notificaions Settings
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(R.string.Settings_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        working with switch1
        switch1= (Switch) findViewById(R.id.switch1_settings);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {

                //if checked is on
                if(ischecked == true){
                    Toast.makeText(settings_2015037.this,"ON", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(settings_2015037.this, "OFF", Toast.LENGTH_SHORT).show();}
            }
        });

        //        working with switch2
        switch2= (Switch) findViewById(R.id.switch2_settings);

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {

                //if checked is on
                if(ischecked == true){
                    Toast.makeText(settings_2015037.this,"ON", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(settings_2015037.this, "OFF", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
