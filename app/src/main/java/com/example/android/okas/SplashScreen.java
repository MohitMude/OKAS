package com.example.android.okas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    private  static int timeout=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

         }
     },timeout) ;

    }
}
