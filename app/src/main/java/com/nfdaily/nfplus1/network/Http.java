package com.nfdaily.nfplus1.network;

import com.nfdaily.nfplus1.network.IHttp.HttpClient;
import com.nfdaily.nfplus1.network.IHttp.Interceptor;
import com.nfdaily.nfplus1.network.IHttp.RespHandler;
import com.nfdaily.nfplus1.util.Logger;
import com.nfdaily.nfplus1.util.UtilString;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Http {
    private Http() {
    }

    private static HttpClient httpClient;

    public static void initHttp(HttpClient client) {
        httpClient = client;
    }

    public static ReqInfo http(ReqInfo reqInfo, RespHandler respHandler, Interceptor interceptor) {
        httpClient.http(reqInfo, respHandler, interceptor);
        return reqInfo;
    }

    public static ReqInfo download(String url, Map<String, Object> paramsMap, RespHandler respHandler, String tag, boolean isShowDialog, Interceptor interceptor) {
        return common(ReqType.GET, url, paramsMap, respHandler, tag, isShowDialog, interceptor, true);
    }

    public static ReqInfo download(String url, Map<String, Object> paramsMap, RespHandler respHandler) {
        return common(ReqType.GET, url, paramsMap, respHandler, null, true, null, true);
    }

    public static ReqInfo get(String url, Map<String, Object> paramsMap, RespHandler respHandler, String tag, boolean isShowDialog, Interceptor interceptor) {
        return common(ReqType.GET, url, paramsMap, respHandler, tag, isShowDialog, interceptor, false);
    }

    public static ReqInfo get(String url, Map<String, Object> paramsMap, RespHandler respHandler, String tag, boolean isShowDialog) {
        return common(ReqType.GET, url, paramsMap, respHandler, tag, isShowDialog, null, false);
    }

    public static ReqInfo get(String url, Map<String, Object> paramsMap, RespHandler respHandler, String tag) {
        return common(ReqType.GET, url, paramsMap, respHandler, tag, true, null, false);
    }

    public static ReqInfo get(String url, Map<String, Object> paramsMap, RespHandler respHandler) {
        return common(ReqType.GET, url, paramsMap, respHandler, null, true, null, false);
    }

    public static ReqInfo get(String url, RespHandler respHandler) {
        return common(ReqType.GET, url, null, respHandler, null, true, null, false);
    }

    public static ReqInfo get(String url) {
        return common(ReqType.GET, url, null, null, null, true, null, false);
    }

    public static ReqInfo post(String url, Map<String, Object> paramsMap, RespHandler respHandler, String tag, boolean isShowDialog, Interceptor interceptor) {
        return common(ReqType.POST, url, paramsMap, respHandler, tag, isShowDialog, interceptor, false);
    }

    public static ReqInfo post(String url, Map<String, Object> paramsMap, RespHandler respHandler, String tag, boolean isShowDialog) {
        return common(ReqType.POST, url, paramsMap, respHandler, tag, isShowDialog, null, false);
    }

    public static ReqInfo post(String url, Map<String, Object> paramsMap, RespHandler respHandler, String tag) {
        return common(ReqType.POST, url, paramsMap, respHandler, tag, true, null, false);
    }

    public static ReqInfo post(String url, Map<String, Object> paramsMap, RespHandler respHandler) {
        return common(ReqType.POST, url, paramsMap, respHandler, null, true, null, false);
    }

    /**
     * 封装请求model
     */
    private static ReqInfo common(ReqType type, String url, Map<String, Object> paramsMap, RespHandler respHandler, String tag, boolean isShowDialog, Interceptor interceptor, boolean isDownload) {
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setReqType(type);
        reqInfo.setUrl(url);
        reqInfo.setShowDialog(isShowDialog);
        reqInfo.setTag(tag);
        reqInfo.setDownload(isDownload);
        reqInfo.setHeadersMap(getHeaders(tag));

        reqInfo.setParamsMap(getParams(tag, paramsMap));

        return http(reqInfo, respHandler, interceptor);
    }

    private static ReqInfo postJson(String url, String originJson, RespHandler respHandler, String tag, boolean isShowDialog, Interceptor interceptor, boolean isDownload) {
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setReqType(ReqType.POST);
        reqInfo.setUrl(url);
        reqInfo.setShowDialog(isShowDialog);
        reqInfo.setTag(tag);
        reqInfo.setDownload(isDownload);
        reqInfo.setHeadersMap(getHeaders(tag));

        reqInfo.setPostString(originJson);
        reqInfo.setPostStringContentType("application/json");

        return http(reqInfo, respHandler, interceptor);
    }

    public static void postJson(String url, String originJson, RespHandler respHandler, String tag, boolean isShowDialog) {
        try {
            JSONObject newJson = new JSONObject();
            newJson.put("terminal", "android");
//            newJson.put("version", UtilSystem.getVersionName(App.getApplication()));
//            newJson.put("token", Sp.getUserToken());
//            newJson.put("sid", Sp.getSid());

            if (UtilString.isAvaliable(originJson)) {
                newJson.put("data", new JSONObject(originJson));
            }

            Logger.i("发出去的未加密的json--" + newJson.toString());
            /*
            传给服务端的,data为json加密后的字符串,vali为json MD5之后的摘要
            {
                "data":"thNqbO4W1N9+sRGAAXf3g1FSI2R7zlw2XC6tQ1xP3HCQ\/\/MRs4pF8tJS0gJJj2pxbp8tZxWIMo7hxKDS1int9g==",
                "vali":"22a53685c77e709664319ddf003ca788",
                "enc":true
             }
             */
            JSONObject encJson = new JSONObject();
            Logger.i("发出去的加密后的json--" + encJson.toString());
            postJson(url, encJson.toString(), respHandler, tag, isShowDialog, null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postJson(String url, String originJson, RespHandler respHandler) {
        postJson(url, originJson, respHandler,"", true);
    }

    public static void postJson(String url, String originJson, String tag, RespHandler respHandler) {
        postJson(url, originJson, respHandler,tag, true);
    }

    /**
     * 配置请求头
     *
     * @param tag 标识
     */
    private static Map<String, List<String>> getHeaders(String tag) {
        //TODO 设置请求头
        Map<String, List<String>> head = new HashMap<>();

//        List<String> terminal = new ArrayList<>();
//        terminal.add("android");
//
//        List<String> version = new ArrayList<>();
//        version.add(UtilSystem.getVersionName(App.getApplication()));
//
//        List<String> token = new ArrayList<>();
//        token.add(Sp.getUserToken());
//
//        List<String> sid = new ArrayList<>();
//        sid.add(Sp.getSid());
//
//        head.put("terminal", terminal);
//        head.put("version", version);
//        head.put("token", token);
//        head.put("sid", sid);
//        Logger.i("头信息:--" + head.toString());
        return head;
    }

    /**
     * 配置加密规则
     *
     * @param tag 标识
     */
    private static Map<String, Object> getParams(String tag, Map<String, Object> paramsMap) {
        if (paramsMap == null) {
            paramsMap = new HashMap<>();
        }
        //TODO 设置加密参数
        Logger.d("加密前参数--" + paramsMap);
        Logger.d("加密后参数--" + paramsMap);
        return paramsMap;
    }
}


