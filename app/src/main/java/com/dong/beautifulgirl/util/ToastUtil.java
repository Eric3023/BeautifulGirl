package com.dong.beautifulgirl.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Dong on 2018/3/12.
 */

public class ToastUtil {

    private static Toast mToast;

    public static void toastShort(Context context, String messeage){
        if(mToast == null){
            mToast = Toast.makeText(context, messeage, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(messeage);
        }
        mToast.show();
    }

    public static void toastLong(Context context, String messeage){
        if(mToast == null){
            mToast = Toast.makeText(context, messeage, Toast.LENGTH_LONG);
        }else{
            mToast.setText(messeage);
        }
        mToast.show();
    }

}
