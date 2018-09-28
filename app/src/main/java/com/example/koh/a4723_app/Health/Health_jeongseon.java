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
import com.example.koh.a4723_app.jeongseon.jeongseon_1;
import com.example.koh.a4723_app.jeongseon.jeongseon_10;
import com.example.koh.a4723_app.jeongseon.jeongseon_2;
import com.example.koh.a4723_app.jeongseon.jeongseon_3;
import com.example.koh.a4723_app.jeongseon.jeongseon_4;
import com.example.koh.a4723_app.jeongseon.jeongseon_5;
import com.example.koh.a4723_app.jeongseon.jeongseon_6;
import com.example.koh.a4723_app.jeongseon.jeongseon_7;
import com.example.koh.a4723_app.jeongseon.jeongseon_8;
import com.example.koh.a4723_app.jeongseon.jeongseon_9;
import com.example.koh.a4723_app.jeongseon.jeongseon_m;

public class Health_jeongseon extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "임산부 건강관리", "출산육아용품 상품권 지원",
            "찾아가는 산부인과/부인과", "예비 부부·부모 건강검진 ", "난임부부 시술비 지원사업",
            "미숙아·선천성이상아 의료비지원 ","난청조기진단사업",
            "산모·신생아건강관리 지원사업 ","고위험임산부 의료비 지원사업","저소득층 기저귀·조제분유 지원사업"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_jeongseon);

        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK"));
        toolbar.setTitle("정선군 보건소");
        setSupportActionBar(toolbar);
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);
        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        _listview.setOnItemClickListener(onItemClickListener);
        ImageButton plus = (ImageButton) findViewById(R.id.map);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health_jeongseon.this);
                LayoutInflater inflater = getLayoutInflater();
                ImageView dialoglayout = (ImageView) inflater.inflate(R.layout.dialog_layout, null);
                dialoglayout.setImageResource(R.drawable.map_jeongseon);

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
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:033-560-2748"));
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
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_m.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_8.class);
                startActivity(intent);

            }
            if(position == 9){
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_9.class);
                startActivity(intent);

            }
            if(position == 10){
                Intent intent = new Intent(Health_jeongseon.this, jeongseon_10.class);
                startActivity(intent);

            }
        }

    };
}
