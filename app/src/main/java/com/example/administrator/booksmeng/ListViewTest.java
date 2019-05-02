package com.example.administrator.booksmeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewTest  extends AppCompatActivity {

    private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango" };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_test);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ListViewTest.this,android.R.layout.simple_list_item_1,data);

        ListView listView=this.findViewById(R.id.listviewTest);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            String value = intent.getStringExtra("key");
            Toast.makeText(this,value,Toast.LENGTH_LONG).show();
            Log.d("ListViewTest.onCreate",value);
        }


    }
}
