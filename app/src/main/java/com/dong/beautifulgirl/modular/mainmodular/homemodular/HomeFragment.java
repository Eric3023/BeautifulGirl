package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dong.beautifulgirl.R;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.widget.LoopViewPager;
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
public class HomeFragment extends Fragment implements HomeContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private View inflateView;
    private ListView listView;
    private PointViewPager pointViewPager;

    private HomeContract.Presenter presenter;
    private LoopViewPager loopViewPager;
    private PointView pointView;
    private List<HomeBean.DataBean> listResultsBeans;
    private List<LoopViewPagerBean> pagerBeans;
    private HomeListAdapter adapter;

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

        if (presenter != null)
            presenter.start(getContext());
        return view;
    }

    private void init(View view) {

        initPointLoopViewpager(view);

        initListView(view);

    }

    @Override
    public void onResume() {
        Log.i("Dong","onResume");
        super.onResume();
    }

    private void initPointLoopViewpager(View view) {
        inflateView = View.inflate(context, R.layout.inflate_home_pointviewpager, null);
        pointViewPager = inflateView.findViewById(R.id.home_pointviewpager);
        if (pointViewPager != null) {
            loopViewPager = pointViewPager.getLoopViewPager();
            pointView = pointViewPager.getPointView();

            initLoopViewPager(loopViewPager);
            initPointView(pointView);
        }
    }

    private void initLoopViewPager(LoopViewPager loopViewPager) {
        pagerBeans = new ArrayList<LoopViewPagerBean>();
        if (loopViewPager != null) {
            loopViewPager.setAuto(false)
                    .setLoop(false)
                    .setBeans(pagerBeans)
                    .setDefaultResouces(new int[]{R.drawable.home_pager_default})
                    .setImageScale(LoopViewPager.CENTER_CROP)
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
        listView = view.findViewById(R.id.home_listview);
        listView.addHeaderView(inflateView);

        listResultsBeans = new ArrayList<HomeBean.DataBean>();
        adapter = new HomeListAdapter(context, listResultsBeans);
        listView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void homeDataChanged(List<HomeBean.DataBean> resultsBeans) {
        this.listResultsBeans.addAll(resultsBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void homeDataHeadChanged(List<HomeBean.DataBean> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                HomeBean.DataBean resultsBean = list.get(i);

                if (resultsBean != null) {
                    LoopViewPagerBean bean = new LoopViewPagerBean();
                    bean.setUrl(resultsBean.getImage_url());
                    bean.setResourceID(R.drawable.img_default0);
                    bean.setObject(resultsBean);
                    pagerBeans.add(bean);
                }
            }
        }
        if(loopViewPager!=null){

//            //不改变循环，只改变数据源
//            LoopPagerAdapter adapter = loopViewPager.getLoopPagerAdapter();
//            adapter.notifyDataSetChanged();

            if (loopViewPager != null) {
                loopViewPager.setAuto(true)
                        .setLoop(true)
                        .setAutoTime(5)
                       .setBeans(pagerBeans)
                        .setDefaultResouces(new int[]{R.drawable.home_pager_default})
                        .initialise();
            }

            pointView.setCount(10);
        }
    }
}
