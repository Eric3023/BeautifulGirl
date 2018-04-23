package com.dong.beautifulgirl.modular.splashmodular;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.modular.mainmodular.mainmodular.MainActivity;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.guidemodular.GuideActivity;
import com.dong.beautifulgirl.util.PackageUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        init();
    }

    /*
     * 初始化内容
     */
    private void init() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(1000 * 3);
                emitter.onNext("success");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (getIsFirstStart()) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                startComponent(GuideActivity.class,
                                        ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this).toBundle());
                            } else {
                                startComponent(GuideActivity.class);
                            }
                        } else
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startComponent(MainActivity.class,
                                    ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this).toBundle());
                        } else {
                            startComponent(MainActivity.class);
                        }
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * @return true第一次安装启动，false不是第一次安装启动
     */
    private boolean getIsFirstStart() {

        SharedPreferences sharedPreferences = getSharedPreferences("FirstRun", Context.MODE_PRIVATE); //私有数据

        String localVersionName = PackageUtil.getLocalVersionName(this, getPackageName());
        String key = localVersionName + "_isFirst";

        boolean isFirst = sharedPreferences.getBoolean(key, true);

        if(isFirst){
            SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器

            editor.putBoolean(key, false);
            editor.commit();//提交修改
            return true;

        }else{
            return false;
        }
    }

    /**
     * 初始化欢迎页启动图片
     */
    private void initView() {
        ImageView splashImg = findViewById(R.id.splash_img);
        Glide.with(this).load("http://img.soogif.com/l4cEZnc87S7r0nNvpl4Fz3nYEhWLf184.gif_s400x0").asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().placeholder(R.drawable.img_default1).into(splashImg);
    }

}
