package mcproject.instanotesv1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Harshit Verma on 15-03-2018.
 */

public class DatesALLAdapter extends RecyclerView.Adapter<DatesALLAdapter.DatesViewHolder>{

    private Context ctx;
    private List<DatesALL> datesList;
    private String coursename;

    public DatesALLAdapter(Context ctx, List<DatesALL> datesList) {
        this.ctx = ctx;
        this.datesList = datesList;
    }

    public DatesALLAdapter(Context ctx, List<DatesALL> datesList, String coursename) {
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,PreviewNotes.class);
                intent.putExtra("Date",holder.textViewTitle.getText());
                ctx.startActivity(intent);
            }
        });
    }


    @Override
    // List size
    public int getItemCount() {
        return datesList.size();
    }


    class DatesViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView,datep1,datep2,datep3;
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
