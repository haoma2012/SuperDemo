package com.example.superdemo.databind.handlers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/5.
 * date binding
 * event handler
 */
public class MyHandlers {

    private Context context;

    public MyHandlers(Context context) {
        this.context = context;
    }

    public void onClickName(View view) {
        Toast.makeText(context, "databinding click name", Toast.LENGTH_SHORT).show();
    }
}
