package com.example.superdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.superdemo.R;
import com.example.superdemo.adapter.ImageAdapter;
import com.example.superdemo.test.DecelerateAccelerateInterpolator;
import com.example.superdemo.test.Images;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/9/10.
 */
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.notification)
    Button notification;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.listview)
    ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        ImageAdapter adapter = new ImageAdapter(this, 0, Images.imageUrls);
        listview.setAdapter(adapter);
    }

    @OnClick({R.id.button, R.id.notification, R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
//                testValueAnimation();
//                objectAnimation();
                startActivity(new Intent(this,MyOCRTestActivity.class));
                break;
            case R.id.notification:
                //发送通知
                sendNotification();
                break;
            case R.id.button3:
                button3.animate().x(500).y(500).setDuration(500).setInterpolator(new DecelerateAccelerateInterpolator());
                break;
        }
    }

    private void objectAnimation() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(listview, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(listview, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(listview, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();
    }

    private void testValueAnimation() {
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 1f);
        animation.setDuration(300);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentCount = (float) animation.getAnimatedValue();
                Log.d("**", "currentCount = " + currentCount);
            }
        });
        animation.start();
    }

    private void sendNotification() {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Notification notification = builder.setContentTitle("这是通知title")
                .setContentText("这是通知内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.login_logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.login_logo))
                .build();

        manager.notify(1, notification);

    }
}
