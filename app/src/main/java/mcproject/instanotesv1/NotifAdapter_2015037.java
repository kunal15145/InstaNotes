package mcproject.instanotesv1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotifAdapter_2015037 extends RecyclerView.Adapter<NotifAdapter_2015037.NotifViewHolder>{

    // Recycler view for Notificaions
    private Context ctx;
    private List<Notif_2015037> notifList;

    public NotifAdapter_2015037(Context ctx, List<Notif_2015037> notifList) {
        this.ctx = ctx;
        this.notifList = notifList;
    }

    //Inflating the list
    @Override
    public NotifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.notifications_list, null);
        return new NotifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotifViewHolder holder, int position) {
        Notif_2015037 notif=notifList.get(position);
        holder.textViewTitle.setText(notif.getTitle());
        holder.textViewDesc.setText(notif.getShortdesc());
        holder.imageView.setImageDrawable(ctx.getResources().getDrawable(notif.getImage()));
    }

    @Override
    // List size
    public int getItemCount() {
        return notifList.size();
    }

    class NotifViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle,textViewDesc;

        public NotifViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDesc=itemView.findViewById(R.id.textViewShortDesc);

        }
    }
}
