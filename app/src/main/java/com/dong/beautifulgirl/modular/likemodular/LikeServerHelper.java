package com.dong.beautifulgirl.modular.likemodular;

import android.content.Context;

import com.dong.beautifulgirl.http.HeadModel;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.test.TestBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class LikeServerHelper {

    private int pn_like;//页码
    private final int rn_like = 30;//一页显示的数量

    private OnLikeDataChangedListener listener;
    private ArrayList<TestBean.DataBean> dataBeans;

    public void loadLikeData(Context context){

        dataBeans = new ArrayList<TestBean.DataBean>();

        HeadModel.getTestData(context, pn_like ,rn_like, UrlConfig.TAG_ROOT, UrlConfig.TAG_THIRD, UrlConfig.IE)
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean detailBean) {
                        if(detailBean!=null){
                            List<TestBean.DataBean> results = detailBean.getData();
                            if(results!=null){
                                if(results!=null&& results.size()>1)
                                    results.remove(results.size()-1);
                                dataBeans.addAll(results);
                                if(listener!=null)
                                    listener.onLikeDataChanged(dataBeans);
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

    public void setOnRecommendDataChangedListener(OnLikeDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnLikeDataChangedListener{
        void onLikeDataChanged(List<TestBean.DataBean> dataBeans);
    }

}
