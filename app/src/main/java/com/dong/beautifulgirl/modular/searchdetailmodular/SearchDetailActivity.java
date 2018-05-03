package com.dong.beautifulgirl.modular.searchdetailmodular;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.detailmodular.DetailActivity;
import com.dong.beautifulgirl.test.TestBean;

import java.util.ArrayList;
import java.util.List;

public class SearchDetailActivity extends BaseActivity implements SearchDetailContract.View {

    private String tag;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager layoutManager;
    private final int CLUM_NUM = 2;
    private ArrayList<SearchDetailBean.DataBean> resultsBeans;
    private SearchDetailAdapter searchDetailAdapter;

    private SearchDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        initIntent();
        initTransition();
        initRecycleView();
        initPresenter();
    }

    private void initIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tag = bundle.getString("TAG");
    }

    private void initTransition() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);

            getWindow().setEnterTransition(explode);
            getWindow().setReturnTransition(explode);

            getWindow().setExitTransition(explode);
            getWindow().setReenterTransition(explode);
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
            public void onClick(List<SearchDetailBean.DataBean> resultsBeans, int position) {
                if (resultsBeans != null && resultsBeans.get(position) != null){
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("POSITION", position);
                    intent.putExtra("TAG", tag);
                    intent.putExtra("RN", 30);
//                    mContext.startActivity(intent);
                }
            }
        });
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
        presenter.loadSearchDetail(this, tag);
    }

    @Override
    public void setPresenter(SearchDetailContract.Presenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void searchDetailDataChanged(List<SearchDetailBean.DataBean> dataBeans) {
        int oldSize = resultsBeans.size();
        resultsBeans.clear();
        searchDetailAdapter.notifyItemRangeRemoved(0, oldSize);
        if(dataBeans!=null){
            resultsBeans.addAll(dataBeans);
            searchDetailAdapter.notifyItemRangeInserted(0 , this.resultsBeans.size());
        }
    }
}
