package com.example.koh.a4723_app.Health;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.koh.a4723_app.R;
import com.example.koh.a4723_app.Wellness.WellnessAdapter;
import com.example.koh.a4723_app.Wellness.WellnessClicked;
import com.example.koh.a4723_app.Wellness.WellnessItem;

import java.util.ArrayList;
import java.util.List;

public class Health_Sokcho extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<WellnessItem> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__sokcho);
        ListView listView = (ListView) findViewById(R.id.wellness_listview);

        data = new ArrayList<>();

        WellnessItem well1 = new WellnessItem(R.drawable.profile,"속초","복지1");
        WellnessItem well2 = new WellnessItem(R.drawable.profile,"속초","복지2");

        data.add(well1);
        data.add(well2);

        WellnessAdapter adapter = new WellnessAdapter(this,R.layout.wellness_item,data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                Intent intent= new Intent(getApplicationContext(), WellnessClicked.class);

                intent.putExtra("profile",Integer.toString(data.get(position).getProfile()));
                intent.putExtra("info",data.get(position).getCity());
                intent.putExtra("wellness",data.get(position).getWellness());
                startActivity(intent);
            }
        });

        Toast.makeText(getApplicationContext(), "sokcho", Toast.LENGTH_LONG).show();


    }

    public void onClick(View v){}

}

