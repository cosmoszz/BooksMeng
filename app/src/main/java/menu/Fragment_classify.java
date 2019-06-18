package menu;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import classify.CategoryBean;
import classify.ClassifyMain;
import classify.ContentListData;
import classify.ContentListView;
import classify.FirstListViewAdapter;
import classify.SecondListViewAdapter;

public class Fragment_classify extends Fragment {

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


    View view;

    public Fragment_classify()
    {

    }

    public static Fragment_classify newInstance()
    {
        Fragment_classify fragment_classify=new Fragment_classify();
        return  fragment_classify;

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        //return  inflater.inflate(R.layout.fragment_classify,container,false);
        view=inflater.inflate(R.layout.fragment_classify,container,false);
        Fresco.initialize(view.getContext());
        InitData();
        InitView();
        LoadData();
        return view;
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
        String json=GetJson(view.getContext(),"category.json");
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
        firstListView=(ListView)view.findViewById(R.id.firstHead);
        secondListView=(ListView)view.findViewById(R.id.secondHead);
        contentListView=view.findViewById(R.id.contentListView);

        firstListViewAdapter=new FirstListViewAdapter(view.getContext(),firstListData);
        secondListViewAdapter=new SecondListViewAdapter(view.getContext(),secondListData);
        contentListViewAdapter =new ContentListView(view.getContext(),homeList);

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
