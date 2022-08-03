package com.example.finalprojecttemplate;

import android.os.Message;

public class MyThread extends Thread {
    private boolean stop;
    private MyHandler handler;

    public MyThread(MyHandler handler) {
        this.stop = true;
        this.handler = handler;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void run() {
        stop = false;
        int i = 0;
        while (!stop) {
            i++;
            if (i == AnimActivity.FRAMES - 1) i = 0;
            sendCounter(i);
            try {
                this.sleep(60);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void sendCounter(int num) {
        Message msg = handler.obtainMessage();
        msg.getData().putInt("count", num);
        handler.sendMessage(msg);
    }
}


