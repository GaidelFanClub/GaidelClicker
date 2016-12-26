package com.example.gfc.gaidelclicker;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton Gaidel;
    private ImageButton Factory;
    private ImageButton Clicker;
    private ImageButton Farm;
    private Button Stats;
    static TextView countOfClick;
    private TextView countOfFarm;
    private TextView countOfFactory;
    private TextView countOfClicker;
    private TextView priceOfFarm;
    private TextView priceOfFactory;
    private TextView priceOfClicker;
    private ImageView svaston;

    private Spinner spinnerScheme;

    private Bonus clicker;
    private Bonus factory;
    private Bonus farm;


    private int countOfF = 0;
    private int countOfFa = 0;
    private int countOfC = 0;
    private int priceFarm = 560;
    private int priceFactory = 150;
    private int priceClicker = 20;
    private int maximum = 0;
    static int maximumAll = 0;
    static double count = 0;

    static double delta = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gaidel = (ImageButton) findViewById(R.id.buttonGaidel);
        Factory = (ImageButton) findViewById(R.id.buttonFactory);
        Clicker = (ImageButton) findViewById(R.id.buttonClicker);
        Farm = (ImageButton) findViewById(R.id.buttonFarm);
        Stats = (Button) findViewById(R.id.buttonStat);

        spinnerScheme = (Spinner) findViewById(R.id.spinner);


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


        priceOfFactory.setText("Цена: " + Integer.toString(priceFactory));
        priceOfClicker.setText("Цена: " + Integer.toString(priceClicker));
        priceOfFarm.setText("Цена: " + Integer.toString(priceFarm));
        AutoClickerThread autoClick = new AutoClickerThread();



        clicker = new Bonus(20, 0.1);
        factory = new Bonus(150, 1);
        farm = new Bonus(560, 8);


        ObjectAnimator anim = ObjectAnimator.ofFloat(svaston, View.ROTATION, 0f, 360f);
        anim.setRepeatCount(-1);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(2000);
        anim.start();


        View.OnClickListener clickOnGaidel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                maximum++;
                countOfClick.setText(Integer.toString((int) count));


            }
        };
        View.OnClickListener clickOnStat = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Кликов в секунду: " + Double.toString(delta) + "\nВсего накликано: " + maximum + "\nВсего собрано: " + (maximumAll + maximum), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 5);
                toast.show();
            }
        };
        View.OnClickListener clickOnFactory = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factory.UpdateItem();
                countOfClick.setText(Integer.toString((int)count));
                countOfFactory.setText(Integer.toString(factory.getCount()));
                priceOfFactory.setText("Цена: " + Integer.toString((int)factory.getPrice()));
            }
        };
        View.OnClickListener clickOnClicker = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicker.UpdateItem();
                countOfClick.setText(Integer.toString((int)count));
                countOfClicker.setText(Integer.toString(clicker.getCount()));
                priceOfClicker.setText("Цена: " + Integer.toString((int)clicker.getPrice()));


            }
        };
        View.OnClickListener clickOnFarm = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farm.UpdateItem();
                countOfClick.setText(Integer.toString((int)count));
                countOfFarm.setText(Integer.toString(farm.getCount()));
                priceOfFarm.setText("Цена: " + Integer.toString((int)farm.getPrice()));
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
        Stats.setOnClickListener(clickOnStat);



        spinnerScheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.gaidellist);
                switch (selectedItemPosition) {
                    case 1
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }




}
