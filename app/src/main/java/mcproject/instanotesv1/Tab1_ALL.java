package mcproject.instanotesv1;

import android.app.DatePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tab1_ALL extends Fragment{

    RecyclerView recyclerView2;
    DatesAdapter adapter2;


    DatePicker datepicker;
    Calendar currentDate;
    int day,month,year;
    TextView choosedate;

    List<Dates> datesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_dates_tab_all, container, false);

        currentDate=Calendar.getInstance();
        day=currentDate.get(Calendar.DAY_OF_MONTH);
        month=currentDate.get(Calendar.MONTH);
        year=currentDate.get(Calendar.YEAR);

        month=month+1;

        choosedate=rootView.findViewById(R.id.choosedate);
        choosedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(rootView.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        choosedate.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        datesList=new ArrayList<>();
        recyclerView2 = (RecyclerView) rootView.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Hard coded Transactions for now
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );
        datesList.add(
                new Dates("18th April, 2018","+2 more","Tuesday",R.drawable.unlock,R.drawable.person_photo,R.drawable.person_photo,R.drawable.person_photo)
        );





        adapter2 = new DatesAdapter(getActivity(), datesList);

        //setting adapter to recyclerview
        recyclerView2.setAdapter(adapter2);

        return rootView;

    }

}
