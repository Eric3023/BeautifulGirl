package com.dong.beautifulgirl.modular.searchmodular;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.searchdetailmodular.SearchDetailActivity;
import com.dong.beautifulgirl.util.ToastUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener, SearchContract.View {

    private TagFlowLayout historyFlowLayout;

    private TagFlowLayout hotFlowLayout;

    private SearchContract.Presenter presenter;
    private TagAdapter<SearchBean> hotTagAdapter;
    private List<SearchBean> hotSearchTags;
    private List<SearchBean> historySearchTags;
    private TagAdapter<SearchBean> historyTagAdapter;
    private LinearLayout historyTitle;
    private EditText searchEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initTransition();

        initTitleView();

        initView();

        initPresenter();

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

    private void initTitleView() {
        searchEditView = findViewById(R.id.search_edit);
        TextView cancleTextView = findViewById(R.id.search_cancle);
        ImageView deletleHistory = findViewById(R.id.search_history_delete);

        cancleTextView.setOnClickListener(this);
        deletleHistory.setOnClickListener(this);
        searchEditView.setOnEditorActionListener(this);
    }

    private void initView() {

        initHistoryView();

        initHotView();
    }

    private void initHotView() {

        hotFlowLayout = findViewById(R.id.search_hot_flowlayout);
        hotSearchTags = new ArrayList<SearchBean>();
        hotTagAdapter = new TagAdapter<SearchBean>(hotSearchTags) {
            @Override
            public View getView(FlowLayout parent, int position, SearchBean searchBean) {
                if (searchBean != null && !TextUtils.isEmpty(searchBean.getTag())) {
                    TextView tv = (TextView) View.inflate(SearchActivity.this, R.layout.item_search_tag, null);
                    tv.setText(searchBean.getTag());
                    return tv;
                } else {
                    return null;
                }
            }
        };
        hotFlowLayout.setAdapter(hotTagAdapter);
        hotFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                presenter.addHistory(hotSearchTags.get(position));
                Intent intent = new Intent(mContext, SearchDetailActivity.class);
                intent.putExtra("TAG", hotSearchTags.get(position).getTag());
                startActivity(intent);
                return false;
            }
        });
    }

    private void initHistoryView() {

        historyTitle = findViewById(R.id.search_histoty_title);

        historyFlowLayout = findViewById(R.id.search_history_flowlayout);
        historySearchTags = new ArrayList<SearchBean>();
        historyTagAdapter = new TagAdapter<SearchBean>(historySearchTags) {
            @Override
            public View getView(FlowLayout parent, int position, SearchBean searchBean) {
                if (searchBean != null && !TextUtils.isEmpty(searchBean.getTag())) {
                    TextView tv = (TextView) View.inflate(SearchActivity.this, R.layout.item_search_tag, null);
                    tv.setText(searchBean.getTag());
                    return tv;
                } else {
                    return null;
                }
            }
        };
        historyFlowLayout.setAdapter(historyTagAdapter);
        historyFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                presenter.addHistory(historySearchTags.get(position));
                Intent intent = new Intent(mContext, SearchDetailActivity.class);
                intent.putExtra("TAG", historySearchTags.get(position).getTag());
                startActivity(intent);
                return false;
            }
        });

    }

    private void initPresenter() {
        presenter = new SearchPresent(this);
        presenter.start(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.search_cancle:
                finish();
                break;
            case R.id.search_history_delete:
                presenter.clearHistory();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
                String str = searchEditView.getText().toString();
                presenter.addHistory(new SearchBean(str));
                searchEditView.setText("");

                Intent intent = new Intent(mContext, SearchDetailActivity.class);
                intent.putExtra("TAG", str);
                startActivity(intent);
                return true;
        }
        return false;
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void historyDataChanged(List<SearchBean> searchBeans) {
        historySearchTags.clear();
        if (searchBeans != null) {
            if(searchBeans.size() == 0)
                historyTitle.setVisibility(View.GONE);
            else
                historyTitle.setVisibility(View.VISIBLE);
            historySearchTags.addAll(searchBeans);
            historyTagAdapter.notifyDataChanged();
        }else{
            historyTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void hotDataChanged(List<SearchBean> searchBeans) {
        hotSearchTags.clear();
        if (searchBeans != null) {
            hotSearchTags.addAll(searchBeans);
            hotTagAdapter.notifyDataChanged();
        }
    }
}
