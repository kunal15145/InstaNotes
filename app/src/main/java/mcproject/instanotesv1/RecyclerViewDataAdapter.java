package mcproject.instanotesv1;

import android.content.Context;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

    private HashMap<Integer,ArrayList<SingleItemModel>> dataList;
    private Context mContext;

    public RecyclerViewDataAdapter(Context context,HashMap<Integer,ArrayList<SingleItemModel>> datalist1) {
        this.dataList = datalist1;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        ArrayList<SingleItemModel> singleSectionItems = dataList.get(i);
        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems);
        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected RecyclerView recycler_view_list;

        public ItemRowHolder(View view) {
            super(view);
            this.recycler_view_list = view.findViewById(R.id.recycler_view_list);

        }

    }

}