package mcproject.instanotesv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity{

    RecyclerView recyclerView;
    NotifAdapter adapter;


    List<Notif> notifList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        notifList=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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


}
