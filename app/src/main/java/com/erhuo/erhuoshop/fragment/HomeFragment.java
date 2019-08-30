package com.erhuo.erhuoshop.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.adapter.HomeCategoryAdapter;
import com.erhuo.erhuoshop.bean.Banner;
import com.erhuo.erhuoshop.bean.HomeCategory;
import com.erhuo.erhuoshop.http.JSONTOOL;
import com.erhuo.erhuoshop.http.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {
    public static final  int UPDATE_UI = 1;

    @ViewInject(R.id.slider)
    private SliderLayout msliderLayout;
    private RecyclerView mrecyclerview;
    private ImageView homescan;
    private List<Banner> mBanner;
    private HomeCategoryAdapter mAdapter;
    private List <HashMap<String, String>> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home,container,false);
        msliderLayout = view.findViewById(R.id.slider);
        homescan = view.findViewById(R.id.home_scan);
        initSlider();
        initRecyclerView(view);
        return view;
    }
    private void initRecyclerView(View view) {
        mrecyclerview = view.findViewById(R.id.recyclerview);
        List<HomeCategory> datas = new ArrayList<>(15);
        HomeCategory category = new HomeCategory("热门活动",R.mipmap.icon_onefour,R.mipmap.icon_onesix,R.mipmap.icon_oneseven);
        datas.add(category);

        category = new HomeCategory("有利可图",R.mipmap.icon_oneone,R.mipmap.icon_onetwo,R.mipmap.icon_onesix);
        datas.add(category);
        category = new HomeCategory("品牌街",R.mipmap.icon_onefour,R.mipmap.icon_onetwo,R.mipmap.icon_oneseven);
        datas.add(category);
        category = new HomeCategory("金融街 包赚翻",R.mipmap.icon_onefive,R.mipmap.icon_onetwo,R.mipmap.icon_onesix);
        datas.add(category);
        category = new HomeCategory("超值购",R.mipmap.icon_oneone,R.mipmap.icon_onetwo,R.mipmap.icon_oneseven );
        datas.add(category);

        mAdapter = new HomeCategoryAdapter(datas,getContext());
        mrecyclerview.setAdapter(mAdapter);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }
    private void initSlider() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("method", "_ALL");
        client.post("http://172.20.10.3:8000/ad/", params, new MyTextListener(this.handler1, 3, 30));
    }

    @SuppressLint("HandlerLeak")
    Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 3:
                    list = JSONTOOL.analyze_some_json(msg.obj.toString());

                    for (int i = 0; i < list.size(); i++){
                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        textSliderView.image(list.get(i).get("imgUrl"));
                        textSliderView.description(list.get(i).get("adgoodsName"));
                        textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                        msliderLayout.addSlider(textSliderView);
                }
                    msliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    Toast.makeText(getActivity(), "信息更新成功", Toast.LENGTH_LONG).show();//信息框

                    break;
                case 30:
                    Toast.makeText(getActivity(), "信息更新失败", Toast.LENGTH_LONG).show();//信息框
//                    t.show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}