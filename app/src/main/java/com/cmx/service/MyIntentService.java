package com.cmx.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Johnson Lu on 16-3-24.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");//调用父类有参构造函数。这里我们手动给服务起个名字为：MyIntentService
        // TODO Auto-generated constructor stub
    }

    //该方法在会在一个单独的线程中执行，来完成工作任务。任务结束后，该Service自动停止
    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub
        for(int i = 0;i<3;i++) {
            //打印当前线程的id
            Log.d("MyIntentService", "IntentService线程的id是：" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("MyIntentService","onDestroy");
    }
}
