package mcproject.instanotesv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Transactions extends AppCompatActivity {


    RecyclerView recyclerView2;
    TransAdapter adapter2;


    List<Trans> transList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        transList=new ArrayList<>();
        recyclerView2 = (RecyclerView) findViewById(R.id.transRecView);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        transList.add(
                new Trans("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans("You uploaded new notes to Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans("You uploaded new notes to Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans("You uploaded new notes to Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );

        adapter2 = new TransAdapter(this, transList);

        //setting adapter to recyclerview
        recyclerView2.setAdapter(adapter2);
    }
    }

