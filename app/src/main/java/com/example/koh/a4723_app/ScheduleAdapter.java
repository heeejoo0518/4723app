package com.example.koh.a4723_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

import java.util.List;

public class ScheduleAdapter extends ArrayAdapter<Add_Schedule> {

    private Context context;
    private List mList;
    private ListView mListView;


    class UserViewHolder {
        public TextView schedule;
    }

    public ScheduleAdapter(Context context, List<Add_Schedule> list,ListView listview){
        super(context, 0, list);
        this.context = context;
        this.mList = list;
        this.mListView = listview;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parentViewGroup){
        View rowView = convertView; // 코드 가독성을 위해서 rowView 변수를 사용합니다.
        UserViewHolder viewHolder;
        String Status;

        // 기존에 생성했던 뷰라면 재사용하고 그렇지 않으면 XML 파일을 새로 view 객체로 변환합니다.
        if (rowView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            rowView = layoutInflater.inflate(R.layout.list_item, parentViewGroup, false);
            viewHolder = new UserViewHolder();
            viewHolder.schedule= (TextView) rowView.findViewById(R.id.schedule);
            rowView.setTag(viewHolder);
            Status = "created";
        }
        else {
            viewHolder = (UserViewHolder) rowView.getTag();
            Status = "reused";
        }

        // 태그 분석을 위한 코드 시작
        String Tag = rowView.getTag().toString();
        int idx = Tag.indexOf("@");
        String tag = Tag.substring(idx + 1);


        Add_Schedule sche = (Add_Schedule) mList.get(position);
        viewHolder.schedule.setText(sche.getSchedule());

        Log.d("@@@", "Schedule[" + position + "]" + " row view is " + Status + ", tag = " + tag);

        return rowView;
    }


}
