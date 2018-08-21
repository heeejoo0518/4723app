package com.example.koh.a4723_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class ScheduleAdapter extends BaseAdapter {
    ArrayList<SingleSchedule> items = new ArrayList<>();

    public ScheduleAdapter() {

    }

    @Override
    public int getCount() {
        return items.size();    // 아이템의 갯수 (배열의 갯수)
    }

    // 클래스 밖에서 item data 추가하는 메소드 정의
    public void addItem(SingleSchedule item){
        items.add(item);
        this.notifyDataSetChanged();
    }

    public void addItems(ArrayList<SingleSchedule> item){
        for(int i=0;i<item.size();i++){
            items.add(item.get(i));
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
        Context context = parent.getContext();

        // convertView : 이전에 썼던 itemview
        if (convertView == null){   // convertView이 null값인 경우에만 view를 새로 만듬
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate(R.layout.list_item, parent, false);
            scheview = new Schedule_View(context); // 어떤 뷰든 안드로이드는 context객체를 받음*
        }else{
            scheview = (Schedule_View)convertView; // 있다면 캐스팅만해서 다시 보여줌
        }

        // 뷰의 몇번째 아이템인지 - position(index값)
        SingleSchedule item = items.get(position);
        scheview.setSchedule(item.getSche());

        return scheview;
    }
}
