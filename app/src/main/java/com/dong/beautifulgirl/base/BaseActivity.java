package com.dong.beautifulgirl.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Dong on 2018/3/12.
 */

public class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public void startComponent(Class<?> cls) {
        if (mContext != null) {
            Intent intent = new Intent(mContext, cls);
            mContext.startActivity(intent);
        }
    }

    public void startComponent(Class<?> cls, Bundle options) {
        if (mContext != null) {
            Intent intent = new Intent(mContext, cls);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mContext.startActivity(intent, options);
            } else {
                mContext.startActivity(intent);
            }
        }

    }

}
