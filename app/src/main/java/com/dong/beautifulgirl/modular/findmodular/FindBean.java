package com.dong.beautifulgirl.modular.findmodular;

import com.dong.beautifulgirl.modular.homemodular.HomeBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class FindBean {
    /**
     * error : false
     * results : [{"_id":"5a0d0c97421aa90fe2f02c60","createdAt":"2017-11-16T11:57:11.4Z","desc":"11-16","publishedAt":"2017-11-16T12:01:05.619Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171116115656_vnsrab_Screenshot.jpeg","used":true,"who":"代码家"},{"_id":"5a0a5141421aa90fef203525","createdAt":"2017-11-14T10:13:21.137Z","desc":"11-14","publishedAt":"2017-11-14T10:43:36.180Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171114101305_NIAzCK_rakukoo_14_11_2017_10_12_58_703.jpeg","used":true,"who":"daimajia"},{"_id":"5a08ea7b421aa90fe7253628","createdAt":"2017-11-13T08:42:35.306Z","desc":"11-13","publishedAt":"2017-11-13T12:10:58.643Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171113084220_LuJgqv_sakura.gun_13_11_2017_8_42_12_311.jpeg","used":true,"who":"daimajia"},{"_id":"5a03b502421aa90fe7253618","createdAt":"2017-11-09T09:53:06.802Z","desc":"11-9","publishedAt":"2017-11-10T08:10:02.838Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171109095254_dOw5qh_bluenamchu_9_11_2017_9_52_47_256.jpeg","used":true,"who":"daimajia"},{"_id":"5a011452421aa90fe7253606","createdAt":"2017-11-07T10:02:58.73Z","desc":"11-7","publishedAt":"2017-11-08T11:00:50.559Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171107100244_0fbENB_yyannwong_7_11_2017_10_2_5_982.jpeg","used":true,"who":"daimajia"},{"_id":"59fa7379421aa90fe50c01cc","createdAt":"2017-11-02T09:23:05.497Z","desc":"11-2","publishedAt":"2017-11-06T12:40:39.976Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171102092251_AY0l4b_alrisaa_2_11_2017_9_22_44_335.jpeg","used":true,"who":"daimajia"},{"_id":"59f9674c421aa90fe50c01c6","createdAt":"2017-11-01T14:18:52.937Z","desc":"11-1","publishedAt":"2017-11-01T14:20:59.209Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171101141835_yQYTXc_enakorin_1_11_2017_14_16_45_351.jpeg","used":true,"who":"daimajia"},{"_id":"59f7e677421aa90fe72535de","createdAt":"2017-10-31T10:56:55.988Z","desc":"10-31","publishedAt":"2017-10-31T12:25:55.217Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-10-31-nozomisasaki_official_31_10_2017_10_49_17_24.jpg","used":true,"who":"代码家"},{"_id":"59f2aabb421aa90fef2034d5","createdAt":"2017-10-27T11:40:43.793Z","desc":"10-27","publishedAt":"2017-10-27T12:02:30.376Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171027114026_v8VFwP_joanne_722_27_10_2017_11_40_17_370.jpeg","used":true,"who":"daimajia"},{"_id":"59f0054a421aa90fe2f02bf4","createdAt":"2017-10-25T11:30:18.697Z","desc":"2017-10-25","publishedAt":"2017-10-25T11:39:10.950Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171025112955_lmesMu_katyteiko_25_10_2017_11_29_43_270.jpeg","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<FindBean.ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<FindBean.ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<FindBean.ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5a0d0c97421aa90fe2f02c60
         * createdAt : 2017-11-16T11:57:11.4Z
         * desc : 11-16
         * publishedAt : 2017-11-16T12:01:05.619Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/20171116115656_vnsrab_Screenshot.jpeg
         * used : true
         * who : 代码家
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
