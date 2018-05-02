package com.dong.beautifulgirl.modular.passwordmodular;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class PasswordServerHelper {

    private OnPasswordDataChangedListener listener;

    public void changePassword(Context context, String oldPassword, String newPassword, String newPasswordConfirm){

        SharedPreferences sharedPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE); //私有数据
        String account = sharedPreferences.getString("account", "");
        String password = sharedPreferences.getString("password", "");

        PasswordBean passwordBean = new PasswordBean();
        if(!password.equals(oldPassword)){
            passwordBean.setSucess(false);
            passwordBean.setMesseage("密码输入不正确");
        }else if(TextUtils.isEmpty(newPassword)){
            passwordBean.setSucess(false);
            passwordBean.setMesseage("新密码不能为空");
        }else if(!newPassword.equals(newPasswordConfirm)){
            passwordBean.setSucess(false);
            passwordBean.setMesseage("密码输入不一致");
        }else{
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("password", "");
            edit.commit();

            SharedPreferences sp = context.getSharedPreferences("accounts", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(account, newPassword);
            editor.commit();

            passwordBean.setSucess(true);
        }

        if(listener!=null){
            listener.onPasswordChanged(passwordBean);
        }
    }

    public void setOnRecommendDataChangedListener(OnPasswordDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnPasswordDataChangedListener{
        void onPasswordChanged(PasswordBean passwordBean);
    }

}
