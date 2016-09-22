package com.example.superdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/1.
 * ToastUtils
 */
public class ToastUtils {

    public static Toast mToast;

    public static void showToast(String text, Context mContext) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
