package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.modular.detailimgmodular.DetailImgActivity;
import com.dong.beautifulgirl.modular.detailmodular.DetailActivity;
import com.dong.beautifulgirl.modular.mainmodular.mainmodular.MainActivity;
import com.dong.beautifulgirl.modular.scanmodular.ScanActivity;
import com.dong.beautifulgirl.test.TestBean;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.PointGalleryViewPager;
import com.dong.pointviewpager.widget.PointView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeContract.View, View.OnClickListener, HomeListAdapter.OnCardItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private ListView listView;
    private PointGalleryViewPager pointGalleryViewPager;

    private HomeContract.Presenter presenter;
    private LoopViewPager loopViewPager;
    private PointView pointView;
    private List<TestBean.DataBean> listResultsBeans;
    private List<LoopViewPagerBean> pagerBeans;
    private HomeListAdapter adapter;
    private ImageView firstCardImg;
    private TextView firstCardTextView;
    private ImageView secondCardImg;
    private TextView secondCardTextView;
    private ImageView thirdCardImg;
    private TextView thirdCardTextView;
    private ImageView fourthCardImg;
    private TextView fourthCardTextView;
    private LinearLayout firstCardLayout;
    private LinearLayout secondCardLayout;
    private LinearLayout thirdCardLayout;
    private LinearLayout fourthCardLayout;
    private CardView firstCard;
    private CardView secondCard;
    private CardView thirdCard;
    private CardView fourthCard;

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
        View inflateView = View.inflate(context, R.layout.inflate_home_pointviewpager, null);
        initHeadView(inflateView);
        initView(view, inflateView);

        if (presenter != null)
            presenter.start(getContext());
        return view;
    }

    private void initHeadView(View view) {
        initCard(view);
        initPointLoopViewpager(view);
    }

    private void initView(View view, View headView) {

        initMenu(view);

        initListView(view, headView);

    }

    private void initMenu(View view) {
        Button menu = view.findViewById(R.id.home_menu);
        Button qCode = view.findViewById(R.id.home_qcode);

        menu.setOnClickListener(this);
        qCode.setOnClickListener(this);
    }


    private void initCard(View view) {
        firstCardLayout = view.findViewById(R.id.home_card_first);
        secondCardLayout = view.findViewById(R.id.home_card_second);
        thirdCardLayout = view.findViewById(R.id.home_card_third);
        fourthCardLayout = view.findViewById(R.id.home_card_fourth);

        firstCardImg = firstCardLayout.findViewById(R.id.home_card_iamgeview);
        firstCardTextView = firstCardLayout.findViewById(R.id.home_card_textview);
        firstCard = firstCardLayout.findViewById(R.id.home_card_cardview);
        secondCardImg = secondCardLayout.findViewById(R.id.home_card_iamgeview);
        secondCardTextView = secondCardLayout.findViewById(R.id.home_card_textview);
        secondCard = secondCardLayout.findViewById(R.id.home_card_cardview);
        thirdCardImg = thirdCardLayout.findViewById(R.id.home_card_iamgeview);
        thirdCardTextView = thirdCardLayout.findViewById(R.id.home_card_textview);
        thirdCard = thirdCardLayout.findViewById(R.id.home_card_cardview);
        fourthCardImg = fourthCardLayout.findViewById(R.id.home_card_iamgeview);
        fourthCardTextView = fourthCardLayout.findViewById(R.id.home_card_textview);
        fourthCard = fourthCardLayout.findViewById(R.id.home_card_cardview);

        firstCard.setOnClickListener(this);
        secondCard.setOnClickListener(this);
        thirdCard.setOnClickListener(this);
        fourthCard.setOnClickListener(this);

    }

    private void initPointLoopViewpager(View view) {
        pointGalleryViewPager = view.findViewById(R.id.home_pointgalleryviewpager);
        if (pointGalleryViewPager != null) {
            loopViewPager = pointGalleryViewPager.getLoopViewPager();
            pointView = pointGalleryViewPager.getPointView();

            initLoopViewPager(loopViewPager);
            initPointView(pointView);
            initGalleryViewPager(pointGalleryViewPager);
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
                    .setOnLoopPagerClickListener(new OnLoopPagerClickListener() {
                        @Override
                        public void onLoopPagerClick(int i, LoopViewPagerBean loopViewPagerBean) {
                            TestBean.DataBean dataBean = (TestBean.DataBean) loopViewPagerBean.getObject();
                            if(dataBean!=null){
                                Intent intent = new Intent(getContext(), DetailActivity.class);
                                intent.putExtra("POSITION", i);
                                intent.putExtra("TAG", UrlConfig.TAG_SEVEVTH);
                                intent.putExtra("RN", 5);
                                getActivity().startActivity(intent);
                            }
                        }
                    })
                    .setCard(true)
                    .setCardElevation(getResources().getDimension(R.dimen.x3))
                    .setCardPadding(0)
                    .initialise();
        }
    }

    private void initPointView(PointView pointView) {
        if (pointView != null) {
            pointView.setRudis(getResources().getDimension(R.dimen.x3))
                    .setDisbottom(getResources().getDimension(R.dimen.y8))
                    .setDistance(getResources().getDimension(R.dimen.x8))
                    .initialise();
        }
    }

    private void initGalleryViewPager(PointGalleryViewPager pointGalleryViewPager) {
        pointGalleryViewPager.setPageWidth((int) getResources().getDimension(R.dimen.x290))//设置ViewPager的宽度，适当小于GalleryViewPager的宽度
                .setPageHeight(RelativeLayout.LayoutParams.MATCH_PARENT)//设置ViewPager的高度
                .setPageScale((float) 0.95)//设置两侧隐藏页面的缩放比例
                .setPageAlpha((float) 0.8)//设置两侧隐藏页面的透明度
                .initialise();
    }

    private void initListView(View view, View headView) {
        listView = view.findViewById(R.id.home_listview);
        listView.addHeaderView(headView);

        listResultsBeans = new ArrayList<TestBean.DataBean>();
        adapter = new HomeListAdapter(context, listResultsBeans);
        adapter.setOnCardItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void homeDataChanged(List<TestBean.DataBean> resultsBeans) {
        this.listResultsBeans.addAll(resultsBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void homeDataCardChanged(List<TestBean.DataBean> list) {
        if(list!=null){
            if(list.size()>0){
                TestBean.DataBean dataBean = list.get(0);
                if(dataBean!=null){
                    firstCard.setTag(dataBean);
                    Picasso.get().load(dataBean.getImage_url()).into(firstCardImg);
                    firstCardTextView.setText(dataBean.getDesc());
                }
            }
            if(list.size()>1){
                TestBean.DataBean dataBean = list.get(1);
                if(dataBean!=null){
                    secondCard.setTag(dataBean);
                    Picasso.get().load(dataBean.getImage_url()).into(secondCardImg);
                    secondCardTextView.setText(dataBean.getDesc());
                }
            }
            if(list.size()>2){
                TestBean.DataBean dataBean = list.get(2);
                if(dataBean!=null){
                    thirdCard.setTag(dataBean);
                    Picasso.get().load(dataBean.getImage_url()).into(thirdCardImg);
                    thirdCardTextView.setText(dataBean.getDesc());
                }
            }
            if(list.size()>3){
                TestBean.DataBean dataBean = list.get(3);
                if(dataBean!=null){
                    fourthCard.setTag(dataBean);
                    Picasso.get().load(dataBean.getImage_url()).into(fourthCardImg);
                    fourthCardTextView.setText(dataBean.getDesc());
                }
            }
        }
    }

    @Override
    public void homeDataHeadChanged(List<TestBean.DataBean> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                TestBean.DataBean resultsBean = list.get(i);

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
        }
    }

    @Override
    public void onClick(View v) {
        MainActivity mainActivity = (MainActivity) getActivity();
        int id = v.getId();
        switch (id){
            case R.id.home_menu:
                if(mainActivity.isOpen())
                    mainActivity.closeSlide();
                else
                    mainActivity.openSlide();
                break;
            case R.id.home_qcode:
                Intent scannerIntent = new Intent(mainActivity, ScanActivity.class);
                mainActivity.startActivityForResult(scannerIntent, MainActivity.REQUEST_CODE_SCANNER);
                break;
            case R.id.home_card_cardview:
                TestBean.DataBean firstDataBean = (TestBean.DataBean) v.getTag();
                if(firstDataBean!=null){

                    ImageView img = v.findViewById(R.id.home_card_iamgeview);

                    Intent intent = new Intent(getContext(), DetailImgActivity.class);
                    intent.putExtra("URL", firstDataBean.getImage_url());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Pair<ImageView, String> imgPair = Pair.create(img, "share detail img");
                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), new Pair[]{imgPair});
                        startActivity(intent, activityOptions.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
                break;
        }
    }

    @Override
    public void onCardItemClick(ImageView shareImg, int i) {
        TestBean.DataBean dataBean = listResultsBeans.get(i);
        if(dataBean!=null){
            Intent intent = new Intent(getContext(), DetailImgActivity.class);
            intent.putExtra("URL", dataBean.getImage_url());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Pair<ImageView, String> imgPair = Pair.create(shareImg, "share detail img");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), new Pair[]{imgPair});
                startActivity(intent, activityOptions.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }
}
