package com.nfdaily.nfplus1.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import com.nfdaily.nfplus1.constant.Constants;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class AppUtil {

    public static final String BRAND_VIVO = "VIVO";

    /**
     * 获取当前App的版本名
     */
    public static String getVersionName(Context context) {
        try {
            if (!TextUtils.isEmpty(Constants.VERSION_NAME)) {
                return Constants.VERSION_NAME;
            }

            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            Constants.VERSION_NAME = info.versionName;
            return Constants.VERSION_NAME;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    /**
     * 获取版本号(内部识别号)
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 获取UUID
     *
     * @return
     */
    public static String getMyUUID() {
        String uniqueId = null;
        String var1 = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;

        try {
            uniqueId = Build.class.getField("SERIAL").get(null).toString();
            return (new UUID((long) var1.hashCode(), (long) uniqueId.hashCode())).toString();
        } catch (Exception var3) {
            uniqueId = "serial";
            return (new UUID((long) var1.hashCode(), (long) uniqueId.hashCode())).toString();
        }
    }

    /**
     * 收起通知栏
     *
     * @param context
     */
    public static void collapseStatusBar(Context context) {
        if (context == null) {
            return;
        }
        try {
            Object statusBarManager = context.getSystemService("statusbar");
            Method collapse;

            if (Build.VERSION.SDK_INT <= 16) {
                collapse = statusBarManager.getClass().getMethod("collapse");
            } else {
                collapse = statusBarManager.getClass().getMethod("collapsePanels");
            }
            collapse.invoke(statusBarManager);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    /**
     * 判断APP是否在前台运行
     */
    public static boolean isAppOnForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String currentPackageName = "";
        if (am.getRunningTasks(1).size() > 0) {
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            currentPackageName = cn.getPackageName();
        }
        return !TextUtils.isEmpty(currentPackageName)
                && currentPackageName.equals(context.getPackageName());
    }

    /**
     * 判断APP是否在前台运行
     */
    public static boolean isAppOnForeground1(Context context) {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String currentPackageName = "";
        int numActivities = 0;
        if (am.getRunningTasks(1).size() > 0) {
            numActivities = am.getRunningTasks(1).get(0).numActivities;
        }
        return numActivities > 1;
    }

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(o);
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return statusBarHeight;
        }

    }


    /**
     * 将系统的编译版本 转化为double类型的
     * @return
     */
    public static final double parseVersion2Double(){
        double version = 0;
        String strVer = Build.VERSION.RELEASE;
        int nIndex = strVer.indexOf('.', strVer.indexOf('.') + 1);
        if (-1 != nIndex) {
            strVer = strVer.substring(0, nIndex);
        }
        if(!TextUtils.isEmpty(strVer)){
            strVer = strVer.toLowerCase();
            if(strVer.contains("android")){
                strVer = strVer.replace("android","");
            }
        }
        try{
            version = Double.parseDouble(strVer);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {

            return version;
        }
    }

    /**
     * 获取当前手机的IP地址
     * @param context
     * @return
     */
//    public static String getIPAddress(Context context) {
//        NetworkInfo info = ((ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
//        if (info != null && info.isConnected()) {
//            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
//                try {
//                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
//                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
//                        NetworkInterface intf = en.nextElement();
//                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
//                            InetAddress inetAddress = enumIpAddr.nextElement();
//                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
//                                return inetAddress.getHostAddress();
//                            }
//                        }
//                    }
//                } catch (SocketException e) {
//                    e.printStackTrace();
//                }
//
//            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
//                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
//                return ipAddress;
//            }
//        } else {
//            //当前无网络连接,请在设置中打开网络
//        }
//        return null;
//    }


}