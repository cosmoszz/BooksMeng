package classity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.renderscript.Sampler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.booksmeng.ListViewTest;
import com.example.administrator.booksmeng.MainActivity;
import com.example.administrator.booksmeng.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URL;
import java.util.List;

public class HomeItemAdapter extends BaseAdapter {
    private  Context context;
    private List<CategoryBean.DataBean.DataListBean> dataListBeans;

    public HomeItemAdapter(Context context, List<CategoryBean.DataBean.DataListBean> dataListBean) {
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
            return 10;
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
        //return this.dataListBeans.get(position);
        return  this.dataListBeans.size();
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
            convertView=View.inflate(this.context, R.layout.item_home_category,null);
            viewHolder=new ViewHolder();
            viewHolder.textView=convertView.findViewById(R.id.item_home_name);
           // viewHolder.mongolTextView=convertView.findViewById(R.id.item_home_name);
            viewHolder.draweeView=convertView.findViewById(R.id.item_album);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(dataListBean.getTitle());

        Uri uri=Uri.parse(dataListBean.getImgURL());
        Log.d("dataListBean.getTitle1",dataListBean.getTitle());
        if(dataListBean.getTitle().equals("防晒"))
        {
            Log.d("dataListBean.getTitle",dataListBean.getTitle());
             uri = Uri.parse("res:///" + R.drawable.ic_home);
        }
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


                SimpleDraweeView draweeView= v.findViewById(R.id.item_album);

                ViewHolder viewHolder1= (ViewHolder) draweeView.getTag();
                Context contextView=draweeView.getContext();
                TextView textView=v.findViewById(R.id.item_home_name);
                //Common.MongolTextView textView=v.findViewById(R.id.item_home_name);
                //Toast toast = Toast.makeText(contextView,textView.getText(),Toast.LENGTH_LONG);
                Log.d("dataListBean.onClick", "fasdfa");
                Log.d("dataListBean.onClick", viewHolder1.textView.getText().toString());
                Log.d("dataListBean.onClick", viewHolder1.draweeViewUri);

                Intent intent=new Intent();
                //intent.setClass(MainActivity.this, BottomNavigationActivty.class);
                intent.setClass(v.getContext(), ListViewTest.class);
                intent.putExtra("key",viewHolder1.draweeViewUri);
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    private static class ViewHolder
    {
        private TextView textView;
       // private Common.MongolTextView mongolTextView;
        private SimpleDraweeView draweeView;
        private  String draweeViewUri;

    }
}
