package mcproject.instanotesv1;

import android.content.Context;
        import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyCoursesAdapter_2015037 extends RecyclerView.Adapter<MyCoursesAdapter_2015037.MyViewHolder> {

    private Context mContext ;
    private List<mycourse_2015037> mData ;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;

    public MyCoursesAdapter_2015037(Context mContext, List<mycourse_2015037> mData) {
        this.mContext = mContext;
        this.mData = mData;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }
    // Recycler view Adapter for courses

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.mycourses_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.CourseName.setText(mData.get(position).getCourseName());
        holder.Semester.setText(mData.get(position).getSemester());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DatesTab_2015037.class);
                intent.putExtra("CourseName",mData.get(position).getCourseName());
                intent.putExtra("SemName",mData.get(position).getSemester());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                mContext.startActivity(intent);

            }
        });

        holder.ibPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popup = new PopupMenu(view.getContext(),view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId()==R.id.new_game){
                            firebaseFirestore.collection("courses")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if(task.isSuccessful()){
                                                for (DocumentSnapshot documentSnapshot:task.getResult()){
                                                    if(documentSnapshot.get("CourseName").equals(holder.CourseName.getText())){
                                                        final String tag = documentSnapshot.getId();
                                                        firebaseFirestore.collection("users")
                                                                .document(firebaseUser.getUid())
                                                                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                                                        if(e!=null){
                                                                            return;
                                                                        }
                                                                        else if (documentSnapshot!=null && documentSnapshot.exists()){
                                                                            final ArrayList<String> Courses;
                                                                            Courses = (ArrayList<String>) documentSnapshot.get("Courses");
                                                                            System.out.println(tag);
                                                                            Courses.remove(tag);
                                                                            firebaseFirestore.collection("users")
                                                                                    .document(firebaseUser.getUid())
                                                                                    .update("Courses",Courses);
                                                                        }
                                                                    }
                                                                });
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    });
                            return true;
                        }
                        return true;
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_unenroll, popup.getMenu());
                popup.show();
            }
        });

    }
    // Default functions
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView CourseName,Semester;
        ImageView img_book_thumbnail;
        ImageButton ibPopupMenu;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            CourseName = itemView.findViewById(R.id.textView9);
            ibPopupMenu=itemView.findViewById(R.id.ib_popup_menu);
            Semester = itemView.findViewById(R.id.textView8);
            img_book_thumbnail = itemView.findViewById(R.id.imageView11);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }


}