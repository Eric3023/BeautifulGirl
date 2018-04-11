package com.dong.beautifulgirl.modular.minemodular;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MineBean {

    private int imgId;
    private String content;

    public MineBean() {
    }

    public MineBean(int imgId, String content) {
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
