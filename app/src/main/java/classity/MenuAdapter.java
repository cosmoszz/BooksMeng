package classity;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.booksmeng.R;

import java.util.List;

//左侧的菜单
public class MenuAdapter extends BaseAdapter {
    private int selectItem=0;
    private Context context;
    private List<String> list;

    public MenuAdapter(Context context,List<String> list)
    {
        this.context=context;
        this.list=list;
    }

    public int getSelectItem()
    {
        return this.selectItem;
    }
    public void setSelectItem(int selectItem)
    {
        this.selectItem=selectItem;
    }
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return this.list.size();
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
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView= View.inflate(this.context, R.layout.item_menu,null);
            viewHolder.textView=(TextView)convertView.findViewById(R.id.item_name);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        if(position==this.selectItem)
        {
            Log.d("MenuAdapterGetView",String.valueOf(position));
            viewHolder.textView.setBackgroundColor(Color.WHITE);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.green));
        }
        else
        {
            Log.d("MenuAdapterGetView",String.valueOf(position));
            Log.d("MenuAdapterGetSelect",String.valueOf(this.selectItem));
            viewHolder.textView.setBackgroundColor(context.getResources().getColor(R.color.blueviolet));
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.black));
        }
        viewHolder.textView.setText(list.get(position));
        return convertView;
    }
    private static  class ViewHolder
    {
        private TextView textView;
    }
}
