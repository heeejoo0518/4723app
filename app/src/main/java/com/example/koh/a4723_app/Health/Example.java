package com.example.koh.a4723_app.Health;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koh.a4723_app.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Example extends AppCompatActivity {

    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat="";
    Elements contents;
    int cnt=0;
    String site ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        textviewHtmlDocument = (TextView)findViewById(R.id.textView);
        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod()); //스크롤 가능한 텍스트뷰로 만들기
     //   Example.JsoupAsyncTask jsoupAsyncTask = new Example.JsoupAsyncTask();
      //  jsoupAsyncTask.execute();

        site = getPreferences("사이트주소");
        Toast.makeText(getApplicationContext(),site, Toast.LENGTH_LONG).show();
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect(site).get();
                //테스트1
                Elements titles= doc.select("div.news-con h1.tit-news");
                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";

                }
                //테스트2

                titles= doc.select("div.news-con h2.tit-news");
                System.out.println("-------------------------------------------------------------");

                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";

                }
                //테스트3

                titles= doc.select("li.section02 div.con h2.news-tl");
                System.out.println("-------------------------------------------------------------");

                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }
                System.out.println("-------------------------------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override

        protected void onPostExecute(Void result) {
            textviewHtmlDocument.setText(htmlContentInStringFormat);
        }
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
}
