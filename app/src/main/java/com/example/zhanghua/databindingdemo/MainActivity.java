package com.example.zhanghua.databindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zhanghua.databindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        change "setContentView()" to "DataBindingUtil.setContentView()", and the "ActivityMainBinding" is layout name appends "Binding"
//        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        user.firstName="Albert";
        user.lastName="Einstein";
        user.isShowPhone=true;
        user.phone="001-043234";
        binding.setUser(user);
    }
}
