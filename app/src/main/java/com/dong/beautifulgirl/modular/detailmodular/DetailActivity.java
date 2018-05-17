package com.dong.beautifulgirl.modular.detailmodular;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.test.TestBean;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class DetailActivity extends AppCompatActivity implements DetailContract.View, DetailAdapter.OnScrollToBottomListener {

    private DetailContract.Presenter presenter;
    private VerticalViewPager verticalViewPager;
    private List<TestBean.DataBean> dataBeans;
    private DetailAdapter detailAdapter;
    private String tag;
    private int position;
    private int rn;

    private int page;
    private boolean needLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initIntent();

        initTransition();

        initRecycleView();

        initPrensenter();

        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null)
            presenter.viewDestroyed();
    }

    private void initIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tag = bundle.getString("TAG");
        position = bundle.getInt("POSITION");
        rn = bundle.getInt("RN");

        page = 0 ;
        needLoad = true;
    }

    private void initTransition() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);

            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);

            getWindow().setExitTransition(slide);
            getWindow().setReenterTransition(slide);
        }

    }

    private void initRecycleView() {
        verticalViewPager = findViewById(R.id.detail_vertical_viewpager);
    }


    private void initPrensenter() {
        presenter = new DetailPresent();
        presenter.viewCreated(this);
        presenter.loadDetailData(this,tag, rn, page);
    }

    private void initData() {
        dataBeans = new ArrayList<TestBean.DataBean>();

        detailAdapter = new DetailAdapter(this, dataBeans);

        verticalViewPager.setAdapter(detailAdapter);

        detailAdapter.setOnScrollToBottomListener(this);

    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void detailDataChanged(List<TestBean.DataBean> dataBeans) {

        if(dataBeans!=null&&dataBeans.size()!=0)
            needLoad =true;
        else
            needLoad =false;

        if(dataBeans!=null){
            this.dataBeans.addAll(dataBeans);
            detailAdapter.notifyDataSetChanged();
            if(page == 0)
                verticalViewPager.setCurrentItem(position, false);
        }

    }

    @Override
    public void onScrollToBottom() {
        if(needLoad){
            page+=30;
            presenter.loadDetailData(this, tag, rn, page);
        }
    }
}
