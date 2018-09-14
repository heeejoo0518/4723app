package com.example.koh.a4723_app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.*;

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

        view.imgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+items.get(position).getCall()));
                        parent.getContext().startActivity(callIntent);
                    }
                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    }

                };
                TedPermission.with(parent.getContext())
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("권한허용 필요")
                        .setPermissions(Manifest.permission.READ_CONTACTS)
                        .check();

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
        LayoutInflater.from(context).inflate(R.layout.list_item2,this);
        name = (TextView) findViewById(R.id.name);
        call=(TextView) findViewById(R.id.call);
        imgbt = (ImageButton)findViewById(R.id.img_call);
    }
    public void setText(TEST_items items){
        name.setText(items.getName());
        call.setText(items.getCall());
    }
}