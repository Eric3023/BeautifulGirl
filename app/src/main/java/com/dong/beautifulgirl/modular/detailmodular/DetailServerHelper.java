package com.dong.beautifulgirl.modular.detailmodular;

import android.content.Context;

import com.dong.beautifulgirl.http.HeadModel;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class DetailServerHelper {

    private int pn;//页码

    private OnDetailDataChangedListener listener;
    private ArrayList<DetailBean.DataBean> dataBeans;

    public void loadLDetailData(Context context, String tag, int rn){

        dataBeans = new ArrayList<DetailBean.DataBean>();

        HeadModel.getDetailData(context, pn ,rn, UrlConfig.TAG_ROOT, tag, UrlConfig.IE)
                .subscribe(new Observer<DetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailBean detailBean) {
                        if(detailBean!=null){
                            List<DetailBean.DataBean> results = detailBean.getData();
                            if(results!=null){
                                if(results!=null&& results.size()>1)
                                    results.remove(results.size()-1);
                                dataBeans.addAll(results);
                                if(listener!=null)
                                    listener.onDetailDataChanged(dataBeans);
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

    public void setOnRecommendDataChangedListener(OnDetailDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnDetailDataChangedListener{
        void onDetailDataChanged(List<DetailBean.DataBean> dataBeans);
    }

}
