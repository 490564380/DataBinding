package com.example.zhanghua.databindingdemo;

import android.content.Context;
import android.widget.Toast;

public class MyTask implements Runnable {

    private Context mContext;

    public MyTask(Context context) {
        mContext = context;
    }

    @Override
    public void run() {
        Toast.makeText(mContext, "tash run", Toast.LENGTH_SHORT).show();
    }
}
