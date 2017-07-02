package com.example.zhanghua.databindingdemo;

import android.view.View;
import android.widget.Toast;

public class MyHandler {

    public void showTost(View view) {
        Toast.makeText(view.getContext(), "This is a toast", Toast.LENGTH_SHORT).show();
    }

    public void onEventListenerExecute(MyTask task) {
        task.run();
    }

}
