package com.example.gfc.gaidelclicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gfc.gaidelclicker.bonus.Bonus;
import com.example.gfc.gaidelclicker.bonus.BonusRepository;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.event.AchievementUnlockedEvent;
import com.example.gfc.gaidelclicker.utils.FormatUtils;
import com.example.gfc.gaidelclicker.utils.RandomUtils;
import com.example.gfc.gaidelclicker.utils.UIUtils;
import com.pierfrancescosoffritti.slidingdrawer.SlidingDrawer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private static final int MIN_GOLD_COOKIE_SPAWN_PERIOD = 1 * 60 * 1000;
    private static final int MAX_GOLD_COOKIE_SPAWN_PERIOD = 3 * 60 * 1000;
    private static final int DATA_SYNC_PERIOD = 15 * 1000; // 15 sec

    private static final float GAIDEL_ANIMATION_SCALE = 1.075f;
    private final OvershootInterpolator overshootInterpolator = new OvershootInterpolator();

    private RelativeLayout relativeLayout;

    private ImageButton gaidel;
    private ImageView svaston;

    private TabLayout tabs;
    @ColorInt
    private int colorPrimary;
    @ColorInt
    private int colorAccent;

    private TextView countOfClicksLabel;
    private TextView speedLabel;

    private SlidingDrawer slidingDrawer;

    private ImageView goldCookie;
    private ObjectAnimator goldCookieAlphaAnimator;

    private Handler handler;
    private Bonus currentDisplayedBonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new UpdateHandler(this);

        initViews();
        setupViewPager(
                tabs,
                new Pair<>((Fragment) new BuildingsFragment(), "Действия"),
                new Pair<>((Fragment) new UpgradesFragment(), "Апгрейды"),
                new Pair<>((Fragment) new AchievementsFragment(), "Ачивки"),
                new Pair<>((Fragment) new StatisticFragment(), "Статистика")
        );
    }

    private void initViews() {
        gaidel = (ImageButton) findViewById(R.id.buttonGaidel);
        relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);

        svaston = (ImageView) findViewById(R.id.svaston);

        tabs = (TabLayout) findViewById(R.id.tab_layout);

        countOfClicksLabel = (TextView) findViewById(R.id.clicks);
        speedLabel = (TextView) findViewById(R.id.speed);

        slidingDrawer = (SlidingDrawer) findViewById(R.id.sliding_drawer);
        slidingDrawer.setDragView(findViewById(R.id.tab_layout));
        colorAccent = ContextCompat.getColor(MainActivity.this, R.color.colorAccent);
        colorPrimary = ContextCompat.getColor(MainActivity.this, R.color.colorPrimary);

        GregorianCalendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if ((day >= 15 && month == 11) || (day <= 5 && month == 0)) {
            gaidel.setBackground(getResources().getDrawable(R.drawable.gaidel_face_gold_ny));
            svaston.setBackground(getResources().getDrawable(R.drawable.svas_ny));
            relativeLayout.setBackground(getResources().getDrawable(R.drawable.background_ny));
        }

        slidingDrawer.addSlideListener(new SlidingDrawer.OnSlideListener() {
            @Override
            public void onSlide(SlidingDrawer slidingDrawer, float v) {
                if (v == 0) {
                    tabs.setSelectedTabIndicatorColor(colorPrimary);
                } else {
                    tabs.setSelectedTabIndicatorColor(colorAccent);
                }
            }
        });

        goldCookie = (ImageView) findViewById(R.id.gold_cookie);

        ObjectAnimator anim = ObjectAnimator.ofFloat(svaston, View.ROTATION, 0f, 360f);
        anim.setRepeatCount(-1);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(2000);
        anim.start();

        gaidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigDecimal clickProfit = BuildingsRepository.getInstance().getClickProfit();
                Toast.makeText(MainActivity.this, "click +" + clickProfit.toBigInteger(), Toast.LENGTH_SHORT).show();
                GlobalPrefs.getInstance().changeBalance(clickProfit);
                countOfClicksLabel.setText(FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getBalance()));
                GlobalPrefs.getInstance().increaseClickCount(1);
                Analytics.getInstance().sendEvent("Click Gaidel", "Clicks Count", FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getBalance()).toString());
            }
        });
        gaidel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        gaidel.animate().setInterpolator(overshootInterpolator).scaleX(GAIDEL_ANIMATION_SCALE).scaleY(GAIDEL_ANIMATION_SCALE).start();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        gaidel.animate().setInterpolator(overshootInterpolator).scaleX(1).scaleY(1).start();
                        break;
                }
                return false;
            }
        });
        goldCookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideHoldCookie();
                if (currentDisplayedBonus.isImmediate()) {
                    currentDisplayedBonus.performImmediateAction(GlobalPrefs.getInstance().getBalance(), GlobalPrefs.getInstance().getWholeProfit());
                    requestGoldCookieSpawn();
                } else {
                    BuildingsRepository.getInstance().setActiveBonus(currentDisplayedBonus);
                    handler.sendEmptyMessageDelayed(UpdateHandler.EXPIRED_GOLD_COOKIE, currentDisplayedBonus.getDurationMillis());
                }
                GlobalPrefs.getInstance().addGoldenCookie();
                Toast.makeText(MainActivity.this, currentDisplayedBonus.getMessage(), Toast.LENGTH_SHORT).show();//TODO string resources
                Analytics.getInstance().sendEvent("Golden Cookie Clicked");
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingDrawer.getState() != SlidingDrawer.COLLAPSED) {
                    slidingDrawer.slideTo(0);
                }
            }
        });
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
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Analytics.getInstance().sendEvent("On Pause");
        handler.removeMessages(UpdateHandler.UPDATE_MESSAGE);
        handler.removeMessages(UpdateHandler.SPAWN_GOLD_COOKIE);
        handler.removeMessages(UpdateHandler.EXPIRED_GOLD_COOKIE);
        EventBus.getDefault().unregister(this);
        Prefs.syncAll();
    }

    private void hideHoldCookie() {
        goldCookie.setVisibility(View.GONE);
        if (goldCookieAlphaAnimator != null) {
            goldCookieAlphaAnimator.cancel();
            goldCookieAlphaAnimator = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAchievementUnlocked(AchievementUnlockedEvent event) {
        //todo string resources
        Toast.makeText(this, "Новое достижение: " + event.getAchievement().getMessage(), Toast.LENGTH_SHORT).show();
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
        currentDisplayedBonus = BonusRepository.getInstance().getRandomBonus();
        goldCookie.setImageResource(currentDisplayedBonus.getImageResourceId());
        goldCookieAlphaAnimator.start();
        int xRange = Math.round(UIUtils.getWidth(this) - goldCookie.getWidth());
        int yRange = Math.round(UIUtils.getHeight(this) - goldCookie.getHeight());
        if (goldCookie.getWidth() == 0) {
            xRange /= 2;
            yRange /= 2;
            //TODO need fix it correct (now first time w,h equals to zero because visibility is gone)
        }
        int xCord = RandomUtils.nextInt(xRange);
        int yCord = RandomUtils.nextInt(yRange);
        goldCookie.setTranslationX(xCord);
        goldCookie.setTranslationY(yCord);
    }

    private void goldCookieExpired() {
        BuildingsRepository.getInstance().setActiveBonus(null);
        hideHoldCookie();
    }

    private void requestGoldCookieSpawn() {
        handler.removeMessages(UpdateHandler.SPAWN_GOLD_COOKIE);
        handler.removeMessages(UpdateHandler.EXPIRED_GOLD_COOKIE);
        goldCookieExpired();
        int spawnDelay = RandomUtils.nextInt(MIN_GOLD_COOKIE_SPAWN_PERIOD, MAX_GOLD_COOKIE_SPAWN_PERIOD);
        handler.sendEmptyMessageDelayed(UpdateHandler.SPAWN_GOLD_COOKIE, spawnDelay);
    }

    private long lastSyncTime = -1;

    private void syncPreferencesIfNeed() {
        long currentTime = System.currentTimeMillis();
        if (lastSyncTime == -1) {
            lastSyncTime = currentTime;
        } else {
            long difference = currentTime - lastSyncTime;
            if (difference > DATA_SYNC_PERIOD) {
                Prefs.syncAll();
                lastSyncTime = currentTime;
            }
        }
    }

    private void update() {
        long previousTs = GlobalPrefs.getInstance().getLastUpdateTs();
        long currentTs = System.currentTimeMillis();
        //TODO need prevent date manipulate

        long timeDifferenceInMs = currentTs - previousTs;
        BigDecimal moneyDifference = BuildingsRepository.getInstance().getDeltaPerSecond().multiply(BigDecimal.valueOf(timeDifferenceInMs / 1000d));
        GlobalPrefs.getInstance().changeBalance(moneyDifference);
        GlobalPrefs.getInstance().putLastUpdateTs(currentTs);

        countOfClicksLabel.setText(FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getBalance()));
        speedLabel.setText(String.format(getText(R.string.per_second_format).toString(), FormatUtils.formatDecimal(BuildingsRepository.getInstance().getDeltaPerSecond())));

        handler.sendEmptyMessageDelayed(UpdateHandler.UPDATE_MESSAGE, UpdateHandler.UPDATE_MESSAGE_DELAY);
        syncPreferencesIfNeed();
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
                    mainActivity.update();
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
    }
}
