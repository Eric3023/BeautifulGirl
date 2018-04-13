package com.dong.beautifulgirl.modular.minemodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MineBean {

    private String headImgUrl;
    private String name;

    public MineBean() {
    }

    public MineBean(String headImgUrl, String name) {
        this.headImgUrl = headImgUrl;
        this.name = name;
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
}
