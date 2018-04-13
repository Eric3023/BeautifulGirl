package com.dong.beautifulgirl.modular.findmodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.http.HeadModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class FindServerHelper {

//    public static final int[] IMG_IDS = {R.drawable.img_default0,R.drawable.img_default1,R.drawable.img_default2};
//    public static final String TITLE = "这是标题内容";
//    public static final String CONTENT = "这是详细说明---这是详细说明---";

    public OnFindDataChangedListener onFindDataChangedListener;

    public void loadFindData(Context context){

        HeadModel.getFindData(context, new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {
                FindBean findBean = response.body();
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
            public void onFailure(Call<FindBean> call, Throwable t) {

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

        HeadModel.getFindHeadData(context, new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {
                FindBean findBean = response.body();
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
            public void onFailure(Call<FindBean> call, Throwable t) {

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
