package com.example.koh.a4723_app;

import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Calendar extends AppCompatActivity {
    CalendarView simpleCalendarView;
    EditText editText;
    String tableName;
    String dbName = "Calendar_DB";
    //String[] schedules = null;
    //ArrayList<HashMap<String, String>> schedules = new ArrayList<>();
    ListView listView;
    ScheduleAdapter adapter;
    //ListAdapter adapter;
    private static final String TAG_SCHEDULE ="schedule";
    SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        tableName = format.format(new Date(System.currentTimeMillis()));
        tableName = "a"+tableName;

        simpleCalendarView = (CalendarView)findViewById(R.id.simpleCalendarView); // get the reference of CalendarView


        adapter = new ScheduleAdapter();
        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listview);
        db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);


        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                //Toast.makeText(getApplicationContext(), year + "년 "+ month +"월 " + dayOfMonth + "일", Toast.LENGTH_LONG).show();
                adapter = new ScheduleAdapter();

                //////테이블이름 생성/////////////////////
                String year_st,month_st,day_st;
                int year__int = year-2000; //2000년부터 정상작동,,
                if(year__int<10) year_st='0'+Integer.toString(year__int); else year_st=Integer.toString(year__int);
                if(month<10) month_st='0'+Integer.toString(month); else month_st=Integer.toString(month);
                if(dayOfMonth<10) day_st='0'+Integer.toString(dayOfMonth); else day_st=Integer.toString(dayOfMonth);
                tableName = year_st+month_st+day_st;
                tableName = "a"+tableName;

                db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (schedule VARCHAR);");//schedule 칼럼 1개 있는 테이블 추가
                adapter.DB_add();
                listView.setAdapter(adapter);
             }
        });


        //Table 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (schedule VARCHAR);");//schedule 칼럼 1개 있는 테이블 추가
        adapter.DB_add();

        listView.setAdapter(adapter);
        //db.close();
        //db.addRecords(schedules,index);//데이터 추가

         /*
         //listView item 클릭 리스너
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 SingleSchedule item = (SingleSchedule) adapter.getItem(position);
                //Toast.makeText(getApplicationContext(),"선택 :"+item.getSche(),Toast.LENGTH_SHORT).show();
            }
        });
        */

        //showList();
        //db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);// DB 다시 오픈


        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT schedule FROM " + tableName, null);
                c.moveToLast();//커서 이동

                String sche = editText.getText().toString();
                db.execSQL("INSERT INTO " + tableName + "(schedule) Values ('" + sche + "');"); // DB에 데이터 추가

                adapter.addItem(new SingleSchedule(sche));
                editText.setText("");//EditText 내용 삭제

                //////////////////////////////////////////////////////////
                //adapter.notifyDataSetChanged(); // 이 메소드를 호출하면 어댑터 쪽에서 리스트뷰를 갱신하라 함.
               // showList();
            }
        });

    }


    class ScheduleAdapter extends BaseAdapter {

        ArrayList<SingleSchedule> items = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();    // 아이템의 갯수 (배열의 갯수)
        }

        // 클래스 밖에서 item data 추가하는 메소드 정의
        public void addItem(SingleSchedule item){
            items.add(item);
            this.notifyDataSetChanged(); // 이 메소드를 호출하면 어댑터 쪽에서 리스트뷰를 갱신하라 함.
        }

        public void DB_add(){
            Cursor c = db.rawQuery("SELECT schedule FROM " + tableName, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String schedule = c.getString(c.getColumnIndex("schedule"));
                        items.add(new SingleSchedule(schedule));
                    } while (c.moveToNext());
                }
            }
        }

        @Override
        public Object getItem(int position) { // 아이템 선택
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {   // id값이 있다면 넘겨줘라.
            return position;                   // 특정 아이디를 만들어서 넘겨줘도 됨.
        }


        // Schedule_View 리턴하는 메소드
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
    /*
    protected void showList(){
        try {
            db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);// DB 다시 오픈

            //테이블에 있는 데이터 가져옴
            Cursor c = db.rawQuery("SELECT schedule FROM " + tableName, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        //테이블에서 두개의 컬럼값을 가져와서
                        String schedule = c.getString(c.getColumnIndex("schedule"));
                        //HashMap에 넣음
                        HashMap<String,String> sche = new HashMap<String,String>();
                        sche.put(TAG_SCHEDULE,schedule);
                        //ArrayList에 추가
                        schedules.add(sche);
                    } while (c.moveToNext());
                }
            }

            db.close();

            //새로운 apapter 생성, 데이터 입력
            adapter = new SimpleAdapter(this, schedules, R.layout.list_item,new String[]{TAG_SCHEDULE},new int[]{ R.id.schedule_});

            //listview 연결
            listView.setAdapter(adapter);

        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }
    }
*/

}
