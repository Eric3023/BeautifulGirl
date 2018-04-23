package com.dong.beautifulgirl.modular.mainmodular.mainmodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MainSlideBean {

    private String headImgUrl;
    private String name;
    private String uid;

    public MainSlideBean() {
    }

    public MainSlideBean(String headImgUrl, String name, String uid) {
        this.headImgUrl = headImgUrl;
        this.name = name;
        this.uid = uid;
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
