package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;

public class LogicActivity extends BaseActivity implements ValueAnimator.AnimatorUpdateListener {

    private ImageView logicBackgroud;
    private ValueAnimator shrinkAnimator;
    private ValueAnimator expandAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);

            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);

            getWindow().setExitTransition(slide);
            getWindow().setReenterTransition(slide);
        }

        initView();

        initAnimator();
    }

    private void initAnimator() {
        shrinkAnimator = ValueAnimator.ofFloat(100, 20);
        shrinkAnimator.setDuration(500);
        shrinkAnimator.setInterpolator(new LinearInterpolator());
        shrinkAnimator.addUpdateListener(this);

        expandAnimator = ValueAnimator.ofFloat(20, 100);
        expandAnimator.setDuration(500);
        expandAnimator.setInterpolator(new LinearInterpolator());
        expandAnimator.addUpdateListener(this);
    }

    private void initView() {
        logicBackgroud = findViewById(R.id.logic_backgroud);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Float value = (Float) animation.getAnimatedValue();

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) logicBackgroud.getLayoutParams();
        layoutParams.height = (int) ((float)getResources().getDimension(R.dimen.y120)*value/100);

        logicBackgroud.setLayoutParams(layoutParams);
    }
}
