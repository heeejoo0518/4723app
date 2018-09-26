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
import com.example.koh.a4723_app.youngweol.youngweol_0;
import com.example.koh.a4723_app.youngweol.youngweol_1;
import com.example.koh.a4723_app.youngweol.youngweol_2;
import com.example.koh.a4723_app.youngweol.youngweol_3;
import com.example.koh.a4723_app.youngweol.youngweol_4;
import com.example.koh.a4723_app.youngweol.youngweol_5;
import com.example.koh.a4723_app.youngweol.youngweol_6;
import com.example.koh.a4723_app.youngweol.youngweol_7;
import com.example.koh.a4723_app.youngweol.youngweol_8;

public class Health_youngweol extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "임산부 건강관리", "신생아 난청조기진단(청각선별검사) 지원", "선천성대사이상검사",
            "산모 · 신생아 건강관리사 지원사업", "산후돌봄 지원사업(산모 · 신생아 건강관리 본인부담금 지원)",
            "산후건강관리 지원사업","청소년산모 임신출산 의료비 지원사업","영양플러스 보충식품 지원",
            "아기와 엄마가 함께하는 행복한 건강교실 운영 "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_youngweol);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("영월군 보건소");
        setSupportActionBar(toolbar);
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);
        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        _listview.setOnItemClickListener(onItemClickListener);

        ImageButton plus = (ImageButton) findViewById(R.id.map);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health_youngweol.this);
                LayoutInflater inflater = getLayoutInflater();
                ImageView dialoglayout = (ImageView) inflater.inflate(R.layout.dialog_layout, null);
                dialoglayout.setImageResource(R.drawable.map_youngwol);

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
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1577-0545"));
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
                Intent intent = new Intent(Health_youngweol.this, youngweol_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_youngweol.this, youngweol_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_youngweol.this, youngweol_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_youngweol.this, youngweol_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_youngweol.this, youngweol_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_youngweol.this, youngweol_5.class);
                startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(Health_youngweol.this, youngweol_6.class);
                startActivity(intent);
            }
            if(position == 7){
                Intent intent = new Intent(Health_youngweol.this, youngweol_7.class);
                startActivity(intent);
            }
            if(position == 8){
                Intent intent = new Intent(Health_youngweol.this, youngweol_8.class);
                startActivity(intent);
            }

        }

    };
}
