package com.dong.beautifulgirl.modular.mainmodular.minemodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MineServerHelper {

    private final String IMG_URL = "http://a.hiphotos.baidu.com/image/pic/item/902397dda144ad34e98003fedca20cf431ad8588.jpg";
    private final String NAME = "Eric";

    private OnMineDataChangedListener listener;

    public void loadMineData(){

        MineBean mineBean = new MineBean();
        mineBean.setName(NAME);
        mineBean.setHeadImgUrl(IMG_URL);

        if(listener!=null)
            listener.onMineDataChanged(mineBean);
    }

    public void setOnRecommendDataChangedListener(OnMineDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnMineDataChangedListener{
        void onMineDataChanged(MineBean mineBean);
    }

}
