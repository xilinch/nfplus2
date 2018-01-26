package com.nfdaily.nfplus1.bean;

import com.google.gson.Gson;
import com.nfdaily.nfplus1.Interface.ICacheable;
import com.nfdaily.nfplus1.bean.news.ArticleBean;
import com.nfdaily.nfplus1.util.UtilLog;
import com.nfdaily.nfplus1.util.UtilSp;
import com.nfdaily.nfplus1.util.UtilString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 启动页Bean
 */
public class SplashAD implements Serializable, ICacheable<SplashAD>{
    private static final long serialVersionUID = 1L;

    // 启动页类型0代表图片(暂无其他类型)
    private int pageType;
    // 启动页停留时间
    private int residenceTime;
    // 广告图片地址
    private String fileUrl;
    // 广告链接地址
    private String webLinkURL;
    //标签
    private String label;
    //文章类型
    private int isArticle;
    // 2017、6/19 广告优化
    private ArticleBean article;
    /**
     * 跳转类型 0表示普通的跳转，1表示南方+文章跳转，2表示南方号栏目跳转。
     */
    private String jumpType;

    public static final int PAGE_TYPE_PIC = 0;


    public int getPageType() {
        return pageType;
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }

    public int getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(int residenceTime) {
        this.residenceTime = residenceTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getWebLinkURL() {
        return webLinkURL;
    }

    public void setWebLinkURL(String webLinkURL) {
        this.webLinkURL = webLinkURL;
    }

    public int getIsArticle() {
        return isArticle;
    }

    public void setIsArticle(int isArticle) {
        this.isArticle = isArticle;
    }

    public ArticleBean getArticle() {
        return article;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setArticle(ArticleBean article) {
        this.article = article;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    @Override
    public String toString() {
        return "SplashAD{" +
                "pageType=" + pageType +
                ", residenceTime=" + residenceTime +
                ", fileUrl='" + fileUrl + '\'' +
                ", webLinkURL='" + webLinkURL + '\'' +
                ", label='" + label + '\'' +
                ", isArticle=" + isArticle +
                ", article=" + article +
                ", jumpType='" + jumpType + '\'' +
                '}';
    }

    /**
     * 从启动页数据中获取广告数据
     * @param splashContent
     * @return
     */
    public static SplashAD getSplashFromString(String splashContent){
        SplashAD splashAD = null;
        try{
            JSONObject jsonObject = new JSONObject(splashContent);
            splashAD = new SplashAD();// 广告配置信息
            JSONArray advList = jsonObject.optJSONArray("adv");
            UtilLog.e("my", "advList:" + advList);
            if(advList != null && advList.length() > 0){
                JSONObject adv = (JSONObject) advList.opt(0);
                String imUrl = adv.optString("type2ImgUrl1");
                if (UtilString.isBlank(imUrl)) {
                    imUrl = adv.optString("type2ImgUrl2");
                }
                if (UtilString.isBlank(imUrl)) {
                    imUrl = adv.optString("type2ImgUrl3");
                }
                if (UtilString.isBlank(imUrl)) {//广告没有图片链接或者格式不正确，表示不需要显示广告
                    return null;
                }
                splashAD = new SplashAD();
                splashAD.setPageType(jsonObject.optInt("pageType"));
                splashAD.setResidenceTime(jsonObject.optInt("pageTime"));
                splashAD.setWebLinkURL(adv.optString("advurl"));
                splashAD.setFileUrl(imUrl);
                splashAD.setLabel(adv.optString("label"));
                String jumpType = adv.optString("jumpType");
                splashAD.setJumpType(jumpType);
                UtilLog.e("my", "splashPage:" + splashAD.toString());
                int isArticle = adv.optInt("isArticle");
                splashAD.setIsArticle(isArticle);
                if ("1".equals(jumpType) || "2".equals(jumpType)) {
                    //分享的文章
                    ArticleBean article = new Gson().fromJson(adv.toString(), ArticleBean.class);
                    article.setJumpType(jumpType);
                    splashAD.setArticle(article);
                }
            }

        } catch (Exception exception){
            exception.printStackTrace();
        }
        return splashAD;
    }

    @Override
    public void save2Cache() {
        String content = "";
        try{
            content = new Gson().toJson(this);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        UtilSp.setSplashAd(content);
    }

    @Override
    public SplashAD getFromCache() {
        String content = UtilSp.getSplashAd();
        SplashAD splashAD = null;
        try{
            splashAD = new Gson().fromJson(content,SplashAD.class);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return splashAD;
    }
}
