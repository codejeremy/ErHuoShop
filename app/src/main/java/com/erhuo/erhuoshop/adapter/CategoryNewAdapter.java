package com.erhuo.erhuoshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erhuo.erhuoshop.CategoryActivity;
import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.bean.NewCatrgory;

import java.util.List;

public class CategoryNewAdapter extends RecyclerView.Adapter<CategoryNewAdapter.ViewHolder> {
    private Context mContext;
    private List<NewCatrgory> mNewCategory;
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView textView;
        TextView pricetextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            image = itemView.findViewById(R.id.drawee_view);
            textView = itemView.findViewById(R.id.text_title);
            pricetextView = itemView.findViewById(R.id.text_price);
        }
    }
    public CategoryNewAdapter(List<NewCatrgory>categorylist){
        mNewCategory = categorylist;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.template_grid_wares,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                NewCatrgory newCatrgory = mNewCategory.get(position);
                Intent intent = new Intent(mContext,CategoryActivity.class);
                intent.putExtra(CategoryActivity.GOODS_NAME,newCatrgory.getName());
                intent.putExtra(CategoryActivity.GOODS_IMAGE_ID,newCatrgory.getImgid());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewCatrgory newCatrgory = mNewCategory.get(i);
        Log.d("加载图片", "onBindViewHolder:111111111111111111111111111111111111 ");
        Glide.with(mContext).load(newCatrgory.getImgid()).into(viewHolder.image);
        viewHolder.textView.setText(newCatrgory.getName());
        viewHolder.pricetextView.setText(newCatrgory.getPrice());

    }

    @Override
    public int getItemCount() {
        return mNewCategory.size();
    }


}
