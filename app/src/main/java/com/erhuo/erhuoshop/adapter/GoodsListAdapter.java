package com.erhuo.erhuoshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.erhuo.erhuoshop.CategoryActivity;
import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.bean.GoodsList;

import java.util.List;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<GoodsList>mGoodlist;



    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView goodsimageView = null;
        TextView goodstextView = null;
        TextView goodspricetext = null;
        Button goodsaddcart = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.goodscardview);
            goodsimageView = itemView.findViewById(R.id.goods_image);
            goodstextView = itemView.findViewById(R.id.goods_descrip);
            goodspricetext = itemView.findViewById(R.id.goods_price);
            goodsaddcart = itemView.findViewById(R.id.goods_add_cart);
        }
    }
    public GoodsListAdapter(List<GoodsList>goodsLists){
        mGoodlist = goodsLists;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.goods_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GoodsList goodsList = mGoodlist.get(position);
                Intent intent = new Intent(mContext,CategoryActivity.class);
                intent.putExtra(CategoryActivity.GOODS_NAME,goodsList.getGoods_descripe());
                intent.putExtra(CategoryActivity.GOODS_IMAGE_ID,goodsList.getImgurlid());
                mContext.startActivity(intent);
            }
        });
        holder.goodsaddcart.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GoodsList goods = mGoodlist.get(i);
        viewHolder.goodsimageView.setImageResource(goods.getImgurlid());
        viewHolder.goodstextView.setText(goods.getGoods_descripe());
        viewHolder.goodspricetext.setText(goods.getGoods_price());
    }

    @Override
    public int getItemCount() {
        return mGoodlist.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goods_add_cart:
                Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
                intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
                mContext.startActivity(intent);
        }
    }
}
