package com.example.koh.a4723_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Weight_Graph extends AppCompatActivity {
    private LineChart lineChart;
    final String tableName = "Weight";
    private final String dbName = "Weight_DB";
    SQLiteDatabase Weight_db=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight__graph);


        Weight_db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Weight_db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (date INTEGER(20) PRIMARY KEY, weight REAL(20) );");

        final Spinner spinner1 = (Spinner)findViewById(R.id.mySpinner1);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.year, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        final Spinner spinner2 = (Spinner)findViewById(R.id.mySpinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.month, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        final Spinner spinner3 = (Spinner)findViewById(R.id.mySpinner3);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,R.array.day, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        final EditText get_weight = (EditText)findViewById(R.id.today_weight);
        Button save_data = (Button)findViewById(R.id.save_data);
        Button delete_data = (Button)findViewById(R.id.delete_data);


       /* String get_spinner1_position = getPreferences("년");
        String get_spinner2_position = getPreferences("월");
        String get_spinner3_position = getPreferences("일");

        if(get_spinner1_position.length() != 0){                            //마지막으로 저장된 날짜로 셋팅
            int position =  Integer.parseInt(get_spinner1_position);
            spinner1.setSelection(position);
        }

        if(get_spinner2_position.length() != 0){
            int position =   Integer.parseInt(get_spinner2_position);
            spinner2.setSelection(position);
        }

        if(get_spinner3_position.length() != 0){
            int position =   Integer.parseInt(get_spinner3_position);
            spinner3.setSelection(position);
        }*/

        final SQLiteDatabase finalWeight_db = Weight_db;
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myDate_year = (String) spinner1.getSelectedItem();
                String myDate_month = (String) spinner2.getSelectedItem();
                String myDate_day = (String) spinner3.getSelectedItem();

                if(myDate_month.length() == 1){ //월,일이 한자리수일때 0을 덧붙임
                    myDate_month = "0"+myDate_month;
                }
                if(myDate_day.length() == 1){
                    myDate_day = "0"+myDate_day;
                }

                String date = myDate_year+myDate_month+myDate_day;

                String weight_str = get_weight.getText().toString();

                if(weight_str.length() > 0){
                    float weight = Float.parseFloat(weight_str);

                    Weight_db.execSQL("DELETE FROM Weight WHERE date = '" + date + "';");
                    Weight_db.execSQL("INSERT INTO " + tableName + "(date, weight) Values ('" + date + "', '" + weight + "');");
                    Toast.makeText(getApplicationContext() , "완료", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext() , "체중을 입력 해주세요", Toast.LENGTH_SHORT).show();
                }
                get_weight.setText("");
                draw_graph();


            }
        });

       delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myDate_year = (String) spinner1.getSelectedItem();
                String myDate_month = (String) spinner2.getSelectedItem();
                String myDate_day = (String) spinner3.getSelectedItem();

                if(myDate_month.length() == 1){ //월,일이 한자리수일때 0을 덧붙임
                    myDate_month = "0"+myDate_month;
                }
                if(myDate_day.length() == 1){
                    myDate_day = "0"+myDate_day;
                }

                String date_str = myDate_year+myDate_month+myDate_day;
                int date = Integer.parseInt(date_str);
               // Weight_db.execSQL("DELETE FROM " + tableName  );
               // Weight_db.execSQL("DELETE FROM tableName WHERE date = '" + date_str+ "';");
                Weight_db.execSQL("DELETE FROM Weight WHERE date = '" + date + "';");

                Toast.makeText(getApplicationContext() , date +"삭제완료", Toast.LENGTH_SHORT).show();
            }
        });


        draw_graph();
        Intent intent = getIntent();
        String data = intent.getStringExtra("날짜");
        //Toast.makeText(getApplicationContext() , date + " " +weight, Toast.LENGTH_SHORT).show();
        String test = getPreferences("월");
        //Toast.makeText(getApplicationContext() , test, Toast.LENGTH_SHORT).show();


    }

    private void savePreferences(String code , String str){ //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }

    private String getPreferences(String code){ //데이터 불러오는 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code , "");
        return temp;

    }



    protected void showList(){

            SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

            Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);

            if (c != null) {

                if (c.moveToFirst()) {
                    do {

                        String date = c.getString(c.getColumnIndex("date"));
                        String weight = c.getString(c.getColumnIndex("weight"));
                        //Toast.makeText(getApplicationContext() , date + " " +weight, Toast.LENGTH_SHORT).show();

                    } while (c.moveToNext());
                }
            }

            ReadDB.close();
    }

    public void draw_graph(){
        lineChart = (LineChart)findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();

        final ArrayList<String> xAxes = new ArrayList<>();
        for (int i=0; i < 280; i++) {
            xAxes.add(i, String.valueOf(i)+"일 차"); //Dynamic x-axis labels
        }

        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                return xAxes.get(index);
            }
        });

        showList();



        SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);

        int data_cnt = 0;


        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    String diff_str = getPreferences("날짜");

                    String date = c.getString(c.getColumnIndex("date"));
                    String weight = c.getString(c.getColumnIndex("weight"));
                    // Toast.makeText(getApplicationContext() , date + " " +weight, Toast.LENGTH_SHORT).show();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                    Date startDate = new Date();
                    try {
                        startDate = sdf.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } //지금 날짜 형식 변환

                    Date endDate = new Date();
                    try {
                        endDate = sdf.parse(diff_str);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } //사용자가 저장한 날짜 불러온거 형식 변환

                    long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000) + 1;

                    if (diffDay < 0) {
                        diffDay = 0 ; //Toast.makeText(getApplicationContext() , "입력일이 이전입니다", Toast.LENGTH_SHORT).show();
                    }
                    float weight_float = Float.parseFloat(weight);
                    entries.add(new Entry(diffDay,weight_float));

                    data_cnt++;

                   // Toast.makeText(getApplicationContext() , diffDay +"테스트", Toast.LENGTH_SHORT).show();
                } while (c.moveToNext());
            }
        }
        if(data_cnt == 0){
            entries.add(new Entry(1,0)); //기본값 (안넣으면 오류남)
        }

        ReadDB.close();


        LineDataSet lineDataSet = new LineDataSet(entries, "속성명1");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 1);



        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        yRAxis.setGranularity(1f);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();
        lineChart.setVisibleXRangeMaximum(5);
        lineChart.moveViewToX(data_cnt);
        lineChart.setVisibleYRangeMaximum(300,null);

    }

}
