package menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.booksmeng.R;

public class Fragment_library extends Fragment {

    public Fragment_library()
    {

    }

    public  static  Fragment_library newInstance()
    {
        Fragment_library fragment_library =new Fragment_library();
        return  fragment_library;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        return  inflater.inflate(R.layout.fragment_3,container,false);
    }

}
