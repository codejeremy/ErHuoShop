package com.erhuo.erhuoshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.adapter.CategoryAdapter;
import com.erhuo.erhuoshop.adapter.CategoryNewAdapter;
import com.erhuo.erhuoshop.adapter.CgAdapter;
import com.erhuo.erhuoshop.adapter.WaresAdapter;
import com.erhuo.erhuoshop.bean.Banner;
import com.erhuo.erhuoshop.bean.Category;
import com.erhuo.erhuoshop.bean.NewCatrgory;
import com.erhuo.erhuoshop.http.OkHttpHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class CatagoryFragment extends Fragment {

    @ViewInject(R.id.recyclerview_category)
    private RecyclerView mRecyclerView;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private WaresAdapter mWaresAdapter;
    @ViewInject(R.id.recyclerview_wares)
    private RecyclerView mRecyclerviewWares;
//    @ViewInject(R.id.refresh_layout)
    private MaterialRefreshLayout mRefreshLaout;
    @ViewInject(R.id.slider)
    private SliderLayout mSliderLayout;
    private CategoryAdapter mCategoryAdapter;
    private CgAdapter cgAdapter = null;
    private int currPage = 1;
    private int totalPage = 1;
    private int pageSize = 10;
    private long category_id = 0;
    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;
    private int state = STATE_NORMAL;
    private List<Banner> mBanner;

    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Category> categoryList = new ArrayList<>();
    private NewCatrgory[] newCatrgories2 = {
            new NewCatrgory(R.mipmap.icon_warelist2,"穿黑色篮球短裤","200"),
            new NewCatrgory(R.mipmap.icon_warelist3,"李宁，韦德之道篮球短裤","300"),
            new NewCatrgory(R.mipmap.icon_warelist4,"李宁，宽松篮球短裤","400"),
            new NewCatrgory(R.mipmap.icon_warelist5,"韦德之道专业定制球衣","300"),
            new NewCatrgory(R.mipmap.icon_warelist6,"专业定制球衣30年","300"),
            new NewCatrgory(R.mipmap.icon_warelist7,"回力花色帆布鞋","300"),
            new NewCatrgory(R.mipmap.icon_warelist8,"橡胶软底，想你所想","300"),
            new NewCatrgory(R.mipmap.icon_warelist9,"女子运动鞋，畅轻跑步","300"),
            new NewCatrgory(R.mipmap.icon_warelist10,"佳能相机，感动常在","300"),
            new NewCatrgory(R.mipmap.icon_warelist11,"OPPOR99，你值得拥有","300"),
            new NewCatrgory(R.mipmap.icon_warelist12,"戴尔笔记本电脑","300")

    };
    private List<NewCatrgory>categorylist2 = new ArrayList<>();
    private CategoryNewAdapter adapter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catagory, container, false);
        ViewUtils.inject(this, view);
        initCate();
        //实现一级菜单
        initCategory();
        initCategoryTwo();
        swipeRefreshLayout = view.findViewById(R.id.swipe_refrsh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
        showSliderViews();
        return view;
    }
    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        initCategoryTwo();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    private void initCategory(){
            Category one = new Category("热门推荐");
            categoryList.add(one);
            Category two = new Category("品牌男装");
            categoryList.add(two);
            Category three = new Category("内衣配饰");
            categoryList.add(three);
            Category four = new Category("家用电器");
            categoryList.add(four);
            Category five = new Category("电脑办公");
            categoryList.add(five);
            Category six = new Category("手机数码");
            categoryList.add(six);
            Category seven = new Category("母婴频道");
            categoryList.add(seven);
            Category eight = new Category("图书管理");
            categoryList.add(eight);
            Category nine = new Category("家居家纺");
            categoryList.add(nine);
            Category ten = new Category("居家生活");
            categoryList.add(ten);
            Category eleven = new Category("个性化妆");
            categoryList.add(eleven);
            Category twelve = new Category("鞋靴箱包");
            categoryList.add(twelve);
            Category thirdteen = new Category("奢侈礼品");
            categoryList.add(thirdteen);
            Category fourteen = new Category("珠宝饰品");
            categoryList.add(fourteen);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(layoutManager);
            CgAdapter adapter = new CgAdapter(categoryList);
            mRecyclerView.setAdapter(adapter);
    }
    private void showSliderViews(){
        TextSliderView textSliderView1 = new TextSliderView(this.getActivity());
        textSliderView1.description("库里球衣")
                .image(R.mipmap.cloth);

        textSliderView1.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(CatagoryFragment.this.getActivity(),"库里球衣",Toast.LENGTH_LONG).show();
            }
        });

        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2
                .description("AJ32")
                .image(R.mipmap.icon_adcategory);

        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(CatagoryFragment.this.getActivity(),"AJ32",Toast.LENGTH_LONG).show();
            }
        });

        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
        textSliderView3
                .description("福特野马")
                .image(R.mipmap.icon_futer);

        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(CatagoryFragment.this.getActivity(),"福特野马",Toast.LENGTH_LONG).show();
            }
        });


        mSliderLayout.addSlider(textSliderView1);
        mSliderLayout.addSlider(textSliderView2);
        mSliderLayout.addSlider(textSliderView3);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        轮播动画报错
//        msliderLayout.setPagerTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(2000);
        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG,"onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG,"onPageSelected");

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG,"onPageScrollStateChanged");

            }
        });
    }
    private void initCategoryTwo(){
        categorylist2.clear();
        for (int i = 0; i <30 ; i++) {
            Random random = new Random();
            int index = random.nextInt(newCatrgories2.length);
            categorylist2.add(newCatrgories2[index]);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerviewWares.setLayoutManager(layoutManager);
        adapter= new CategoryNewAdapter(categorylist2);
        mRecyclerviewWares.setAdapter(adapter);
    }
    private void initCate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String url = "http://10.130.150.80:8000/Goods/?format=json";
                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = client.newCall(request).execute();
                    String json = response.body().string();
                    Log.d("json", json);
                    Gson gson = new Gson();
                    mBanner = gson.fromJson(json, new TypeToken<List<Banner>>() {
                    }.getType());
                    for (Banner banner : mBanner
                            ) {
                        Log.d("Homefragment", "goodsName is" + banner.getName());
                        Log.d("Homefragment", "goodsDesc is" + banner.getDescription());
                        Log.d("Homefragment", "goodsPrice is" + banner.getId());
                        Log.d("Homefragment", "goodsImgurl is" + banner.getImgUrl());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}