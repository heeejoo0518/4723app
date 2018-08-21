package com.example.koh.a4723_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Status extends Fragment {
    String tableName = "Table_status";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // onCreateView 안에서는 inflate가 가능. 위에 보면 파라미터가 있음.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_status, container, false);
        return rootView;
    }
}
