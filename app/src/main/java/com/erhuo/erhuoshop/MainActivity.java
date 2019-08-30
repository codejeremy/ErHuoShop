package com.erhuo.erhuoshop;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.erhuo.erhuoshop.bean.Tab;
import com.erhuo.erhuoshop.fragment.CartFragment;
import com.erhuo.erhuoshop.fragment.CatagoryFragment;
import com.erhuo.erhuoshop.fragment.HomeFragment;
import com.erhuo.erhuoshop.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LayoutInflater mInflater;
    private FragmentTabHost mTabhost;
    private ImageView img = null;
    private TextView text = null;
    private List<Tab>mTabs = new ArrayList<>(4);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();

    }

    private void initTab() {
        Tab tab_home = new Tab(R.string.home,R.drawable.selector_icon_home,HomeFragment.class);
        Tab tab_catagory = new Tab(R.string.catagory,R.drawable.selector_icon_discovery,CatagoryFragment.class);
        Tab tab_cart = new Tab(R.string.cart,R.drawable.selector_icon_cart,CartFragment.class);
        Tab tab_mine = new Tab(R.string.mine,R.drawable.selector_icon_user,MineFragment.class);

        mTabs.add(tab_home);
        mTabs.add(tab_catagory);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);
        mInflater = LayoutInflater.from(this);
        mTabhost = this.findViewById(R.id.tabhost);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab : mTabs){
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec,tab.getFragment(),null);
        }
        //mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //mTabhost.setCurrentTab(0);
    }
    private View buildIndicator(Tab tab){
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        img =view.findViewById(R.id.icon_tab);
        text = view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(getString(tab.getTitle()));
        return view;
    }
}
