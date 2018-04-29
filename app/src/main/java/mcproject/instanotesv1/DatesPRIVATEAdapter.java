package mcproject.instanotesv1;

import android.content.Context;
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

public class DatesPRIVATEAdapter extends RecyclerView.Adapter<DatesPRIVATEAdapter.DatesViewHolder>{

    private Context ctx;
    private List<DatesPRIVATE> datesList;

    public DatesPRIVATEAdapter(Context ctx, List<DatesPRIVATE> datesList) {
        this.ctx = ctx;
        this.datesList = datesList;
    }

    //Inflating the list
    @Override
    public DatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.datesprivate_list, null);
        return new DatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DatesViewHolder holder, int position) {
        DatesPRIVATE dates=datesList.get(position);
        holder.textViewTitle.setText(dates.getTitle());
        holder.textViewDesc.setText(dates.getShortdesc());
        holder.imageView.setImageDrawable(ctx.getResources().getDrawable(dates.getImage()));

    }


    @Override
    // List size
    public int getItemCount() {
        return datesList.size();
    }


    class DatesViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle,textViewDesc;

        public DatesViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.datesimg);
            textViewTitle=itemView.findViewById(R.id.datestitle);
            textViewDesc=itemView.findViewById(R.id.datesdesc);

        }
    }
}
