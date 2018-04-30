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
    private static final String User_ID_TAG = "UserID";
    private static final String Unlock_TAG = "Unlocks";
    String coursename;

    public DatesALLAdapter(Context ctx, List<DatesALL> datesList, String coursename) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        this.ctx = ctx;
        this.datesList = datesList;
        this.coursename = coursename;
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
        int i=0;
        if(i==0)
        {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx,PreviewNotes.class);
                    intent.putExtra("Date",holder.textViewTitle.getText());
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
                                    firebaseFirestore.collection("unlocks")
                                            .whereEqualTo(User_ID_TAG, firebaseUser.getUid())
                                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot documentSnapshots) {

                                            if(documentSnapshots.isEmpty()){
                                                Map<String,Object> NewUnlock = new HashMap<>();
                                                NewUnlock.put(User_ID_TAG, firebaseUser.getUid());
                                                ArrayList<String> unl=new ArrayList<String>();
                                                NewUnlock.put(Unlock_TAG, unl);
                                                firebaseFirestore.collection("unlocks").document().set(NewUnlock)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {

                                                            }
                                                        });
                                            }
                                            else{
                                                for(DocumentSnapshot doc : documentSnapshots){
                                                    ArrayList<String> unl= (ArrayList<String>) doc.get(Unlock_TAG);
                                                    
                                                    firebaseFirestore.collection("unlocks").document(doc.getId())
                                                            .update(Unlock_TAG, unl);
                                                }
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
