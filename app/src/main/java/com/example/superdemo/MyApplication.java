package com.example.superdemo;

import android.app.Activity;
import android.app.Application;

import com.example.superdemo.utils.CrashHandler;
import com.example.superdemo.utils.FileUtils;
import com.example.superdemo.utils.ToastUtils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 * MyApplication
 */
public class MyApplication extends Application {

    //运用list来保存们每一个activity是关键
    static List<Activity> mList = new LinkedList<>();
    //为了实现每次使用该类时不创建新的对象而创建的静态对象
    private static MyApplication instance;
    public static String cacheDir;
    public static ToastUtils myToast;

    public static MyApplication getInstance() {
        if (null == instance) {
            synchronized (MyApplication.class) {
                if (null == instance) {
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myToast = new ToastUtils(getApplicationContext());
        Logger.init().logLevel(LogLevel.FULL);
        LeakCanary.install(this);
        //初始化日志输出工具
        CrashHandler.init(new CrashHandler(getApplicationContext()));
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && FileUtils.isExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public int getActivitySize() {
        return mList == null ? 0 : mList.size();
    }

    //关闭每一个list内的activity
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //         System.exit(0);
        }
    }

    //杀进程
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }


    public void finishAll() {
        for (Activity activity : mList) {
            activity.finish();
        }
        mList.clear();
        //这个主要是用来关闭进程的, 光把所有activity finish的话，进程是不会关闭的
        System.exit(0);
        System.gc();
//      android.os.Process.killProcess(android.os.Process.myPid());
    }
}
