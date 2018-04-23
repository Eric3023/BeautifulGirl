package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;

import android.content.Context;
import android.graphics.Color;
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

public class RecommendTabAdapter extends RecyclerView.Adapter <RecommendTabAdapter.ViewHolder>{

    private Context context;
    private List<RecommendTabBean> tabBeans;
    private OnClickListener onClickListener;

    private View oldLineView;
    private int selectPositon;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        View lineView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            lineView = view.findViewById(R.id.item_recommend_tab_line);
            textView = view.findViewById(R.id.item_recommend_tab_textview);
        }
    }

    public  interface   OnClickListener{
        void onClick(List<RecommendTabBean> resultsBeans, int position);
    }

    public RecommendTabAdapter(Context context, List<RecommendTabBean> tabBeans) {
        this.context = context;
        this.tabBeans = tabBeans;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecommendTabAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_tab,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(tabBeans!=null){
            final RecommendTabBean tabBean = tabBeans.get(position);
            if(tabBean!=null){
                holder.textView.setText(tabBean.getTab());
                if(position == selectPositon){
                    holder.lineView.setBackgroundColor(context.getResources().getColor(R.color.colorblue));
                    oldLineView = holder.lineView;
                }else{
                    holder.lineView.setBackgroundColor(Color.TRANSPARENT);
                }

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(oldLineView!=null)
                            oldLineView.setBackgroundColor(Color.TRANSPARENT);
                        holder.lineView.setBackgroundColor(context.getResources().getColor(R.color.colorblue));
                        oldLineView = holder.lineView;

                        selectPositon = position;

                        if(onClickListener!=null)
                            onClickListener.onClick(tabBeans, position);
                    }
                });
            }

        }
    }

    @Override
    public int getItemCount() {
        return tabBeans!=null? tabBeans.size(): 0;
    }
}
