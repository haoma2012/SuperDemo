package com.example.superdemo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/9/8.
 * CounterView 自绘控件
 */
public class CounterView extends View implements View.OnClickListener {

    private Paint mPaint;
    private Rect mBound;
    private int mCount;

    public CounterView(Context context) {
        super(context, null);
    }

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBound = new Rect();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text, 0, text.length(), mBound);

        //居中显示
        float textWidth = mBound.width();
        float textHeight = mBound.height();
        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
