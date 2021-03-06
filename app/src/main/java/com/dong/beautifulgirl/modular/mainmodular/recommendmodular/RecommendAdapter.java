package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by donghuadong on 2018/4/10.
 */

public class RecommendAdapter extends RecyclerView.Adapter <RecommendAdapter.ViewHolder>{

    private Context context;
    private List<TestBean.DataBean> resultsBeans;
    private OnClickListener onClickListener;
    private OnScrollToBottomListener onScrollToBottomListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = view.findViewById(R.id.item_recommend_img);
            textView = view.findViewById(R.id.item_recommend_tv);
        }
    }

    public interface OnClickListener{
        void onClick(List<TestBean.DataBean> resultsBeans, int position);
    }

    public interface OnScrollToBottomListener{
        void onScrollToBottom();
    }

    public RecommendAdapter(Context context, List<TestBean.DataBean> resultsBeans) {
        this.context = context;
        this.resultsBeans = resultsBeans;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnScrollToBottomListener(OnScrollToBottomListener onScrollToBottomListener) {
        this.onScrollToBottomListener = onScrollToBottomListener;
    }

    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(position == getItemCount() -1&&onScrollToBottomListener!=null){
            onScrollToBottomListener.onScrollToBottom();
        }

        if(resultsBeans!=null){
            final TestBean.DataBean resultsBean = resultsBeans.get(position);
            if(resultsBean!=null){
                Picasso.get().load(resultsBean.getImage_url()).into(holder.imageView);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(resultsBean.getDesc());
                holder.textView.setText(stringBuilder);

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onClickListener!=null)
                            onClickListener.onClick(resultsBeans, position);
                    }
                });
            }

        }
    }

    @Override
    public int getItemCount() {
        return resultsBeans!=null? resultsBeans.size(): 0;
    }
}
