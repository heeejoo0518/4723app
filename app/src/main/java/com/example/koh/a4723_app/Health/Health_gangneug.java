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
import com.example.koh.a4723_app.gangneug.gangneug_0;
import com.example.koh.a4723_app.gangneug.gangneug_1;
import com.example.koh.a4723_app.gangneug.gangneug_2;
import com.example.koh.a4723_app.gangneug.gangneug_3;
import com.example.koh.a4723_app.gangneug.gangneug_4;
import com.example.koh.a4723_app.gangneug.gangneug_5;
import com.example.koh.a4723_app.gangneug.gangneug_6;
import com.example.koh.a4723_app.gangneug.gangneug_7;
import com.example.koh.a4723_app.gangneug.gangneug_8;

public class Health_gangneug extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "어떻게 할지 생각해보기", "출산장려금 지원",
            "신생아청각선별검사", "산모신생아 건강관리사지원", "고위험 임산부 의료비 지원","선천성대사이상 환아 지원",
            "난임시술비지원","청소년산모 임신,출산 의료비지원",
            "출산 후 의료,약제비지원","영양플러스사업"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_gangneug);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("강릉시 보건소");
        setSupportActionBar(toolbar);
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);
        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        _listview.setOnItemClickListener(onItemClickListener);

        ImageButton plus = (ImageButton) findViewById(R.id.map);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health_gangneug.this);
                LayoutInflater inflater = getLayoutInflater();
                ImageView dialoglayout = (ImageView) inflater.inflate(R.layout.dialog_layout, null);
                dialoglayout.setImageResource(R.drawable.map_gangneung);

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
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:033-660-3011"));
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
                Intent intent = new Intent(Health_gangneug.this, gangneug_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_gangneug.this, gangneug_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_gangneug.this, gangneug_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_gangneug.this, gangneug_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_gangneug.this, gangneug_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_gangneug.this, gangneug_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_gangneug.this, gangneug_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_gangneug.this, gangneug_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_gangneug.this, gangneug_8.class);
                startActivity(intent);

            }


        }

    };
}
