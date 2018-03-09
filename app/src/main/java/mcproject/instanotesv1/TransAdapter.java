package mcproject.instanotesv1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.TransViewHolder>{


    private Context ctx;
    private List<Trans> transList;

    public TransAdapter(Context ctx, List<Trans> transList) {
        this.ctx = ctx;
        this.transList = transList;
    }

    @Override
    public TransViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.trans_list, null);
        return new TransViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransViewHolder holder, int position) {
        Trans trans=transList.get(position);
        holder.transtitle.setText(trans.getTitle());
        holder.transdesc.setText(trans.getShortdesc());
        holder.transimg.setImageDrawable(ctx.getResources().getDrawable(trans.getImage()));
    }

    @Override
    public int getItemCount() {
        return transList.size();
    }

    class TransViewHolder extends RecyclerView.ViewHolder{

        ImageView transimg;
        TextView transtitle,transdesc;

        public TransViewHolder(View itemView) {
            super(itemView);

            transimg=itemView.findViewById(R.id.transimg);
            transtitle=itemView.findViewById(R.id.transtitle);
            transdesc=itemView.findViewById(R.id.transdesc);

        }
    }
}
