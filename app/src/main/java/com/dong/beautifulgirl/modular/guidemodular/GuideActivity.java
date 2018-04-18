package com.dong.beautifulgirl.modular.guidemodular;

import android.app.ActivityOptions;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.modular.mainmodular.MainActivity;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.splashmodular.SplashActivity;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.PointView;
import com.dong.pointviewpager.widget.PointViewPager;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {

    private PointViewPager mPointViewPager;
    private LoopViewPager mLoopViewPager;
    private PointView mPointView;
    private OnLoopPageChangeListener onLoopPageChangeListener;
    private List<LoopViewPagerBean> beans;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade);
            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }


        initData();

        initView();
    }

    private void initData() {
        beans = new ArrayList<LoopViewPagerBean>();
        beans.add(new LoopViewPagerBean(R.drawable.guide_img1, null));
        beans.add(new LoopViewPagerBean(R.drawable.guide_img2, null));
        beans.add(new LoopViewPagerBean(R.drawable.guide_img3, null));
        beans.add(new LoopViewPagerBean(R.drawable.guide_img4, null));

        onLoopPageChangeListener = new OnLoopPageChangeListener() {
            @Override
            protected void onViewPageScrollStateChanged(int i) {

            }

            @Override
            protected void onViewPageSelected(int i) {
                if(mLoopViewPager!= null&&i == mLoopViewPager.getCount()-1)
                    mButton.setVisibility(View.VISIBLE);
                else
                    mButton.setVisibility(View.GONE);
            }

            @Override
            protected void onViewPageScrolled(int i, float v, int i1) {

            }
        };

    }

    private void initView() {
        mPointViewPager = findViewById(R.id.guide_bg_vp);
        mLoopViewPager = mPointViewPager.getLoopViewPager();
        mPointView = mPointViewPager.getPointView();

        mButton = findViewById(R.id.guide_comfire_btn);

        //设置ViewPager的参数
        if(mLoopViewPager!=null){
            mLoopViewPager
                    .setBeans(beans)
                    .setOnLoopPageChangeListener(onLoopPageChangeListener)
                    .setDefaultResouces(new int[]{R.drawable.guide_img1})
                    .initialise();
            mLoopViewPager.setOffscreenPageLimit(4);
        }

        //设置圆点的参数
        if(mPointView !=null){
            mPointView
                    .setRudis(getResources().getDimension(R.dimen.x3))
                    .setDistance(getResources().getDimension(R.dimen.x8))
                    .setDisbottom(getResources().getDimension(R.dimen.y2))
                    .initialise();
        }

        //点击进入主页面
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startComponent(MainActivity.class,
                            ActivityOptions.makeSceneTransitionAnimation(GuideActivity.this).toBundle());
                } else {
                    startComponent(MainActivity.class);
                }                finish();
            }
        });
    }
}
