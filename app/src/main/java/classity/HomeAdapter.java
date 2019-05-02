package classity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.booksmeng.R;

import java.util.List;

public class HomeAdapter extends BaseAdapter {


    private Context context;
    private List<CategoryBean.DataBean> list;

    public HomeAdapter(Context context,List<CategoryBean.DataBean> list)
    {
        this.context=context;
        this.list=list;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryBean.DataBean dataBean=list.get(position);

        List<CategoryBean.DataBean.DataListBean> dataListBeanGetView = dataBean.getDataList();;
        ViewHolder viewHolder=null;

        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=View.inflate(this.context, R.layout.item_home,null);
            viewHolder.gridViewForScrollView=convertView.findViewById(R.id.gridView);
            viewHolder.textView=convertView.findViewById(R.id.blank);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        HomeItemAdapter adapter = new HomeItemAdapter(this.context,dataListBeanGetView );
        viewHolder.textView.setText(dataBean.getModuleTitle());
        viewHolder.gridViewForScrollView.setAdapter(adapter);
        return convertView;

    }

    private static class ViewHolder
    {
        private TextView textView;
        private  GridViewForScrollView gridViewForScrollView;
    }
}
