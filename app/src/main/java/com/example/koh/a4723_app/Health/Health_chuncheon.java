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
import com.example.koh.a4723_app.chuncheon.chuncheon_0;
import com.example.koh.a4723_app.chuncheon.chuncheon_1;
import com.example.koh.a4723_app.chuncheon.chuncheon_2;
import com.example.koh.a4723_app.chuncheon.chuncheon_3;
import com.example.koh.a4723_app.chuncheon.chuncheon_4;
import com.example.koh.a4723_app.chuncheon.chuncheon_5;
import com.example.koh.a4723_app.chuncheon.chuncheon_6;
import com.example.koh.a4723_app.chuncheon.chuncheon_7;
import com.example.koh.a4723_app.chuncheon.chuncheon_8;

public class Health_chuncheon extends AppCompatActivity {

    private ListView _listview;

    private String[] items = { "출산장려시책", "한부모가족 복지시설","저소득 한부모 가정지원",
            "청소년 한부모 자립지원", "임신출산 안내책자","임산부 지원사업",
            "난임부부 지원","한방난임 지원", "임산부 교실"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_chuncheon);
        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("BLACK")); //제목의 칼라
        toolbar.setTitle("춘천시 보건소");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        //-----------------------------------------------------------
        _listview = (ListView)findViewById(R.id.health_listview);

        _listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        _listview.setOnItemClickListener(onItemClickListener);

        ImageButton plus = (ImageButton) findViewById(R.id.map);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Health_chuncheon.this);
                LayoutInflater inflater = getLayoutInflater();
                ImageView dialoglayout = (ImageView) inflater.inflate(R.layout.dialog_layout, null);
                dialoglayout.setImageResource(R.drawable.map_chuncheon);

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
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:033-250-3550"));
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
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_0.class);
                startActivity(intent);
            }
            if(position == 1){
                //setFrag(1);
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_1.class);
                startActivity(intent);
            }
            if(position == 2){
                //  setFrag(2);
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_2.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_3.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_4.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_5.class);
                startActivity(intent);

            }
            if(position == 6){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_6.class);
                startActivity(intent);

            }
            if(position == 7){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_7.class);
                startActivity(intent);

            }
            if(position == 8){
                Intent intent = new Intent(Health_chuncheon.this, chuncheon_8.class);
                startActivity(intent);

            }
        }

    };
}
