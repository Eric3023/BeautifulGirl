package com.dong.beautifulgirl.adapter;

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

public class HomeListAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public HomeListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list!=null? list.size():0;
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
            view = View.inflate(context, R.layout.item_home_listview, null);
            viewHolder.img = (ImageView) view.findViewById(R.id.home_listview_img);
            viewHolder.title = (TextView) view.findViewById(R.id.home_listview_title);
            viewHolder.content = (TextView) view.findViewById(R.id.home_listview_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.get().load(R.drawable.guide_img2).resize((int)context.getResources().getDimension(R.dimen.x100),(int)context.getResources().getDimension(R.dimen.y50)).into(viewHolder.img);
        viewHolder.title.setText("巴萨大使：阿图尔能在巴萨成功"+list.get(i));
        viewHolder.content.setText("巴萨大使：阿图尔能在巴萨成功"+list.get(i));
        return view;
    }
}

class ViewHolder {
    ImageView img;
    TextView title;
    TextView content;
}
