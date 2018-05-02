package com.dong.beautifulgirl.modular.detailmodular;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.test.TestBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/24.
 */

public class DetailAdapter extends PagerAdapter {


    private List<TestBean.DataBean> dataBeans;
    private Context context;

    public DetailAdapter(Context context, List<TestBean.DataBean> dataBeans) {
        this.dataBeans = dataBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBeans != null ? dataBeans.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail, null);
        ImageView img = view.findViewById(R.id.item_detail_img);
        TextView textView = view.findViewById(R.id.item_detail_text);

        if (dataBeans != null && dataBeans.get(position) != null) {
            TestBean.DataBean dataBean = dataBeans.get(position);
            Picasso.get().load(dataBean.getImage_url()).fit().centerInside().into(img);

            if (TextUtils.isEmpty(dataBean.getDesc())) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText(dataBean.getDesc());
            }
        }
        view.setId(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.findViewById(position));
    }
}
