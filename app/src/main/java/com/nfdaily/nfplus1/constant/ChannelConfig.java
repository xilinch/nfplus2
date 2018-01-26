package com.nfdaily.nfplus1.constant;

import android.text.TextUtils;

import com.nfdaily.nfplus1.util.UtilSp;

import org.json.JSONObject;

/**
 * Created by xilinch on 18-1-9.
 * 频道管理
 */

public class ChannelConfig {

    /**
     * 设置频道数据
     */
    public static void setChannels() {
        String splashContent = UtilSp.getSplashData();
        if (!TextUtils.isEmpty(splashContent)) {
            //启动页数据
            try {
                JSONObject jsonObject = new JSONObject(splashContent);
                JSONObject defaultColumnJson = jsonObject.optJSONObject("defaultColumn");
                JSONObject defaultColumn = defaultColumnJson.optJSONObject("columns");
                String defaultColumnVersion = defaultColumnJson.optString("version");

                JSONObject moreColumnJson = jsonObject.optJSONObject("moreColumn");
                JSONObject moreColumn = moreColumnJson.optJSONObject("columns");
                String moreColumnVersion = moreColumnJson.optString("version");

                JSONObject cityColumnJson = jsonObject.optJSONObject("cityColumn");
                JSONObject cityColumn = cityColumnJson.optJSONObject("columns");
                String cityColumnVersion = cityColumnJson.optString("version");

                JSONObject epaperColumnJson = jsonObject.optJSONObject("epaperColumn");
                JSONObject epaperColumn = epaperColumnJson.optJSONObject("columns");
                String epaperColumnVersion = epaperColumnJson.optString("version");
                String lastDefaultColumnVersion = UtilSp.getDefaultColumnsVersion();
                //版本号保持一致，无需更新
                if(!TextUtils.isEmpty(lastDefaultColumnVersion) && lastDefaultColumnVersion.equals(defaultColumnVersion)){
                    //donothing
                } else {
                    if(defaultColumn != null){
                        UtilSp.setdefaultColumn(defaultColumn.toString());
                    }
                    UtilSp.setDefaultColumnsVersion(defaultColumnVersion);
                }
                String lastMoreColumnVersion = UtilSp.getMoreColumnsVersion();
                //版本号保持一致，无需更新
                if(!TextUtils.isEmpty(lastMoreColumnVersion) && lastMoreColumnVersion.equals(moreColumnVersion)){
                    //donothing
                } else {
                    if(moreColumn != null){
                        UtilSp.setMoreColumn(moreColumn.toString());
                    }
                    UtilSp.setMoreColumnsVersion(moreColumnVersion);
                }

                String lastCityColumnVersion = UtilSp.getCityColumnsVersion();
                //版本号保持一致，无需更新
                if(!TextUtils.isEmpty(lastCityColumnVersion) && lastCityColumnVersion.equals(cityColumnVersion)){
                    //donothing
                } else {
                    if(cityColumn != null){
                        UtilSp.setCityColumn(cityColumn.toString());
                    }
                    UtilSp.setCityColumnsVersion(cityColumnVersion);
                }
                String lastEpapperColumnVersion = UtilSp.getEpaperColumnsVersion();
                //版本号保持一致，无需更新
                if(!TextUtils.isEmpty(lastEpapperColumnVersion) && lastEpapperColumnVersion.equals(epaperColumnVersion)){
                    //donothing
                } else {
                    if(epaperColumn != null){
                        UtilSp.setEpaperColumn(epaperColumn.toString());
                    }
                    UtilSp.setEpaperColumnsVersion(epaperColumnVersion);
                }

            } catch (Exception exception) {
                exception.printStackTrace();

            }
        }
    }


}
