package com.dong.beautifulgirl.modular.findmodular;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.modular.homemodular.HomeBean;
import com.dong.beautifulgirl.util.ToastUtil;
import com.dong.pointviewpager.adapter.LoopPagerAdapter;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.dong.pointviewpager.widget.GalleryViewPager;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.PointView;
import com.dong.pointviewpager.widget.PointViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindFragment extends Fragment implements FindContract.View {
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
    private GalleryViewPager galleryViewPager;

    private FindContract.Presenter presenter;
    private LoopViewPager loopViewPager;

    private List<FindBean.ResultsBean> resultsBeans;
    private FindListAdapter adapter;
    private List<LoopViewPagerBean> beans;

    public FindFragment() {
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
    public static FindFragment newInstance(String param1, String param2) {
        FindFragment fragment = new FindFragment();
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


    private void initPointLoopViewpager(View view) {
        inflateView = View.inflate(context, R.layout.inflate_find_viewpager, null);
        galleryViewPager = inflateView.findViewById(R.id.find_loopviewpager);
        loopViewPager = galleryViewPager.getLoopViewPager();

        initLoopViewPager(loopViewPager);

        initGalleryViewPager(galleryViewPager);
    }

    private void initLoopViewPager(LoopViewPager loopViewPager) {
        beans = new ArrayList<LoopViewPagerBean>();
        if (loopViewPager != null) {
            loopViewPager.setAuto(false)
                    .setAutoTime(5)
                    .setLoop(false)
                    .setBeans(beans)
                    .setDefaultResouces(new int[]{R.drawable.home_pager_default})
                    .setOnLoopPagerClickListener(new OnLoopPagerClickListener() {
                        @Override
                        public void onLoopPagerClick(int i, LoopViewPagerBean loopViewPagerBean) {
                            ToastUtil.toastShort(context, "点击位置：" + i);
                        }
                    })
                    .initialise();
        }
    }

    private void initGalleryViewPager(GalleryViewPager galleryViewPager) {
        galleryViewPager.setPageWidth((int) getResources().getDimension(R.dimen.x240))//设置ViewPager的宽度，适当小于GalleryViewPager的宽度
                .setPageHeight(RelativeLayout.LayoutParams.MATCH_PARENT)//设置ViewPager的高度
                .setPageScale((float) 0.9)//设置两侧隐藏页面的缩放比例
                .setPageAlpha((float) 0.5)//设置两侧隐藏页面的透明度
                .initialise();
    }

    private void initListView(View view) {
        listView = view.findViewById(R.id.home_listview);
        listView.addHeaderView(inflateView);

        resultsBeans = new ArrayList<FindBean.ResultsBean>();
        adapter = new FindListAdapter(context, resultsBeans);
        listView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(FindContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void findDataChanged(List<FindBean.ResultsBean> resultsBeans) {
        this.resultsBeans.addAll(resultsBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void findDataHeadChanged(List<FindBean.ResultsBean> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                FindBean.ResultsBean resultsBean = list.get(i);

                if (resultsBean != null) {
                    LoopViewPagerBean bean = new LoopViewPagerBean();
                    bean.setUrl(resultsBean.getUrl());
                    bean.setObject(resultsBean);
                    beans.add(bean);
                }
            }
        }
        if(loopViewPager!=null){
            LoopPagerAdapter loopPagerAdapter = loopViewPager.getLoopPagerAdapter();
            loopPagerAdapter.notifyDataSetChanged();
        }
    }
}
