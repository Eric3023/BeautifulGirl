package com.dong.beautifulgirl.modular.likemodular;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.ImageView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.test.TestBean;
import com.dong.beautifulgirl.util.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LikeActivity extends AppCompatActivity implements LikeContract.View, LikeAdapter.OnClickListener, CarouselLayoutManager.OnCenterItemSelectionListener, LikeAdapter.OnScrollToBottomListener {

    private RecyclerView recyclerView;
    private List<TestBean.DataBean> dataBeans;

    private LikeContract.Presenter presenter;
    private LikeAdapter likeAdapter;
    private CarouselLayoutManager layoutManager;
    private ImageView background;
    private int position;

    private int page;
    private boolean needLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        initIntent();

        initTransition();

        initRecycleView();

        initData();

        initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.viewDestroyed();
    }

    private void initIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
            position = bundle.getInt("POSITION", 0);

        page = 0;
        needLoad =true;
    }

    private void initTransition() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade);

            getWindow().setEnterTransition(fade);
            getWindow().setReturnTransition(fade);

            getWindow().setExitTransition(fade);
            getWindow().setReenterTransition(fade);
        }

    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.like_recyclerview);
        background = findViewById(R.id.like_backgroud);
    }

    private void initData() {
        dataBeans = new ArrayList<TestBean.DataBean>();
        layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL);
        layoutManager.setMaxVisibleItems(5);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        likeAdapter = new LikeAdapter(this, dataBeans);
        recyclerView.setAdapter(likeAdapter);
        recyclerView.addOnScrollListener(new CenterScrollListener());
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        likeAdapter.setOnClickListener(this);
        likeAdapter.setOnScrollToBottomListener(this);
        layoutManager.addOnItemSelectionListener(this);

        layoutManager.scrollToPosition(position);
    }

    private void initPresenter() {
        presenter = new LikePresent();
        presenter.viewCreated(this);
        presenter.start(this);
    }

    @Override
    public void setPresenter(LikeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void likeDataChanged(List<TestBean.DataBean> dataBeans) {

        if(dataBeans!=null && dataBeans.size()!=0)
            needLoad =true;
        else
            needLoad=false;

        int oldSize = this.dataBeans.size();
        this.dataBeans.addAll(dataBeans);
        int newSize = this.dataBeans.size();
        likeAdapter.notifyItemRangeInserted(oldSize, newSize);
    }

    @Override
    public void onClick(List<TestBean.DataBean> resultsBeans, int position) {
        TestBean.DataBean dataBean = resultsBeans.get(position);
        ToastUtil.toastLong(this, dataBean.getDesc());
    }

    @Override
    public void onCenterItemChanged(int adapterPosition) {
        if (adapterPosition >= 0) {
            TestBean.DataBean dataBean = dataBeans.get(adapterPosition);
            if(dataBean!=null&& !TextUtils.isEmpty(dataBean.getImage_url()))
                Picasso.get().load(dataBean.getImage_url()).into(background);
        }
    }

    @Override
    public void onScrollToBottom() {
        if(needLoad){
            page+=30;
            presenter.loadLikeData(this, page);
        }

    }
}
