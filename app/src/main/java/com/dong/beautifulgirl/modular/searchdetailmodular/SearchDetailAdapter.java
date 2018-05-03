package com.dong.beautifulgirl.modular.searchdetailmodular;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.test.TestBean;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class SearchDetailAdapter extends RecyclerView.Adapter <SearchDetailAdapter.ViewHolder>{

    private Context context;
    private List<SearchDetailBean.DataBean> resultsBeans;
    private OnClickListener onClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = view.findViewById(R.id.item_search_detail_img);
        }
    }

    public  interface   OnClickListener{
        void onClick(List<SearchDetailBean.DataBean> resultsBeans, int position);
    }

    public SearchDetailAdapter(Context context, List<SearchDetailBean.DataBean> resultsBeans) {
        this.context = context;
        this.resultsBeans = resultsBeans;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public SearchDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_detail,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(resultsBeans!=null){
            final SearchDetailBean.DataBean resultsBean = resultsBeans.get(position);
            if(resultsBean!=null){
                if(!TextUtils.isEmpty(resultsBean.getThumbURL())){
                    ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
                    layoutParams.height = (int) (new Random().nextInt(450) + 250);
                    layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;

                    holder.imageView.setLayoutParams(layoutParams);
                    Glide.with(context).load(resultsBean.getThumbURL()).into(holder.imageView);
                }

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
