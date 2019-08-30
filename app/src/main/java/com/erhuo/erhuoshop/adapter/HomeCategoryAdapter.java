package com.erhuo.erhuoshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.WarelistActivity;
import com.erhuo.erhuoshop.bean.HomeCategory;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    private static int VIEW_TYPE_L = 0;
    private static int VIEW_TYPE_R = 1;

    private LayoutInflater minflater;

    Context context;

    private List<HomeCategory> mDatas;
    public HomeCategoryAdapter(List<HomeCategory>datas,Context context){
        this.context = context;
        mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        minflater = LayoutInflater.from(viewGroup.getContext());

        if(type == VIEW_TYPE_R){
            View view = minflater.inflate(R.layout.template_home_cardview2,viewGroup,false);
            final ViewHolder holder1 = new ViewHolder(view);
            RecycleclickItem(holder1);
            return holder1;

        }else{
            View view = minflater.inflate(R.layout.template_home_cardview,viewGroup,false);
            final ViewHolder holder2 = new ViewHolder(view);
            RecycleclickItem(holder2);
            return holder2;
        }
    }
    public void RecycleclickItem(ViewHolder holder){
        holder.categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WarelistActivity.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeCategory category = mDatas.get(i);
        viewHolder.mtextView.setText(category.getName());
        viewHolder.mImageViewBig.setImageResource(category.getImgBig());
        viewHolder.mImageViewSmallTop.setImageResource(category.getImgSmallTop());
        viewHolder.mImageViewSmallBottom.setImageResource(category.getImgSmallBottom());
    }
    @Override
    public int getItemCount() {

        return mDatas.size();
    }
    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0){
            return VIEW_TYPE_R;
        }
        else
            return VIEW_TYPE_L;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mtextView = null;
        ImageView mImageViewBig = null;
        ImageView mImageViewSmallTop = null;
        ImageView mImageViewSmallBottom = null;
        View categoryView = null;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryView = itemView;
            mtextView = itemView.findViewById(R.id.text_title);
            mImageViewBig = itemView.findViewById(R.id.imgview_big);
            mImageViewSmallBottom = itemView.findViewById(R.id.imgview_small_bottom);
            mImageViewSmallTop = itemView.findViewById(R.id.imgview_small_top);
        }
    }
}
