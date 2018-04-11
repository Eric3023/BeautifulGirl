package com.dong.beautifulgirl.modular.minemodular;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.beautifulgirl.R;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MineAdapter extends RecyclerView.Adapter <MineAdapter.ViewHolder>{

    private List<MineBean> recommendBeans;
    private OnClickListener onClickListener;

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

    public  interface   OnClickListener{
        void onClick(List<MineBean> recommendBeans, int position);
    }

    public MineAdapter(List<MineBean> recommendBeans) {
        this.recommendBeans = recommendBeans;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public MineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(recommendBeans!=null){
            MineBean recommendBean = recommendBeans.get(position);
            if(recommendBean!=null){
                holder.imageView.setImageResource(recommendBean.getImgId());
                holder.textView.setText(recommendBean.getContent());

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onClickListener!=null)
                            onClickListener.onClick(recommendBeans, position);
                    }
                });
            }

        }
    }

    @Override
    public int getItemCount() {
        return recommendBeans!=null? recommendBeans.size(): 0;
    }
}
