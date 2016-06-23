package com.example.superdemo.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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

}
