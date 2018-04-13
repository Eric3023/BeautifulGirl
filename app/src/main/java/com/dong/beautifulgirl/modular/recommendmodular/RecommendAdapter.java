package com.dong.beautifulgirl.modular.recommendmodular;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dong.beautifulgirl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class RecommendAdapter extends RecyclerView.Adapter <RecommendAdapter.ViewHolder>{

    private Context context;
    private List<RecommendBean.ResultsBean> resultsBeans;
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
        void onClick(List<RecommendBean.ResultsBean> resultsBeans, int position);
    }

    public RecommendAdapter(Context context, List<RecommendBean.ResultsBean> resultsBeans) {
        this.context = context;
        this.resultsBeans = resultsBeans;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(resultsBeans!=null){
            final RecommendBean.ResultsBean resultsBean = resultsBeans.get(position);
            if(resultsBean!=null){
                Picasso.get().load(resultsBean.getUrl()).resize((int) context.getResources().getDimension(R.dimen.x150), (int) context.getResources().getDimension(R.dimen.y80)).into(holder.imageView);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(resultsBean.getCreatedAt());
                for (int j = 0; j < position% 3; j++) {
                    stringBuilder.append("\n");
                    stringBuilder.append(resultsBean.getCreatedAt());
                }

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
