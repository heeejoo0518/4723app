package com.example.koh.a4723_app;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Weight_Graph extends AppCompatActivity {
    private LineChart lineChart;
    final String tableName = "Weight";
    private final String dbName = "Weight_DB";
    SQLiteDatabase Weight_db = null;
    static String diff_str;
    int data_num = 0;
    long last_diff_day = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight__graph);

        Weight_db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Weight_db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (date INTEGER(20) PRIMARY KEY, weight REAL(20) );");
        final SQLiteDatabase finalWeight_db = Weight_db;

        final Spinner spinner1 = (Spinner) findViewById(R.id.mySpinner1);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        final Spinner spinner2 = (Spinner) findViewById(R.id.mySpinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        final Spinner spinner3 = (Spinner) findViewById(R.id.mySpinner3);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.day, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        final EditText get_weight = (EditText) findViewById(R.id.today_weight);
        Button save_data = (Button) findViewById(R.id.save_data);
        Button delete_data = (Button) findViewById(R.id.delete_data);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);
        String year = currentDate.substring(0, 4);

        String month, day;

        if (currentDate.substring(4, 5).equals("1")) {
            month = currentDate.substring(4, 6);
        } else {
            month = currentDate.substring(5, 6);
        }
        if (currentDate.substring(6, 7).equals("0")) {

            day = currentDate.substring(7, 8);
        } else {
            day = currentDate.substring(6, 8);

        }

        int month_int =Integer.parseInt(month);
        int day_int =Integer.parseInt(day);

        if(year.equals("2018")){
            spinner1.setSelection(0);
        }
        else {
            spinner1.setSelection(1);
        }

        spinner2.setSelection(month_int-1);
        spinner3.setSelection(day_int-1
        );

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

                diff_str = getPreferences("날짜");

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

                if (diffDay > 0) {
                    String weight_str = get_weight.getText().toString();


                    if (weight_str.length() > 0) { //사용자가 체중 입력을 했을 경우 DB에 저장
                        float weight = Float.parseFloat(weight_str);

                        Weight_db.execSQL("DELETE FROM Weight WHERE date = '" + date + "';");
                        Weight_db.execSQL("INSERT INTO " + tableName + "(date, weight) Values ('" + date + "', '" + weight + "');");

                        draw_graph();
                        last_diff_day = diffDay;

                    } else { //사용자가 체중 입력을 하지 않았을 경우
                        Toast.makeText(getApplicationContext(), "체중을 입력 해주세요", Toast.LENGTH_SHORT).show();
                    }
                } else if (diffDay <= 0) {
                    Toast.makeText(getApplicationContext(), "마지막 생리 이후 날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                }



            }
        });

        delete_data.setOnClickListener(new View.OnClickListener() {
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

                diff_str = getPreferences("날짜");

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

                if (diffDay > 0) {

                    Weight_db.execSQL("DELETE FROM Weight WHERE date = '" + date + "';");
                    last_diff_day = diffDay;
                    draw_graph();
                    Toast.makeText(getApplicationContext(), "삭제 완료", Toast.LENGTH_SHORT).show();
                } else if (diffDay <= 0) {
                    Toast.makeText(getApplicationContext(), "마지막 생리 이후 날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void savePreferences(String code, String str) { //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }

    private String getPreferences(String code) { //데이터 불러오는 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code, "");
        return temp;

    }

    public void draw_graph() {
        lineChart = (LineChart) findViewById(R.id.chart);

        List<Entry> hide_entries = new ArrayList<>();// 투명 dataset
        for (int i = 1; i <= 280; i++) {
            hide_entries.add(new Entry(i, 0));
        }

        List<Entry> entries = new ArrayList<>(); // 나타낼 dataset


        String diff_str = getPreferences("날짜");
        SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    String date = c.getString(c.getColumnIndex("date"));
                    String weight = c.getString(c.getColumnIndex("weight"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                    int diff_1 = Integer.parseInt(diff_str);
                    int diff_2 = Integer.parseInt(date);

                    if (diff_1 <= diff_2) {

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
                            Toast.makeText(getApplicationContext(), "입력일이 이전입니다", Toast.LENGTH_SHORT).show();
                        } else if (diffDay > 0) {
                            float weight_float = Float.parseFloat(weight);
                            entries.add(new Entry(diffDay, weight_float));
                            last_diff_day = diffDay;
                        }

                    }

                } while (c.moveToNext());
            }

        }

        ReadDB.close();
        //

        final ArrayList<String> xAxes = new ArrayList<>(); //x축 라벨 만들기
        for (int i = 0; i <= 280; i++) {
            xAxes.add(i, String.valueOf(i) + "일 차"); //Dynamic x-axis labels
        }

        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                return xAxes.get(index);
            }
        });

        Collections.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return (o1.getX() < o2.getX() ? -1 : (o1.getX() > o2.getX() ? 1 : 0));

            }
        });

        showList();


        if (data_num == 0) {
            entries.add(new Entry(0, 0));
        }
        LineDataSet hide_lineDataSet = new LineDataSet(hide_entries, "");
        hide_lineDataSet.setVisible(false);

        LineDataSet lineDataSet = new LineDataSet(entries, "");

        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(4);
        lineDataSet.setCircleColor(Color.parseColor("#808080"));
        lineDataSet.setColor(Color.parseColor("#808080"));

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
        yRAxis.setGranularity(1f);
        yRAxis.setGranularityEnabled(true);
        //------------------------------------------

        Description description = new Description(); // 라벨 지우기
        description.setText("");
        lineChart.setDescription(description);
        //------------------------------------------

        if(last_diff_day > 5){
            lineChart.moveViewToX(last_diff_day-3);
        }
        else{
            lineChart.moveViewToX(0);
        }
        lineChart.getLegend().setEnabled(false);
        lineChart.setVisibleXRangeMaximum(5);
        lineChart.setDoubleTapToZoomEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.invalidate();


        MyMarkerView marker = new MyMarkerView(this, R.layout.activity_my_marker_view);
        marker.setChartView(lineChart);
        lineChart.setMarker(marker);

    }

    protected void showList() {
        data_num = 0;
        String diff = getPreferences("날짜");
        SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);

        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    String date = c.getString(c.getColumnIndex("date"));
                    String weight = c.getString(c.getColumnIndex("weight"));

                    int diff_1 = Integer.parseInt(date);
                    int diff_2 = Integer.parseInt(diff);

                    if (diff_1 >= diff_2) {
                        data_num++;
                    }

                } while (c.moveToNext());
            }
        }
        ReadDB.close();
    }


}
