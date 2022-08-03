package com.example.finalprojecttemplate;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MyHandler extends Handler {
    private AnimActivity animActivity;

    public MyHandler(AnimActivity animActivity) {
        super();
        this.animActivity = animActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Bundle data = msg.getData();
        int num = data.getInt("count");
        animActivity.setFrame(num);
    }

}
