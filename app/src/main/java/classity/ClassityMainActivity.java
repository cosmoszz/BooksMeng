package classity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.booksmeng.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ClassityMainActivity extends AppCompatActivity {
    private List<String> menuList=new ArrayList<>();
    private List<CategoryBean.DataBean> homeList=new ArrayList<>();
    private List<Integer> showTitle;

    private ListView lv_menu;
    private ListView lv_home;

    private MenuAdapter menuAdapter;
    private HomeAdapter homeAdapter;
    private int currentItem;

    private TextView tv_title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classity_main);
        Fresco.initialize(this);
        InitView();
        LoadData();

    }
    private void LoadData()
    {
        String json=GetJson(this,"category.json");
        CategoryBean categoryBean=JSONObject.parseObject(json, CategoryBean.class);
        showTitle=new ArrayList<>();
        for(int i=0;i<categoryBean.getData().size();i++)
        {
            CategoryBean.DataBean dataBean=categoryBean.getData().get(i);
            showTitle.add(i);
            menuList.add(dataBean.getModuleTitle());
            homeList.add(dataBean);
        }
        tv_title.setText(categoryBean.getData().get(0).getModuleTitle());
        menuAdapter.notifyDataSetChanged();
        homeAdapter.notifyDataSetChanged();

    }
    private void InitView()
    {
        lv_menu=this.findViewById(R.id.lv_menu);
        lv_home=this.findViewById(R.id.lv_home);
        tv_title=this.findViewById(R.id.tv_titile);

        menuAdapter=new MenuAdapter(this,menuList);
        lv_menu.setAdapter(menuAdapter);

        homeAdapter=new HomeAdapter(this,homeList);
        lv_home.setAdapter(homeAdapter);

        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
                tv_title.setText(menuList.get(position));
                lv_home.setSelection(showTitle.get(position));
            }
        });

        lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollstate;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollstate=scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                if(this.scrollstate==AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                {
                    return;
                }
                int current=showTitle.indexOf(firstVisibleItem);
                if(currentItem!=current&&current>=0)
                {
                    currentItem=current;
                    tv_title.setText(menuList.get(current));
                    menuAdapter.setSelectItem(current);
                    menuAdapter.notifyDataSetInvalidated();
                }

            }
        });

    }

    public static String GetJson(Context context,String file)
    {
        StringBuilder stringBuilder=new StringBuilder();

        AssetManager assetManager=context.getAssets();

        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(
                    assetManager.open(file),"utf-8"
            ));
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
