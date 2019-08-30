package com.erhuo.erhuoshop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.erhuo.erhuoshop.adapter.WareListAdapter;
import com.erhuo.erhuoshop.fragment.WareListOneFragment;
import com.erhuo.erhuoshop.fragment.WareListThreeFragment;
import com.erhuo.erhuoshop.fragment.WareListTwoFragment;

import java.util.ArrayList;
import java.util.List;

public class WarelistActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView wareBack;
    private String[] titles = new String[]{"默认","价格","销量"};
    private WareListAdapter mAdapter;
    private ImageView change;
    public static boolean ACTION = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warelist);
        init();
        setupWithViewPager();
    }
    private void init(){
        wareBack = findViewById(R.id.wareback);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        change = findViewById(R.id.changeBtn);

        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        wareBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setupWithViewPager(){
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new WareListOneFragment());
        mFragments.add(new WareListTwoFragment());
        mFragments.add(new WareListThreeFragment());

        mAdapter = new WareListAdapter(getSupportFragmentManager());
        mAdapter.addTitlesAndFragments(titles, mFragments);

        viewPager.setAdapter(mAdapter); // 给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager); //关联TabLayout和ViewPager

    }
}
