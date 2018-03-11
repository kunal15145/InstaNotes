package mcproject.instanotesv1;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private static int COURSE_TYPE_CARD = 0;
    private static int COURSE_INFO_CARD = 1;
    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position){
        if(position==0)
            return COURSE_INFO_CARD;
        else return COURSE_TYPE_CARD;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(i==COURSE_TYPE_CARD) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
            return new SingleItemRowHolder(v);
        }
        else if(i==COURSE_INFO_CARD) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardbranch, null);
            return new SingleItemRowHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, int i) {

        final SingleItemModel singleItem = itemsList.get(i);
        if(holder.getItemViewType()==COURSE_TYPE_CARD){
            holder.cardtopic.setText(singleItem.getTopic());
            holder.cardsem.setText(singleItem.getSem());
            holder.cardbutton.setText(singleItem.getJoinbtn());
            holder.cardbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),singleItem.getTopic(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView cardtopic,cardsem;
        protected TextView cardbutton;
        protected ImageView cardimg;

        public SingleItemRowHolder(View view) {
            super(view);
            this.cardtopic = view.findViewById(R.id.cardtopic);
            this.cardbutton = view.findViewById(R.id.cardbutton);
            this.cardimg = view.findViewById(R.id.cardimg);
            this.cardsem = view.findViewById(R.id.cardsem);

        }

    }

}