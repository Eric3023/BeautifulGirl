package com.dong.beautifulgirl.modular.minemodular;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.util.ToastUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment implements MineContract.View, MineAdapter.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MineContract.Presenter presenter;
    private RecyclerView recyclerView;

    private final int CLUM_NUM = 2;
    private MineAdapter recommendAdapter;

    public MineFragment() {
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
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
            presenter.start();

        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recommend_recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(CLUM_NUM, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void RecommendDataChanged(List<MineBean> recommendBeans) {
        recommendAdapter = new MineAdapter(recommendBeans);
        recommendAdapter.setOnClickListener(this);
        recyclerView.setAdapter(recommendAdapter);
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(List<MineBean> recommendBeans, int position) {
        ToastUtil.toastLong(getContext(), "数据内容为：" + recommendBeans.get(position).getContent());
    }
}