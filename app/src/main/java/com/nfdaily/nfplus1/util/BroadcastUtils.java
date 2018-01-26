package com.nfdaily.nfplus1.util;

import android.content.Context;
import android.content.Intent;

import com.nfdaily.nfplus1.constant.Constants;


public class BroadcastUtils {

    /**
     * 发送登录的用户推送信息
     *
     * @param context
     */
    public static void sendLoginBroadCast(Context context) {
        //用户登录发送推送数据信息
//        String key = Constants.KEY_SEND_PUSH_INFO+ReaderApplication.getInstace().iPushManager.getPushID();
//        if (Account.checkAccountInfo(context) != null) {
//            key = key + Account.checkAccountInfo(context).getUserId();
//        }
//        UtilSharePreference.SharedPreferencesUtil sharedPreferencesUtil = new UtilSharePreference().getSharedPreferencesUtil();
//        if (!sharedPreferencesUtil.getBoolean(key, false)) {
//            Intent intent = new Intent();
//            intent.setAction(com.nfdaily.nfplus.common.Constants.LOGIN_BROADCAST);
//            context.sendBroadcast(intent);
//            ReaderApplication.getInstace().setPushTokenGot(true);
//        }
    }

    /**
     * 发送刷新用户所有的南方号订阅信息
     *
     * @param context
     */
    public static void sendUpdateUserSubInfoBroadCast(Context context) {
//        Intent intent = new Intent();
//        intent.setAction(com.nfdaily.nfplus.common.Constants.UPDATE_SUB_INFO);
//        context.sendBroadcast(intent);
    }


}
