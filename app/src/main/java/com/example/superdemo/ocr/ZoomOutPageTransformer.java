package com.example.superdemo.ocr;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2016/9/19.
 * ZoomOutPageTransformer
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static float MIN_ALPHA = 0.35F;
    private static float MIN_SCALE = 0.65F;

    public void transformPage(View paramView, float paramFloat)
    {
        int i = paramView.getWidth();
        int j = paramView.getHeight();
        if (paramFloat < -1.0F)
        {
            paramView.setAlpha(0.0F);
            return;
        }
        if (paramFloat <= 1.0F)
        {
            float f1 = Math.max(MIN_SCALE, 1.0F - Math.abs(paramFloat));
            float f2 = j * (1.0F - f1) / 2.0F;
            float f3 = i * (1.0F - f1) / 2.0F;
            if (paramFloat < 0.0F) {
                paramView.setTranslationX(f3 - f2 / 2.0F);
            }
            for (;;)
            {
                paramView.setScaleX(f1);
                paramView.setScaleY(f1);
                paramView.setAlpha(MIN_ALPHA + (f1 - MIN_SCALE) / (1.0F - MIN_SCALE) * (1.0F - MIN_ALPHA));

                paramView.setTranslationX(-f3 + f2 / 2.0F);
                return;
            }
        }
        paramView.setAlpha(0.0F);
    }
}
