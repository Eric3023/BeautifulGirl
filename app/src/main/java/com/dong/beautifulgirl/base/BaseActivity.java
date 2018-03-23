package com.dong.beautifulgirl.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dong.beautifulgirl.activity.MainActivity;


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

    protected void startComponent(Class<?> cls) {
        if(mContext!= null){
            Intent intent = new Intent(mContext, cls);
            mContext.startActivity(intent);
        }
    }

}
