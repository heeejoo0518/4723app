package com.example.koh.a4723_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler() , 2000);
    }

    private class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            Splash.this.finish();
        }
    }

}
