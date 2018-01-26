package com.nfdaily.nfplus1.util;


import com.android.network.RequestUtil;

/**
 * Created by xilinch on 18-1-8.
 */

public class UtilTemplate {

    /**
     * 下载模板
     * url http://test166.nfapp.southcn.com/apptem/ab4f4ca7-678d-45e7-890f-d740f4d3084f.zip
     * fileDir  /data/user/0/com.nfdaily.nfplus/files/FounderReader/localClientTemplateTemp
     */
    public static void downLoadTemplate(){
        RequestUtil.httpDownloadFile();
    }

    /**
     * 解压模板
     */
    public static void unzipTemplate(){

    }
}
