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
import com.example.koh.a4723_app.hongcheon.hongcheon_0;
import com.example.koh.a4723_app.hongcheon.hongcheon_1;
import com.example.koh.a4723_app.hongcheon.hongcheon_2;
import com.example.koh.a4723_app.hongcheon.hongcheon_3;
import com.example.koh.a4723_app.hongcheon.hongcheon_4;
import com.example.koh.a4723_app.hongcheon.hongcheon_5;
import com.example.koh.a4723_app.hongcheon.hongcheon_6;
import com.example.koh.a4723_app.hongcheon.hongcheon_7;
import com.example.koh.a4723_app.hongcheon.hongcheon_8;
import com.example.koh.a4723_app.hongcheon.hongcheon_9;

public class Health_hongcheon extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "영유아 건강관리", "영양제 지원",
            "교실운영", "미숙아 및 선천성 이상아 의료비 지원", "신생아 청각선별검사 무료 쿠폰 지원",
            "저소득층 기저귀,조제분유 지원", "난임부부 지원","고위험 임산부 의료비 사업 지원",
            "산모,신생아 건강관리 지원사업"};
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_hongcheon);
        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("홍성군 보건소");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);

        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
       _listview.setOnItemClickListener(onItemClickListener);

        ImageButton plus = (ImageButton) findViewById(R.id.map);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health_hongcheon.this);
                LayoutInflater inflater = getLayoutInflater();
                ImageView dialoglayout = (ImageView) inflater.inflate(R.layout.dialog_layout, null);
                dialoglayout.setImageResource(R.drawable.map_hongcheon);

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
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:033-434-4000"));
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
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_hongcheon.this, hongcheon_8.class);
                startActivity(intent);

            }



        }

    };
}
