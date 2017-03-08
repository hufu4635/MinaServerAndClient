package com.pengfei.minademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void connect(View v){
       new Thread(new Runnable() {
           @Override
           public void run() {
               new MinaThread().run();
           }
       }).start();
    }


}
