package com.bw.jiguoshuai202003020;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ViewPager vp;

    private ArrayList<Fragment> list=new ArrayList<>();
    private ArrayList<String> list1=new ArrayList<>();
    private TabLayout tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = findViewById(R.id.vp);
        tb = findViewById(R.id.tb);

        Fragment_One one = new Fragment_One();
        Fragment_Two two = new Fragment_Two();
        Fragment_Three three = new Fragment_Three();
        Fragment_Four four = new Fragment_Four();
        Fragment_Five five = new Fragment_Five();
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        list1.add("首页");
        list1.add("商品页");
        list1.add("主页");
        list1.add("详情");
        list1.add("我的");
        tb.addTab(tb.newTab().setText(list1.get(0)));
//        2、	创建Fragment实现和底部导航联动
        tb.setupWithViewPager(vp);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

    }



    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list1.get(position);
        }
    }
}
