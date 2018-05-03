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
 * Created by Harshit Verma on 15-03-2018.
 */

public class DatesPRIVATEAdapter_2015037 extends RecyclerView.Adapter<DatesPRIVATEAdapter_2015037.DatesViewHolder>{

    private Context ctx;
    private List<DatesPRIVATE_2015037> datesList;
    private String coursename;

    public DatesPRIVATEAdapter_2015037(Context ctx, List<DatesPRIVATE_2015037> datesList, String coursename) {
        this.ctx = ctx;
        this.datesList = datesList;
        this.coursename = coursename;
    }

    //Inflating the list
    @Override
    public DatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.datesprivate_list, null);
        return new DatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DatesViewHolder holder, int position) {
        DatesPRIVATE_2015037 dates=datesList.get(position);
        holder.textViewTitle.setText(dates.getTitle());
        holder.textViewDesc.setText(dates.getShortdesc());
        holder.imageView.setImageDrawable(ctx.getResources().getDrawable(dates.getImage()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,PreviewNotes_2015037.class);
                intent.putExtra("Date",holder.textViewTitle.getText());
                intent.putExtra("coursename",coursename);
                intent.putExtra("Flag",1);
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
        ImageView imageView;
        TextView textViewTitle,textViewDesc;
        CardView cardView;

        public DatesViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.datesimg);
            textViewTitle=itemView.findViewById(R.id.datestitle);
            textViewDesc=itemView.findViewById(R.id.datesdesc);
            cardView = itemView.findViewById(R.id.privatedata);
        }
    }
}
