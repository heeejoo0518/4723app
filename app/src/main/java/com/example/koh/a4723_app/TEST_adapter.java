package com.example.koh.a4723_app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

class TEST_items {
    private String name;
    private String call;
    public void setName(String name){
        this.name=name;
    }
    public void setCall(String call){
        this.call=call;
    }
    public String getName(){
        return this.name;
    }
    public String getCall(){
        return this.call;
    }
}

public class TEST_adapter extends BaseAdapter {
    ArrayList<TEST_items> items = new ArrayList<TEST_items>();

    public TEST_adapter() {}

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String name,String call){
        TEST_items item = new TEST_items();
        item.setName(name);
        item.setCall(call);
        items.add(item);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        TEST_View view = null;
        Context context = parent.getContext();

        if (convertView == null){
            view = new TEST_View(context);
        }else{
            view = (TEST_View)convertView;
        }
        view.setText(items.get(position));

        final TEST_View finalView = view;
        final Context finalcontext = context;
        view.imgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Permission = ContextCompat.checkSelfPermission(finalcontext, Manifest.permission.CALL_PHONE);
                if(Permission== PackageManager.PERMISSION_GRANTED){
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+items.get(position).getCall()));
                    finalcontext.startActivity(callIntent);
                }
                else{
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) finalcontext, "Manifest.permission.CALL_PHONE")){
                        Snackbar.make(finalView, "이 앱을 실행하려면 통화 접근 권한이 필요합니다.",
                                Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                ActivityCompat.requestPermissions( (Activity)finalcontext, new String[]{"Manifest.permission.CALL_PHONE"}, 200);
                            }
                        }).show();
                    }else {
                    ActivityCompat.requestPermissions((Activity)finalcontext,new String[]{"Manifest.permission.CALL_PHONE"},200);
                    }
                }
            }
        });
        return view;
    }
}


class TEST_View extends LinearLayout {
    TextView name;
    TextView call;
    ImageButton imgbt;
    public TEST_View(Context context){
        super(context);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item2,this, true);
        name = (TextView) findViewById(R.id.name);
        call=(TextView) findViewById(R.id.call);
        imgbt = (ImageButton)findViewById(R.id.img_call);
    }
    public void setText(TEST_items items){
        name.setText(items.getName());
        call.setText(items.getCall());
    }
}