package com.example.koh.a4723_app;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.database.SQLException;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import android.database.sqlite.SQLiteDatabase;

public class TEST extends FragmentActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    SQLiteDatabase db=null;

    private GoogleApiClient mGoogleApiClient = null;
    private static GoogleMap mGoogleMap = null;
    private Marker currentMarker = null;
    GoogleMap mMap;

    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private static final int UPDATE_INTERVAL_MS = 1000;  // 1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500; // 0.5초

    private Activity mActivity;
    boolean askPermissionOnceAgain = false;
    boolean mRequestingLocationUpdates = false;
    Location mCurrentLocation;
    boolean mMoveMapByUser = true;
    boolean mMoveMapByAPI = true;
    static LatLng currentPosition = new LatLng(37.56, 126.97); //currentPosition을 서울로 초기화

    String tableName = "Hospital";
    String dbName = "H_address.db";

    ArrayList<TEST_item_all> testItems = new ArrayList<>();//병원 이름, 전화번호, 위도, 경도, 마커 저장
    TreeMap<Double,TEST_item_all> treeMap = new TreeMap<Double,TEST_item_all>();
    ArrayList<Double> mapKeys = new ArrayList<>();

    TEST_adapter adapter;
    ListView listView;
    Cursor c;

    EditText editText;


    LocationRequest locationRequest = new LocationRequest()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL_MS)
            .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_testbutton);

        Log.d(TAG, "onCreate");

        listView = (ListView) findViewById(R.id.list);
        editText = (EditText)findViewById(R.id.searchEditText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editText.getText().toString();
                if(editText.length()<=0) setList();
                else search(text);
            }
        });

        mActivity = this;

        //----------구글맵
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //------------------여기서부터 DB

        // DB Create and Open

        db = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " ("
                +"name TEXT,"
                + "p_Number TEXT,"
                +"address TEXT,"
                + "latitude REAL,"
                +"longitude REAL);");

        setRecord();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Marker marker = treeMap.get(mapKeys.get(position)).getMarker();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(marker.getPosition());
                mGoogleMap.moveCamera(cameraUpdate);
                marker.showInfoWindow();
            }
        });
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {
        treeMap = new TreeMap<Double,TEST_item_all>();
        adapter=new TEST_adapter();
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < testItems.size(); i++) {
                // (testItems.getName) 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (testItems.get(i).getName().toLowerCase().contains(charText)){
                    // 검색된 데이터를 리스트에 추가한다.
                    double distance = getDistance(testItems.get(i).getLatLng());
                    treeMap.put(distance,testItems.get(i));
                }
            }
        for (Map.Entry<Double,TEST_item_all> entry : treeMap.entrySet()) {
            adapter.addItem(entry.getValue().getTEST_items());
        }
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //db에 레코드 저장
    public void setRecord(){
        c = db.rawQuery("SELECT * FROM " + tableName, null);
        if(c.getCount()<=0){//db에 저장된 값이 없을 때만 새로 입력
            //산후도우미 제공기관=====================================================================
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('YWCA','033-651-2385','강원도 강릉시 수리골길 27','37.77303153','128.8996002');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('해피케어','033-655-3579','강원도 강릉시 홍제로 44','37.75233362','128.8861547');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('동해YWCA','033-531-3007','강원도 동해시 발한로 182','37.5468641','129.1064642');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('산모나라','033-635-3582','강원도 속초시 신흥3길 11','38.201638','128.535232');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('속초YWCA 돌봄과 살림','033-636-3525','강원도 속초시 번영로 184','38.215143','128.590126');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('원주YWCA','033-742-6091','강원도 원주시 천사로 190','37.350291','127.945422');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('에스엠천사','033-762-7118','강원도 원주시 흥업면 북원로 1600 남원주 두산 위브 APT상가 2층 203호','37.291036','127.917833');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('굿아이맘','033-744-3575','강원도 원주시 평원로 37, 3층','37.349528','127.952162');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('맘스매니저','033-731-7325','강원도 원주시 이화4길 87, 101호','37.341905','127.934176');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('(사) 대한여자기독교춘천YWCA','033-254-4878','강원도 춘천시 중앙로 14','37.88359876','127.7288026');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('참사랑어머니회','033-253-7307','강원도 춘천시 충혼길5번길 1 ','37.86342922','127.7234628');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('춘천아가마지','033-253-3532','강원도 춘천시 삭주로 98-1, 2층','37.88357854','127.7418473');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('친정맘','033-257-3248','강원도 춘천시 효석로 77번길 20-1','37.86108404','127.7401399');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('빅맘','033-257-3249','강원도 춘천시 만천로 57-1, 2층','37.87839424','127.7669285');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('산모도우미119','033-255-3519','강원도 춘천시 충혼길 44번길 12','37.86131399','127.7201695');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('바우처베스트맘','033-256-3710','강원도 춘천시 백령로 152 3층','37.87512581','127.7436019');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('평창지역자활센터','033-332-5432','강원도 평창군 평창읍 노성로 129','37.36903725','128.3978508');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('즐거운산모도우미센터','033-432-2200','강원도 홍천군 홍천읍 성산터길 12-10','37.656453','127.886526');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('(유)열린사회서비스센터','033-343-7132','강원도 횡성군 횡성읍 어사매로 41','37.489426','127.992162');");


            //산부인과===================================================================================
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('세가온산부인과의원','033-643-7114','강원도 강릉시 율곡로 2982-6 (교동)','37.768286','128.892276');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('아이앤맘산부인과의원','033-648-8114','강원도 강릉시 옥가로 21, 4,5층 (옥천동)','37.758351','128.898988');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('행복누리산부인과의원','033-641-6680','강원도 강릉시 경강로 2109 (임당동)','37.75568','128.897174');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('하나산부인과의원','033-651-6008','강원도 강릉시 강릉대로 385 (포남동)','37.769095','128.907824');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('경선산부인과의원','033-643-7131','강원도 강릉시 옥가로7번길 3 (옥천동)','37.757318','128.898499');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('여정언산부인과의원','033-648-4906','강원도 강릉시 경강로 2082 (임당동)','37.753839','128.895155');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('아이맘산부인과의원','033-535-6566','강원도 동해시 한섬로 113-7 (천곡동)','37.5222931','129.1160145');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('고산부인과의원','033-535-5528','강원도 동해시 한섬로 125 (천곡동)','37.5218627','129.1174935');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('그레이스산부인과의원','033-532-0050','강원도 동해시 천곡로 86-2, 3층 (천곡동, 대륜빌딩)','37.5228564','129.1150365');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('중앙산부인과의원','033-637-7000','강원도 속초시 동해대로 4117 (조양동)','38.189388','128.583933');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('고려산부인과의원','033-635-3579','강원도 속초시 중앙로 96 (청학동)','38.201535','128.586264');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('제일산부인과의원','033-635-9050','강원도 속초시 청학로 47 (청학동)','38.202559','128.582745');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('최종열산부인과의원','033-633-0055','강원도 속초시 중앙로108번길 6 (청학동)','38.201883','128.58794');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('서울산부인과의원','033-574-0041','강원도 삼척시 진주로 20 (남양동, 중앙상가)','37.442663','129.164214');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('율산부인과의원','033-765-0655','강원도 원주시 능라동길 61, 8층 (무실동, 정한타워)','37.334167','127.931513');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('아름다운산부인과의원','033-747-8855','강원도 원주시 봉화로 10, 2층 (단계동)','37.3456011','127.9274613');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('연세순풍산부인과의원','033-745-0199','강원도 원주시 장미공원길 43-6 (단계동)','37.3465174','127.9301377');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('미즈산부인과의원','033-733-6100','강원도 원주시 이화6길 1 (단계동)','37.343509','127.9324384');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('미래산부인과의원','033-766-8008','강원도 원주시 단구로 183 (명륜동)','37.3360497','127.9487241');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('예사랑산부인과의원','033-764-3333','강원도 원주시 원일로 120, 6층 (중앙동, 중앙빌딩)','37.35027','127.9484402');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('새봄산부인과의원','033-745-5600','강원도 원주시 시청로 98 (무실동)','37.3358834','127.9287155');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('신희경산부인과의원','033-735-0755','강원도 원주시 서원대로 158, 2층 (단계동)','37.3440237','127.9291613');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('수앤영산부인과의원','033-253-0005','강원도 춘천시 금강로 72, 2층 (조양동, 흥안빌딩)','37.8788593486893','127.72890206262');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('변미숙산부인과의원','033-256-5005','강원도 춘천시 경춘로 2348 (온의동)','37.8630067581705','127.719674465675');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('춘천순산부인과의원','033-252-2345','강원도 춘천시 중앙로 82-1 (중앙로2가)','37.8785173169754','127.725152001453');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('미래산부인과의원','033-241-7677','강원도 춘천시 중앙로 118 (소양로4가)','37.8767439335544','127.721998440731');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('한마음산부인과의원','033-553-6901','강원도 태백시 서황지로 4 (황지동)','37.171999','128.990107');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('호산부인과의원','033-264-1700','강원도 춘천시 퇴계로 189 (석사동)','37.8575024601731','127.73984424496');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('아름다운산부인과의원','033-244-7800','강원도 춘천시 터미널길 21 (온의동)','37.8643929852424','127.719100969001');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('모아산부인과의원','033-432-9020','강원도 홍천군 홍천읍 홍천로 356','37.688594','127.885036');");



            //보건소===================================================================================
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('고성군보건소','033-681-4000','강원도 고성군 간성읍 수성로 30','38.37705462','128.4719878');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('죽왕면보건지소','033-632-0068','강원도 고성군 죽왕면 심층수길 9 (죽왕면보건지소)','38.32544448','128.5258685');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('토성면보건지소','033-633-7217','강원도 고성군 토성면 토성로 174 (토성면보건지소)','38.25969155','128.5566697');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('현내면보건지소','033-682-0207','강원도 고성군 현내면 대진6길 20 (현내면보건지소)','38.49932455','128.4236696');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('도원보건진료소','033-632-3939','강원도 고성군 토성면 사그메기길 5 (도원보건진료소)','38.2713907','128.5000992');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('명파보건진료소','033-682-0098','강원도 고성군 현내면 금강산로 814 (명파보건진료소)','38.53893138','128.4021606');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('산학보건진료쇼','033-682-3902','강원도 고성군 현내면 산학2길 2-13 (산학보건진료소)','38.47638361','128.4088487');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('성천보건진료소','033-635-1763','강원도 고성군 토성면 용원로 277 (성천보건진료소)','38.22738656','128.5477638');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('송정보건진료소','033-682-1826','강원도 고성군 거진읍 건봉사로 1511 (송정보건진료소)','38.44110943','128.4234846');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('아야진보건지료소','033-631-4818','강원도 고성군 토성면 아야진해변길 83 (아야진보건진료소)','38.27255762','128.5557126');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('어천보건진료소','033-681-3356','강원도 고성군 간성읍 어천1길 45-4 (어천보건진료소)','38.36072746','128.4207022');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('용암보건진료소','033-633-0938','강원도 고성군 토성면 장새미길 9 (용암보건진료소)','38.2581137','128.5285342');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('동해시보건소','033-520-7200','강원도 동해시 천곡로 100-2 (천곡동)','37.5227764','129.1171111');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('삼화보건지소','033-533-1045','강원도 동해시 무릉1길 10 (이로동, 삼화동주민센터)','37.4883895','129.0674739');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('흘리보건진료소','033-681-3373','강원도 고성군 간성읍 흘리길 190 (흘리보건진료소)','38.26434131','128.3740103');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('속초시보건소','033-636-4000','강원도 속초시 수복로 36 (교동)','38.196388','128.575859');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('삼척시보건소','033-572-4000','강원도 삼척시 척주로 76(남양동)','37.44374981','129.172152');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('도계보건출장소','033-541-4000','강원도 삼척시 도계읍 전두안길 56 (도계보건출장소)','37.23315565','129.043211');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('원덕보건출장소','033-573-4000','강원도 삼척시 원덕읍 삼척로 445-53 (원덕보건출장소)','37.175668','129.334408');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('근덕보건지소','033-573-0578','강원도 삼척시 근덕면 교가2길 10 (근덕보건지소)','37.380428','129.228322');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('노곡보건지소','033-572-5542','강원도 삼척시 노곡면 미근로 886 (노곡보건지소)','37.347911','129.164002');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('신기보건지소','033-541-1900','강원도 삼척시 신기면 신기길 15 (신기보건지소)','37.349607','129.083158');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('가곡보건지소','033-572-7014','강원도 삼척시 가곡면 가곡천로 1455 (가곡보건지소)','37.147689','129.205806');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('미로보건지소','033-572-0014','강원도 삼척시 미로면 적병길 8 (미로보건지소)','37.417815','129.112484');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('하장보건지소','033-552-0008','강원도 삼척시 하장면 하장길 118 (하장보건지소)','37.349284','128.943118');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('임원보건진료소','033-552-0008','강원도 삼척시 원덕읍 삼척로 1213','37.232984','129.342461');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('상정보건진료소','033-574-5730','강원도 삼척시 미로면 상정길 83','37.381524','129.094739');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('마읍보건진료소','033-572-7282','강원도 삼척시 노곡면 문의재로 2559-17','37.274152','129.179293');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('역둔보건진료소','033-574-3718','강원도 삼척시 하장면 역둔원동로 1-8','37.294073','128.883779');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('장호보건진료소','033-572-3453','강원도 삼척시 근덕면 삼척로 2110-20','37.284377','129.313174');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('매원보건진료소','033-574-9401','강원도 삼척시 근덕면 궁촌길 480','37.304486','129.272781');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('원주시보건소','033-737-4002','강원도 원주시 원일로 139, 3층 (일산동, 건강문화센터)','37.35158571','127.9467949');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('귀래면보건지소','033-737-4536','강원도 원주시 귀래면 북원로 106','37.16951177','127.8845911');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('문막읍보건지소','033-737-4531','강원도 원주시 문막읍 건등로 11','37.31263282','127.8172869');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('부론면보건지소','033-737-4535','강원도 원주시 부론면 법천시장길 59','37.20783965','127.7502498');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('소초면보건지소','033-737-4532','강원도 원주시 소초면 치악로 2790','37.41775233','128.0025673');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('신림면보건지소','033-737-4539','강원도 원주시 신림면 치악로 32','37.23014434','128.0787331');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('지정면보건지소','033-737-4534','강원도 원주시 지정면 간현로 126','37.36188286','127.84034');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('판부면보건지소','033-737-4538','강원도 원주시 치악로 1501 (관설동)','37.31827881','127.9667013');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('호저면보건지소','033-737-4533','강원도 원주시 호저면 호저로 429','37.41055754','127.9257315');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('흥업면보건지소','033-737-4537','강원도 원주시 흥업면 울업1길 3','37.30293168','127.9212276');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('고산보건진료소','033-737-4522','강원도 원주시 호저면 광학로 16','37.45003296','127.9307122');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('단강보건진료소','033-737-4525','강원도 원주시 부론면 부귀로 753','37.15329527','127.7865688');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('비두보건진료소','033-737-4526','강원도 원주시 문막읍 귀문로 956','37.2510191','127.8358711');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('사제보건진료소','033-737-4524','강원도 원주시 흥업면 사제로 424','37.3277377','127.887812');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('산현보건진료소','033-737-4523','강원도 원주시 호저면 칠봉로 380','37.42776342','127.8929744');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('판대보건진료소','033-737-4528','강원도 원주시 지정면 장지길 2','37.36874174','127.8018581');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('학곡보건진료소','033-737-4521','강원도 원주시 소초면 구룡사로 5','37.43726567','128.0573748');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('황둔보건진료소','033-737-4527','강원도 원주시 신림면 신림황둔로 1213','37.26230577','128.1900399');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('양양군보건소','033-670-2550','강원도 양양군 양양읍 양양로 9-5','38.077694','128.627381');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('석항보건진료소','033-378-4900','강원도 영월군 중동면 영월로 3856-3 ','37.198046','128.61982');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('연덕보건진료소','033-374-9609','강원도 영월군 북면 삼방산길 28-11','37.268852','128.365162');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('신천보건진료소','033-372-6099','강원도 영월군 한반도면 서강로 724-4 ','37.236626','128.325272');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('운학보건진료소','033-374-6110','강원도 영월군 무릉도원면 덕은골길 6 ','37.326958','128.189793');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('무릉보건진료소','033-370-1634','강원도 영월군 무릉도원면 중방길 62-2 ','37.288693','128.269324');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('중동면보건지소','033-378-4012','강원도 영월군 중동면 태백산로 1202','37.145006','128.680075');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('김삿갓면보건지소','033-372-9085','강원도 영월군 김삿갓면 옥동장터길 34 ','37.125626','128.570131');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('상동읍보건지소','033-372-2875','강원도 영월군 상동읍 태백산로 3189 ','37.126929','128.826743');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('주천통합보건지소','033-372-7156','강원도 영월군 주천면 송학주천로 1467-20','37.275344','128.270291');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('강원도 영월군 북면보건지소','033-372-3300','강원도 영월군 북면 문학로길 29','37.254483','128.455742');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('강원도 영월군 남면보건지소','033-372-4107','강원도 영월군 남면 연당로 123','37.190583','128.40748');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('강원도 영월군 한반도면보건지소','033-372-5570','강원도 영월군 한반도면 쌍용로 203-3 ','37.177772','128.326662');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('영월군보건소','033-372-2191','강원도 영월군 영월읍 하송로 44','37.18432','128.463524');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('철원군보건소','033-450-5550','강원도 철원군 갈말읍 군탄로 16 (철원군보건소)','38.148083','127.302724');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('통합보건지소','033-450-5651','강원도 철원군 철원읍 금학로255번길 22','38.210776','127.213528');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('김화보건지소','033-450-5652','강원도 철원군 김화읍 호국로 6510','38.249626','127.421249');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('서면보건지소','033-450-5655','강원도 철원군 서면 신술2길 38','38.164364','127.414985');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('근남보건지소','033-450-5656','강원도 철원군 근남면 하오재로 2050-1','38.236671','127.47103');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('잠곡보건진료소','033-450-5856','강원도 철원군 근남면 하오재로 1298 (잠곡2리경로당부녀회)','38.179261','127.464951');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('마현보건진료소','033-450-5855','강원도 철원군 근남면 마현1길 20-41 (마현보건진료지소)','38.273064','127.540287');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('내대보건진료소','033-450-5853','강원도 철원군 갈말읍 내대5길 5-14','38.203048','127.297679');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('지경보건진료소','033-450-5852','강원도 철원군 갈말읍 지경안길 22-18 (지경보건진료소)','38.23691','127.348437');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('양지보건진료소','033-450-5854','강원도 철원군 동송읍 양지3길 13-20 (양지보건진료소)','38.268511','127.284273');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('대마보건진료소','033-450-5850','강원도 철원군 철원읍 묘장로 348-19 (대마보건진료소)','38.263628','127.164382');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('도창보건진료소','033-450-5851','강원도 철원군 김화읍 도창3길 11 (도창보건진료소)','38.269832','127.355125');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('인제군보건소 ','033-460-2425','강원도 인제군 인제읍 인제로 140, 34','38.065881','128.167195');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('인구보건복지협회강원지회가족보건의원','033-263-3167','강원도 춘천시 김유정로 1846 (퇴계동)','37.8515979477212','127.734546780479');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('태백시보건소','033-552-4000','강원도 태백시 태백로 905(황지동)','37.176566','128.992441');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('화천보건의료원','033-441-4000','강원도 화천군 화천읍 강변로111','38.107563','127.709116');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 안흥보건지소','033-340-5731','강원도 횡성군 안흥면 안흥로 31','37.41252942','128.1565651');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 둔내보건지소','033-340-5741','강원도 횡성군 둔내면 둔내로 17','37.51219383','128.2126346');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 갑천보건지소','033-340-5751','강원도 횡성군 갑천면 청정로 매일3길4','37.55911078','128.1089994');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 청일보건지소','033-340-5761','강원도 횡성군 청일면 유동로 17-1','37.58357623','128.150127');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 공근보건지소','033-340-5771','강원도 횡성군 공근면 학담시장길 12','37.53682319','127.9620619');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 서원보건지소','033-340-5781','강원도 횡성군 서원면 서원로 160','37.48626654','127.8504238');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 강림보건지소','033-340-5791','강원도 횡성군 강림면 태종로 46','37.36007911','128.1250942');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 정금보건진료소','033-342-2606','강원도 횡성군 우천면 하궁로 21','37.50336443','128.1054013');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 소사보건진료소','033-342-9704','강원도 횡성군 안흥면 봉화로 소사9길 43','37.4645766','128.1536048');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 화동보건진료소','033-345-2283','강원도 횡성군  둔내면 경강로 5369','37.56767784','128.227967');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 하대보건진료소','033-345-9398','강원도 횡성군 갑천면 갑천로 595','37.53802445','128.1615149');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 추동보건진료소','033-345-0429','강원도 횡성군 갑천면 외갑천로 247','37.56373467','128.034346');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 봉덕보건진료소','033-342-5619','강원도 횡성군 청일면 봉덕로 305','37.57051519','128.1808768');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 가곡보건진료소','033-342-3404','강원도 횡성군 공근면 금계로 가곡1길 3','37.57146585','127.9853679');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 유현보건진료소','033-342-0087','강원도 횡성군 서원면 경강로 유현11길2','37.52724518','127.8849628');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군보건소','033-340-5613','강원도 횡성군 횡성읍 횡성로 379','37.48715749','127.9844403');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('횡성군 우천보건지소','033-340-5721','강원도 횡성군 우천면 우항1길 5-34','37.45973453','128.0631569');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('방림보건지소','033-332-6124','강원도 평창군 방림면 방림1길 14 (방림보건지소)','37.4265224','128.3915586');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('대화보건지소','033-332-6125','강원도 평창군 대화면 남산1길 36','37.5014012','128.4576694');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('대관령보건지소','033-336-6129','강원도 평창군 대관령면 대관령로 63','37.6741813','128.7040351');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('신리보건진료소','033-332-5161','강원도 평창군 대화면 고토곡길 4 (신리보건진료소)','37.5388712','128.4598264');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('등매보건진료소','033-332-5162','강원도 평창군 봉평면 금당계곡로 1777-9 (등매보건진료소)','37.5528649','128.3934439');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('봉평보건지소','033-336-6126','강원도 평창군 봉평면 기풍8길 32-4','37.6162308','128.3807136');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('개수보건진료소','033-332-5160','강원도 평창군 대화면 금당계곡로 832 (개수보건진료소)','37.4870151','128.3915013');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('용산보건진료소','033-336-5169','강원도 평창군 대관령면 큰터길 21 (용산보건진료소)','37.660546','128.6645614');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('거문보건진료소','033-336-5166','강원도 평창군 진부면 거문길 13-3 (거문보건진료소)','37.6031223','128.5315378');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('두일보건진료소','033-336-5167','강원도 평창군 진부면 방아다리로 338-3 (두일 보건진료소)','37.686934','128.5610169');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('진부보건지소','033-336-6128','강원도 평창군 진부면 석두로 12-6','37.6354903','128.5539859');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('고길보건진료소','033-332-5155','강원도 평창군 평창읍 고길천4길 13-15 (고길리보건진료소)','37.4045513','128.4552769');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('도돈보건진료소','033-332-5157','강원도 평창군 평창읍 평창강로 854 (도돈보건진료소)','37.3248985','128.3700993');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('용평보건지소','033-332-6127','강원도 평창군 용평면 갈정지길 10','37.5899185','128.4165757');");


            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('다수보건진료소','033-336-5156','강원도 평창군 평창읍 숫돌고개길 16 (다수보건진료소)','37.3954473','128.3666782');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('하안미보건진료소','033-332-5159','강원도 평창군 대화면 동천길 91 (하안미보건진료소)','37.4507705','128.4473025');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('미탄보건지소','033-332-6123','강원도 평창군 미탄면 미탄문화길 37 (미탄보건소)','37.3408682','128.4996897');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('면온보건진료소','033-332-5163','강원도 평창군 봉평면 진조길 14 (면온보건진료소)','37.5630648','128.3481691');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('수항보건진료소','033-332-5168','강원도 평창군 진부면 오대천로 924 (수항보건진료소)','37.5595215','128.5634616');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('계촌보건진료소','033-332-5158','강원도 평창군 방림면 운지로 294-14 (계촌보건진료소)','37.4507597','128.3023973');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('유천보건진료소','033-332-5170','강원도 평창군 대관령면 전나무길 8 (유천보건진료소)','37.6708434','128.6011895');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('속사보건진료소','033-332-5165','강원도 평창군 용평면 양지길 10','37.6384828','128.4949793');");
            db.execSQL("INSERT INTO "+ tableName +
                    " (name, p_Number, address, latitude, longitude) Values ('평창군보건의료원','033-332-4000','강원도 평창군 평창읍 종부로 61','37.3607053','128.3890248');");



        }
    }

    public void setTestItems(){//db에 있는 레코드를 testItems에 저장
        c = db.rawQuery("SELECT * FROM " + tableName, null);
        testItems.clear();
        if(c.moveToFirst()){
            do {
                TEST_item_all testItem = new TEST_item_all();
                String Name = c.getString(c.getColumnIndex("name"));
                String Call = c.getString(c.getColumnIndex("p_Number"));
                double lat = c.getDouble(c.getColumnIndex("latitude"));
                double lng = c.getDouble(c.getColumnIndex("longitude"));
                String address = c.getString(c.getColumnIndex("address"));
                testItem.setAll(Name,Call,lat,lng);
                /////마커추가
                MarkerOptions mo = new MarkerOptions().position(testItem.getLatLng()).title(Name).snippet(address);
                Marker marker = mGoogleMap.addMarker(mo);
                testItem.setMarker(marker);
                /////
                testItems.add(testItem);

            }while(c.moveToNext());
        }
    }
    public void setList(){//adapter 새로 설정하고 listview 연결
        treeMap = new TreeMap<Double,TEST_item_all>();
        adapter = new TEST_adapter();
        for(int i=0;i<testItems.size();i++){
            double distance = getDistance(testItems.get(i).getLatLng());
            treeMap.put(distance,testItems.get(i));
        }
        for (Map.Entry<Double,TEST_item_all> entry : treeMap.entrySet()) {
            adapter.addItem(entry.getValue().getTEST_items());
        }
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public double getDistance(LatLng latlng) {
        double distance = 0;

        Location locationA = new Location("A");
        locationA.setLatitude(currentPosition.latitude);
        locationA.setLongitude(currentPosition.longitude);

        Location locationB = new Location("B");
        locationB.setLatitude(latlng.latitude);
        locationB.setLongitude(latlng.longitude);
        distance = locationA.distanceTo(locationB);
        return distance;
    }
    public void setMapKeys(){
        //treeMap에 저장된 key를 순서대로 ArrayList mapKeys에 저장
        mapKeys = new ArrayList<>();
        for (Double key : treeMap.keySet()) {
            mapKeys.add(key);
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        if (mGoogleApiClient.isConnected()) {

            Log.d(TAG, "onResume : call startLocationUpdates");
            if (!mRequestingLocationUpdates) startLocationUpdates();
        }


        //앱 정보에서 퍼미션을 허가했는지를 다시 검사해봐야 한다.
        if (askPermissionOnceAgain) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                askPermissionOnceAgain = false;

                checkPermissions();
            }
        }
    }


    private void startLocationUpdates() {

        if (!checkLocationServicesStatus()) {

            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
            showDialogForLocationServiceSetting();
        }else {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음");
                return;
            }


            Log.d(TAG, "startLocationUpdates : call FusedLocationApi.requestLocationUpdates");
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
            mRequestingLocationUpdates = true;

            mGoogleMap.setMyLocationEnabled(true);

        }

    }


    private void stopLocationUpdates() {

        Log.d(TAG,"stopLocationUpdates : LocationServices.FusedLocationApi.removeLocationUpdates");
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        mRequestingLocationUpdates = false;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.d(TAG, "onMapReady :");

        mGoogleMap = googleMap;
        setTestItems();
        String text = editText.getText().toString();
        if(editText.length()<=0) setList();
        else search(text);
        setMapKeys();//mapKey 재배치

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 서울로 이동
        setDefaultLocation();

        //mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener(){

            @Override
            public boolean onMyLocationButtonClick() {

                Log.d( TAG, "onMyLocationButtonClick : 위치에 따른 카메라 이동 활성화");
                mMoveMapByAPI = true;
                return true;
            }
        });
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                Log.d( TAG, "onMapClick :");
            }
        });

        mGoogleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {

            @Override
            public void onCameraMoveStarted(int i) {

                if (mMoveMapByUser == true && mRequestingLocationUpdates){

                    Log.d(TAG, "onCameraMove : 위치에 따른 카메라 이동 비활성화");
                    mMoveMapByAPI = false;
                }

                mMoveMapByUser = true;

            }
        });


        mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {

            @Override
            public void onCameraMove() {


            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {

        currentPosition = new LatLng( location.getLatitude(), location.getLongitude());


        Log.d(TAG, "onLocationChanged : ");

        String markerTitle = getCurrentAddress(currentPosition);
        String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                + " 경도:" + String.valueOf(location.getLongitude());

        //현재 위치에 마커 생성하고 이동
        setCurrentLocation(location, markerTitle, markerSnippet);

        mCurrentLocation = location;
        String text = editText.getText().toString();
        if(editText.length()<=0) setList();
        else search(text);//list 다시 생성
        setMapKeys();
    }


    @Override
    protected void onStart() {

        if(mGoogleApiClient != null && mGoogleApiClient.isConnected() == false){

            Log.d(TAG, "onStart: mGoogleApiClient connect");
            mGoogleApiClient.connect();
        }

        super.onStart();
    }

    @Override
    protected void onStop() {

        if (mRequestingLocationUpdates) {

            Log.d(TAG, "onStop : call stopLocationUpdates");
            stopLocationUpdates();
        }

        if ( mGoogleApiClient.isConnected()) {

            Log.d(TAG, "onStop : mGoogleApiClient disconnect");
            mGoogleApiClient.disconnect();
        }

        super.onStop();
    }


    @Override
    public void onConnected(Bundle connectionHint) {


        if ( mRequestingLocationUpdates == false ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

                if (hasFineLocationPermission == PackageManager.PERMISSION_DENIED) {

                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                } else {

                    Log.d(TAG, "onConnected : 퍼미션 가지고 있음");
                    Log.d(TAG, "onConnected : call startLocationUpdates");
                    startLocationUpdates();
                    mGoogleMap.setMyLocationEnabled(true);
                }

            }else{

                Log.d(TAG, "onConnected : call startLocationUpdates");
                startLocationUpdates();
                mGoogleMap.setMyLocationEnabled(true);
            }
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed");
        setDefaultLocation();
    }


    @Override
    public void onConnectionSuspended(int cause) {

        Log.d(TAG, "onConnectionSuspended");
        if (cause == CAUSE_NETWORK_LOST)
            Log.e(TAG, "onConnectionSuspended(): Google Play services " +
                    "connection lost.  Cause: network lost.");
        else if (cause == CAUSE_SERVICE_DISCONNECTED)
            Log.e(TAG, "onConnectionSuspended():  Google Play services " +
                    "connection lost.  Cause: service disconnected");
    }


    public String getCurrentAddress(LatLng latlng) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {

        mMoveMapByUser = false;


        if (currentMarker != null) currentMarker.remove();


        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);

        //구글맵의 디폴트 현재 위치는 파란색 동그라미로 표시
        //마커를 원하는 이미지로 변경하여 현재 위치 표시하도록 수정 fix - 2017. 11.27
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

        currentMarker = mGoogleMap.addMarker(markerOptions);


        if ( mMoveMapByAPI ) {

            Log.d( TAG, "setCurrentLocation :  mGoogleMap moveCamera "
                    + location.getLatitude() + " " + location.getLongitude() ) ;
            // CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLatLng, 15);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
            mGoogleMap.moveCamera(cameraUpdate);
        }
    }


    public void setDefaultLocation() {

        mMoveMapByUser = false;


        //디폴트 위치, Seoul
        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 여부 확인하세요";


        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mGoogleMap.moveCamera(cameraUpdate);

    }


    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        boolean fineLocationRationale = ActivityCompat
                .shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasFineLocationPermission == PackageManager
                .PERMISSION_DENIED && fineLocationRationale)
            showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");

        else if (hasFineLocationPermission
                == PackageManager.PERMISSION_DENIED && !fineLocationRationale) {
            showDialogForPermissionSetting("퍼미션 거부 + Don't ask again(다시 묻지 않음) " +
                    "체크 박스를 설정한 경우로 설정에서 퍼미션 허가해야합니다.");
        } else if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED) {


            Log.d(TAG, "checkPermissions : 퍼미션 가지고 있음");

            if ( mGoogleApiClient.isConnected() == false) {

                Log.d(TAG, "checkPermissions : 퍼미션 가지고 있음");
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (permsRequestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && grantResults.length > 0) {

            boolean permissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

            if (permissionAccepted) {


                if ( mGoogleApiClient.isConnected() == false) {

                    Log.d(TAG, "onRequestPermissionsResult : mGoogleApiClient connect");
                    mGoogleApiClient.connect();
                }



            } else {

                checkPermissions();
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(TEST.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ActivityCompat.requestPermissions(mActivity,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.create().show();
    }

    private void showDialogForPermissionSetting(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(TEST.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                askPermissionOnceAgain = true;

                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + mActivity.getPackageName()));
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(myAppSettings);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.create().show();
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(TEST.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d(TAG, "onActivityResult : 퍼미션 가지고 있음");


                        if ( mGoogleApiClient.isConnected() == false ) {

                            Log.d( TAG, "onActivityResult : mGoogleApiClient connect ");
                            mGoogleApiClient.connect();
                        }
                        return;
                    }
                }

                break;
        }
    }
}

