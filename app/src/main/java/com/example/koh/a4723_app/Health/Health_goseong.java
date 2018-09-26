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
import com.example.koh.a4723_app.goseong.goseong_0;
import com.example.koh.a4723_app.goseong.goseong_1;
import com.example.koh.a4723_app.goseong.goseong_2;
import com.example.koh.a4723_app.goseong.goseong_3;
import com.example.koh.a4723_app.goseong.goseong_4;
import com.example.koh.a4723_app.goseong.goseong_5;
import com.example.koh.a4723_app.goseong.goseong_6;
import com.example.koh.a4723_app.goseong.goseong_7;
import com.example.koh.a4723_app.goseong.goseong_8;
import com.example.koh.a4723_app.goseong.goseong_9;

public class Health_goseong extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "임산부〮아동의 건강관리 지원", "찾아가는 산부인과 운영",
            "선천성 대사이상 검사 및 환아 관리", "미숙아 및 선천성 이상아 의료비 지원", "신생아 청각선별검사 무료 쿠폰 지원",
            "저소득층 기저귀,조제분유 지원", "난임부부 지원","고위험 임산부 의료비 사업 지원",
            "산모,신생아 건강관리사 지원","산모신생아건강관리 본인부담금 지원","산후건강관리지원"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_goseong);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("고성군 보건소");
        setSupportActionBar(toolbar);
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);
        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        _listview.setOnItemClickListener(onItemClickListener);

        ImageButton plus = (ImageButton) findViewById(R.id.map);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health_goseong.this);
                LayoutInflater inflater = getLayoutInflater();
                ImageView dialoglayout = (ImageView) inflater.inflate(R.layout.dialog_layout, null);
                dialoglayout.setImageResource(R.drawable.map_goseong);

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
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:033-681-4000"));/////////
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
                Intent intent = new Intent(Health_goseong.this, goseong_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_goseong.this, goseong_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_goseong.this, goseong_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_goseong.this, goseong_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_goseong.this, goseong_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_goseong.this, goseong_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_goseong.this, goseong_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_goseong.this, goseong_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_goseong.this, goseong_8.class);
                startActivity(intent);

            }
            if(position == 9){
                Intent intent = new Intent(Health_goseong.this, goseong_9.class);
                startActivity(intent);

            }


        }

    };
}
