package menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.booksmeng.R;

public class Fragment_classify extends Fragment {

    public Fragment_classify()
    {

    }

    public static  Fragment_classify newInstance()
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
       // return super.onCreateView(inflater, container, savedInstanceState);
        return  inflater.inflate(R.layout.fragment_1,container,false);
    }
}
