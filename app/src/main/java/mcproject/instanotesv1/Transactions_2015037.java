package mcproject.instanotesv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Transactions_2015037 extends AppCompatActivity {


    RecyclerView recyclerView2;
    TransAdapter_2015037 adapter2;


    List<Trans_2015037> transList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transactions);
//        setContentView(R.layout.activity_feedback);
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(R.string.transactions_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        transList=new ArrayList<>();
        recyclerView2 = findViewById(R.id.transRecView);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        // Hard coded Transactions_2015037 for now
        transList.add(
                new Trans_2015037("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans_2015037("You uploaded new notes to Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans_2015037("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans_2015037("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans_2015037("You uploaded new notes to Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans_2015037("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans_2015037("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans_2015037("You uploaded new notes to Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );
        transList.add(
                new Trans_2015037("You unlocked Media Security - 16.4.2018","2 days ago",R.drawable.person_photo)
        );

        adapter2 = new TransAdapter_2015037(this, transList);

        //setting adapter to recyclerview
        recyclerView2.setAdapter(adapter2);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}

