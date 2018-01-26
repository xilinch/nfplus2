package com.nfdaily.nfplus1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.nfdaily.nfplus1.application.ReaderApplication;

import java.net.PortUnreachableException;

/**
 * Created by xilinch on 17-12-26.
 */

public class UtilSp {

    /**
     * 新版本的缓存文件
     */
    private static final String S_DEFAULT_FILE = "nf_saveFile";

    /**
     * 配置接口
     */
    public static final String SPLASH_DATA_KEY = "splash data key";
    /**
     * 上一个应用版本
     */
    public static final String APP_LAST_VERSION_CODE = "app last version code";
    /**
     * 配置广告
     */
    public static final String SPLASH_AD_KEY = "splash ad key";

    /**
     * 默认栏目
     */
    public static final String DEFAULT_COLUMNS_KEY = "default column key";
    /**
     * 更多栏目
     */
    public static final String MORE_COLUMNS_KEY = "more columns key";
    /**
     * 电子报栏目版本号
     */
    public static final String EPAPER_COLUMNS_KEY = "epaper columns key";
    /**
     *
     * 城市栏目
     */
    public static final String CITY_COLUMNS_KEY = "city columns key";
    /**
     * 默认栏目版本号
     */
    public static final String DEFAULT_COLUMNS_VERSION_KEY = "default column new version key";
    /**
     * 更多栏目版本号
     */
    public static final String MORE_COLUMNS_VERSION_KEY = "more columns new version key";
    /**
     * 城市栏目版本号
     */
    public static final String CITY_COLUMNS_VERSION_KEY = "city columns new version key";
    /**
     * 新闻数字报 版本号
     */
    public static final String EPAPER_COLUMN_VERSION_KEY = "epaper column version key";


    /**
     * 全部使用string类型
     * @param key
     * @param value
     */
    private static void setString(String key, String value){
        SharedPreferences sharedPreferences = ReaderApplication.getInstance().getSharedPreferences(S_DEFAULT_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 全部使用string类型
     * @param key
     * @param defaultValue
     * @return
     */
    private static String getString(String key, String defaultValue){
        String value = defaultValue;
        SharedPreferences sharedPreferences = ReaderApplication.getInstance().getSharedPreferences(S_DEFAULT_FILE, Context.MODE_PRIVATE);
        value = sharedPreferences.getString(key,defaultValue);
        return value;
    }

    /**
     * 设置配置内容
     */
    public static void setSplashData(String data){
        setString(SPLASH_DATA_KEY, data);
    }

    /**
     * 获取配置内容
     * @return
     */
    public static String getSplashData(){
        return getString(SPLASH_DATA_KEY, null);
    }

    /**
     * 设置广告内容
     * @param data
     */
    public static void setSplashAd(String data){
        if(data == null){
            data = "";
        }
        setString(SPLASH_AD_KEY,data);
    }

    /**
     * 获取广告内容
     * @return
     */
    public static String getSplashAd(){
        return getString(SPLASH_AD_KEY,null);
    }

    /**
     * 设置版本
     * @param versionCode
     */
    public static void setAppLastVersionCode(String versionCode){
        if(versionCode == null){
            versionCode = "";
        }
        setString(APP_LAST_VERSION_CODE, versionCode);
    }

    /**
     * 获取上一个版本code
     * @return
     */
    public static String getAppLastVersionCode(){
        String versionCode = "";
        versionCode = getString(APP_LAST_VERSION_CODE,"");
        return versionCode;
    }


    /**
     * 设置缺省频道内容
     * @param data
     */
    public static void setdefaultColumn(String data){
        if(TextUtils.isEmpty(data)){
            data = "";
        }
        setString(DEFAULT_COLUMNS_KEY,data);
    }

    /**
     * 设置更多频道内容
     * @param data
     */
    public static void setMoreColumn(String data){
        if(TextUtils.isEmpty(data)){
            data = "";
        }
        setString(MORE_COLUMNS_KEY,data);
    }

    /**
     * 设置城市频道内容
     * @param data
     */
    public static void setCityColumn(String data){
        if(TextUtils.isEmpty(data)){
            data = "";
        }
        setString(CITY_COLUMNS_KEY,data);
    }

    /**
     * 设置电子报频道内容
     * @param data
     */
    public static void setEpaperColumn(String data){
        if(TextUtils.isEmpty(data)){
            data = "";
        }
        setString(EPAPER_COLUMNS_KEY,data);
    }

    /**
     * 获取缺省频道内容
     *
     */
    public static String getdefaultColumn(){
        String data = getString(DEFAULT_COLUMNS_KEY,"");
        return data;
    }

    /**
     * 设置更多频道内容
     */
    public static String getMoreColumn(){
        String data = getString(MORE_COLUMNS_KEY,"");
        return data;
    }

    /**
     * 设置城市频道内容
     */
    public static String getCityColumn(){
        String data = getString(CITY_COLUMNS_KEY,"");
        return data;
    }

    /**
     * 设置电子报频道内容
     */
    public static String getEpaperColumn(){
        String data = getString(EPAPER_COLUMNS_KEY,"");
        return data;
    }

    /**
     * 设置默认栏目版本号
     */
    public static void setDefaultColumnsVersion(String version){
        if(TextUtils.isEmpty(version)){
            version = "";
        }
        setString(DEFAULT_COLUMNS_VERSION_KEY,version);
    }

    /**
     * 设置更多栏目版本号
     */
    public static void setMoreColumnsVersion(String version){
        if(TextUtils.isEmpty(version)){
            version = "";
        }
        setString(MORE_COLUMNS_VERSION_KEY,version);
    }

    /**
     * 设置城市栏目版本号
     */
    public static void setCityColumnsVersion(String version){
        if(TextUtils.isEmpty(version)){
            version = "";
        }
        setString(CITY_COLUMNS_VERSION_KEY,version);
    }

    /**
     * 设置版本号
     */
    public static void setEpaperColumnsVersion(String version){
        if(TextUtils.isEmpty(version)){
            version = "";
        }
        setString(EPAPER_COLUMN_VERSION_KEY,version);
    }

    /**
     * 获取默认栏目版本号
     */
    public static String getDefaultColumnsVersion(){

        return getString(DEFAULT_COLUMNS_VERSION_KEY,"");
    }

    /**
     * 获取更多栏目版本号
     */
    public static String getMoreColumnsVersion(){
        return getString(MORE_COLUMNS_VERSION_KEY,"");
    }

    /**
     * 获取城市栏目版本号
     */
    public static String getCityColumnsVersion(){
        return getString(CITY_COLUMNS_VERSION_KEY,"");
    }

    /**
     * 获取数字报版本号
     */
    public static String getEpaperColumnsVersion(){
        return getString(EPAPER_COLUMN_VERSION_KEY,"");
    }

}
