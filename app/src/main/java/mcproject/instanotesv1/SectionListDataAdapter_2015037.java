package mcproject.instanotesv1;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class SectionListDataAdapter_2015037 extends RecyclerView.Adapter<SectionListDataAdapter_2015037.SingleItemRowHolder> {

    // Recycler View for my courses
    private static int COURSE_TYPE_CARD = 0;
    private static int COURSE_INFO_CARD = 1;
    private ArrayList<SingleItemModel_2015037> itemsList;
    private Context mContext;
    private static final String CoursesJoined = "Courses";
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    private ArrayList<String> courses;

    public SectionListDataAdapter_2015037(Context context, ArrayList<SingleItemModel_2015037> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    public void filterList(ArrayList<SingleItemModel_2015037> filterdNames) {
        this.itemsList = filterdNames;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position){
        if(position==0)
            return COURSE_INFO_CARD;
        else return COURSE_TYPE_CARD;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(i==COURSE_TYPE_CARD) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
            return new SingleItemRowHolder(v);
        }
        else if(i==COURSE_INFO_CARD) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardbranch, null);
            return new SingleItemRowHolder(v);
        }
        return null;
    }

    // Default functions
    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, final int i) {

        final SingleItemModel_2015037 singleItem = itemsList.get(i);
        if(holder.getItemViewType()==COURSE_TYPE_CARD){
            holder.cardtopic.setText(singleItem.getTopic());
            holder.cardsem.setText(singleItem.getSem());
            holder.cardbutton.setText(singleItem.getJoinbtn());
            holder.cardbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    make_changes_firebase(holder);
                    Toast.makeText(view.getContext(),holder.cardtopic.getText(),LENGTH_SHORT).show();
                    itemsList.remove(i);
                    notifyDataSetChanged();
                }
            });
        }
        else if(holder.getItemViewType()==COURSE_INFO_CARD){
            holder.cardtopic.setText(singleItem.getTopic());
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView cardtopic,cardsem;
        protected TextView cardbutton;
        protected ImageView cardimg;

        public SingleItemRowHolder(View view) {
            super(view);
            this.cardtopic = view.findViewById(R.id.cardtopic);
            this.cardbutton = view.findViewById(R.id.cardbutton);
            this.cardimg = view.findViewById(R.id.cardimg);
            this.cardsem = view.findViewById(R.id.cardsem);
        }

    }

    // added courses to firebase
    public void make_changes_firebase(final SingleItemRowHolder holder){
        firebaseFirestore.collection("users").document(firebaseUser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc=task.getResult();
                            courses = (ArrayList<String>) doc.get("Courses");
                            firebaseFirestore.collection("courses")
                                    .whereEqualTo("CourseName", holder.cardtopic.getText())
                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                    if(task.isSuccessful()){
                                        for (DocumentSnapshot doc:task.getResult()){
                                            courses.add(doc.getId());
                                            firebaseFirestore.collection("users").document(firebaseUser.getUid())
                                                    .update("Courses", courses);
                                        }
                                    }
                                }
                            });

                        }
                    }
                });


    }

}