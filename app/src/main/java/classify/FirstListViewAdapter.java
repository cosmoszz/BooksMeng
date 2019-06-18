package classify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.booksmeng.R;


import java.util.List;

public class FirstListViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> dataList;
    private int selectPosition=0;

    public FirstListViewAdapter(Context context,List<String> dataList)
    {
        this.context=context;
        this.dataList=dataList;
    }
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return dataList.size();
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
        return dataList.get(position);
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
        //加载android 资源
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=View.inflate(this.context,R.layout.first_listview_item,null);

            viewHolder.textView=(TextView)convertView.findViewById(R.id.listViewText);
            viewHolder.imageView=convertView.findViewById(R.id.bluePic);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //做颜色背景修改
        if(position==this.getSelectPosition())
        {
            //选中
            viewHolder.textView.setBackgroundResource(R.drawable.flower_background);

            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.blue));
        }
        else
        {
            //未选中
            viewHolder.textView.setBackgroundResource(0);
            viewHolder.textView.setBackgroundColor(context.getResources().getColor(R.color.white));
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.black));
        }
        //赋值
        viewHolder.textView.setText(dataList.get(position));
        return convertView;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    private  static  class ViewHolder
    {
        TextView textView;
        ImageView imageView;
    }
}
