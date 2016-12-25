package com.example.gfc.gaidelclicker;

import android.animation.ObjectAnimator;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton Gaidel;
    ImageButton Factory;
    ImageButton Clicker;
    ImageButton Farm;
    TextView countOfClick;
    TextView countOfFarm;
    TextView countOfFactory;
    TextView countOfClicker;
    TextView priceOfFarm;
    TextView priceOfFactory;
    TextView priceOfClicker;
    ImageView svaston;


    int countOfF = 0;
    int countOfFa = 0;
    int countOfC = 0;
    int priceFarm = 560;
    int priceFactory = 150;
    int priceClicker = 20;
    double count = 0;
    double delta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gaidel = (ImageButton) findViewById(R.id.buttonGaidel);
        Factory = (ImageButton) findViewById(R.id.buttonFactory);
        Clicker = (ImageButton) findViewById(R.id.buttonClicker);
        Farm = (ImageButton) findViewById(R.id.buttonFarm);
        svaston = (ImageView) findViewById(R.id.imageView);

        countOfClick = (TextView) findViewById(R.id.clicks);
        countOfClicker = (TextView) findViewById(R.id.clickers);
        countOfFactory = (TextView) findViewById(R.id.factories);
        countOfFarm = (TextView) findViewById(R.id.farms);
        priceOfFarm = (TextView) findViewById(R.id.textView7);
        priceOfFactory = (TextView) findViewById(R.id.textView5);
        priceOfClicker = (TextView) findViewById(R.id.textView);

        countOfFarm.setText(Integer.toString(countOfFa));
        countOfFactory.setText(Integer.toString(countOfF));
        countOfClicker.setText(Integer.toString(countOfC));
        countOfClick.setText(Integer.toString((int) count));
        countOfClick.setTextSize(36);


        priceOfFactory.setText("Цена:" + Integer.toString(priceFactory));
        priceOfClicker.setText("Цена:" + Integer.toString(priceClicker));
        priceOfFarm.setText("Цена:" + Integer.toString(priceFarm));
        DelterThread a = new DelterThread();


        ObjectAnimator anim = ObjectAnimator.ofFloat(svaston, View.ROTATION, 0f, 360f);
        anim.setRepeatCount(-1);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(2000);
        anim.start();


        View.OnClickListener clickOnGaidel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countOfClick.setText(Integer.toString((int) count));
                //Toast toast = Toast.makeText(getApplicationContext(), "Gaidel!GO!GO!", Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.BOTTOM, 0, 5);
                //toast.show();

            }
        };
        View.OnClickListener clickOnFactory = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count >= priceFactory) {
                    countOfF++;
                    count -= priceFactory;
                    delta += 1;
                    priceFactory += (int) priceFactory * 0.2;
                    priceOfFactory.setText("Цена:" + Integer.toString(priceFactory));
                    countOfFactory.setText(Integer.toString(countOfF));
                    countOfClick.setText(Integer.toString((int) count));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Не доступно для покупки", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 5);
                    toast.show();
                }

            }
        };
        View.OnClickListener clickOnClicker = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count >= priceClicker) {
                    countOfC++;
                    count -= priceClicker;
                    delta += 0.1;
                    priceClicker += (int) priceClicker * 0.2;
                    priceOfClicker.setText("Цена:" + Integer.toString(priceClicker));
                    countOfClicker.setText(Integer.toString(countOfC));
                    countOfClick.setText(Integer.toString((int) count));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Не доступно для покупки", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 5);
                    toast.show();
                }

            }
        };
        View.OnClickListener clickOnFarm = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count >= priceFarm) {
                    countOfFa++;
                    count -= priceFarm;
                    delta += 8;
                    priceFarm += (int) priceFarm * 0.2;
                    priceOfFarm.setText("Цена:" + Integer.toString(priceFarm));
                    countOfFarm.setText(Integer.toString(countOfFa));
                    countOfClick.setText(Integer.toString((int) count));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Не доступно для покупки", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 5);
                    toast.show();
                }

            }
        };

        Gaidel.setOnClickListener(clickOnGaidel);
        Gaidel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Gaidel.setScaleX(1f);
                        Gaidel.setScaleY(1f);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        Gaidel.setScaleX(0.99f);
                        Gaidel.setScaleY(0.99f);
                        break;
                    }
                }
                return false;
            }
        });
        Factory.setOnClickListener(clickOnFactory);
        Clicker.setOnClickListener(clickOnClicker);
        Farm.setOnClickListener(clickOnFarm);

    }

    class DelterThread extends Thread {


        public DelterThread() {
            super();
            start();
        }

        public void run() {
            while (true) {
                try {
                    for (int i = 0; i < 10; i++) {
                        count += delta * 0.1;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                countOfClick.setText(Integer.toString((int) count));
                            }
                        });
                        Thread.sleep(100);
                    }

                } catch (InterruptedException e) {
                    System.out.println("Второй поток прерван");
                }
            }

        }

    }
}
