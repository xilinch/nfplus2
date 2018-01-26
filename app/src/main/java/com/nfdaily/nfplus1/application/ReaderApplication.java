package com.nfdaily.nfplus1.application;

import android.text.TextUtils;
import android.util.Log;

import com.nfdaily.nfplus1.base.BaseApplication;
import com.nfdaily.nfplus1.constant.Config;
import com.nfdaily.nfplus1.network.Http;
import com.nfdaily.nfplus1.network.okhttp.OkClient;
import com.nfdaily.nfplus1.util.AppUtil;
import com.nfdaily.nfplus1.util.UtilSp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by xilinch on 17-12-9.
 */

public class ReaderApplication extends BaseApplication {

    /**
     * 实例
     */
    private static ReaderApplication readerApplication;

    /**
     * 设备id
     */
    private String deviceId;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("my","ReaderApplication: onCreate");
        readerApplication = this;
        init();
    }


    private void init(){

        Http.initHttp(new OkClient() {
            @Override
            protected OkHttpClient getOkHttpClient() {
                // 地址列表接口的数据很大
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(3, TimeUnit.SECONDS);
                builder.readTimeout(3, TimeUnit.SECONDS);
                builder.writeTimeout(3, TimeUnit.SECONDS);
                return builder.build();
            }
        });

    }

    /**
     * 获取应用实例
     * @return
     */
    public static ReaderApplication getInstance(){
        if(readerApplication == null){
            readerApplication = new ReaderApplication();
        }
        return readerApplication;
    }

    /**
     * 获取设备id
     * @return
     */
    public String getDeviceId() {
        if(TextUtils.isEmpty(deviceId)){
            deviceId = AppUtil.getMyUUID();
        }
        return deviceId;
    }


}
