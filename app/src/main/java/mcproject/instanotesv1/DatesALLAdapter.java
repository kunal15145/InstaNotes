package mcproject.instanotesv1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Harshit Verma on 15-03-2018.
 */

public class DatesALLAdapter extends RecyclerView.Adapter<DatesALLAdapter.DatesViewHolder>{

    private Context ctx;
    private List<DatesALL> datesList;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    private static final String Unlock_TAG = "Unlocks";
    private String coursename;
    private static final String OWN_TAG = "OWN";
    private static final String DATE_TAG = "DATE";
    private static final String Visitors = "Visitors";
    private static final String Course_TAG="Course";


    public DatesALLAdapter(Context ctx, List<DatesALL> datesList, String coursename) {
        this.ctx = ctx;
        this.datesList = datesList;
        this.coursename = coursename;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    //Inflating the list
    @Override
    public DatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.dates_list, null);
        return new DatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DatesViewHolder holder, int position) {
        DatesALL dates=datesList.get(position);
        holder.textViewTitle.setText(dates.getTitle());
        holder.dateperson.setText(dates.getDateperson());
        holder.textViewDesc.setText(dates.getShortdesc());
        holder.imageView.setImageDrawable(ctx.getResources().getDrawable(dates.getImage()));
        if(holder.imageView.getDrawable().getConstantState()==ctx.getResources().getDrawable(R.drawable.unlock).getConstantState())
        {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx,PreviewNotes.class);
                    intent.putExtra("Date",holder.textViewTitle.getText());
                    intent.putExtra("coursename",coursename);
                    intent.putExtra("Flag",0);
                    ctx.startActivity(intent);

                }
            });
        }
        else
        {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(ctx);
                    builder.setMessage("Do you want to unlock this lecture? This will cost you 1 instacoin")
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    firebaseFirestore.collection("uploads")
                                            .whereEqualTo(OWN_TAG,"1")
                                            .whereEqualTo(Course_TAG,coursename)
                                            .whereEqualTo(DATE_TAG,holder.textViewTitle)
                                            .get()
                                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                @Override
                                                public void onSuccess(QuerySnapshot documentSnapshots) {
                                                    for(DocumentSnapshot documentSnapshot:documentSnapshots){
                                                        ArrayList<Map<String,Object>> obj = (ArrayList<Map<String, Object>>) documentSnapshot.get("User_uploads");
                                                        ArrayList<String> list1 = (ArrayList<String>) obj.get(0).get(Visitors);
                                                        list1.add(firebaseUser.getUid());
                                                        firebaseFirestore.collection("uploads")
                                                                .document(documentSnapshot.getId())
                                                                .update(Visitors,list1);
                                                    }
                                                }
                                            });
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
            });


        }

    }


    @Override
    // List size
    public int getItemCount() {
        return datesList.size();
    }


    class DatesViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle,textViewDesc,dateperson;
        CardView cardView;
        public DatesViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.datesimg);
            dateperson=itemView.findViewById(R.id.dateperson);
            textViewTitle=itemView.findViewById(R.id.datestitle);
            textViewDesc=itemView.findViewById(R.id.datesdesc);
            cardView = itemView.findViewById(R.id.datasingle);
        }
    }
}
