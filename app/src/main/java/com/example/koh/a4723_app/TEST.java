package com.example.koh.a4723_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TEST extends FragmentActivity implements OnMapReadyCallback{
   // @Override

    GoogleMap mMap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testbutton);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // ↑매개변수로 GoogleMap 객체가 넘어옵니다.

        // camera 좌쵸를 서울역 근처로 옮겨 봅니다.
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(
                new LatLng(37.555744, 126.970431)   // 위도, 경도
        ));

        // 구글지도(지구) 에서의 zoom 레벨은 1~23 까지 가능합니다.
        // 여러가지 zoom 레벨은 직접 테스트해보세요
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        googleMap.animateCamera(zoom);   // moveCamera 는 바로 변경하지만,
        // animateCamera() 는 근거리에선 부드럽게 변경합니다

        // marker 표시
        // market 의 위치, 타이틀, 짧은설명 추가 가능.
        MarkerOptions marker = new MarkerOptions();
        marker .position(new LatLng(37.555744, 126.970431))
                .title("서울역")
                .snippet("Seoul Station");
        googleMap.addMarker(marker).showInfoWindow(); // 마커추가,화면에출력

        MarkerOptions marker1 = new MarkerOptions();
        marker1 .position(new LatLng(38.555744, 126.970431))
                .title("예시")
                .snippet("example");
        googleMap.addMarker(marker1).showInfoWindow();

        // 마커클릭 이벤트 처리
        // GoogleMap 에 마커클릭 이벤트 설정 가능.
         googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 마커 클릭시 호출되는 콜백 메서드
                Toast.makeText(getApplicationContext(),
                        marker.getTitle() + " 클릭했음"
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


}
