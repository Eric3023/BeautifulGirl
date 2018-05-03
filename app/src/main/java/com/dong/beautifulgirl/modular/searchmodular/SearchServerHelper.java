package com.dong.beautifulgirl.modular.searchmodular;

import android.content.Context;
import android.text.TextUtils;

import com.dong.beautifulgirl.http.HeadModel;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.test.TestBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class SearchServerHelper {

    private int pn_like;//页码
    private final int rn_like = 30;//一页显示的数量

    private OnSearchDataChangedListener listener;
    private ArrayList<SearchBean> hotBeans;
    private ArrayList<SearchBean> historyBeans;

    public void loadHistorySearchData(Context context){
        historyBeans = new ArrayList<SearchBean>();
        if(listener!=null){
            listener.onHistorySearchDataChanged(historyBeans);
        }
    }

    public void loadHotSearchData(Context context){

        hotBeans = new ArrayList<SearchBean>();
        hotBeans.add(new SearchBean("刘亦菲"));
        hotBeans.add(new SearchBean("可爱"));
        hotBeans.add(new SearchBean("车展"));
        hotBeans.add(new SearchBean("明星"));
        hotBeans.add(new SearchBean("气质美女"));
        hotBeans.add(new SearchBean("非常迷人"));
        hotBeans.add(new SearchBean("渡边麻友"));
        hotBeans.add(new SearchBean("海贼王"));
        hotBeans.add(new SearchBean("火影忍者"));
        hotBeans.add(new SearchBean("死神"));
        hotBeans.add(new SearchBean("七原罪"));
        if(listener!=null){
            listener.onHotSearchDataChanged(hotBeans);
        }
    }

    public void setOnSearchDataChangedListener(OnSearchDataChangedListener listener) {
        this.listener = listener;
    }

    public void addHistory(SearchBean searchBean) {
        for (int i = 0; i < historyBeans.size(); i++) {
            SearchBean bean = historyBeans.get(i);
            if(bean!=null){
                if(!TextUtils.isEmpty(bean.getTag())&&TextUtils.equals(bean.getTag(), searchBean.getTag())){
                    historyBeans.remove(bean);
                }
            }
        }
        historyBeans.add(0, searchBean);
        if(listener!=null){
            listener.onHistorySearchDataChanged(historyBeans);
        }
    }

    public void clearHistory() {
        historyBeans.clear();
        if(listener!=null){
            listener.onHistorySearchDataChanged(historyBeans);
        }
    }

    public interface OnSearchDataChangedListener{
        void onHistorySearchDataChanged(List<SearchBean> searchBeans);
        void onHotSearchDataChanged(List<SearchBean> searchBeans);

    }

}
