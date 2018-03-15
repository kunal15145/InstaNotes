package mcproject.instanotesv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class AllDates extends AppCompatActivity {

    RecyclerView recyclerView2;
    DatesAdapter adapter2;


    List<Dates> datesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_dates);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(R.string.MC_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        datesList=new ArrayList<>();
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        // Hard coded Transactions for now
        datesList.add(
                new Dates("18th April, 2018","Tuesday",R.drawable.unlock)
        );
        datesList.add(
                new Dates("16th April, 2018","Tuesday",R.drawable.unlock)
        );
        datesList.add(
                new Dates("16th April, 2018","Tuesday",R.drawable.unlock)
        );
        datesList.add(
                new Dates("16th April, 2018","Tuesday",R.drawable.unlock)
        );
        datesList.add(
                new Dates("16th April, 2018","Tuesday",R.drawable.unlock)
        );
        datesList.add(
                new Dates("16th April, 2018","Tuesday",R.drawable.unlock)
        );

        adapter2 = new DatesAdapter(this, datesList);

        //setting adapter to recyclerview
        recyclerView2.setAdapter(adapter2);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
