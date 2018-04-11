package com.dong.beautifulgirl.modular.recommendmodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class RecommendBean {

    private int imgId;
    private String content;

    public RecommendBean() {
    }

    public RecommendBean(int imgId, String content) {
        this.imgId = imgId;
        this.content = content;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
