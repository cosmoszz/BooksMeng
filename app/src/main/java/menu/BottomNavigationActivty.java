package menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.booksmeng.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BottomNavigationActivty extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener,ViewPager.OnTouchListener {

    @BindView(R.id.widgetNavigationView)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    Fragment_classify fragment_classify;
    Fragment_home fragment_home;
    Fragment_library fragment_library;
    Fragment_store fragment_store;

    List<Fragment> fragmentList;

    Unbinder unbinder;

    BottomNavigationAdapter bottomNavigationAdapter;

    BottomNavigationAdapterHelper bottomNavigationAdapterHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        unbinder=ButterKnife.bind(this);
        this.init();
    }

    @SuppressLint("NewApi")
    private void init()
    {
        fragmentList=new ArrayList<>();
        fragment_classify=new Fragment_classify();
        fragment_home=new Fragment_home();
        fragment_library=new Fragment_library();
        fragment_store=new Fragment_store();
        if(!fragmentList.contains(fragment_classify))
        {
            fragmentList.add(fragment_classify);
        }
        if(!fragmentList.contains(fragment_home))
        {
            fragmentList.add(fragment_home);
        }
        if(!fragmentList.contains(fragment_library))
        {
            fragmentList.add(fragment_library);
        }
        if(!fragmentList.contains(fragment_store))
        {
            fragmentList.add(fragment_store);
        }

        //为界面layout 添加监听
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationAdapterHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.item_1);

        bottomNavigationAdapter=new BottomNavigationAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(bottomNavigationAdapter);
        viewPager.addOnPageChangeListener(this);


        // 如果想禁止滑动，可以把下面的代码取消注释
//        viewPager.setOnTouchListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        unbinder.unbind();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case  R.id.item_1:
                viewPager.setCurrentItem(0);
                break;
            case  R.id.item_2:
                viewPager.setCurrentItem(1);
                break;
            case  R.id.item_3:
                viewPager.setCurrentItem(2);
                break;
            case  R.id.item_4:
                viewPager.setCurrentItem(3);
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }



    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
