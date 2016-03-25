package com.cmx.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1_start_service;
    private Button button2_stop_service;
    private Button button3_intent_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1_start_service = (Button) findViewById(R.id.start_service);
        button2_stop_service = (Button) findViewById(R.id.stop_service);
        button3_intent_service = (Button) findViewById(R.id.intent_service);
        button1_start_service.setOnClickListener(this);
        button2_stop_service.setOnClickListener(this);
        button3_intent_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.intent_service:
                Log.d("MainActivity", "主线程的id是：" + Thread.currentThread().getId());

                //android.os.Debug.waitForDebugger();

                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}
