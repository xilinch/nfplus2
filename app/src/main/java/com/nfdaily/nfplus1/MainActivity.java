package com.nfdaily.nfplus1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nfdaily.nfplus1.base.BaseActivity;
import com.nfdaily.nfplus1.fragment.MyFragment;
import com.nfdaily.nfplus1.fragment.home.HomeActivitiesFragment;
import com.nfdaily.nfplus1.fragment.home.HomeCityfragment;
import com.nfdaily.nfplus1.fragment.home.HomeMainFragment;
import com.nfdaily.nfplus1.fragment.home.HomeServiceFragment;
import com.nfdaily.nfplus1.network.BaseRespHandler;
import com.nfdaily.nfplus1.network.Http;
import com.nfdaily.nfplus1.network.ReqInfo;
import com.nfdaily.nfplus1.network.RespInfo;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.rb_new)
    RadioButton rbNew;
    @InjectView(R.id.rb_city)
    RadioButton rbCity;
    @InjectView(R.id.rb_service)
    RadioButton rbService;
    @InjectView(R.id.rb_activity)
    RadioButton rbActivity;
    @InjectView(R.id.rb_my)
    RadioButton rbMy;
    @InjectView(R.id.rg_tab)
    RadioGroup rgTab;
    @InjectView(R.id.container)
    LinearLayout container;
    /**
     * 首页
     */
    private HomeMainFragment homeMainFragment;

    /**
     * 城市
     */
    private HomeCityfragment homeCityfragment;

    /**
     * 服务
     */
    private HomeServiceFragment homeServiceFragment;

    /**
     * 活动
     */
    private HomeActivitiesFragment homeActivitiesFragment;

    /**
     * 我的
     */
    private MyFragment myFragment;

    /**
     * 管理
     */
    private FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initFragmentTransaction();
    }

    private void request() {

        Http.get("http://www.baidu.com", new BaseRespHandler() {
            @Override
            public Object onParse2Model(ReqInfo reqInfo, RespInfo respInfo) {
                if (respInfo != null) {
                    String content = respInfo.getDataString();
                    Log.i("my", "content：" + content);

                }
                return super.onParse2Model(reqInfo, respInfo);
            }

            @Override
            public void onFailure(ReqInfo reqInfo, RespInfo respInfo) {
                super.onFailure(reqInfo, respInfo);
            }
        });
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化
     */
    private void initFragmentTransaction(){
        if(fragmentTransaction == null){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
        }
        if(homeMainFragment == null){
            homeMainFragment = new HomeMainFragment();
        }
        if(fragmentTransaction == null){
            initFragmentTransaction();
        }

        fragmentTransaction.add(R.id.container,homeMainFragment,"14");
    }

    public static void launchActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);

    }

    @OnClick(R.id.rb_new)
    public void onRbNewClicked() {
        if(homeMainFragment == null){
            homeMainFragment = new HomeMainFragment();
        }
        if(fragmentTransaction == null){
            initFragmentTransaction();
        }

        fragmentTransaction.add(R.id.container,homeMainFragment,"14");

    }

    @OnClick(R.id.rb_city)
    public void onRbCityClicked() {
        if(homeCityfragment == null){
            homeCityfragment = new HomeCityfragment();
        }
        if(fragmentTransaction == null){
            initFragmentTransaction();
        }

        fragmentTransaction.add(R.id.container,homeCityfragment,"15");
    }

    @OnClick(R.id.rb_service)
    public void onRbServiceClicked() {
        if(homeServiceFragment == null){
            homeServiceFragment = new HomeServiceFragment();
        }
        if(fragmentTransaction == null){
            initFragmentTransaction();
        }

        fragmentTransaction.add(R.id.container,homeServiceFragment,"16");
    }

    @OnClick(R.id.rb_activity)
    public void onRbActivityClicked() {
        if(homeActivitiesFragment == null){
            homeActivitiesFragment = new HomeActivitiesFragment();
        }
        if(fragmentTransaction == null){
            initFragmentTransaction();
        }

        fragmentTransaction.add(R.id.container,homeActivitiesFragment,"17");
    }

    @OnClick(R.id.rb_my)
    public void onRbMyClicked() {
        if(myFragment == null){
            myFragment = new MyFragment();
        }
        if(fragmentTransaction == null){
            initFragmentTransaction();
        }

        fragmentTransaction.add(R.id.container,myFragment,"18");
    }
}
