package mcproject.instanotesv1;

import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

        import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<mycourse> mData ;


    public RecyclerViewAdapter(Context mContext, List<mycourse> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.CourseName.setText(mData.get(position).getCourseName());
        holder.Semester.setText(mData.get(position).getSemester());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AllDates.class);
                intent.putExtra("CourseName",mData.get(position).getCourseName());
                intent.putExtra("SemName",mData.get(position).getSemester());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                mContext.startActivity(intent);

            }
        });




        /*holder.ibPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(),view);
                popup.setOnMenuItemClickListener(this);// to implement on click event on items of menu
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();

            }
        });*/
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