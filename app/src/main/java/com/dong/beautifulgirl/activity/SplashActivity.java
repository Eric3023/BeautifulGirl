package com.dong.beautifulgirl.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.squareup.picasso.Picasso;

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
                Thread.sleep(1000*5);
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
                        if(getIsFirstStart())
                            startComponent(GuideActivity.class);
                        else
                            startComponent(MainActivity.class);
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
        return false;
    }

    /**
     * 初始化欢迎页启动图片
     */
    private void initView() {
        ImageView splashImg = findViewById(R.id.splash_img);
        Glide.with(this).load(R.drawable.splash_bg_animation).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(splashImg);
    }

}
