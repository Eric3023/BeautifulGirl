package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.modular.searchmodular.SearchActivity;
import com.dong.beautifulgirl.modular.detailmodular.DetailActivity;
import com.dong.beautifulgirl.modular.mainmodular.mainmodular.MainActivity;
import com.dong.beautifulgirl.test.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment implements RecommendContract.View, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecommendContract.Presenter presenter;
    private RecyclerView recyclerView;

    private final int CLUM_NUM = 2;
    private RecommendAdapter recommendAdapter;
    private StaggeredGridLayoutManager layoutManager;
    private RecyclerView tabRecyclerView;
    private LinearLayoutManager tabLayoutManager;
    private RecommendTabAdapter recommendTabAdapter;
    private List<TestBean.DataBean> resultsBeans;
    private List<RecommendTabBean> tabBeans;
    private String tag = UrlConfig.TAG_FIRST;

    public RecommendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        initView(view);

        if (presenter != null)
            presenter.start(getContext());

        return view;
    }

    private void initView(View view) {
        initMenu(view);
        initTabRecycleView(view);
        initRecycleView(view);
    }

    private void initMenu(View view) {
        Button menu = view.findViewById(R.id.recommend_menu);
        Button qCode = view.findViewById(R.id.recommend_search);

        menu.setOnClickListener(this);
        qCode.setOnClickListener(this);
    }

    private void initTabRecycleView(View view) {
        tabRecyclerView = view.findViewById(R.id.recommend_tab_recycleview);
        tabLayoutManager = new LinearLayoutManager(getActivity());
        tabLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tabRecyclerView.setLayoutManager(tabLayoutManager);

        tabBeans = new ArrayList<RecommendTabBean>();
        recommendTabAdapter = new RecommendTabAdapter(getContext(), tabBeans);
        recommendTabAdapter.setOnClickListener(new RecommendTabAdapter.OnClickListener() {
            @Override
            public void onClick(List<RecommendTabBean> tabBeans, int position) {
                if (tabBeans != null && tabBeans.get(position) != null) {
                    int size = resultsBeans.size();
                    resultsBeans.clear();
                    recommendAdapter.notifyItemRangeRemoved(0, size);
                    tag = tabBeans.get(position).getTab();
                    presenter.loadRecommend(RecommendFragment.this.getActivity(), tabBeans.get(position).getTab());
                }
            }
        });
        tabRecyclerView.setAdapter(recommendTabAdapter);
    }

    private void initRecycleView(View view) {

        recyclerView = view.findViewById(R.id.recommend_recyclerview);
        layoutManager = new StaggeredGridLayoutManager(CLUM_NUM, StaggeredGridLayoutManager.VERTICAL) {
        };
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);

        resultsBeans = new ArrayList<TestBean.DataBean>();
        recommendAdapter = new RecommendAdapter(getContext(), resultsBeans);
        recommendAdapter.setOnClickListener(new RecommendAdapter.OnClickListener() {
            @Override
            public void onClick(List<TestBean.DataBean> resultsBeans, int position) {
                if (resultsBeans != null && resultsBeans.get(position) != null){
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("POSITION", position);
                    intent.putExtra("TAG", tag);
                    intent.putExtra("RN", 30);
                    getActivity().startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(recommendAdapter);

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

    @Override
    public void RecommendTabChanged(List<RecommendTabBean> tabBeans) {
        if (this.tabBeans != null && tabBeans != null) {
            this.tabBeans.clear();
            this.tabBeans.addAll(tabBeans);
            recommendTabAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void RecommendDataChanged(List<TestBean.DataBean> resultsBeans) {
        if (resultsBeans != null) {
            int size = this.resultsBeans.size();
            this.resultsBeans.addAll(resultsBeans);
            recommendAdapter.notifyItemRangeInserted(size, this.resultsBeans.size());
        }
    }

    @Override
    public void setPresenter(RecommendContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        MainActivity mainActivity = (MainActivity) getActivity();
        int id = v.getId();
        switch (id) {
            case R.id.recommend_menu:
                if(mainActivity.isOpen())
                    mainActivity.closeSlide();
                else
                    mainActivity.openSlide();
                break;
            case R.id.recommend_search:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(mainActivity);
                    mainActivity.startComponent(SearchActivity.class, activityOptions.toBundle());
                }else{
                    mainActivity.startComponent(SearchActivity.class);
                }
                break;
        }
    }
}
