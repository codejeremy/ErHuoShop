package com.erhuo.erhuoshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.adapter.GoodsListAdapter;
import com.erhuo.erhuoshop.bean.GoodsList;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class WareListOneFragment extends Fragment {
    @ViewInject(R.id.goods_list_recycle_view)
    public static RecyclerView recyclerView;
    private List<GoodsList> goodsListList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_warelistone, null);
        ViewUtils.inject(this, mView);

        initGoods();

        return mView;
    }

    private void initGoods() {
            GoodsList goods1 = new GoodsList(R.mipmap.icon_warelist3,"李宁，韦德之道篮球短裤","300.0");
            goodsListList.add(goods1);
            GoodsList goods2 = new GoodsList(R.mipmap.icon_warelist10,"佳能相机，感动常在","500.0");
            goodsListList.add(goods2);
            GoodsList goods3 = new GoodsList(R.mipmap.icon_warelist4,"李宁，宽松篮球短裤","400.0");
            goodsListList.add(goods3);
            GoodsList goods4 = new GoodsList(R.mipmap.icon_warelist2,"穿黑色篮球短裤","200.0");
            goodsListList.add(goods4);
            GoodsList goods5 = new GoodsList(R.mipmap.icon_warelist12,"戴尔笔记本电脑","600.0");
            goodsListList.add(goods5);
            GoodsList goods6 = new GoodsList(R.mipmap.icon_warelist8,"橡胶软底，想你所想","800.0");
            goodsListList.add(goods6);
            GoodsList goods7 = new GoodsList(R.mipmap.icon_warelist7,"回力花色帆布鞋","900.0");
            goodsListList.add(goods7);
            GoodsList goods8 = new GoodsList(R.mipmap.icon_warelist5,"韦德之道篮球服，可以定制","700.0");
            goodsListList.add(goods8);
            GoodsList goods9 = new GoodsList(R.mipmap.icon_warelist6,"专业定制篮球服","700.0");
            goodsListList.add(goods9);
            GoodsList goods10 = new GoodsList(R.mipmap.icon_warelist9,"女子运动鞋，畅轻跑步","900.0");
            goodsListList.add(goods10);
            GoodsList goods11 = new GoodsList(R.mipmap.icon_warelist11,"OPPOR99，你值得拥有","700.0");
            goodsListList.add(goods11);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        GoodsListAdapter goodsListAdapter = new GoodsListAdapter(goodsListList);
        recyclerView.setAdapter(goodsListAdapter);
    }

}
