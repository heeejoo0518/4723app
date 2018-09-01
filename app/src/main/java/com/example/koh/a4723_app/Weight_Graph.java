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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class Weight_Graph extends AppCompatActivity {
    private LineChart lineChart;
    final String tableName = "Weight";
    private final String dbName = "Weight_DB";
    SQLiteDatabase Weight_db = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight__graph);

        Weight_db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Weight_db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (date INTEGER(20) PRIMARY KEY, weight REAL(20) );");
        final SQLiteDatabase finalWeight_db = Weight_db;

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


        draw_graph();

        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String myDate_year = (String) spinner1.getSelectedItem();
                String myDate_month = (String) spinner2.getSelectedItem();
                String myDate_day = (String) spinner3.getSelectedItem();

                if (myDate_month.length() == 1) { //월,일이 한자리수일때 0을 덧붙임
                    myDate_month = "0" + myDate_month;
                }
                if (myDate_day.length() == 1) {
                    myDate_day = "0" + myDate_day;
                }

                String date = myDate_year + myDate_month + myDate_day;

                String diff_str = getPreferences("날짜");

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

                long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000) + 1; // 저장할 날짜가 임신 며칠차 인지

                if(diffDay >= 0) {
                    String weight_str = get_weight.getText().toString();

                    Toast.makeText(getApplicationContext(), weight_str + "완료", Toast.LENGTH_SHORT).show();

                    if(weight_str.length() > 0){ //사용자가 체중 입력을 했을 경우 DB에 저장
                        float weight = Float.parseFloat(weight_str);

                        Weight_db.execSQL("DELETE FROM Weight WHERE date = '" + date + "';");
                        Weight_db.execSQL("INSERT INTO " + tableName + "(date, weight) Values ('" + date + "', '" + weight + "');");

                        draw_graph();
                        Toast.makeText(getApplicationContext() , "완료", Toast.LENGTH_SHORT).show();

                    }
                    else{ //사용자가 체중 입력을 하지 않았을 경우
                        Toast.makeText(getApplicationContext() , "체중을 입력 해주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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

    public void draw_graph(){

        lineChart = (LineChart)findViewById(R.id.chart);

        List<Entry> hide_entries = new ArrayList<>();// 투명 dataset
        for(int i=0;i<=280;i++){
            hide_entries.add(new Entry(i,0));
        }

        List<Entry> entries = new ArrayList<>(); // 나타낼 dataset
        entries.add(new Entry(1,0));

        String diff_str = getPreferences("날짜");
        SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    String date = c.getString(c.getColumnIndex("date"));
                    String weight = c.getString(c.getColumnIndex("weight"));
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


                } while (c.moveToNext());
            }
        }

        ReadDB.close();











        final ArrayList<String> xAxes = new ArrayList<>(); //x축 라벨 만들기
        for (int i=0; i <= 280; i++) {
            xAxes.add(i, String.valueOf(i)+"일 차"); //Dynamic x-axis labels
        }

        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                return xAxes.get(index);
            }
        });


        LineDataSet hide_lineDataSet = new LineDataSet(hide_entries, "");
        hide_lineDataSet.setVisible(false);


        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(4);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(hide_lineDataSet); // add the datasets
        dataSets.add(lineDataSet);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);
        xAxis.setGranularity(1f);
        //------------------------------------------
        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight(); // y축 오른쪽 값 안보이게
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);
        yRAxis.setGranularity(10f);


        //------------------------------------------

        Description description = new Description(); // 라벨 지우기
        description.setText("");
        lineChart.setDescription(description);
        //------------------------------------------
        lineChart.setVisibleXRangeMaximum(5);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.invalidate();

    }


    protected void showList(){

        SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);

        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    String date = c.getString(c.getColumnIndex("date"));
                    String weight = c.getString(c.getColumnIndex("weight"));
                    Toast.makeText(getApplicationContext() , date + " " +weight, Toast.LENGTH_SHORT).show();

                } while (c.moveToNext());
            }
        }

        ReadDB.close();
    }
}

