package com.nfdaily.nfplus1.util;

import android.content.Context;
import android.util.Log;
import com.android.network.RequestUtil;
import com.nfdaily.nfplus1.application.ReaderApplication;
import com.nfdaily.nfplus1.bean.Account;
import com.nfdaily.nfplus1.constant.Config;

import java.util.HashMap;

/**
 * 数据采集工具类
 */
public class UtilsDataCollect {
    //采集API专用
    private static HashMap<String, String> params = new HashMap<>();
    //手机接口专用
    private static HashMap<String, String> mobileParams = new HashMap<>();
    /**
     * 大数据接口采集地址(采集API)
     */
    private static String URL_DATA_COLLECT;

    /**
     * 大数据接口采集地址(手机API)
     */
    private static String URL_MOBILE_DATA_COLLECT;

    static {
        Context context = ReaderApplication.getInstance();
//        LocationUtil locationUtil = ReaderApplication.getInstance().getLocationUtil();

        URL_DATA_COLLECT = Config.getHostCaijiUrl() + "analytics/sendRecord";
        URL_MOBILE_DATA_COLLECT = Config.getHostUrl(Config.COLLECTION_USERBEHAVIORDATA);

        //设备号
        params.put("deviceid", AppUtil.getMyUUID());
        mobileParams.put("nfdeviceid", AppUtil.getMyUUID());

        //登录用户Id
        Account account = new Account().getFromCache();
        if (account != null) {
            params.put("userid", account.getUserId());
            mobileParams.put("nfuserid", account.getUserId());
        }

//        //IP地址
//        if (!StringUtils.isBlank(AppUtil.getIPAddress(context))) {
//            params.put("ip", AppUtil.getIPAddress(context));
//            mobileParams.put("nfip", AppUtil.getIPAddress(context));
//        }
//
//        //经度
//        if (locationUtil != null && locationUtil.getLongitude() != 0) {
//            params.put("longitude", locationUtil.getLongitude() + "");
//            mobileParams.put("nflongitude", locationUtil.getLongitude() + "");
//        }
//
//        //纬度
//        if (locationUtil != null && locationUtil.getLatitude() != 0) {
//            params.put("latitude", locationUtil.getLatitude() + "");
//            mobileParams.put("nflatitude", locationUtil.getLatitude() + "");
//        }
//
//        //城市
//        if (locationUtil != null && !StringUtils.isBlank(locationUtil
//                .getLocCityName())) {
//            params.put("area", locationUtil.getLocCityName());
//            mobileParams.put("nfarea", locationUtil.getLocCityName());
//        }
//
//        LogUtil.e("埋点参数1:",params.toString());
//        LogUtil.e("埋点参数2:",mobileParams.toString());
    }

    /**
     * 数据类型映射关系
     */
    public enum DATATYPE {
        /**
         * 推荐列表中对文章点击（×）按钮
         */
        ACTION_DELETE_RECOMMEND_ARTICLE(1000001),
        /**
         * 文章喜欢
         */
        ACTION_LIKE_ARTICLE(1000002),
        /**
         * 点击搜索按钮时触发
         */
        ACTION_SEARCH_CLICK(1000003),
        /**
         * 搜索结果点击时触发
         */
        ACTION_SEARCH_RESULT_CLICK(1000004),
        /**
         * 打开新闻
         */
        ACTION_ARTICLE_LIST_CLICK(1000005),
        /**
         * 退出新闻
         */
        ACTION_EXIST_ARTICLE_DETAIL(1000006),
        /**
         * 收藏
         */
        ACTION_COLLECTION(1000007),
        /**
         * 取消收藏
         */
        ACTION_CANCLE_COLLECTION(1000008),
        /**
         * 分享操作
         */
        ACTION_SHARE(1000009),
        /**
         * 其他操作
         */
        ACTION_OTHER(1009999);

        private int value;

        DATATYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 发送数据请求
     * 采集API
     *
     * @param otherParams
     */
    public static void sendData(HashMap<String, String> otherParams) {
        if(params!=null) {
            params.putAll(otherParams);
            Log.e("数据采集API参数:",params.toString());
            RequestUtil.httpGet(ReaderApplication.getInstance(), URL_DATA_COLLECT, params, null);
        }
    }

    /**
     * 发送数据请求
     * 手机接口间接采集API
     *
     * @param otherParams
     */
    public static void sendMobileData(HashMap<String, String> otherParams) {
        if(params!=null) {
            mobileParams.putAll(otherParams);
            Log.e("数据采集手机接口参数:",mobileParams.toString());
            RequestUtil.httpGet(ReaderApplication.getInstance(), URL_MOBILE_DATA_COLLECT, mobileParams, null);
        }
    }

}
