package mcproject.instanotesv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity{

    RecyclerView recyclerView;
    NotifAdapter adapter;


    List<Notif> notifList;
    // Main activity for notifications

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notifications);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        notifList=new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Hard coded default values
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        notifList.add(
                new Notif("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        adapter = new NotifAdapter(this, notifList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
