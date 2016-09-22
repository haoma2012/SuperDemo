package com.example.superdemo.test;

import android.animation.TimeInterpolator;

/**
 * Created by Administrator on 2016/9/13.
 * 自定义速度控制器~
 */
public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input) / 2);
        } else {
            result = (float) (2 - Math.sin(Math.PI * input) / 2);
        }
        return result;
    }
}
