package com.dong.beautifulgirl.modular.logicandsignmodular.signmodular;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class SignServerHelper {

    private final String IMG_URL = "http://a.hiphotos.baidu.com/image/pic/item/902397dda144ad34e98003fedca20cf431ad8588.jpg";
    private final String NAME = "Eric";

    private OnSignDataChangedListener listener;

    public void loadSignData(Context context, String accout, String password){

        SharedPreferences sharedPreferences = context.getSharedPreferences("accounts", Context.MODE_PRIVATE); //私有数据

        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString(accout, password);

        edit.commit();

        if(listener!=null){
            SignBean signBean = new SignBean();
            signBean.setMesseage(true);
            listener.onSignDataChanged(signBean);
        }
    }

    public void setOnRecommendDataChangedListener(OnSignDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnSignDataChangedListener{
        void onSignDataChanged(SignBean signBean);
    }

}
