package com.example.gfc.gaidelclicker;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by Artem on 26.12.2016.
 */

class AutoClickerThread extends Thread {
    private double count;
    private double delta;
    private Handler handler;

    public AutoClickerThread() {
        super();
        handler = new Handler(Looper.getMainLooper());
        start();
    }

    public void run() {
        while (true) {
            try {
                for (int i = 0; i < 10; i++) {
                    MainActivity.count += MainActivity.delta * 0.1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.countOfClick.setText(Integer.toString((int) MainActivity.count));

                        }
                    });
                    Thread.sleep(100);
                }
                MainActivity.maximumAll += delta;


            } catch (InterruptedException e) {
                System.out.println("Второй поток прерван");
            }
        }

    }

}
