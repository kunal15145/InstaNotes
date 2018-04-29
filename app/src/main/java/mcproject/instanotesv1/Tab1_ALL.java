package mcproject.instanotesv1;

import android.app.DatePickerDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tab1_ALL extends Fragment{

    RecyclerView recyclerView2;
    DatesALLAdapter adapter2;

    Calendar currentDate;
    int day,month,year;
    TextView choosedate;
    FirebaseUser firebaseUser;
    String coursename;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    List<DatesALL> datesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        DatesTab datesTab = (DatesTab)getActivity();
        coursename = datesTab.getCoursename();
        final View rootView = inflater.inflate(R.layout.fragment_dates_tab_all, container, false);

        currentDate=Calendar.getInstance();
        day=currentDate.get(Calendar.DAY_OF_MONTH);
        month=currentDate.get(Calendar.MONTH);
        year=currentDate.get(Calendar.YEAR);


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
        recyclerView2 = rootView.findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter2 = new DatesALLAdapter(getActivity(), datesList);
        recyclerView2.setAdapter(adapter2);
        addnotes();
        return rootView;

    }

    private void addnotes() {
        firebaseFirestore.collection("uploads")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if(e!=null){
                            return;
                        }
                        else {
                            for(DocumentSnapshot documentSnapshot:documentSnapshots){
                                if(documentSnapshot.get("Course").equals(coursename)){
                                    ArrayList<String> list = (ArrayList<String>) documentSnapshot.get("Images");
                                    int count = list.size();
                                    String input_date= (String) documentSnapshot.get("DATE");
                                    SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
                                    Date dt1= null;
                                    try {
                                        dt1 = format1.parse(input_date);
                                    } catch (ParseException e1) {
                                        e1.printStackTrace();
                                    }
                                    DateFormat format2=new SimpleDateFormat("EEEE");
                                    String finalDay=format2.format(dt1);
                                    datesList.add(new DatesALL(input_date,String.valueOf(count),finalDay,R.drawable.unlock,R.drawable.user,R.drawable.user,R.drawable.user));
                                }
                                else {
                                    continue;
                                }
                            }
                        }
                        adapter2.notifyDataSetChanged();
                    }
                });
    }

}
