package com.nfdaily.nfplus1.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nfdaily.nfplus1.MainActivity;
import com.nfdaily.nfplus1.R;
import com.nfdaily.nfplus1.base.BaseActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xilinch on 17-12-9.
 */

public class GuidActivity extends BaseActivity {

    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.imgbutton_help_finish)
    Button imgbuttonHelpFinish;

    private ArrayList<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        ButterKnife.inject(this);
        initView();
        initListener();
        initData();
    }

    @Override
    protected void initView() {
        views = new ArrayList<>();
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.mipmap.help1);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.mipmap.help2);
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.mipmap.help3);
        views.add(imageView1);
        views.add(imageView2);
        views.add(imageView3);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(views);
        viewPager.setAdapter(adapter);

    }


    @Override
    protected void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("my","onPageSelected:" + position);
                if (position == views.size() - 1) {
                    imgbuttonHelpFinish.setVisibility(View.VISIBLE);
                } else {
                    imgbuttonHelpFinish.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        imgbuttonHelpFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.launchActivity(GuidActivity.this,null);
            }
        });
    }

    @Override
    protected void initData() {

    }


    private class MyViewPagerAdapter extends PagerAdapter{

        private ArrayList<View> views;

        public MyViewPagerAdapter(ArrayList<View> views){
            this.views = views;
            if (this.views == null){
                this.views = new ArrayList<>();
            }
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            (container).addView(views.get(position));
            return views.get(position);
        }


    }



    protected static void launchActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GuidActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
