package com.dong.beautifulgirl.modular.mainmodular.findmodular;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dong on 2018/3/23.
 */

public class FindListAdapter extends BaseAdapter {

    private List<FindBean.DataBean> list;
    private Context context;

    public FindListAdapter(Context context, List<FindBean.DataBean> list) {
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

        FindBean.DataBean bean = list.get(i);
        if (bean != null) {
            Picasso.get().load(bean.getImage_url()).into(viewHolder.img);
            viewHolder.title.setText(bean.getDesc());
            viewHolder.content.setText(bean.getColum());
        }
        return view;
    }
}

class ViewHolder {
    ImageView img;
    TextView title;
    TextView content;
}
