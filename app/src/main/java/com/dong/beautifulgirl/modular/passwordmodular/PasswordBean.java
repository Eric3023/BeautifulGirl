package com.dong.beautifulgirl.modular.passwordmodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class PasswordBean {

    private boolean isSucess;
    private String messeage;

    public boolean isSucess() {
        return isSucess;
    }

    public void setSucess(boolean sucess) {
        isSucess = sucess;
    }

    public String getMesseage() {
        return messeage;
    }

    public void setMesseage(String messeage) {
        this.messeage = messeage;
    }
}
