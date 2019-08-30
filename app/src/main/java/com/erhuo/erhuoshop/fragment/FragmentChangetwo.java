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

public class FragmentChangetwo extends Fragment {
    private NewCatrgory[] newCatrgories3 = {
        new NewCatrgory(R.mipmap.icon_warelist11,"品牌男装","200"),

};
    private List<NewCatrgory> categorylist3 = new ArrayList<>();
    private RecyclerView mRecyclerviewWares2;
    private CategoryNewAdapter adapter2 = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_changetwo,container,false);
        mRecyclerviewWares2 = view.findViewById(R.id.recyclerview_wares2);
        initCategoryTwo();
        return view;
    }

    private void initCategoryTwo(){
        categorylist3.clear();
        for (int i = 0; i <30 ; i++) {
            Random random = new Random();
            int index = random.nextInt(newCatrgories3.length);
            categorylist3.add(newCatrgories3[index]);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerviewWares2.setLayoutManager(layoutManager);
        adapter2= new CategoryNewAdapter(categorylist3);
        mRecyclerviewWares2.setAdapter(adapter2);
    }
}