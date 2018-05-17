package com.dong.beautifulgirl.modular.mainmodular.findmodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.http.HeadModel;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class FindServerHelper {

    private int pn_head;//页码
    private final int rn_head = 10;//一页显示的数量

    private final int rn_con = 30;//一页显示的数量

    public OnFindDataChangedListener onFindDataChangedListener;

    public void loadFindData(Context context, int page){

        HeadModel.getTestData(context, page, rn_con, UrlConfig.TAG_ROOT, UrlConfig.TAG_NINETH, UrlConfig.IE)
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean findBean) {
                        if(findBean!=null){
                            List<TestBean.DataBean> results = findBean.getData();
                            if(results!=null){
                                Log.i("Dong","获取Find数据："+results.size());
                                if(results.size()>1)
                                    results.remove(results.size()-1);
                                if(onFindDataChangedListener!=null)
                                    onFindDataChangedListener.OnFindDataChanged(results);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void loadFindHeadData(Context context){

        HeadModel.getTestData(context, pn_head, rn_head, UrlConfig.TAG_ROOT, UrlConfig.TAG_FIFTH, UrlConfig.IE)
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean findBean) {
                        if(findBean!=null){
                            List<TestBean.DataBean> results = findBean.getData();
                            if(results!=null){
                                Log.i("Dong","获取Find Head数据："+results.size());
                                if(results.size()>1)
                                    results.remove(results.size()-1);
                                if(onFindDataChangedListener!=null)
                                    onFindDataChangedListener.OnFindDataHeadChanged(results);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setOnHomeDataChangedListener(OnFindDataChangedListener onFindDataChangedListener) {
        this.onFindDataChangedListener = onFindDataChangedListener;
    }

    public interface OnFindDataChangedListener{
        void OnFindDataChanged(List<TestBean.DataBean> resultsBeans);
        void OnFindDataHeadChanged(List<TestBean.DataBean> resultsBeans);
    }

}
