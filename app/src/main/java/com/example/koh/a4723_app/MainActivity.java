package com.example.koh.a4723_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public static Context mContext;
    public static String data;
    static Button benefit;
    String setText_str = "";
    SQLiteDatabase db;
    public void saveBenefits(){//레코드 입력 함수
        getSharedPreferences("isSaved", Context.MODE_PRIVATE).edit().remove("save").apply();
        getSharedPreferences("isSaved", Context.MODE_PRIVATE).edit().putInt("save", 1).apply();
        db.execSQL("CREATE TABLE IF NOT EXISTS YangYang (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//양양군
        db.execSQL("CREATE TABLE IF NOT EXISTS JeongSeon (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//정선군
        db.execSQL("CREATE TABLE IF NOT EXISTS SamCheok (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//삼척시
        db.execSQL("CREATE TABLE IF NOT EXISTS PyeongChang (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//평창군
        db.execSQL("CREATE TABLE IF NOT EXISTS YeongWol (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//영월군
        db.execSQL("CREATE TABLE IF NOT EXISTS Goseong (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//고성군
        db.execSQL("CREATE TABLE IF NOT EXISTS GangNeung (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//강릉시
        db.execSQL("CREATE TABLE IF NOT EXISTS InJe (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//인제군
        db.execSQL("CREATE TABLE IF NOT EXISTS TaeBaek (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//태백시
        db.execSQL("CREATE TABLE IF NOT EXISTS YangGu (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//양구군
        db.execSQL("CREATE TABLE IF NOT EXISTS WonJu (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//원주시
        db.execSQL("CREATE TABLE IF NOT EXISTS ChunCheon (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//춘천시
        db.execSQL("CREATE TABLE IF NOT EXISTS SokCho (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//속초시
        db.execSQL("CREATE TABLE IF NOT EXISTS HongCheon (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//홍천군
        db.execSQL("CREATE TABLE IF NOT EXISTS DongHae (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//동해시
        db.execSQL("CREATE TABLE IF NOT EXISTS Hoengseong (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//횡성군
        db.execSQL("CREATE TABLE IF NOT EXISTS ChearWon (checked VARCHAR, _start VARCHAR, _end VARCHAR, get VARCHAR(100));");//철원군
        //==================================================================================양양군
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','0','12','엽산제 무료 지급');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','0','40','유산균제 1회 제공');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','0','64','표준모자보건수첩 제공');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','16','48','철분제 무료 제공');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','34','44','바우처 서비스 신청');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','44','신생아 청각선별검사');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','44','출산장려금 지원사업');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','48','저소득층 기저귀·조제분유 지원사업');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','64','고위험 임산부 의료비 지원');");
        db.execSQL("INSERT INTO YangYang (checked, _start, _end ,get) Values ('0','40','64','의료비 지원사업');");
        //==================================================================================정선군
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','0','12','엽산제 제공');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','16','40','철분제 제공');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','28','44','신생아 청각선별검사 지원');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','34','44','산모·신생아건강관리 지원사업');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','40','44','출산육아용품 상품권 지원');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','40','64','미숙아·선천성이상아 의료비지원');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','40','64','고위험임산부 의료비 지원사업');");
        db.execSQL("INSERT INTO JeongSeon (checked, _start, _end ,get) Values ('0','40','134','저소득층 기저귀·조제분유 지원사업');");
        //==================================================================================삼척시
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','0','40','임산부 산전검진비 지원');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','20','40','고위험 임산부 의료비지원 사업');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','28','44','신생아 난청조기진단 검사');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','32','44','공공산후조리원 이용료 지원');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','34','44','산모신생아 건강관리사업');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','40','44','출산장려금 지원');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','40','44','출생아 건강보장보험료 지원');");
        db.execSQL("INSERT INTO SamCheok (checked, _start, _end ,get) Values ('0','40','134','저소득층 기저귀 조제분유 지원사업');");
        //==================================================================================평창군
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','0','12','엽산제 지급');");
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','16','40','철분제 지급');");
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','40','48','임산부 산후돌봄 지원');");
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','40','64','고위험 임산부 의료비 지원');");
        db.execSQL("INSERT INTO PyeongChang (checked, _start, _end ,get) Values ('0','40','134','기저귀,조제분유 지원');");
        //==================================================================================영월군
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','0','12','엽산제 지원');");
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','0','40','영양플러스 보충식품 지원');");
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','8','10','무료검사(빈혈, 매독, B형간염, 에이즈 검사 등)');");
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','16','40','철분제 지원');");
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','28','44','신생아 난청조기진단(청각선별검사) 지원');");
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','34','44','산모 · 신생아 건강관리사 지원사업');");
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','36','40','임신 축하기념품 지원');");
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','40','40','선천성대사이상검사비 지원');");
        db.execSQL("INSERT INTO YeongWol (checked, _start, _end ,get) Values ('0','40','64','산후건강관리 지원사업');");
        //==================================================================================고성군
        db.execSQL("INSERT INTO Goseong (checked, _start, _end ,get) Values ('0','0','12','엽산제 지원');");
        db.execSQL("INSERT INTO Goseong (checked, _start, _end ,get) Values ('0','0','32','출산용품 지원(아기내복,아기속싸개)');");
        db.execSQL("INSERT INTO Goseong (checked, _start, _end ,get) Values ('0','20','40','철분제 지원');");
        db.execSQL("INSERT INTO Goseong (checked, _start, _end ,get) Values ('0','40','52','고위험 임산부 지원');");
        db.execSQL("INSERT INTO Goseong (checked, _start, _end ,get) Values ('0','40','88','저소득층 기저귀,조제분유 지원');");
        //==================================================================================강릉시
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','0','15','엽산제');");
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','0','40','모성 검사');");
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','16','52','철분제');");
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','20','40','출산 준비 교실');");
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','25','40','출산용품');");
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','28','40','신생아 청각선별검사 쿠폰 발급');");
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','34','40','산모・신생아 건강관리사지원');");
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','40','44','출산 장려금');");
        db.execSQL("INSERT INTO GangNeung (checked, _start, _end ,get) Values ('0','40','52','고위험임산부 의료비지원');");
        //==================================================================================인제군
        db.execSQL("INSERT INTO InJe (checked, _start, _end ,get) Values ('0','0','12','엽산제 지급');");
        db.execSQL("INSERT INTO InJe (checked, _start, _end ,get) Values ('0','16','52','철분제 지급');");
        db.execSQL("INSERT INTO InJe (checked, _start, _end ,get) Values ('0','24','40','신생아청각검사 쿠폰');");
        db.execSQL("INSERT INTO InJe (checked, _start, _end ,get) Values ('0','34','44','산모·신생아 도우미지원사업');");
        //==================================================================================태백시
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','0','8','모성 혈액검사');");
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','0','12','엽산제 지급');");
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','16','16','산전 기형아 검사 쿠폰 ');");
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','16','28','출산 준비 교실');");
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','16','40','철분제 지급');");
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','20','32','초음파 쿠폰 ');");
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','24','40','신생아 난청 조기 진단사업');");
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','34','44','산모, 신생아 도우미 지원 사업');");
        db.execSQL("INSERT INTO TaeBaek (checked, _start, _end ,get) Values ('0','40','64','고위험 임산부 의료비 지원');");
        //==================================================================================양구군
        db.execSQL("INSERT INTO YangGu (checked, _start, _end ,get) Values ('0','20','40','임산부 철분제 지원');");
        db.execSQL("INSERT INTO YangGu (checked, _start, _end ,get) Values ('0','36','43','산모·신생아 도우미지원사업');");
        db.execSQL("INSERT INTO YangGu (checked, _start, _end ,get) Values ('0','40','88','양구군 출생아 건강보험 가입지원');");
        db.execSQL("INSERT INTO YangGu (checked, _start, _end ,get) Values ('0','40','88','출산장려금 지원');");
        //==================================================================================원주시
        db.execSQL("INSERT INTO WonJu (checked, _start, _end ,get) Values ('0','4','12','엽산제 지원');");
        db.execSQL("INSERT INTO WonJu (checked, _start, _end ,get) Values ('0','8','10','모성검사');");
        db.execSQL("INSERT INTO WonJu (checked, _start, _end ,get) Values ('0','16','40','철분제 지원');");
        db.execSQL("INSERT INTO WonJu (checked, _start, _end ,get) Values ('0','24','40','신생아 난청조기진단사업');");
        db.execSQL("INSERT INTO WonJu (checked, _start, _end ,get) Values ('0','28','64','모유수유 교실');");
        db.execSQL("INSERT INTO WonJu (checked, _start, _end ,get) Values ('0','34','44','산모 신생아 건강관리 지원');");
        db.execSQL("INSERT INTO WonJu (checked, _start, _end ,get) Values ('0','40','41','선천성대사이상 검사 및 환아관리');");
        db.execSQL("INSERT INTO WonJu (checked, _start, _end ,get) Values ('0','40','64','산후 건강관리 지원사업(산후 의료비 지원)');");
        //==================================================================================춘천시
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','0','12','엽산제 지급');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','0','48','임신·출산진료비 지원');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','17','40','철분제 지급');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','28','44','청각선별검사');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','28','44','신생아 청각 선별검사 지원');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','34','44','산모, 신생아 건강관리 지원');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','40','41','선천성대사이상검사');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','40','44','유축기 대여');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','40','64','고위험 임산부 의료비 지원');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','40','64','산후 의료비 지원');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','40','88','출산장려금 지원');");
        db.execSQL("INSERT INTO ChunCheon (checked, _start, _end ,get) Values ('0','40','92','저소득층 기저귀·조제분유 지원');");
        //==================================================================================속초시
        db.execSQL("INSERT INTO SokCho (checked, _start, _end ,get) Values ('0','34','44','산모,신생아 건강관리사 지원');");
        db.execSQL("INSERT INTO SokCho (checked, _start, _end ,get) Values ('0','40','64','출산장려금');");
        db.execSQL("INSERT INTO SokCho (checked, _start, _end ,get) Values ('0','52','624','아이돌봄 서비스지원');");
        //==================================================================================홍천군
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','0','12','엽산제');");
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','16','40','철분제');");
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','20','40','출산 준비교실 운영');");
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','34','44','산모/신생아도우미 바우처 지원사업');");
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','40','41','선천성대사이상검사비지원');");
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','40','44','신생아 청각선별검사 지원사업');");
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','40','52','산후영양제 지원');");
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','40','52','고위험 임산부 의료비 지원사업');");
        db.execSQL("INSERT INTO HongCheon (checked, _start, _end ,get) Values ('0','40','136','저소득층 기저귀 조제분유 지원 사업');");
        //==================================================================================동해시
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','0','4','임신 반응 검사');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','0','5','임신초기 혈액검사');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','0','12','엽산제');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','0','40','초음파 검사 쿠폰(2회)');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','14','18','기형아 검사 쿠폰');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','16','40','철분제');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','20','28','행복한 예비맘 건강교실');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','24','26','임신성 당뇨 검사 쿠폰');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','34','36','분만전 검사 쿠폰');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','34','44','산모·신생아건강관리 지원');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','35','44','청각선별검사 쿠폰 지원');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','40','64','고위험 임산부 의료비 지원사업');");
        db.execSQL("INSERT INTO DongHae (checked, _start, _end ,get) Values ('0','40','64','저소득층 기저귀 조제분유 지원사업');");
        //==================================================================================횡성군
        db.execSQL("INSERT INTO Hoengseong (checked, _start, _end ,get) Values ('0','0','40','임산부 산전검사 쿠폰제');");
        db.execSQL("INSERT INTO Hoengseong (checked, _start, _end ,get) Values ('0','0','48','청소년산모 임신·출산 의료비지원');");
        db.execSQL("INSERT INTO Hoengseong (checked, _start, _end ,get) Values ('0','28','40','신생아 청각 선별검사');");
        db.execSQL("INSERT INTO Hoengseong (checked, _start, _end ,get) Values ('0','34','44','산모신생아건강관리사 지원사업');");
        //==================================================================================철원군
        db.execSQL("INSERT INTO ChearWon (checked, _start, _end ,get) Values ('0','0','12','엽산제 지원사업');");
        db.execSQL("INSERT INTO ChearWon (checked, _start, _end ,get) Values ('0','16','40','철분제 지원사업');");
        db.execSQL("INSERT INTO ChearWon (checked, _start, _end ,get) Values ('0','28','44','난청조기진단사업');");
        db.execSQL("INSERT INTO ChearWon (checked, _start, _end ,get) Values ('0','34','44','산모 신생아 도우미 지원사업');");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mContext = this;

        db = this.openOrCreateDatabase("Benefit", MODE_PRIVATE, null);//db이름: Benefit
        int check_save = getSharedPreferences("isSaved", Context.MODE_PRIVATE).getInt("save",0);
        if(check_save==0) saveBenefits(); //한번도 DB 저장한 적이 없을 때만 레코드 입력
        db.close();

        //툴바 설정
        toolbar.setTitleTextColor(Color.parseColor("WHITE")); //제목의 칼라
        toolbar.setTitle(" ");
        //toolbar.setSubtitle("메인페이지^__________^"); //부제목 넣기
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher); //제목앞에 아이콘 넣기
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.


        ImageButton Weight_Graph = (ImageButton) findViewById(R.id.Weight_Graph);
        TextView info_txt =  (TextView) findViewById(R.id.info_txt);
        ImageButton Health_Service = (ImageButton) findViewById(R.id.Health_Service);
        //Button Find_Hospital = (Button) findViewById(R.id.Find_Hospital);
        ImageButton Calendar = (ImageButton) findViewById(R.id.Calendar);
        ImageButton TEST = (ImageButton) findViewById(R.id.testbutton);//테스트버튼
        TextView weeks_txt = (TextView) findViewById(R.id.info_weeks_txt);
        benefit = (Button) findViewById(R.id.info_weeks);


        btSet(BtBenefit()); //버튼에 표시할 string

        setText_str = "";

        String baby_name = getPreferences("아기이름");
        String my_date = getPreferences("날짜"); // 사용자가 저장한 마지막 생리 날짜 불러오기
        String check = getPreferences("자동계산");
        String due_date = getPreferences("출산날짜");



        if(my_date != ""){

            long now = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(now);
            String nowDate = sdf.format(date); // 현재 시간 구하고 yyyyMMdd 형식으로 변환

            Date startDate = new Date();
            Date endDate = new Date();

            try {
                startDate = sdf.parse(nowDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                endDate = sdf.parse(my_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
            long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
            final String tmp = "오늘은 " + diffDay / 7 + "주 " + diffDay % 7 + "일째";
            weeks_txt.setText(tmp);
            getSharedPreferences("pref", MODE_PRIVATE).edit().remove("몇주차").apply();
            getSharedPreferences("pref", MODE_PRIVATE).edit().putLong("몇주차",diffDay/7).apply();


        }
        else if(my_date  == ""){

            //setText_str += "마지막 월경 날짜를 입력해주세요\n";
            weeks_txt.setText("마지막 월경 날짜를 입력해주세요");
        }

        if(check.equals("true")) {

            long now = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(now);
            String nowDate = sdf.format(date); // 현재 시간 구하고 yyyyMMdd 형식으로 변환
            Date startDate = new Date();
            Date endDate = new Date();

            if (my_date.length() != 0 ) {
                try {
                    startDate = sdf.parse(nowDate);

                } catch (ParseException e) {
                    e.printStackTrace();

                }

                try {
                    endDate = sdf.parse(my_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.

                long test1 = endDate.getTime() / 1000;
                long test2 = 280 * 24 * 60 * 60;
                long test3 = test1 + test2;
                String test4 = Long.toString(test3);

                Date date3 = new Date(test3);
                Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

                String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date((test1 + test2) * 1000L));

                if (timeStamp.length() != 0) {

                    String year = timeStamp.substring(0, 4);
                    String month = null;

                    if (timeStamp.substring(4, 5).equals("1")) {
                        month = timeStamp.substring(4, 6);
                        //Toast.makeText(getApplicationContext() , " ㅋ", Toast.LENGTH_SHORT).show();
                    } else {
                        month = timeStamp.substring(5, 6);
                    }
                    String day = timeStamp.substring(6, 8);
                    setText_str += year + "년 " + month + "월 " + day + "일, ";

                }
            }
        } else if(check.equals("false")){

            String my_date2 = getPreferences("출산날짜");
            String year = my_date2.substring(0, 4);
            String month = null;
            String day = null;
            if (my_date2.substring(4, 5).equals("1")) {
                month = my_date2.substring(4, 6);
            } else {
                month = my_date2.substring(5, 6);
            }
            if (my_date2.substring(6, 7).equals("1")) {
                day = my_date2.substring(6, 8);
            } else {
                day = my_date2.substring(7, 8);
            }

            setText_str += year + "년" + month + "월" + day + "일, ";

        }else if(check.equals("")) {

            setText_str +="(출산 예정일을 입력해주세요) \n";

        }

        if(baby_name.length()>0){
            String name1= getComleteWordByJongsung(baby_name,"을","를");
            setText_str += name1 + " 만나는 날!";
        }
        else {
            setText_str += "(아기의 이름을 등록해주세요) \n";
        }


        info_txt.setText(setText_str);



        Weight_Graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String test = getPreferences("날짜");
                //Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();

                if(test.length() ==0) {
                    Toast.makeText(getApplicationContext(), "먼저 아이의 정보를 저장 해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this,Weight_Graph.class);
                    startActivity(intent);
                }
            }
        });

        Health_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.koh.a4723_app.Health.Health_Service.class);
                startActivity(intent);
            }
        });

       /* Find_Hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Find_Hospital.class);
                startActivity(intent);
            }
        });*/

        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.koh.a4723_app.calendar.Calendar.class);
                startActivity(intent);
            }
        });
        //테스트버튼-===========================
        TEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TEST.class);
                startActivity(intent);
            }
        });
        //==========================================
        benefit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Benefit.class);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.My_Page:
                Intent intent = new Intent(MainActivity.this,My_Page.class);
                startActivityForResult(intent, REQ_ADD_CONTACT);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    static final int REQ_ADD_CONTACT = 1 ;

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == REQ_ADD_CONTACT) {
            if (resultCode == RESULT_OK) {

                String baby_name = intent.getStringExtra("아기이름");
                savePreferences("아기이름",baby_name);
            }
        }
    }

    private void savePreferences(String code , String str){ //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(code, str);
        editor.commit();
    }

    private String getPreferences(String code){ //데이터 불러오는 함수
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String temp = pref.getString(code , "");
        return temp;

    }
    public static final String getComleteWordByJongsung(String name, String firstValue, String secondValue) {
        char lastName = name.charAt(name.length() - 1); // 한글의 제일 처음과 끝의 범위밖일 경우는 오류
        if (lastName < 0xAC00 || lastName > 0xD7A3) {
            return name;
        }
        String seletedValue = (lastName - 0xAC00) % 28 > 0 ? firstValue : secondValue;
        return name+seletedValue;
    }




    public void onResume() {
        super.onResume();
        ImageButton Weight_Graph = (ImageButton) findViewById(R.id.Weight_Graph);
        TextView weeks_txt = (TextView) findViewById(R.id.info_weeks_txt);
        TextView info_txt =  (TextView) findViewById(R.id.info_txt);
        setText_str = "";

        String baby_name = getPreferences("아기이름");
        String my_date = getPreferences("날짜"); // 사용자가 저장한 마지막 생리 날짜 불러오기
        String check = getPreferences("자동계산");
        String due_date = getPreferences("출산날짜");

        btSet(BtBenefit());

        if(my_date != ""){

            long now = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(now);
            String nowDate = sdf.format(date); // 현재 시간 구하고 yyyyMMdd 형식으로 변환

            Date startDate = new Date();
            Date endDate = new Date();

            try {
                startDate = sdf.parse(nowDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                endDate = sdf.parse(my_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
            long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
            final String tmp = "오늘은 " + diffDay / 7 + "주 " + diffDay % 7 + "일째";
            weeks_txt.setText(tmp);
            getSharedPreferences("pref", MODE_PRIVATE).edit().remove("몇주차").apply();
            getSharedPreferences("pref", MODE_PRIVATE).edit().putLong("몇주차",diffDay/7).apply();

        }
        else if(my_date  == ""){

           //setText_str += "마지막 월경 날짜를 입력해주세요\n";
            weeks_txt.setText("마지막 월경 날짜를 입력해주세요");
        }

        if(check.equals("true")) {

            long now = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(now);
            String nowDate = sdf.format(date); // 현재 시간 구하고 yyyyMMdd 형식으로 변환
            Date startDate = new Date();
            Date endDate = new Date();

            if (my_date.length() != 0 ) {
                try {
                    startDate = sdf.parse(nowDate);

                } catch (ParseException e) {
                    e.printStackTrace();

                }

                try {
                    endDate = sdf.parse(my_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.

                long test1 = endDate.getTime() / 1000;
                long test2 = 280 * 24 * 60 * 60;
                long test3 = test1 + test2;
                String test4 = Long.toString(test3);

                Date date3 = new Date(test3);
                Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

                String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date((test1 + test2) * 1000L));

                if (timeStamp.length() != 0) {

                    String year = timeStamp.substring(0, 4);
                    String month = null;

                    if (timeStamp.substring(4, 5).equals("1")) {
                        month = timeStamp.substring(4, 6);
                        //Toast.makeText(getApplicationContext() , " ㅋ", Toast.LENGTH_SHORT).show();
                    } else {
                        month = timeStamp.substring(5, 6);
                    }
                    String day = timeStamp.substring(6, 8);
                    setText_str += year + "년 " + month + "월 " + day + "일, ";

                }
            }
        } else if(check.equals("false")){

            String my_date2 = getPreferences("출산날짜");
            String year = my_date2.substring(0, 4);
            String month = null;
            String day = null;
            if (my_date2.substring(4, 5).equals("1")) {
                month = my_date2.substring(4, 6);
            } else {
                month = my_date2.substring(5, 6);
            }
            if (my_date2.substring(6, 7).equals("1")) {
                day = my_date2.substring(6, 8);
            } else {
                day = my_date2.substring(7, 8);
            }

            setText_str += year + "년" + month + "월" + day + "일, ";

        }else if(check.equals("")) {

            setText_str +="(출산 예정일을 입력해주세요) \n";

        }

        if(baby_name.length()>0){
            String name1= getComleteWordByJongsung(baby_name,"을","를");
            setText_str += name1 + " 만나는 날!";
        }
        else {
            setText_str += "(아기의 이름을 등록해주세요) \n";
        }


        info_txt.setText(setText_str);

    }
    public static void btSet(String str){//버튼 텍스트 바꾸는 함수
        benefit.setText(str);
    }
    public String BtBenefit(){//혜택정리 버튼에 표시할 텍스트 설정
        SQLiteDatabase db = this.openOrCreateDatabase("Benefit", MODE_PRIVATE, null);//db이름: Benefit
        String str="";
        String tbName = Benefit.center(getPreferences("보건소"));
        if(tbName.equals("해당하는 보건소를 등록해주세요.")) return tbName;
        if(getPreferences("자동계산").equals("")) {
            str="출산 예정일을 입력해주세요";
            return str;
        }

        Cursor c = db.rawQuery("SELECT * FROM " + tbName, null);
        int week = (int)getSharedPreferences("pref", MODE_PRIVATE).getLong("몇주차",0);
        if(c.moveToFirst()){
            do {
                int start = c.getInt(c.getColumnIndex("_start"));
                int end = c.getInt(c.getColumnIndex("_end"));
                if(week>=start && week <=end){
                    str+=c.getString(c.getColumnIndex("get"));
                    str+="\n";
                }
            }while(c.moveToNext());
        }
        db.close();
        return str.substring(0,str.length()-1);
    }
}