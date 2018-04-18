package com.dong.beautifulgirl.modular.mainmodular.minemodular;


import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.modular.logicmodular.LogicActivity;
import com.dong.circleimageview.widget.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment implements MineContract.View{
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

    private void initView(View view) {
        circleImageView = view.findViewById(R.id.mine_photo_circleImageView);
        nameTextView = view.findViewById(R.id.mine_name_tv);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineFragment.this.getContext(), LogicActivity.class);
                FragmentActivity activity = MineFragment.this.getActivity();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<CircleImageView, String> photoImageViewPair = Pair.create(circleImageView, "share photo img");
                    Pair<TextView, String> nameTextViewPair = Pair.create(nameTextView, "share photo tv");
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, new Pair[]{photoImageViewPair, nameTextViewPair});
                    activity.startActivity(intent, activityOptions.toBundle());
                }else{
                    activity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void mineDataChanged(MineBean mineBean) {
        Log.i("Dong", "URL:"+mineBean.getHeadImgUrl());
        circleImageView
                .setResourceID(R.drawable.home_pager_default)
                .setPath(mineBean.getHeadImgUrl())//圆形图片源为网络图片
                .setEdge(true)//设置是否显示边缘圆环
                .setEdgeColor(Color.WHITE)//设置边缘颜色
                .setEdgeWidth((int) getResources().getDimension(R.dimen.x3));//设置边缘宽度
        nameTextView.setText(mineBean.getName());
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
