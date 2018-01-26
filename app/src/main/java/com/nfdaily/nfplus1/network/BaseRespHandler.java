package com.nfdaily.nfplus1.network;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;

import com.nfdaily.nfplus1.util.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BaseRespHandler<T> extends RespHandlerAdapter<T> {

    private Activity activityContext;

    //todo
    public static final String REQ_SUCCESS = "1";

    public BaseRespHandler(Activity activityContext) {
        super();
        this.activityContext = activityContext;
    }

    public BaseRespHandler() {
    }

    @Override
    public void onReadySendRequest(ReqInfo reqInfo) {
        super.onReadySendRequest(reqInfo);
        showDialog(reqInfo);
    }

    /**
     * 如果显示dialog，则isShowDialog为true 且 activityContext非空
     */
    private void showDialog(ReqInfo reqInfo) {
//        if (activityContext != null && reqInfo.isShowDialog()) {
//            DialogManager dialogManager = DialogManager.getInstance(activityContext);
//            // TODO 设置dialog
//            Dialog dialog = new HttpLoadingDialog(activityContext);
//            addDialogListener(dialog);
//            dialogManager.setDialog(dialog);
//            dialogManager.mayShow(toString());
//        }
    }

    /**
     * 解析是否成功的规则，根据项目的json而定
     */
    @Override
    public boolean onMatchAppStatusCode(ReqInfo reqInfo, RespInfo respInfo, T resultBean) {
        super.onMatchAppStatusCode(reqInfo, respInfo, resultBean);
        //TODO 解析规则
//        if (resultBean instanceof IHttpRespInfo) {
//            if (UtilString.equals(((IHttpRespInfo) resultBean).getRet(), REQ_SUCCESS)) {
//                return true;
//            } else {
//                statusCodeWrongLogic(resultBean);
//                return false;
//            }
//        } else {
//            Logger.e(OkCallback.TAG_HTTP,"onMatchAppStatusCode()中的返回结果不是IHttpRespInfo类型",null);
//            throw new RuntimeException("onMatchAppStatusCode()中的返回结果不是IHttpRespInfo类型");
//        }
        return true;
    }

    public void statusCodeWrongLogic(T resultBean) {
//        Logger.shortToast(((IHttpRespInfo) resultBean).getMsg());
    }

    @Override
    public void onFailure(ReqInfo reqInfo, RespInfo respInfo) {
        super.onFailure(reqInfo, respInfo);
        Logger.shortToast("网络出错啦");
    }

    @Override
    public void onEnd(ReqInfo reqInfo, RespInfo respInfo) {
        super.onEnd(reqInfo, respInfo);
        closeDialog(reqInfo.isShowDialog());
    }

    private void closeDialog(boolean isShowDialog) {
        if (activityContext != null && isShowDialog) {
            DialogManager.getInstance(activityContext).mayClose(toString());
        }
    }

    public final void addDialogListener(Dialog dialog) {
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    closeDialog(true);
//                    //todo
//                    if (activityContext != null && !(activityContext instanceof MainActivity)) {
//                        activityContext.finish();
//                    }
//                }
//                return false;
//            }
//        });
    }

    @Override
    public T onParse2Model(ReqInfo reqInfo, RespInfo respInfo) {
        //加了密的
        String dataString = respInfo.getDataString();
        try {
            respInfo.setDataString(dataString);
            JSONObject jsonObject = new JSONObject(dataString);
            String encData = jsonObject.optString("data");
            if(!TextUtils.isEmpty(encData) && encData.startsWith("[")){
                    JSONArray dataJsonObject = new JSONArray(encData);
                    if(dataJsonObject != null && dataJsonObject.length() > 0){
                        respInfo.setDataString(dataJsonObject.toString());
                    }
                } else if(!TextUtils.isEmpty(encData) && encData.startsWith("(")){
                    JSONObject dataJsonObject = new JSONObject(encData);
                    respInfo.setDataString(dataJsonObject.toString());
                }
            //服务端返回的加密数据
//            JSONObject jsonObject = new JSONObject(dataString);
//            String encData = jsonObject.optString("data");
//            String md5Server = jsonObject.optString("vali");
//            boolean enc = jsonObject.optBoolean("enc");
//            String putData = encData;
//            if(enc){
//                //客户端解密
//                putData = TextUtils.isEmpty(encData)?"":XjApiUtil.decrypt(encData);
//                Logger.i(OkCallback.TAG_HTTP,"解密后的数据@@@"+putData);
//                if (UtilString.equals(md5Server, md5Client)){
//                    JSONObject dataJsonObject = new JSONObject(putData);
//
//                    respInfo.setDataString(dataJsonObject.toString());
//                } else {
//                    Logger.shortToast("网络数据被篡改了");
//                    Logger.e(OkCallback.TAG_HTTP,reqInfo + "@@@网络数据被篡改了",null);
//                }
//            } else {
//                if(!TextUtils.isEmpty(putData) && putData.startsWith("[")){
//                    JSONArray dataJsonObject = new JSONArray(putData);
//                    if(dataJsonObject != null && dataJsonObject.length() > 0){
//                        respInfo.setDataString(dataJsonObject.toString());
//                    }
//                } else if(!TextUtils.isEmpty(putData) && putData.startsWith("(")){
//                    JSONObject dataJsonObject = new JSONObject(putData);
//                    respInfo.setDataString(dataJsonObject.toString());
//                }

//            }
            return super.onParse2Model(reqInfo, respInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
