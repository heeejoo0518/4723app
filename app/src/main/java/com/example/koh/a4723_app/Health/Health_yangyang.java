package com.example.koh.a4723_app.Health;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.koh.a4723_app.R;
import com.example.koh.a4723_app.R;
public class Health_yangyang extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "List1", "List2", "List3", "List4", "List5", "List6",

            "List7", "List8", "List9", "List10" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_yangyang);

        _listview = (ListView)findViewById(R.id.health_listview);

        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

        _listview.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            if(position == 0){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
            if(position == 1){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
            if(position == 2){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
            if(position == 7){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
            if(position == 8){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }

            if(position == 9){
                Intent intent = new Intent(Health_yangyang.this, Health_chulwon.class);
                startActivity(intent);
            }
        }

    };
}
