package com.dong.beautifulgirl.modular.mainmodular.minemodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MineBean {

    private String headImgUrl;
    private String name;
    private String uid;
    private String password;

    public MineBean() {
    }

    public MineBean(String headImgUrl, String name, String uid, String password) {
        this.headImgUrl = headImgUrl;
        this.name = name;
        this.uid = uid;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
