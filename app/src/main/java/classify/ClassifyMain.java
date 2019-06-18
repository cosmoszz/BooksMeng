package classify;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;

import com.example.administrator.booksmeng.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ClassifyMain extends AppCompatActivity {
    //数据
    private List<String> firstListData;
    private List<String> secondListData;
    private List<CategoryBean.DataBean> homeList=new ArrayList<>();

    private List<ContentListData> contentListData;

    //View
    private ListView firstListView;
    private ListView secondListView;
    private ListView contentListView;

    //Adapter
    private FirstListViewAdapter firstListViewAdapter;
    private SecondListViewAdapter secondListViewAdapter;
    private ContentListView contentListViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.classify_main_1);
        InitData();
        InitView();
        LoadData();
    }

    public static String GetJson(Context context, String file)
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
    private void LoadData() {
        String json=GetJson(this,"category.json");
        CategoryBean categoryBean=JSONObject.parseObject(json, CategoryBean.class);
        //showTitle=new ArrayList<>();
        for(int i=0;i<categoryBean.getData().size();i++)
        {
            CategoryBean.DataBean dataBean=categoryBean.getData().get(i);
            //showTitle.add(i);
            //menuList.add(dataBean.getModuleTitle());
            firstListData.add(dataBean.getModuleTitle());
            secondListData.add(dataBean.getModuleTitle());
            homeList.add(dataBean);
        }
        //tv_title.setText(categoryBean.getData().get(0).getModuleTitle());
        //menuAdapter.notifyDataSetChanged();
        //homeAdapter.notifyDataSetChanged();
        firstListViewAdapter.notifyDataSetChanged();
        secondListViewAdapter.notifyDataSetChanged();
    }

    private void InitView() {
        firstListView=(ListView)findViewById(R.id.firstHead);
        secondListView=(ListView)findViewById(R.id.secondHead);
        contentListView=findViewById(R.id.contentListView);

        firstListViewAdapter=new FirstListViewAdapter(this,firstListData);
        secondListViewAdapter=new SecondListViewAdapter(this,secondListData);
        contentListViewAdapter =new ContentListView(this,homeList);

        firstListView.setAdapter(firstListViewAdapter);
        secondListView.setAdapter(secondListViewAdapter);
        contentListView.setAdapter(contentListViewAdapter);



        firstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                firstListViewAdapter.setSelectPosition(position);
                firstListViewAdapter.notifyDataSetInvalidated();
                secondListView.setSelection(position);
            }
        });
        secondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secondListViewAdapter.setSelectPosition(position);
                secondListViewAdapter.notifyDataSetInvalidated();
                contentListView.setSelection(position);
            }
        });

        contentListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollstate;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollstate=scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(this.scrollstate==AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                {
                    return;
                }
                firstListViewAdapter.setSelectPosition(firstVisibleItem);
                firstListViewAdapter.notifyDataSetInvalidated();
            }
        });
    }

    private void InitData() {
        this.firstListData=new ArrayList<>();
        this.secondListData=new ArrayList<>();
        this.contentListData=new ArrayList<>();

        for (int i=0;i<10;i++)
        {
            //this.firstListData.add(" ᠠᠰᠳᠹᠠᠹ ᠠᠰᠤᠳ");
        }

        for(int i=0;i<5;i++)
        {
           // this.secondListData.add(" ᠠᠰᠳᠹᠠᠹ ᠠᠰᠤᠳ");
        }

        for(int i=0;i<10;i++)
        {
            ContentListData contentListData1=new ContentListData("ᠠᠰᠳᠹᠠᠹ"
                    ,R.drawable.blue_background
                    ,"https://wicdn.xiaohongchun.com/xhc-plat/1498710472390_45K78X5reB.png");
           // this.contentListData.add(contentListData1);
        }

    }
}
