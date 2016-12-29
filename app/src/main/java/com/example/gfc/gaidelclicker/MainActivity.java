package com.example.gfc.gaidelclicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gfc.gaidelclicker.bonus.BuildingsAdapter;
import com.example.gfc.gaidelclicker.bonus.BuildingsRepository;
import com.example.gfc.gaidelclicker.bonus.OnBuildingClickListener;
import com.example.gfc.gaidelclicker.utils.FormatUtils;
import com.example.gfc.gaidelclicker.utils.UIUtils;
import com.pierfrancescosoffritti.slidingdrawer.SlidingDrawer;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int MIN_GOLD_COOKIE_SPAWN_PERIOD = 10 * 1000;
    private static final int MAX_GOLD_COOKIE_SPAWN_PERIOD = 30 * 1000;

    private static final float GAIDEL_ANIMATION_SCALE = 1.075f;

    private final Random random = new Random();

    private ImageButton gaidel;
    private ImageView svaston;

    private TextView countOfClicksLabel;
    private TextView speedLabel;

    private SlidingDrawer slidingDrawer;

    private ImageView goldCookie;
    private ObjectAnimator goldCookieAlphaAnimator;

    private RecyclerView recyclerView;
    private BuildingsAdapter adapter;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new UpdateHandler(this);

        initViews();
        setupViewPager(
                (TabLayout) findViewById(R.id.tab_layout),
                new Pair<>((Fragment) new BuildingsFragment(), "Действия"),
                new Pair<>((Fragment) new AchievementsFragment(), "Ачивки"),
                new Pair<>((Fragment) new StatisticFragment(), "Статистика")
        );
        initRecycler();
    }

    private void initViews() {
        gaidel = (ImageButton) findViewById(R.id.buttonGaidel);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        svaston = (ImageView) findViewById(R.id.svaston);

        countOfClicksLabel = (TextView) findViewById(R.id.clicks);
        speedLabel = (TextView) findViewById(R.id.speed);

        slidingDrawer = (SlidingDrawer) findViewById(R.id.sliding_drawer);
        slidingDrawer.setDragView(findViewById(R.id.tab_layout));

        goldCookie = (ImageView) findViewById(R.id.gold_cookie);

        ObjectAnimator anim = ObjectAnimator.ofFloat(svaston, View.ROTATION, 0f, 360f);
        anim.setRepeatCount(-1);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(2000);
        anim.start();

        gaidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalPrefs.getInstance().changeBalance(BuildingsRepository.getInstance().getClickProfit());
                countOfClicksLabel.setText(FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getBalance()));
                Analytics.getInstance().sendEvent("Click Gaidel", "Clicks Count", FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getBalance()).toString());
            }
        });
        gaidel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        gaidel.animate().setInterpolator(new OvershootInterpolator()).scaleX(GAIDEL_ANIMATION_SCALE).scaleY(GAIDEL_ANIMATION_SCALE).start();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        gaidel.animate().setInterpolator(new OvershootInterpolator()).scaleX(1).scaleY(1).start();
                        break;
                    }
                }
                return false;
            }
        });
        goldCookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuildingsRepository.getInstance().setGoldMode(true);
                hideHoldCookie();
                handler.sendEmptyMessageDelayed(UpdateHandler.EXPIRED_GOLD_COOKIE, 77 * 1000);
                Toast.makeText(MainActivity.this, "Прибыль увеличена в 77 раз на 77 секунд!", Toast.LENGTH_SHORT).show();//TODO string resources
                Analytics.getInstance().sendEvent("Golden Cookie Clicked");
            }
        });
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new BuildingsAdapter();
        adapter.setOnBuildingClickListener(new OnBuildingClickListener() {
            @Override
            public void onBonusClick(Building bonus) {
                int res = GlobalPrefs.getInstance().getBalance().compareTo(bonus.getPrice());
                if (res != -1) {
                    GlobalPrefs.getInstance().changeBalance(new BigDecimal("-" + bonus.getPrice()));
                    BuildingsRepository.getInstance().buy(bonus);
                    adapter.notifyDataSetChanged();
                    Analytics.getInstance().sendEvent("Buy Building", "type", bonus.getName(), bonus.getCount());
                } else {
                    Analytics.getInstance().sendEvent("Click On Unavailable Building", "type", bonus.getName());
                }
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setData(BuildingsRepository.getInstance().getBuildings());
    }

    private void setupViewPager(TabLayout tabs, Pair<Fragment, String>... fragments) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);

        tabs.setupWithViewPager(viewPager);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                slidingDrawer.setState(SlidingDrawer.EXPANDED);
                Analytics.getInstance().sendEvent(String.format("Open Tab %s", tab.getText()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                slidingDrawer.setState(SlidingDrawer.EXPANDED);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Analytics.getInstance().sendEvent("On Resume");
        requestGoldCookieSpawn();
        handler.sendEmptyMessage(UpdateHandler.UPDATE_MESSAGE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Analytics.getInstance().sendEvent("On Pause");
        handler.removeMessages(UpdateHandler.UPDATE_MESSAGE);
        handler.removeMessages(UpdateHandler.SPAWN_GOLD_COOKIE);
        handler.removeMessages(UpdateHandler.EXPIRED_GOLD_COOKIE);
    }

    private void hideHoldCookie() {
        goldCookie.setVisibility(View.GONE);
        if (goldCookieAlphaAnimator != null) {
            goldCookieAlphaAnimator.cancel();
            goldCookieAlphaAnimator = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (slidingDrawer.getState() != SlidingDrawer.COLLAPSED) {
            slidingDrawer.slideTo(0);
        } else {
            super.onBackPressed();
        }
    }

    private void spawnGoldCookie() {

        goldCookieAlphaAnimator = ObjectAnimator.ofFloat(goldCookie, View.ALPHA, 0f, 1f);
        goldCookieAlphaAnimator.setDuration(6 * 1000);
        goldCookieAlphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
        goldCookieAlphaAnimator.setRepeatCount(1);
        goldCookieAlphaAnimator.addListener(new AnimatorListenerAdapter() {

            private boolean isCanceled;

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                goldCookie.setVisibility(View.VISIBLE);
                Analytics.getInstance().sendEvent("Golden Cookie Shown");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                isCanceled = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isCanceled) {
                    requestGoldCookieSpawn();
                }
            }
        });
        goldCookieAlphaAnimator.start();
        int xRange = Math.round(UIUtils.getWidth(this) - goldCookie.getWidth());
        int yRange = Math.round(UIUtils.getHeight(this) - goldCookie.getHeight());
        if (goldCookie.getWidth() == 0) {
            xRange /= 2;
            yRange /= 2;
            //TODO need fix it correct (now first time w,h equals to zero because visibility is gone)
        }
        int xCord = random.nextInt(xRange);
        int yCord = random.nextInt(yRange);
        goldCookie.setTranslationX(xCord);
        goldCookie.setTranslationY(yCord);
    }

    private void goldCookieExpired() {
        BuildingsRepository.getInstance().setGoldMode(false);
        hideHoldCookie();
    }

    private void requestGoldCookieSpawn() {
        handler.removeMessages(UpdateHandler.SPAWN_GOLD_COOKIE);
        handler.removeMessages(UpdateHandler.EXPIRED_GOLD_COOKIE);
        goldCookieExpired();
        int spawnDelay = MIN_GOLD_COOKIE_SPAWN_PERIOD + random.nextInt(MAX_GOLD_COOKIE_SPAWN_PERIOD - MIN_GOLD_COOKIE_SPAWN_PERIOD);
        handler.sendEmptyMessageDelayed(UpdateHandler.SPAWN_GOLD_COOKIE, spawnDelay);
    }

    private static class UpdateHandler extends Handler {

        private static final int UPDATE_MESSAGE = 0;
        private static final int SPAWN_GOLD_COOKIE = 1;
        private static final int EXPIRED_GOLD_COOKIE = 2;
        private static final int UPDATE_MESSAGE_DELAY = 200;

        WeakReference<MainActivity> mainActivityWeakReference;

        UpdateHandler(MainActivity mainActivity) {
            mainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity = mainActivityWeakReference.get();
            if (mainActivity == null) {
                return;
            }
            switch (msg.what) {
                case UPDATE_MESSAGE:
                    performUpdate(mainActivity);
                    return;
                case SPAWN_GOLD_COOKIE:
                    mainActivity.spawnGoldCookie();
                    return;
                case EXPIRED_GOLD_COOKIE:
                    mainActivity.goldCookieExpired();
                    mainActivity.requestGoldCookieSpawn();
                    break;

            }
        }

        private void performUpdate(MainActivity mainActivity) {
            long previousTs = GlobalPrefs.getInstance().getLastUpdateTs();
            long currentTs = System.currentTimeMillis();
            //TODO need prevent date manipulate

            long timeDifferenceInMs = currentTs - previousTs;
            BigDecimal moneyDifference = BuildingsRepository.getInstance().getDeltaPerSecond().multiply(BigDecimal.valueOf(timeDifferenceInMs / 1000d));
            GlobalPrefs.getInstance().changeBalance(moneyDifference);
            GlobalPrefs.getInstance().putLastUpdateTs(currentTs);

            mainActivity.countOfClicksLabel.setText(FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getBalance()));
            mainActivity.speedLabel.setText(String.format(mainActivity.getText(R.string.per_second_format).toString(), FormatUtils.formatDecimal(BuildingsRepository.getInstance().getDeltaPerSecond())));

            sendEmptyMessageDelayed(UPDATE_MESSAGE, UPDATE_MESSAGE_DELAY);
        }
    }
}
