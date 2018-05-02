package com.dong.beautifulgirl.modular.mainmodular.minemodular;


import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.modular.likemodular.LikeActivity;
import com.dong.beautifulgirl.modular.logicandsignmodular.LogicAndSignActivity;
import com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular.LogicActivity;
import com.dong.beautifulgirl.modular.mainmodular.mainmodular.MainActivity;
import com.dong.beautifulgirl.modular.passwordmodular.PasswordActivity;
import com.dong.beautifulgirl.test.TestBean;
import com.dong.beautifulgirl.util.ToastUtil;
import com.dong.circleimageview.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment implements MineContract.View, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MineContract.Presenter presenter;
    private CircleImageView circleImageView;
    private TextView nameTextView;
    private TextView uidTextView;
    private RecyclerView likeRecycleView;
    private LinearLayoutManager likeLayoutManager;
    private ArrayList<TestBean.DataBean> dataBeans;
    private MineLikeAdapter likeAdapter;
    private LinearLayout likeLayout;
    private LinearLayout changePassWordLayout;
    private LinearLayout userReturnLayout;
    private LinearLayout checkVersionLayout;
    private Button changeAccountLayout;
    private Button no_login_ben;

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

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        initView(view);

        if (presenter != null)
            presenter.start(getContext());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null)
            presenter.loadMineData(getContext());
    }

    private void initView(View view) {

        initMesseageView(view);
        initTabView(view);
        initRecycleView(view);
    }

    /**
     * 初始化个人信息View（头像/昵称）
     *
     * @param view
     */
    private void initMesseageView(View view) {
        circleImageView = view.findViewById(R.id.mine_photo_circleImageView);
        nameTextView = view.findViewById(R.id.mine_name_tv);
        uidTextView = view.findViewById(R.id.mine_uid_tv);
        no_login_ben = view.findViewById(R.id.mine_no_login_btn);

        no_login_ben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineFragment.this.getContext(), LogicAndSignActivity.class);
                FragmentActivity activity = MineFragment.this.getActivity();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, new Pair[]{});
                    activity.startActivity(intent, activityOptions.toBundle());
                } else {
                    activity.startActivity(intent);
                }
            }
        });

    }

    /**
     * 初始化各个书签
     *
     * @param view
     */
    private void initTabView(View view) {
        likeLayout = view.findViewById(R.id.mine_like);
        changePassWordLayout = view.findViewById(R.id.mine_change_paassword);
        userReturnLayout = view.findViewById(R.id.mine_user_return);
        checkVersionLayout = view.findViewById(R.id.mine_check_version);
        changeAccountLayout = view.findViewById(R.id.mine_change_accout);

        likeLayout.setOnClickListener(this);
        changePassWordLayout.setOnClickListener(this);
        userReturnLayout.setOnClickListener(this);
        checkVersionLayout.setOnClickListener(this);
        changeAccountLayout.setOnClickListener(this);
    }

    /**
     * 初始化收藏RecycleView
     *
     * @param view
     */
    private void initRecycleView(View view) {
        likeRecycleView = view.findViewById(R.id.mine_like_recyclerview);
        likeLayoutManager = new LinearLayoutManager(getActivity());
        likeLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        likeRecycleView.setLayoutManager(likeLayoutManager);

        dataBeans = new ArrayList<TestBean.DataBean>();
        likeAdapter = new MineLikeAdapter(getContext(), dataBeans);
        likeAdapter.setOnClickListener(new MineLikeAdapter.OnClickListener() {
            @Override
            public void onClick(List<TestBean.DataBean> dataBeans, int position) {
                if (position != likeAdapter.getItemCount() - 1) {
                    MainActivity activity = (MainActivity) getActivity();
                    Intent intent = new Intent(activity, LikeActivity.class);
                    intent.putExtra("POSITION", position);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity);
                        activity.startActivity(intent, activityOptions.toBundle());
                    }else{
                        activity.startActivity(intent);
                    }
                } else {
                    MainActivity activity = (MainActivity) getActivity();
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity);
                        activity.startComponent(LikeActivity.class, activityOptions.toBundle());
                    }else{
                        activity.startComponent(LikeActivity.class);
                    }
                }
            }
        });
        likeRecycleView.setAdapter(likeAdapter);
    }

    @Override
    public void mineDataChanged(MineBean mineBean) {
        String name = mineBean.getName();
        String password = mineBean.getUid();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
            Log.i("Dong", "URL:" + mineBean.getHeadImgUrl());
            no_login_ben.setVisibility(View.GONE);
            circleImageView.setVisibility(View.VISIBLE);
            circleImageView
                    .setResourceID(R.drawable.home_pager_default)
                    .setPath(mineBean.getHeadImgUrl())//圆形图片源为网络图片
                    .setEdge(true)//设置是否显示边缘圆环
                    .setEdgeColor(Color.WHITE)//设置边缘颜色
                    .setEdgeWidth((int) getResources().getDimension(R.dimen.x3));//设置边缘宽度
            nameTextView.setVisibility(View.VISIBLE);
            nameTextView.setText(mineBean.getName());
            uidTextView.setVisibility(View.VISIBLE);
            uidTextView.setText("UID:" + mineBean.getUid());
        }else{
            no_login_ben.setVisibility(View.VISIBLE);
            circleImageView.setVisibility(View.GONE);
            nameTextView.setVisibility(View.GONE);
            uidTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void mineLikeDataChanged(List<TestBean.DataBean> dataBeans) {
        if (dataBeans != null) {
            int size = this.dataBeans.size();
            this.dataBeans.clear();
            likeAdapter.notifyItemRangeRemoved(0, size);

            int num = dataBeans.size() > 5 ? 5 : dataBeans.size();
            for (int i = 0; i < num; i++) {
                TestBean.DataBean dataBean = dataBeans.get(i);
                this.dataBeans.add(dataBean);
            }
            TestBean.DataBean dataBean = new TestBean.DataBean();
            this.dataBeans.add(dataBean);

//            this.dataBeans.addAll(dataBeans);
            likeAdapter.notifyItemRangeInserted(0, this.dataBeans.size());
        }
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onClick(View v) {
        MainActivity activity = (MainActivity) getActivity();
        int id = v.getId();
        switch (id) {
            case R.id.mine_like:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity);
                    activity.startComponent(LikeActivity.class, activityOptions.toBundle());
                }else{
                    activity.startComponent(LikeActivity.class);
                }
                break;
            case R.id.mine_change_paassword:
                activity.startComponent(PasswordActivity.class);
                break;
            case R.id.mine_user_return:
                ToastUtil.toastLong(getContext(), "用户反馈");
                break;
            case R.id.mine_check_version:
                ToastUtil.toastLong(getContext(), "检查更新");
                break;
            case R.id.mine_change_accout:
                activity.startComponent(LogicActivity.class);
                break;
        }
    }
}
