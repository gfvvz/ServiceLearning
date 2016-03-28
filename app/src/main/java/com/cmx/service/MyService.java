package com.cmx.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Johnson Lu on 16-3-24.
 */
public class MyService extends Service {

    private final static String TAG = "Johnson-Service";

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        new Thread(new Runnable() {
            public void run() {
                //处理具体的逻辑
                //stopSelf();  //服务执行完毕后自动停止
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onDestroy");
        return mBinder;  //在这里返回新建的MyBinder类
    }

    //MyBinder类，继承Binder：让里面的方法执行下载任务，并获取下载进度
    class MyBinder extends Binder {

        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }
        public int getProgress(){
            Log.d("TAG", "getProgress() executed");
            return 0;
        }

    }
}
