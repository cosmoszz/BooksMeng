package menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.booksmeng.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BottomNavigationActivty extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener,ViewPager.OnTouchListener ,ViewPager.OnClickListener {

    @BindView(R.id.widgetNavigationView)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.ma_iv_index)
    ImageView imageView;

    Fragment_classify fragment_classify;
    Fragment_home fragment_home;
    Fragment_library fragment_library;
    Fragment_store fragment_store;
    Fragment_current fragment_current;

    List<Fragment> fragmentList;
    private Fragment fragment_now = null;
    private int pos=-1;

    Unbinder unbinder;

    BottomNavigationAdapter bottomNavigationAdapter;

    BottomNavigationAdapterHelper bottomNavigationAdapterHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        unbinder=ButterKnife.bind(this);
        this.init();

        imageView=this.findViewById(R.id.ma_iv_index);
        imageView.setOnClickListener(this);

    }

    @SuppressLint("NewApi")
    private void init()
    {
        fragmentList=new ArrayList<>();
        fragment_classify=new Fragment_classify();
        fragment_home=new Fragment_home();
        fragment_library=new Fragment_library();
        fragment_store=new Fragment_store();
        fragment_current=new Fragment_current();

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
        if(!fragmentList.contains(fragment_current))
        {
            fragmentList.add(fragment_current);
        }

        //imageview
         //imageView.setOnClickListener((View.OnClickListener) this);

        //为界面layout 添加监听
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationAdapterHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.item_1);

        bottomNavigationAdapter=new BottomNavigationAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(bottomNavigationAdapter);
        viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener)this);


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
                pos=0;
                break;
            case  R.id.item_2:
                viewPager.setCurrentItem(1);
                pos=1;
                break;
            case  R.id.item_3:
                viewPager.setCurrentItem(2);
                pos=2;
                break;
            case  R.id.item_4:
                viewPager.setCurrentItem(3);
                pos=3;
                break;
            case R.id.ma_iv_index:
            case R.id.item_mid:
                viewPager.setCurrentItem(4);
                //bottomNavigationView.getMenu().getItem(pos).setChecked(false);
                //return false;
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i("onPageSelected",String.valueOf(position));
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


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */

    @Override
    public void onClick(View v) {
        Toast.makeText(BottomNavigationActivty.this,"centent",Toast.LENGTH_LONG);


        viewPager.setCurrentItem(4);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        Log.d("click","centent");
        Log.d("pos",String.valueOf(pos));
    }
}
