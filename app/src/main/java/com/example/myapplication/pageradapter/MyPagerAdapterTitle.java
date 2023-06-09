package com.example.myapplication.pageradapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

/**
 * @author: ZhangJian
 * @date: 2023/4/10 14:35
 * @description:
 */
public class MyPagerAdapterTitle extends PagerAdapter {
    private ArrayList<View> viewLists;
    private ArrayList<String> titleLists;
    public MyPagerAdapterTitle(ArrayList<View> aList) {}
    public MyPagerAdapterTitle(ArrayList<View> viewLists, ArrayList<String> titleLists) {
        this.viewLists = viewLists;
        this.titleLists = titleLists;
    }
    @Override
    public int getCount() {
        return viewLists.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleLists.get(position);
    }

}