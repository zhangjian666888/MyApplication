package com.example.myapplication.activaties;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.myapplication.R;
import com.example.myapplication.pageradapter.MyPagerAdapter;

import java.util.ArrayList;

/**
 * @author: ZhangJian
 * @date: 2023/4/10 14:32
 * @description:
 */
public class RecoveryActivaty extends AppCompatActivity implements
        View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private ViewPager evaluatePage;
    private ImageView lineImage;
    private TextView evaluateTextView;
    private TextView trainTextView;
    private TextView talkTextView;
    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recovery_view);
        //初始化页面
        initViews();
    }
    private void initViews() {
        evaluatePage = (ViewPager) findViewById(R.id.evaluatePager);
        evaluateTextView = (TextView) findViewById(R.id.evaluateTextView);
        trainTextView = (TextView) findViewById(R.id.trainTextView);
        talkTextView = (TextView) findViewById(R.id.talkTextView);
        lineImage = (ImageView) findViewById(R.id.img_cursor);

        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        lineImage.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3

        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        LinearLayout inflate = (LinearLayout) mInflater.inflate(R.layout.evaluate_view, null, false);

        listViews.add(inflate);
        listViews.add(mInflater.inflate(R.layout.train_view, null, false));
        listViews.add(mInflater.inflate(R.layout.talk_view, null, false));
        evaluatePage.setAdapter(new MyPagerAdapter(listViews));
        evaluatePage.setCurrentItem(0);//设置ViewPager当前页，从0开始算
        //初始化事件
        initEvent();
    }

    private void initEvent(){
        evaluateTextView.setOnClickListener(this);
        trainTextView.setOnClickListener(this);
        talkTextView.setOnClickListener(this);

        evaluatePage.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.evaluateTextView:
                evaluatePage.setCurrentItem(0);
                break;
            case R.id.trainTextView:
                evaluatePage.setCurrentItem(1);
                break;
            case R.id.talkTextView:
                evaluatePage.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        lineImage.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }


}
