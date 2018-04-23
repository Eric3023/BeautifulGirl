package com.dong.beautifulgirl.modular.mainmodular.minemodular;

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

public class MineServerHelper {

    private final String IMG_URL = "http://a.hiphotos.baidu.com/image/pic/item/902397dda144ad34e98003fedca20cf431ad8588.jpg";
    private final String NAME = "Eric";
    private final String UID = "827142667";

    private int pn_like;//页码
    private final int rn_like = 30;//一页显示的数量

    private OnMineDataChangedListener listener;

    public void loadMineData(){

        MineBean mineBean = new MineBean();
        mineBean.setName(NAME);
        mineBean.setHeadImgUrl(IMG_URL);
        mineBean.setUid(UID);

        if(listener!=null)
            listener.onMineDataChanged(mineBean);
    }

    public void setOnRecommendDataChangedListener(OnMineDataChangedListener listener) {
        this.listener = listener;
    }

    public void loadMineLikeData(Context context) {

        HeadModel.getMineLikeData(context, pn_like ,rn_like, UrlConfig.TAG_ROOT, UrlConfig.TAG_THIRD, UrlConfig.IE)
                .subscribe(new Observer<MineLikeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MineLikeBean mineLikeBean) {
                        if(mineLikeBean!=null){
                            List<MineLikeBean.DataBean> results = mineLikeBean.getData();
                            if(results!=null){
                                if(results!=null&& results.size()>1)
                                    results.remove(results.size()-1);
                                if(listener!=null)
                                    listener.onMineLikeDataChanged(results);
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

    public interface OnMineDataChangedListener{
        void onMineDataChanged(MineBean mineBean);

        void onMineLikeDataChanged(List<MineLikeBean.DataBean> dataBeans);
    }

}
