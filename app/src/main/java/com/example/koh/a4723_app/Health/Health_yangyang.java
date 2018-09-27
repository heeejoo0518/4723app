package com.example.koh.a4723_app.Health;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.koh.a4723_app.R;
import com.example.koh.a4723_app.yangyang.yangyang_0;
import com.example.koh.a4723_app.yangyang.yangyang_1;
import com.example.koh.a4723_app.yangyang.yangyang_10;
import com.example.koh.a4723_app.yangyang.yangyang_2;
import com.example.koh.a4723_app.yangyang.yangyang_3;
import com.example.koh.a4723_app.yangyang.yangyang_4;
import com.example.koh.a4723_app.yangyang.yangyang_5;
import com.example.koh.a4723_app.yangyang.yangyang_6;
import com.example.koh.a4723_app.yangyang.yangyang_7;
import com.example.koh.a4723_app.yangyang.yangyang_8;
import com.example.koh.a4723_app.yangyang.yangyang_9;

public class Health_yangyang extends AppCompatActivity  {

    private ListView _listview;

    private String[] items = {  "임산부 등록 관리", "미숙아 및 선천성이상아 의료비 지원", "선천성대사이상검사 및 환아관리",            "신생아 청각선별검사","찾아가는 산부인과", "고위험 임산부 의료비 지원",
            "산모신생아 건강관리 지원사업",
            "산후 건강관리 의료비지원사업","출산장려금 지원사업","저소득층 기저귀•조제분유 지원사업"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_yangyang);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("양양군 보건소");
        setSupportActionBar(toolbar);
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);
        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        _listview.setOnItemClickListener(onItemClickListener);

       // frag1 = new Frag1();
       // setFrag(0);

        ImageButton plus = (ImageButton) findViewById(R.id.map);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health_yangyang.this);
                LayoutInflater inflater = getLayoutInflater();
                ImageView dialoglayout = (ImageView) inflater.inflate(R.layout.dialog_layout, null);
                dialoglayout.setImageResource(R.drawable.map_yangyang);

                //다이알로그 yes 버튼==
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //==============
                builder.setView(dialoglayout);
                builder.show();
            }
        });

        ImageButton phone = (ImageButton) findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Permission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
                if(Permission== PackageManager.PERMISSION_GRANTED){
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:033-670-2877"));
                    startActivity(callIntent);
                }
                else{
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getApplicationContext(), "Manifest.permission.CALL_PHONE")){
                        Snackbar.make(v, "이 앱을 실행하려면 통화 접근 권한이 필요합니다.",
                                Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                ActivityCompat.requestPermissions( (Activity)getApplicationContext(), new String[]{"Manifest.permission.CALL_PHONE"}, 200);
                            }
                        }).show();
                    }else {
                        ActivityCompat.requestPermissions((Activity)getApplicationContext(),new String[]{"Manifest.permission.CALL_PHONE"},200);
                    }
                }
            }
        });

    }



    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            if(position == 0){
               Intent intent = new Intent(Health_yangyang.this, yangyang_0.class);
               startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_yangyang.this, yangyang_1.class);
                startActivity(intent);
            }
            if(position == 2){
              //  setFrag(2);
                Intent intent = new Intent(Health_yangyang.this, yangyang_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_yangyang.this, yangyang_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_yangyang.this, yangyang_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_yangyang.this, yangyang_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_yangyang.this, yangyang_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_yangyang.this, yangyang_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_yangyang.this, yangyang_8.class);
                startActivity(intent);

            }

            if(position == 9){
                Intent intent = new Intent(Health_yangyang.this, yangyang_9.class);
                startActivity(intent);

            }
            if(position==10){
                Intent intent = new Intent(Health_yangyang.this, yangyang_10.class);
                startActivity(intent);

            }
        }

    };

    }

