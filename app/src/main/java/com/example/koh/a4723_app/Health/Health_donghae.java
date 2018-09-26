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
import com.example.koh.a4723_app.donghae.donghae_0;
import com.example.koh.a4723_app.donghae.donghae_1;
import com.example.koh.a4723_app.donghae.donghae_2;
import com.example.koh.a4723_app.donghae.donghae_3;
import com.example.koh.a4723_app.donghae.donghae_4;
import com.example.koh.a4723_app.donghae.donghae_5;
import com.example.koh.a4723_app.donghae.donghae_6;
import com.example.koh.a4723_app.donghae.donghae_7;
import com.example.koh.a4723_app.donghae.donghae_8;
import com.example.koh.a4723_app.donghae.donghae_9;

public class Health_donghae extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "국민행복카드 발급 안내", "보건소 임산부 등록 전 신혼부부 무료검진", "보건소 임산부 등록 후 지원현황",
            "산전 지원현황", "산후 지원현황","임부(청소년)등록관리","임산부 검사",
            "영양제 보급","모유수유실 운영","임산부 프로그램(행복한 예비맘 건강교실)" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_donghae);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("동해시 보건소");
        setSupportActionBar(toolbar);
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);

        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        _listview.setOnItemClickListener(onItemClickListener);

        ImageButton plus = (ImageButton) findViewById(R.id.map);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health_donghae.this);
                LayoutInflater inflater = getLayoutInflater();
                ImageView dialoglayout = (ImageView) inflater.inflate(R.layout.dialog_layout, null);
                dialoglayout.setImageResource(R.drawable.map_donghae);

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
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:033-530-2401"));
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

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            if(position == 0){
                Intent intent = new Intent(Health_donghae.this, donghae_0.class);
                startActivity(intent);
            }
            if(position == 1){
                Intent intent = new Intent(Health_donghae.this, donghae_1.class);
                startActivity(intent);
            }
            if(position == 2){
                Intent intent = new Intent(Health_donghae.this, donghae_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_donghae.this, donghae_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_donghae.this,donghae_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_donghae.this, donghae_5.class);
                startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(Health_donghae.this, donghae_6.class);
                startActivity(intent);
            }
            if(position == 7){
                Intent intent = new Intent(Health_donghae.this, donghae_7.class);
                startActivity(intent);
            }
            if(position == 8){
                Intent intent = new Intent(Health_donghae.this, donghae_8.class);
                startActivity(intent);
            }

            if(position == 9){
                Intent intent = new Intent(Health_donghae.this, donghae_9.class);
                startActivity(intent);
            }
        }

    };
      }



