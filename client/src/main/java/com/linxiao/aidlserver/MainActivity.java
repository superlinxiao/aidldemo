package com.linxiao.aidlserver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.linxiao.aidlclient.R;
import com.linxiao.aidlserver.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Intent intent = new Intent();
    intent.setAction("com.linxiao.aldlservice");

    //5.0以后必须设置package 不然会报错（error:Service Intent must be explicit: Intent { act=com.linxiao.aldlservice }）
    //参考：https://blog.csdn.net/xiangruyimo122/article/details/72884500
    intent.setPackage("com.linxiao.aidlserver");
    bindService(intent, new ServiceConnection() {
      @Override
      public void onServiceConnected(ComponentName name, IBinder service) {
        IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        try {
          long add = iMyAidlInterface.add(2, 2L);
          Toast.makeText(MainActivity.this, String.valueOf(add), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onServiceDisconnected(ComponentName name) {

      }
    }, Context.BIND_AUTO_CREATE);
  }
}
