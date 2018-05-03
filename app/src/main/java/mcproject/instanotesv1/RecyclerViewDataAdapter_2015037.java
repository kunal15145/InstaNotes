package mcproject.instanotesv1;

import android.content.Context;
        import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
        import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerViewDataAdapter_2015037 extends RecyclerView.Adapter<RecyclerViewDataAdapter_2015037.ItemRowHolder> {

    private ArrayList<ArrayList<SingleItemModel_2015037>> dataList;
    private Context mContext;

    public RecyclerViewDataAdapter_2015037(Context context, ArrayList<ArrayList<SingleItemModel_2015037>> datalist1) {
        this.dataList = datalist1;
        this.mContext = context;
    }
    // Default Functions for Adapter
    @Override
    public ItemRowHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    public void filterList(ArrayList<ArrayList<SingleItemModel_2015037>> filterdNames) {
        this.dataList = filterdNames;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder itemRowHolder, int i) {
        final ArrayList<SingleItemModel_2015037> singleSectionItems = dataList.get(i);
        final SectionListDataAdapter_2015037 itemListDataAdapter = new SectionListDataAdapter_2015037(mContext, singleSectionItems);
        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder{

        protected RecyclerView recycler_view_list;

        public ItemRowHolder(View view) {
            super(view);
            /*SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(recycler_view_list);*/
            this.recycler_view_list = view.findViewById(R.id.recycler_view_list);
        }

    }

}