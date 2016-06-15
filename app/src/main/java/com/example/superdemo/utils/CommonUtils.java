package com.example.superdemo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/6/15.
 */
public class CommonUtils {

    /**
     * Glide 加载图片
     */
    public static void loadGlideImage(Context context, String imagUrl, ImageView imageView){
        Glide.with(context)
                .load(imagUrl)
                .into(imageView);
    }
}
