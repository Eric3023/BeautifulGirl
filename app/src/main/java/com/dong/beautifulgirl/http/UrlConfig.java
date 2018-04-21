package com.dong.beautifulgirl.http;


/**
 * Created by Dong on 2018/3/13.
 */

public class UrlConfig {

    public static final String TAG_ROOT = "美女";

    public static final String TAG_FIRST ="全部";
    public static final String TAG_SECOND ="小清新";
    public static final String TAG_THIRD ="唯美";
    public static final String TAG_FOURTH ="长腿";
    public static final String TAG_FIFTH ="诱惑";
    public static final String TAG_SIXTH ="性感";
    public static final String TAG_SEVEVTH ="气质";
    public static final String TAG_EIGHTH ="可爱";
    public static final String TAG_NINETH ="长发";
    public static final String TAG_TENTH ="车模";
    public static final String TAG_ELEVENTH ="丝袜";
    public static final String TAG_TWELTH ="写真";
    public static final String TAG_THIRTEENTH ="网络美女";
    public static final String TAG_FOURTEENTH ="宅男女神";

    public static final String BASE_URL = "http://image.baidu.com/";

    public static final String HOME_URL = "channel/listjson?pn=0&rn=20&tag1=" + TAG_ROOT + "&tag2="+TAG_SECOND+"&ie=utf8";//小清新

    public static final String HOME_HEAD_URL = "channel/listjson?pn=0&rn=5&tag1=" + TAG_ROOT + "&tag2="+TAG_THIRD+"&ie=utf8";//唯美

    public static final String HOME_CARD_URL = "channel/listjson?pn=0&rn=4&tag1=" + TAG_ROOT + "&tag2="+TAG_SEVEVTH+"&ie=utf8";//气质


    public static final String RECOMMEND_URL = "channel/listjson?pn=0&rn=30&tag1=" + TAG_ROOT + "&tag2="+TAG_TWELTH+"&ie=utf8";//写真

    public static final String FIND_URL = "channel/listjson?pn=0&rn=20&tag1=" + TAG_ROOT + "&tag2="+TAG_NINETH+"&ie=utf8";//长发

    public static final String FIND_HEAD_URL = "channel/listjson?pn=0&rn=5&tag1=" + TAG_ROOT + "&tag2="+TAG_FIFTH+"&ie=utf8";//诱惑

}
