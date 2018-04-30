package mcproject.instanotesv1;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private static final String OWN_TAG = "OWN";
    private static final String DATE_TAG = "DATE";
    private static final String Course_TAG="Course";

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
                .whereEqualTo(Course_TAG,coursename)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        for(DocumentSnapshot documentSnapshot:documentSnapshots){
                            ArrayList<Map<String,Object>> list = (ArrayList<Map<String, Object>>) documentSnapshot.get("User_uploads");
                            String date = (String) documentSnapshot.get("DATE");
                            SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
                            Date dt1= null;
                            try {
                                dt1 = format1.parse(date);
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            DateFormat format2=new SimpleDateFormat("EEEE");
                            String finalDay=format2.format(dt1);
                            String s = (String) documentSnapshot.get("OWN");
                            if(s.equals("0")){
                                datesList.add(new DatesALL(date,String.valueOf(list.size())+"uploads",finalDay,R.drawable.unlock));
                            }
                            else if(s.equals("1")){
                                datesList.add(new DatesALL(date,String.valueOf(list.size())+"uploads",finalDay,R.drawable.lock));
                            }
                        }
                        datesList.sort(new Comparator<DatesALL>() {
                            @Override
                            public int compare(DatesALL datesALL, DatesALL t1) {
                                return datesALL.getTitle().compareTo(t1.getTitle());
                            }
                        });
                        adapter2.notifyDataSetChanged();
                    }
                });

    }

}
