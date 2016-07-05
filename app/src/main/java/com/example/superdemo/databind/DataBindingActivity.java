package com.example.superdemo.databind;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.superdemo.R;
import com.example.superdemo.databind.handlers.MyHandlers;
import com.example.superdemo.databinding.ActivityDatabindingBinding;
import com.example.superdemo.model.User;

/**
 * DataBinding
 * 1.set build.gradle dataBinding.enabled = true
 * 2.change xml root --> layout
 * 3.HelloWorldBinding binding =DataBindingUtil.setContentView(this, R.layout.hello_world);
 * binding.hello.setText("Hello World"); // you should use resources!
 */
public class DataBindingActivity extends AppCompatActivity {

//    @BindView(R.id.main_top_name)
//    TextView mainTopName;
//    @BindView(R.id.main_top_back)
//    RelativeLayout mainTopBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.main_top);
//        ButterKnife.bind(this);

        ActivityDatabindingBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_databinding);

        User user = new User("yang","dehao",25);
        binding.setUser(user);
        binding.setHandlers(new MyHandlers(getApplicationContext()));
    }
}
