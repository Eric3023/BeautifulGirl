package com.dong.beautifulgirl.modular.searchdetailmodular;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.detailimgmodular.DetailImgActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchDetailActivity extends BaseActivity implements SearchDetailContract.View, SearchDetailAdapter.OnScrollToBottomListener {

    private String tag;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager layoutManager;
    private final int CLUM_NUM = 2;
    private ArrayList<SearchDetailBean.DataBean> resultsBeans;
    private SearchDetailAdapter searchDetailAdapter;

    private SearchDetailContract.Presenter presenter;

    private int page;
    private boolean needLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        initIntent();
//        initTransition();
        initRecycleView();
        initPresenter();
    }

    private void initIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tag = bundle.getString("TAG");
        page = 0;
        needLoad = true;
    }

    private void initTransition() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition fade= TransitionInflater.from(this).inflateTransition(R.transition.fade);

            getWindow().setEnterTransition(fade);
            getWindow().setReturnTransition(fade);

            getWindow().setExitTransition(fade);
            getWindow().setReenterTransition(fade);
        }

    }

    private void initRecycleView() {

        recyclerView = findViewById(R.id.search_detail_recyclerview);
        layoutManager = new StaggeredGridLayoutManager(CLUM_NUM, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);

        resultsBeans = new ArrayList<SearchDetailBean.DataBean>();
        searchDetailAdapter = new SearchDetailAdapter(this, resultsBeans);
        searchDetailAdapter.setOnClickListener(new SearchDetailAdapter.OnClickListener() {
            @Override
            public void onClick(List<SearchDetailBean.DataBean> resultsBeans,ImageView shareImg, int position) {
                if (resultsBeans != null && resultsBeans.get(position) != null){
                    Intent intent = new Intent(mContext, DetailImgActivity.class);
                    intent.putExtra("URL", resultsBeans.get(position).getThumbURL());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Pair<ImageView, String> imgPair = Pair.create(shareImg, "share detail img");
                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SearchDetailActivity.this, new Pair[]{imgPair});
                        startActivity(intent, activityOptions.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            }
        });
        searchDetailAdapter.setOnScrollToBottomListener(this);
        recyclerView.setAdapter(searchDetailAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
    }

    private void initPresenter() {
        presenter= new SearchDetailPresent(this);
        presenter.loadSearchDetail(this, tag, page);
    }

    @Override
    public void setPresenter(SearchDetailContract.Presenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void searchDetailDataChanged(List<SearchDetailBean.DataBean> dataBeans) {
        if(dataBeans!=null&&dataBeans.size()!=0)
            needLoad = true;
        else
            needLoad = false;

        int oldSize = resultsBeans.size();
        if(dataBeans!=null){
            resultsBeans.addAll(dataBeans);
            searchDetailAdapter.notifyItemRangeInserted(oldSize , this.resultsBeans.size());
        }
    }

    @Override
    public void onScrollToBottom() {
        if(needLoad){
            page+=30;
            presenter.loadSearchDetail(this, tag, page);
        }
    }
}
