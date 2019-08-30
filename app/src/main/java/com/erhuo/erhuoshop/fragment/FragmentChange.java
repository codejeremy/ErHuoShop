package com.erhuo.erhuoshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.adapter.CategoryNewAdapter;
import com.erhuo.erhuoshop.bean.NewCatrgory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentChange extends Fragment {

    private NewCatrgory[] newCatrgories2 = {
            new NewCatrgory(R.mipmap.icon_warelist2,"热门商品","200"),

    };
    private List<NewCatrgory> categorylist2 = new ArrayList<>();
    private RecyclerView mRecyclerviewWares1;
    private CategoryNewAdapter adapter1 = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_change,container,false);
        mRecyclerviewWares1 = view.findViewById(R.id.recyclerview_wares1);
        initCategoryTwo();
        return view;
    }

    private void initCategoryTwo(){
        categorylist2.clear();
        for (int i = 0; i <30 ; i++) {
            Random random = new Random();
            int index = random.nextInt(newCatrgories2.length);
            categorylist2.add(newCatrgories2[index]);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerviewWares1.setLayoutManager(layoutManager);
        adapter1= new CategoryNewAdapter(categorylist2);
        mRecyclerviewWares1.setAdapter(adapter1);
    }
}
