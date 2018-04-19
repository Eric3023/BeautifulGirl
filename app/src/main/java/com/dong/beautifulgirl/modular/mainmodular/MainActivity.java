package com.dong.beautifulgirl.modular.mainmodular;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.mainmodular.findmodular.FindFragment;
import com.dong.beautifulgirl.modular.mainmodular.findmodular.FindPresenter;
import com.dong.beautifulgirl.modular.mainmodular.homemodular.HomeFragment;
import com.dong.beautifulgirl.modular.mainmodular.homemodular.HomePresenter;
import com.dong.beautifulgirl.modular.mainmodular.minemodular.MineFragment;
import com.dong.beautifulgirl.modular.mainmodular.minemodular.MinePresent;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendFragment;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendPresent;
import com.dong.beautifulgirl.util.ToastUtil;
import com.dong.tabviewpager.widget.BottomTabFragmentViewPager;
import com.dong.tabviewpager.widget.FragmentViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{

    private long lastBackTime;//上一次返回键点击时间
    private static final long BACK_DURATION = 2*1000;//返回键点击间隔

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private BottomTabFragmentViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
            Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade);
            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(fade);

            getWindow().setExitTransition(fade);
            getWindow().setReenterTransition(slide);
        }

        initData();
        initView();
    }

    /*
     * 初始化数据
     */
    private void initData() {
        HomeFragment homeFragment = HomeFragment.newInstance("home", "0");
        RecommendFragment recommendfragment = RecommendFragment.newInstance("recommend", "1");
        FindFragment findFragment = FindFragment.newInstance("find", "2");
        MineFragment mineFragment = MineFragment.newInstance("mine", "3");

        fragments.add(homeFragment);
        HomePresenter homePresenter = new HomePresenter(homeFragment);
        fragments.add(recommendfragment);
        RecommendPresent recommendPresent = new RecommendPresent(recommendfragment);
        fragments.add(findFragment);
        FindPresenter findPresenter = new FindPresenter(findFragment);
        fragments.add(mineFragment);
        MinePresent minePresent = new MinePresent(mineFragment);
    }

    /*
     * 初始化View视图
     */
    private void initView() {

        viewPager = findViewById(R.id.main_viewpager);

        FragmentViewPager fragmentViewPager = viewPager.getFragmentViewPager();
        BottomNavigationBar bottomNavigationBar = viewPager.getBottomNavigationBar();

        //初始化ViewPager
        fragmentViewPager.setActivity(this)
                .setBeans(fragments)
                .initialise();
        //预加载三个页面
        fragmentViewPager.setOffscreenPageLimit(3);

        //初始化底部导航栏
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_tab_home_selected,"主页").setInactiveIconResource(R.drawable.ic_tab_home).setActiveColor(getResources().getColor(R.color.colorblue)))
                .addItem(new BottomNavigationItem(R.drawable.ic_tab_topic_selected,"主题").setInactiveIconResource(R.drawable.ic_tab_topic).setActiveColor(getResources().getColor(R.color.colorblue)))
                .addItem(new BottomNavigationItem(R.drawable.ic_tab_message_selected,"消息").setInactiveIconResource(R.drawable.ic_tab_message).setActiveColor(getResources().getColor(R.color.colorblue)))
                .addItem(new BottomNavigationItem(R.drawable.ic_tab_me_selected,"我的").setInactiveIconResource(R.drawable.ic_tab_me).setActiveColor(getResources().getColor(R.color.colorblue)))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //点击两次返回键，退出应用
            long nowBackTime = System.currentTimeMillis();
            long time = nowBackTime - lastBackTime;
            lastBackTime = nowBackTime;
            if(time > BACK_DURATION){
                ToastUtil.toastShort(mContext, "再次点击返回键，退出应用");
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
