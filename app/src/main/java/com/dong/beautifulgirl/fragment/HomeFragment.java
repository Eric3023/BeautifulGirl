package com.dong.beautifulgirl.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.adapter.HomeListAdapter;
import com.dong.beautifulgirl.util.ToastUtil;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.PointGalleryViewPager;
import com.dong.pointviewpager.widget.PointView;
import com.dong.pointviewpager.widget.PointViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private View inflateView;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        context = getContext();

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        return view;
    }

    private void init(View view) {

        initPointLoopViewpager(view);

        initListView(view);

    }


    private void initPointLoopViewpager(View view) {
        inflateView = View.inflate(context, R.layout.home_pointviewpager, null);
        PointViewPager pointViewPager = inflateView.findViewById(R.id.home_pointviewpager);
        if (pointViewPager != null) {
            LoopViewPager loopViewPager = pointViewPager.getLoopViewPager();
            PointView pointView = pointViewPager.getPointView();

            initLoopViewPager(loopViewPager);
            initPointView(pointView);
        }
    }

    private void initLoopViewPager(LoopViewPager loopViewPager) {
        if (loopViewPager != null) {
            loopViewPager.setAuto(true)
                    .setAutoTime(5)
                    .setLoop(true)
                    .setDefaultResouces(new int[]{R.drawable.home_pager_default})
                    .setOnLoopPagerClickListener(new OnLoopPagerClickListener() {
                        @Override
                        public void onLoopPagerClick(int i, LoopViewPagerBean loopViewPagerBean) {
                            ToastUtil.toastShort(context, "点击位置："+i);
                        }
                    })
                    .initialise();
        }
    }

    private void initPointView(PointView pointView) {
        if (pointView != null) {
            pointView.setRudis(getResources().getDimension(R.dimen.x3))
                    .setDisbottom(getResources().getDimension(R.dimen.x5))
                    .setDistance(getResources().getDimension(R.dimen.x5))
                    .initialise();
        }
    }


    private void initListView(View view) {
        ListView listView = view.findViewById(R.id.home_listview);

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }

        HomeListAdapter adapter = new HomeListAdapter(context, list);

        listView.addHeaderView(inflateView);

        listView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}