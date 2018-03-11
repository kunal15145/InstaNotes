package mcproject.instanotesv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class join_courses extends AppCompatActivity {


    ArrayList<SectionDataModel> allSampleData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_courses);
        allSampleData = new ArrayList<>();


        createDummyData();


        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);


    }

    public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            SectionDataModel dm = new SectionDataModel();

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 5; j++) {
                if(j==1)
                {
                    singleItem.add(new SingleItemModel("Designing Human Centered Systems ", "URL ","Winter 2018","Join"));
                }
                else
                {
                    singleItem.add(new SingleItemModel("Mobile Computing ", "URL ","Monsoon 2018","Join"));
                }

            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }


}
