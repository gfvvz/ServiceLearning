package com.cmx.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1_start_service;
    private Button button2_stop_service;
    private Button button3_intent_service;
    private Button button4_bind_service;
    private Button button5_unbind_service;

    private MyService.MyBinder myBinder;
    boolean mBound = false; //一开始，并没有和Service绑定.这个参数是用来显示绑定状态

    //匿名内部类：服务连接对象
    private ServiceConnection connection = new ServiceConnection() {

        //当服务异常终止时会调用。注意，解除绑定服务时不会调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }

        //和服务绑定成功后，服务会回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            //在Activity中调用Service里面的方法
            myBinder.startDownload();
            myBinder.getProgress();
            mBound = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1_start_service = (Button) findViewById(R.id.start_service);
        button2_stop_service = (Button) findViewById(R.id.stop_service);
        button3_intent_service = (Button) findViewById(R.id.intent_service);
        button4_bind_service = (Button) findViewById(R.id.bind_service);
        button5_unbind_service = (Button) findViewById(R.id.unbind_service);

        button1_start_service.setOnClickListener(this);
        button2_stop_service.setOnClickListener(this);
        button3_intent_service.setOnClickListener(this);
        button4_bind_service.setOnClickListener(this);
        button5_unbind_service.setOnClickListener(this);
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

                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                /**
                 * 如果和Service是绑定的状态，就解除绑定。
                 * 如果我们既点击了Start Service按钮，又点击了Bind Service按钮，无论单独点击Stop Service按钮还是Unbind Service按钮，Service都不会被销毁。
                 * 必须将Unbind Service按钮和Stop Service按钮都点击一下（没有先后顺序），Service才会被销毁。
                 * 结论：一个Service必须要在既没有和任何Activity关联又处理停止状态的时候才会被销毁。
                 */
                if (mBound) {
                    mBound = false;
                    unbindService(connection);
                }
                break;
            default:
                break;
        }
    }
}
