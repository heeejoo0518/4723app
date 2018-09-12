package com.example.koh.a4723_app.Health;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.koh.a4723_app.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Health_chulwon extends AppCompatActivity {

    private String htmlPageUrl = "http://www.yonhapnews.co.kr/";
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat="";
    Elements contents;
    int cnt=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_health_chulwon);

        textviewHtmlDocument = (TextView)findViewById(R.id.textView);

        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod()); //스크롤 가능한 텍스트뷰로 만들기



      //  Button htmlTitleButton = (Button)findViewById(R.id.button);

      //  htmlTitleButton.setOnClickListener(new View.OnClickListener() {


           // public void onClick(View v) {
             //   System.out.println( (cnt+1) +"번째 파싱");
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();

               // cnt++;
           // }
      //  });

    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                Document doc = Jsoup.connect(htmlPageUrl).get();


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


}


