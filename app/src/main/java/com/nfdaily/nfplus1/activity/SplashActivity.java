package com.nfdaily.nfplus1.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.network.RequestUtil;
import com.bumptech.glide.Glide;
import com.nfdaily.nfplus1.MainActivity;
import com.nfdaily.nfplus1.R;
import com.nfdaily.nfplus1.application.ReaderApplication;
import com.nfdaily.nfplus1.base.BaseActivity;
import com.nfdaily.nfplus1.bean.Account;
import com.nfdaily.nfplus1.bean.SplashAD;
import com.nfdaily.nfplus1.constant.ChannelConfig;
import com.nfdaily.nfplus1.constant.Config;
import com.nfdaily.nfplus1.constant.Constants;
import com.nfdaily.nfplus1.network.BaseRespHandler;
import com.nfdaily.nfplus1.network.Http;
import com.nfdaily.nfplus1.network.ReqInfo;
import com.nfdaily.nfplus1.network.RespInfo;
import com.nfdaily.nfplus1.util.AppUtil;
import com.nfdaily.nfplus1.util.BroadcastUtils;
import com.nfdaily.nfplus1.util.FileUtils;
import com.nfdaily.nfplus1.util.UtilNetwork;
import com.nfdaily.nfplus1.util.UtilSp;
import com.nfdaily.nfplus1.util.UtilString;
import com.nfdaily.nfplus1.util.UtilTemplate;
import com.nfdaily.nfplus1.util.UtilToast;
import com.nfdaily.nfplus1.util.UtilsDensity;
import com.nfdaily.nfplus1.view.CircleTextProgressbar;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xilinch on 17-12-8.
 */

public class SplashActivity extends BaseActivity {


    @InjectView(R.id.image)
    ImageView ivAd;
    @InjectView(R.id.fl_top)
    FrameLayout flTop;
    @InjectView(R.id.tv_tag)
    TextView tv_tag;
    @InjectView(R.id.pro_ad_skip)
    CircleTextProgressbar pro_ad_skip;
    /**
     * 广告内容
     */
    private String splashContent;
    /**
     * 模型转化
     */
    private SplashAD splashAD;

    /**
     * 是否推送
     */
    private boolean isTuisong;
    /**
     * 是否广告
     */
    private boolean isAd;

    /**
     * 没配置数据
     */
    private boolean noSplashData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        initView();
        initListener();
        initData();
    }

    @Override
    protected void initView() {
        flTop = (FrameLayout) findViewById(R.id.fl_top);
        flTop.getLayoutParams().height = (int) (UtilsDensity.getScreenWidth(this) * 36.0f / 25f);
    }

    @Override
    protected void initListener() {
        ivAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击进入下一个页面
                GuidActivity.launchActivity(SplashActivity.this, null);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        splashContent = UtilSp.getSplashData();
        if (TextUtils.isEmpty(splashContent)) {
            //为空 没有配置
            noSplashData = true;
        } else {
            noSplashData = false;
            Config.initSplashConfig();
            displayAd();
            downLoadTemplate();
        }
        requestConfig();
    }

    /**
     * 显示广告
     */
    private void displayAd() {
        if (!TextUtils.isEmpty(splashContent)) {
            //根据广告内容显示
            splashAD = SplashAD.getSplashFromString(splashContent);
            if (splashAD != null) {
                //展示广告
                if (pro_ad_skip != null) {
                    pro_ad_skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pro_ad_skip.stop();
                            pro_ad_skip.setVisibility(View.GONE);
                            intoAPP();
                        }
                    });
                }
                ivAd.setVisibility(View.VISIBLE);
                ivAd.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getApplicationContext())
                        .load(splashAD.getFileUrl())
                        .dontAnimate().into(ivAd);
                tv_tag = (TextView) findViewById(R.id.tv_tag);
                String label = splashAD.getLabel();
                if (label != null) {
                    label = label.trim();
                }
                if (!TextUtils.isEmpty(label) && "广告".equals(label)) {
                    tv_tag.setVisibility(View.VISIBLE);
                }
                if (!UtilString.isBlank(splashAD.getWebLinkURL()) || (splashAD.getArticle() != null && splashAD.getArticle().getColID() > 0)) {
                    ivAd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isAd = true;
                            intoAPP();
                        }
                    });
                }
                // 广告页显示时长
                int pageDelay = splashAD.getResidenceTime() * 1000;
                if (pro_ad_skip.getVisibility() != View.VISIBLE) {
                    pro_ad_skip.setVisibility(View.VISIBLE);
                }
                if (pro_ad_skip != null) {
                    pro_ad_skip.setOnProgressFinishListener(new CircleTextProgressbar.OnProgressFinishListener() {
                        @Override
                        public void progressFinish() {
                            intoAPP();
                        }
                    });
                }
                pro_ad_skip.start(pageDelay);
            }
        }
    }

    /**
     * 下载模板
     */
    private void downLoadTemplate() {
        if (!TextUtils.isEmpty(splashContent)) {
            try {
                UtilTemplate.downLoadTemplate();

            } catch (Exception exception) {
                exception.printStackTrace();

            }

        }
    }

    /**
     * 获取配置
     */
    private void requestConfig() {
        if (UtilNetwork.isNetworkAvailable(this)) {
            String url = Config.getHostUrl(Config.URL_APP_CONFIG_V3) + "?appID=2&version=" + AppUtil.getVersionName(this);
            Log.i("my", "requestConfig：");
            Http.get(url, new BaseRespHandler() {
                @Override
                public Object onParse2Model(ReqInfo reqInfo, RespInfo respInfo) {
                    if (respInfo != null) {
                        String content = respInfo.getDataString();
                        Log.i("my", "content：" + content);
                        UtilSp.setSplashData(content);
                        Config.initSplashConfig();
                        ChannelConfig.setChannels();
                        if (noSplashData) {
                            //需要重新设置配置数据
                            displayAd();
                            downLoadTemplate();
                        }
                    }
                    return super.onParse2Model(reqInfo, respInfo);
                }

                @Override
                public void onFailure(ReqInfo reqInfo, RespInfo respInfo) {
                    super.onFailure(reqInfo, respInfo);
                }
            });
        } else {
            //donothing
            if(noSplashData){
                configError();
            }
        }

    }

    /**
     * 提示没有配置
     */
    private void configError(){
        UtilToast.toastShow("网络异常，请检查网络后重试！");
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        am.killBackgroundProcesses(getPackageName());
        finish();
    }


    /**
     * 进入下一页，类型有三种 引导页、首页（广告），首页（推送）
     */
    private void intoAPP() {
        String showHelp = UtilSp.getAppLastVersionCode();
        String currentVersionCode = AppUtil.getVersionCode(this) + "";
        if (!currentVersionCode.equals(showHelp)) {
            //显示引导页
            Intent help = new Intent(this, GuidActivity.class);
            Account account = new Account().getFromCache();
            if (account != null && TextUtils.isEmpty(account.getUserUUID())) {
                // 3.9版本第一次启动时 检查删除原来的登录状态。
                String folderName = Constants.CACHE_FOLDER + File.separator + "Account";
                String fileName = "1_" + "nfaccount.txt";
                FileUtils.delete(this, folderName, fileName, FileUtils.STORE_PLACE_PHONE);
                //TODO 待完善，里面逻辑暂时注释
                BroadcastUtils.sendUpdateUserSubInfoBroadCast(this);
            }
            startActivity(help);
            UtilSp.setAppLastVersionCode(currentVersionCode);
            finish();
        } else {
            //进入主页
            Intent intent = new Intent();
            intent.setClass(getApplication().getApplicationContext(), MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("isAD", isAd);
            if (isAd && splashAD != null) {
                if ("0".equals(splashAD.getJumpType())) {
                    //外链广告
                    bundle.putString("adURL", splashAD.getWebLinkURL());
                } else if ("1".equals(splashAD.getJumpType())) {
                    if (splashAD.getArticle() != null) {
                        bundle.putSerializable("article", splashAD.getArticle());
                    }
                } else if ("2".equals(splashAD.getJumpType())) {
                    if (splashAD.getArticle() != null) {
                        bundle.putSerializable("article", splashAD.getArticle());
                    }

                } else {
                    //DO NOTHING
                }

            }
            if (isTuisong) {
                //TODO 推送的数据

            }
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }


    protected static void launchActivity(Context context, Bundle bundle) {

    }
}
