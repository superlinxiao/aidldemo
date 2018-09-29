package com.linxiao.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.linxiao.aidlserver.aidl.IMyAidlInterface;

/**
 * Description
 * Author lizheng
 * Create Data  2018\9\29 0029
 */
public class AddService extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
      @Override
      public long add(int anInt, long aLong) {
        return anInt + aLong;
      }
    };
    return stub;
  }

}
