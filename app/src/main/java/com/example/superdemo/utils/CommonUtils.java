package com.example.superdemo.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.superdemo.R;

/**
 * Created by Administrator on 2016/6/15.
 */
public class CommonUtils {

    /**
     * Glide 加载图片
     */
    public static void loadGlideImage(Context context, String imagUrl, ImageView imageView) {
        Glide.with(context)
                .load(imagUrl)
                .placeholder(R.mipmap.login_logo)
                .error(R.mipmap.login_logo)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    /**
     * 修改状态栏颜色
     *
     * @param activity activity
     * @param color   color
     */
    public static void changeSystemBar(Activity activity, int color, float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarAlpha(alpha);
        tintManager.setStatusBarTintResource(color);
    }

    @TargetApi(19)
    public static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    /**
     * 获取当前应用的版本号
     */
    public static String getVersionName(Context context){
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0.0.0";
        }
        String versionName = packageInfo.versionName;
        return versionName;
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
//        ConnectivityManager connectivity = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (null != connectivity) {
//            NetworkInfo info = connectivity.getActiveNetworkInfo();
//            if (null != info && info.isConnected()) {
//                if (info.getState() == NetworkInfo.State.CONNECTED) {
//                    return true;
//                }
//            }
//        }
        return true;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}
