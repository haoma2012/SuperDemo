package com.example.superdemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/7/8.
 */
public class RemoteService extends Service{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    private final IRemoteService.Stub mBinder = new IRemoteService.Stub();
}
