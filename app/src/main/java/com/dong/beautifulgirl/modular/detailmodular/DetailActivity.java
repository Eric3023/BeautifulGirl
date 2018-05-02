package com.dong.beautifulgirl.modular.detailmodular;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.test.TestBean;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class DetailActivity extends AppCompatActivity implements DetailContract.View{

    private DetailContract.Presenter presenter;
    private VerticalViewPager verticalViewPager;
    private List<TestBean.DataBean> dataBeans;
    private DetailAdapter detailAdapter;
    private String tag;
    private int position;
    private int rn;

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

    private void initIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tag = bundle.getString("TAG");
        position = bundle.getInt("POSITION");
        rn = bundle.getInt("RN");
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
        presenter = new DetailPresent(this);
        presenter.loadDetailData(this,tag, rn);
    }

    private void initData() {
        dataBeans = new ArrayList<TestBean.DataBean>();

        detailAdapter = new DetailAdapter(this, dataBeans);

        verticalViewPager.setAdapter(detailAdapter);

    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void detailDataChanged(List<TestBean.DataBean> dataBeans) {

        if(dataBeans!=null){
            this.dataBeans.addAll(dataBeans);
            detailAdapter.notifyDataSetChanged();
            verticalViewPager.setCurrentItem(position, false);
        }

    }
}
