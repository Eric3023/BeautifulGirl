package com.dong.beautifulgirl.modular.mainmodular.findmodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.http.HeadModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class FindServerHelper {

//    public static final int[] IMG_IDS = {R.drawable.img_default0,R.drawable.img_default1,R.drawable.img_default2};
//    public static final String TITLE = "这是标题内容";
//    public static final String CONTENT = "这是详细说明---这是详细说明---";

    public OnFindDataChangedListener onFindDataChangedListener;

    public void loadFindData(Context context){

        HeadModel.getFindData(context)
                .subscribe(new Observer<FindBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindBean findBean) {
                        if(findBean!=null){
                            List<FindBean.ResultsBean> results = findBean.getResults();
                            if(results!=null){
                                Log.i("Dong","获取Find数据："+results.size());
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

//        List<FindBean> homeBeans = new ArrayList<FindBean>();
//        for (int i = 0; i < 50; i++) {
//            FindBean bean = new FindBean();
//            bean.setImgId(IMG_IDS[i%IMG_IDS.length]);
//            bean.setTitle(TITLE);
//            bean.setContent(CONTENT);
//
//            homeBeans.add(bean);
//        }
//
//        Log.i("Dong", "加载Home数据："+homeBeans.size());
//        if(onFindDataChangedListener!=null)
//            onFindDataChangedListener.OnHomeDataChanged(homeBeans);
    }

    public void loadFindHeadData(Context context){

        HeadModel.getFindHeadData(context)
                .subscribe(new Observer<FindBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindBean findBean) {
                        if(findBean!=null){
                            List<FindBean.ResultsBean> results = findBean.getResults();
                            if(results!=null){
                                Log.i("Dong","获取Find Head数据："+results.size());
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
        void OnFindDataChanged(List<FindBean.ResultsBean> resultsBeans);
        void OnFindDataHeadChanged(List<FindBean.ResultsBean> resultsBeans);
    }

}
