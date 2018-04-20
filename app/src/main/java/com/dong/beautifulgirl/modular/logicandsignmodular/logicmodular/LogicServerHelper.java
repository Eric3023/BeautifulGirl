package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class LogicServerHelper {

    private final String IMG_URL = "http://a.hiphotos.baidu.com/image/pic/item/902397dda144ad34e98003fedca20cf431ad8588.jpg";
    private final String NAME = "Eric";

    private OnLogicDataChangedListener listener;

    public void loadLogicData(){

        LogicBean logicBean = new LogicBean();
        logicBean.setName(NAME);
        logicBean.setHeadImgUrl(IMG_URL);

        if(listener!=null)
            listener.onLogicDataChanged(logicBean);
    }

    public void setOnRecommendDataChangedListener(OnLogicDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnLogicDataChangedListener{
        void onLogicDataChanged(LogicBean logicBean);
    }

}
