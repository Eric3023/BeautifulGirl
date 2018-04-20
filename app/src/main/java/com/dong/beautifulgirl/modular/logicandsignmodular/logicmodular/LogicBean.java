package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class LogicBean {

    private String headImgUrl;
    private String name;

    public LogicBean() {
    }

    public LogicBean(String headImgUrl, String name) {
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
