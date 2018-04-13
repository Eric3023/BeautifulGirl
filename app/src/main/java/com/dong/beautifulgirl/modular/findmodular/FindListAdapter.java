package com.dong.beautifulgirl.modular.findmodular;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.circleimageview.widget.RoundImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dong on 2018/3/23.
 */

public class FindListAdapter extends BaseAdapter {

    private List<FindBean.ResultsBean> list;
    private Context context;

    public FindListAdapter(Context context, List<FindBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.item_find_listview, null);
            viewHolder.img = (ImageView) view.findViewById(R.id.find_listview_img);
            viewHolder.title = (TextView) view.findViewById(R.id.find_listview_title);
            viewHolder.content = (TextView) view.findViewById(R.id.find_listview_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        FindBean.ResultsBean bean = list.get(i);
        if (bean != null) {
            Picasso.get().load(bean.getUrl()).resize((int)context.getResources().getDimension(R.dimen.x300), (int)context.getResources().getDimension(R.dimen.y120)).into(viewHolder.img);
            viewHolder.title.setText(bean.getCreatedAt());
            viewHolder.content.setText(bean.getPublishedAt());
        }
        return view;
    }
}

class ViewHolder {
    ImageView img;
    TextView title;
    TextView content;
}
