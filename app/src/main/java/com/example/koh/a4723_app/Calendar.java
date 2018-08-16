package com.example.koh.a4723_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;



public class Calendar extends AppCompatActivity {
    CalendarView simpleCalendarView;
    SingerAdapter adapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        simpleCalendarView = (CalendarView)findViewById(R.id.simpleCalendarView); // get the reference of CalendarView

        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), year + "년 "+ month +"월 " + dayOfMonth + "일", Toast.LENGTH_LONG).show();
            }
        });

        editText = (EditText) findViewById(R.id.editText);

        //ListView
        ListView listView = (ListView) findViewById(R.id.listview);
        // 어댑터 클래스 구성 끝낸 후, 리스트뷰에 어댑터 객체를 만든 후 설정 필요
        adapter = new SingerAdapter();

        //데이터추가
        adapter.addItem(new SingleSchedule("스케줄1"));
        adapter.addItem(new SingleSchedule("스케줄2"));
        adapter.addItem(new SingleSchedule("스케줄3"));
        adapter.addItem(new SingleSchedule("스케줄4"));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingleSchedule item = (SingleSchedule) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택 :"+item.getSche(),Toast.LENGTH_SHORT).show();
            }
        });

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sche = editText.getText().toString();
                adapter.addItem(new SingleSchedule(sche));
                adapter.notifyDataSetChanged(); // 이 메소드를 호출하면 어댑터 쪽에서 리스트뷰를 갱신하라 함.
            }
        });

    }


    class SingerAdapter extends BaseAdapter {

        ArrayList<SingleSchedule> items = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();    // 아이템의 갯수 (배열의 갯수)
        }

        // 클래스 밖에서 item data 추가하는 메소드 정의
        public void addItem(SingleSchedule item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) { // 아이템 선택
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {   // id값이 있다면 넘겨줘라.
            return position;                   // 특정 아이디를 만들어서 넘겨줘도 됨.
        }


        // SingerItemView(아이템뷰)를 리턴하는 메소드
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Schedule_View scheview = null;

            // convertView : 이전에 썼던 itemview
            if (convertView == null){   // convertView이 null값인 경우에만 view를 새로 만듬
                scheview = new Schedule_View(getApplicationContext()); // 어떤 뷰든 안드로이드는 context객체를 받음*
            }else{
                scheview = (Schedule_View)convertView; // 있다면 캐스팅만해서 다시 보여줌
            }


            // 뷰의 몇번째 아이템인지 - position(index값)
            SingleSchedule item = items.get(position);
            scheview.setSchedule(item.getSche());

            return scheview;
        }
    }

}
