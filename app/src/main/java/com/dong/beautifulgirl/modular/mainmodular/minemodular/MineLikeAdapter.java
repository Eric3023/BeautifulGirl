package com.dong.beautifulgirl.modular.mainmodular.minemodular;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MineLikeAdapter extends RecyclerView.Adapter <MineLikeAdapter.ViewHolder>{

    private Context context;
    private List<MineLikeBean.DataBean> resultsBeans;
    private OnClickListener onClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = view.findViewById(R.id.item_mine_like_iamgeview);
        }
    }

    public  interface   OnClickListener{
        void onClick(List<MineLikeBean.DataBean> resultsBeans, int position);

    }

    public MineLikeAdapter(Context context, List<MineLikeBean.DataBean> resultsBeans) {
        this.context = context;
        this.resultsBeans = resultsBeans;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public MineLikeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_like,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(resultsBeans!=null){
            final MineLikeBean.DataBean resultsBean = resultsBeans.get(position);
            if(resultsBean!=null){
                if(position != getItemCount()-1){
                    holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Picasso.get().load(resultsBean.getImage_url()).into(holder.imageView);
                }
                else{
                    holder.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    holder.imageView.setBackgroundColor(context.getResources().getColor(R.color.colorGray));
                    holder.imageView.setImageResource(R.drawable.see_more);
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(resultsBean.getDesc());

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
