package mcproject.instanotesv1;

        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.List;

/**
 * Created by Harshit on 11/03/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Book> mData ;


    public RecyclerViewAdapter(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_book,parent,false);
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

                Intent intent = new Intent(mContext,Book_Activity.class);

                // passing data to the book activity
                intent.putExtra("CourseName",mData.get(position).getCourseName());
                intent.putExtra("SemName",mData.get(position).getSemester());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView CourseName,Semester;
        ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            CourseName = (TextView) itemView.findViewById(R.id.textView9) ;
            Semester = (TextView) itemView.findViewById(R.id.textView8) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.imageView11);

            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}