package classify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.booksmeng.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


/**
 * 分类界面的 所有的item
 */
public class ContentListViewItemAdapter extends BaseAdapter {


    private Context context;
    private List<ContentListData> contentListData;

    private List<CategoryBean.DataBean.DataListBean> dataListBeans;

//    public ContentListViewItemAdapter(Context context, List<ContentListData> contentListData) {
//        this.context = context;
//        this.contentListData = contentListData;
//    }
    public ContentListViewItemAdapter(Context context, List<CategoryBean.DataBean.DataListBean> dataListBean) {
        this.context=context;
        this.dataListBeans=dataListBean;
    }


    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        if(this.dataListBeans!=null)
            return this.dataListBeans.size();
        else
            return 100;
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
        return this.dataListBeans.get(position);
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
        CategoryBean.DataBean.DataListBean dataListBean=this.dataListBeans.get(position);
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=View.inflate(this.context, R.layout.content_listview_item,null);
            viewHolder.textView=convertView.findViewById(R.id.contentText);
            viewHolder.draweeView=convertView.findViewById(R.id.contentImage);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
       // ContentListData contentListData_temp=this.contentListData.get(position);
        //viewHolder.textView.setText(contentListData_temp.getContentTitle());

        viewHolder.textView.setText(dataListBean.getTitle());

        viewHolder.textView.setTextColor(context.getResources().getColor(R.color.black));
        viewHolder.textView.setBackgroundColor(context.getResources().getColor(R.color.white));


        //Uri uri=Uri.parse(contentListData_temp.getItemUrl());

        Uri uri=Uri.parse(dataListBean.getImgURL());
        //Log.d("dataListBean.getTitle1",contentListData_temp.getContentTitle());
        if(dataListBean.getTitle().equals("防晒"))
        {
            Log.d("dataListBean.getTitle",dataListBean.getTitle());
            //uri = Uri.parse("res:///" + R.drawable.ic_home);
        }
        //uri=Uri.parse("res:///" + R.drawable.ic_home);
        viewHolder.draweeView.setImageURI(uri);
        viewHolder.draweeViewUri=uri.toString();
        viewHolder.draweeView.setTag(viewHolder);


        viewHolder.draweeView.setOnClickListener(new View.OnClickListener() {

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {


//                SimpleDraweeView draweeView= v.findViewById(R.id.item_album);
//
//                ViewHolder viewHolder1= (ViewHolder) draweeView.getTag();
//                Context contextView=draweeView.getContext();
//                TextView textView=v.findViewById(R.id.item_home_name);
//                //Common.MongolTextView textView=v.findViewById(R.id.item_home_name);
//                //Toast toast = Toast.makeText(contextView,textView.getText(),Toast.LENGTH_LONG);
//                Log.d("dataListBean.onClick", "fasdfa");
//                Log.d("dataListBean.onClick", viewHolder1.textView.getText().toString());
//                Log.d("dataListBean.onClick", viewHolder1.draweeViewUri);
//
//                Intent intent=new Intent();
//                //intent.setClass(MainActivity.this, BottomNavigationActivty.class);
//                intent.setClass(v.getContext(), ListViewTest.class);
//                intent.putExtra("key",viewHolder1.draweeViewUri);
//                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    public static class ViewHolder
    {
        public TextView textView;
        private SimpleDraweeView draweeView;
        private  String draweeViewUri;

    }
}
