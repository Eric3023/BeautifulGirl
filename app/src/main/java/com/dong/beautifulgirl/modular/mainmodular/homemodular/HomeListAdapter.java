package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.test.TestBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dong on 2018/3/23.
 */

public class HomeListAdapter extends BaseAdapter {

    private List<TestBean.DataBean> list;
    private Context context;
    private OnCardItemClickListener onCardItemClickListener;
    private OnScrollToBottomListener onScrollToBottomListener;

    public HomeListAdapter(Context context, List<TestBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnCardItemClickListener(OnCardItemClickListener onCardItemClickListener) {
        this.onCardItemClickListener = onCardItemClickListener;
    }

    public void setOnScrollToBottomListener(OnScrollToBottomListener onScrollToBottomListener) {
        this.onScrollToBottomListener = onScrollToBottomListener;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
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

        final ImageView shareImg = viewHolder.img;

        TestBean.DataBean bean = list.get(i);
        if (bean != null) {
            Picasso.get().load(bean.getImage_url()).into(viewHolder.img);
            viewHolder.title.setText(bean.getAbs());
            viewHolder.content.setText(bean.getDesc());

            final ViewHolder finalViewHolder = viewHolder;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onCardItemClickListener!=null){
                        onCardItemClickListener.onCardItemClick(shareImg, i);
                    }
                }
            });
        }

        if(i == getCount() -1&& onScrollToBottomListener!=null)
            onScrollToBottomListener.onScrollToBottom();

        return view;
    }

    public interface OnCardItemClickListener{

        void onCardItemClick(ImageView shareImg, int i);
    }

    public interface OnScrollToBottomListener{

        void onScrollToBottom();
    }
}

class ViewHolder {
    ImageView img;
    TextView title;
    TextView content;
}
