package com.nfdaily.nfplus1.constant;

import android.text.TextUtils;

import com.nfdaily.nfplus1.util.UtilSp;

import org.json.JSONObject;

/**
 * Created by xilinch on 17-12-22.
 *
 * 修改环境直接修改 type的值 就可以进行切换
 */

public class Config {

    enum Type{
        DEV,
        TEST,
        PREPARE,
        RELEASE,

    }

    /**
     * 开发环境
     */
    private static Type type = Type.DEV;

    private static final String API_DEV = "http://devapi.nfapp.southcn.com/nanfang_if/";
    private static final String API_DEV_UPGRADE = "http://devapi.nfapp.southcn.com/nanfang_if/";
    private static final String API_DEV_CAIJI = "http://devcaiji.nfapp.southcn.com/";

    private static final String API_TEST = "http://testapi.nfapp.southcn.com/nanfang_if/";
    private static final String API_TEST_UPGRADE = "http://testapi.nfapp.southcn.com/nfplus-app-api/";
    private static final String API_TEST_CAIJI = "http://testcaiji.nfapp.southcn.com/";

    private static final String API_PREPARE = "http://preapi.nfapp.southcn.com/nanfang_if/";
    private static final String API_PREPARE_UPGRADE = "http://preapi.nfapp.southcn.com/nanfang_if/";
    private static final String API_PREPARE_CAIJI = "http://caiji.nfapp.southcn.com/";

    private static final String API_RELEASE = "http://api.nfapp.southcn.com/nanfang_if/";
    private static final String API_RELEASE_UPGRADE = "http://api.nfapp.southcn.com/nfplus-app-api/";
    private static final String API_RELEASE_CAIJI = "http://caiji.nfapp.southcn.com/";

    //后台配置的地址----begin
    // 内容模板地址
    private static String contentTemplate = "";

    // 直播分享url
    private static String liveShareUrl = "https://static.nfapp.southcn.com/apptpl/liveToShare.html";
    // 专题列表分享url
    private static String specialShareUrl = "https://static.nfapp.southcn.com/apptpl/specialPage.html";
    //个人中心分享的地址
    private static String shareAppAddressesUrl = "http://static.nfapp.southcn.com/nfapp/index.htm";
    //会员中心地址
    private static String memberCenterUrl = "http://uc.nfapp.southcn.com/amuc/api/member";
    //V3.9.0启用新版本会员中心地址
    private static String newMemberCenterUrl = "http://member.nfapp.southcn.com";
    //大数据会员中心地址
    private static String recommendUCenterUrl = "http://ucenter.nfapp.southcn.com";
    // 活动
    private static String activityUrl;
    // 南方号ID
    private static String nfhID;
    // 南方号ID
    private static String tjID;
    //小红点栏目id
    private static String redPointIds;
    //-----------后台配置的地址


    // 程序配置信息地址,新的配置接口
    public final static String URL_APP_CONFIG_V3 = "v3/getConfig";

    // 获取栏目
    public final static String URL_GET_COLUMNS = "getColumns";

    // 获取栏目稿件
    public final static String URL_GET_COLUMN_ARTICLES = "getArticles";

    //获取南方号的父栏目下的子栏目列表
    public final static String URL_GET_NFH_CURRENT_COLUMNS = "nfh/nfhColumnDetailCats";
    //南方号更多
    public final static String URL_GET_NFH_MORE_COLUMN_LIST = "nfh/nfhMoreColumn";

    //获取推荐稿件
    public final static String URL_GET_COLUMN_RECOMMEND = "searchArticlesByRec";

    // 获取单条稿件
    public final static String URL_GET_ARTICLE = "getArticleContent";

    // 获取新闻评论 wzf add modify by zzh 20161226 使用新接口V3.8.0
    public final static String URL_GET_COMMENT_LIST_SIMPLE = "discussListSimple/v1";
    //3.8.0 新接口  zzh
    public static final String URL_COMMIT_COMMENT = "discuss/v2";

    /**
     * 直播预约
     */
    public static final String URL_LVIESUBSCRIPTION = "liveSubscription";

    /**
     * 获取新数据接口 V3.3
     */
    public static final String URL_LIVIEUPDATECOUNT = "liveUpdateCount/v1";

    // 点赞接口
    public final static String URL_POST_COMMITCOUNT = "event";

    // 订阅选题接口(订阅频道中的子频道订阅接口)
    public static final String TOPIC_SUB = "subCol/saveAndGetInfo";

    // 取消订阅选题接口(订阅频道中的取消子频道订阅接口)
    public static final String TOPIC_SUB_CANCEL = "subCol/cancel";
    // 相关南方号的接口
    public static final String RELATED_NFH = "nfh/getNfhRelatedColumn";

    //nf意见反馈接口
    public final static String URL_POST_FEED = "feed";
    //nf用户事件接口
    public final static String URL_POST_EVENT = "event";
    //nf获取评论数接口
    public final static String URL_GET_COMMENT_COUNT = "discussCount";

    public final static String URL_TOPICSUBVIEW = "topicSubView";

    //一次性获取我订阅的所有南方号信息
    public final static String URL_GET_NFH_ORDER_COLUMN = "nfh/myNfhSubscribeColumnInfo";
    //获取天气信息的接口
    public final static String URL_GET_WEATHER = "NanfangInterface/api/weather/now";
    //检查升级的接口
    public final static String URL_GET_VERSION_UPDATE = "check/version/update";
    //升级下载的统计接口
    public final static String URL_UPGRADE_DOWN_ADD = "client/useragent/downadd";
    //工具接口
    public final static String URL_GETUTILSCOLUMNS = "getUtilsColumns";
    //新的办事接口
    public final static String URL_GETSERVICECOLUMNS = "getServiceColumns";
    //添加投诉
    public final static String URL_ADDCOMPLAINT = "addComplaint";

    //投诉规定：
    public static final String URL_WEB_COMPLAINT_RULE = "http://static.nfapp.southcn.com/apptpl/app/complaint_rule.html";
    //投诉电话：
    public static final String URL_WEB_COMPLAIN_CONTACT = "http://static.nfapp.southcn.com/apptpl/app/complain_contact.html";
    /**
     * 获取专题页面数据
     */
    public final static String URL_GET_SPECIAL_INFO = "v1/getSpecialTopic";

    /**
     * 数字报分享icon
     */
    public static final String URL_NEWSPAPER_SHARE_ICON = "http://epaper.southcn.com/m/ipaper/images/logo_nfrb.png";

    /**
     * 推荐频道,频道列表
     */
    public static final String URL_RECOMMEND_CHANNELS_GETARTICLES = "recommend/article/getPersonalArticles";//自己的接口

    /**
     * 获取话题文章列表
     */
    public static final String URL_RECOMMEND_TOPIC_GETTOPICARTICLES = "recommend/article/getTopicArticles";

    /**
     * 获取所有类别
     */
    public static final String URL_RECOMMEND_TOPIC_GETALLCATEGORYS = "recommend/topic/getAllCategorys";

    /**
     * 获取分类话题列表
     */
    public static final String URL_RECOMMEND_TOPIC_GETCATEGORYTOPICS = "recommend/topic/getCategoryTopics";

    /**
     * 获取用户订阅话题列表
     */
    public static final String URL_GET_MY_TOPIC = "recommend/topic/getUserTopics";

    /**
     * 用户订阅话题
     */
    public static final String URL_RECOMMEND_TOPIC_TAKETOPICS = "recommend/topic/takeTopic";


    /**
     * 提交用户不感兴趣元素
     */
    public static final String URL_RECOMMEND_USER_SUBHATEELEMENT = "recommend/article/putHateArticle";

    /**
     * 提交添加/更新用户标签
     */
    public static final String URL_UPDATE_USER_LABEL_TEMPLATE = "/usercenter/userlabelTemplate";

    /**
     * 获取大数据标签
     */
    public static final String URL_RECOMMEND_USERLABEL_TEMPLATE = "/usercenter/labelTemplates";

    /**
     * 统计设备信息
     */
    public static final String URL_RECOMMEND_DEVICEINFO = "/usercenter/deviceInfo";

    /**
     * 统计用户信息
     */
    public static final String URL_RECOMMEND_USERINFO = "/usercenter/userInfo";
    /**
     * 搜索南方号接口
     */
    public static final String URL_SEARCH_NFH = "search/searchNfh";
    /**
     * 搜索服务接口
     */
    public static final String URL_SEARCH_SERVICE = "search/searchService";
    /**
     * 新搜索接口
     */
    public static final String URL_SEARCH_ALL = "search/searchAll";

    /***南方号URL开始***/
    //南方号首页第一屏数据
    public static final String NFH_URL_GET_HOME = "nfh/getNfhHome";
    //南方号首页最新发布
    public static final String NFH_URL_GET_RECENT_ARICLES = "nfh/getNfhRecentArticles";
    //南方号城市列表
    public static final String NFH_URL_GET_CITY_LIST = "nfhCityListInfo";
    //南方号订阅管理页面
    public static final String NFH_URL_GET_SUB_MANAGE = "nfh/nfhSubManager";
    //我的订阅信息
    public static final String NFH_URL_GET_MY_SUB = "nfh/myNfhSubscribeInfo";
    //搜索南方号
    public static final String NFH_URL_SEARCH = "search/searchNfh";
    //搜索南方号和文章
    public static final String NFH_URL_SEARCH_ALL = "search/searchNfhAndArticle";
    //推荐相关南方号
    public static final String NFH_URL_RECOMMEND_COLUMNS = "recommend/nfhCol/getRelatedNfhRecommend";
    //推荐更多南方号
    public static final String NFH_URL_MORE_RECOMMEND_COLUMNS = "recommend/nfhCol/getPersonalNfhRecommend";
    //删除某个相关南方号
    public static final String NFH_URL_DELETE_COLUMN = "recommend/nfhCol/putHateNfhColumn";
    //南方号推送开关
    public static final String NFH_URL_PUSH_STATUS = "nfh/push/status";
    //登录发送推送Id
    public static final String NFH_URL_LOGIN_PUSH = "push/loginRegister";
    //退出登录发送推送Id
    public static final String NFH_URL_LOGOUT_PUSH = "push/logoutRegister";
    /***南方号URL开始***/

    //登录新接口 3.8.0
    public static final String PIC_CODE = "/ucapi/user/graphCaptcha";
    public static final String PHONE_CODE = "/ucapi/user/messageCaptcha";
    public static final String PHONE_REGISTER_AND_LOGIN = "/ucapi/user/login";
    public static final String THIRD_PART_LOGIN = "/ucapi/user/thirdPartLogin";

    //用户信息
    public static final String CHECK_MESSAGE_NUM = "/ucapi/msg/msgNum/";
    public static final String USER_MSG_LIST = "/ucapi/msg/msgList";
    public static final String GET_USER_AVATAR = "/ucapi/user/userImg";
    public static final String GET_ARTICLE_DETAIL = "getArticleContent?articleId=";
    public static final String UPLOAD_AVATAR = "/ucapi/user/uploadImg";
    public static final String UPLOAD_USER_INFO = "/ucapi/user/saveOrUpdateUserInfo";
    public static final String BIND_PIC_CODE = "/ucapi/user/bind/graphCaptcha";
    public static final String BIND_MESSAGE_CAPTCHA = "/ucapi/user/bind/bindMessageCaptcha";
    public static final String BIND_PHONE_NUM = "/ucapi/user/bind/bindPhone";
    public static final String UNBIND_MESSAGE_CAPTCHA = "/ucapi/user/bind/unBindMessageCaptcha";
    public static final String UNBIND_PHONE_NUM = "/ucapi/user/bind/unbindPhone";
    public static final String USER_COMMENT_LIST = "/v1/ucapi/user/commentList";

    //直播新接口 3.8.0
    public static final String PRAISE_NUM = "/livePraiseNum";
    public static final String SUBMIT_PRAISE_NUM = "/event";
    //    public static final String LIVE_CHAT = "liveView/v2";
    public static final String LIVE_CHAT = "v1/liveView";
    public static final String CHAT_COMMENT_LIST = "discussListSimple/v1";
    public static final String CHECK_NEW_MESSAGE = "latestLiveDiscussion";

    //3.9.5新版专题 展开更多接口
    public static final String SPECIAL_SHOW_MORE = "v1/getSpecialTopicArticles";

    //3.9.5新活动列表接口
    public static final String ACTIVITY_LIST = "activity/online/onlineActList";

    //大数据采集的用户行为
    public static final String COLLECTION_USERBEHAVIORDATA = "collection/userBehaviorData";

    /**
     * 通用接口地址
     */
    private static String HOST;
    /**
     * 升级
     */
    private static String HOST_UPGRADE;
    /**
     * 采集接口地址
     */
    private static String HOST_CAIJI;

    /**
     * 初始化配置
     */
    public static void initConfig(){
        if(Type.DEV == type){
            //开发
            HOST = API_DEV;
            HOST_UPGRADE = API_DEV_UPGRADE;
            HOST_CAIJI = API_DEV_CAIJI;
        } else if (Type.TEST == type){
            //测试
            HOST = API_TEST;
            HOST_CAIJI = API_TEST_CAIJI;
            HOST_UPGRADE = API_TEST_UPGRADE;
        } else if (Type.PREPARE == type) {
            //预发布
            HOST = API_PREPARE;
            HOST_CAIJI = API_PREPARE_CAIJI;
            HOST_UPGRADE = API_PREPARE_UPGRADE;

        } else if (Type.RELEASE == type) {
            //发布
            HOST = API_RELEASE;
            HOST_CAIJI = API_RELEASE_CAIJI;
            HOST_UPGRADE = API_RELEASE_UPGRADE;
        } else {
            //默认走正式环境配置
            HOST = API_RELEASE;
            HOST_CAIJI = API_RELEASE_CAIJI;
            HOST_UPGRADE = API_PREPARE_UPGRADE;
        }

    }

    /**
     * 获取通用接口地址
     * @param key
     * @return
     */
    public static String getHostUrl(String key){
        String url = "";
        if(TextUtils.isEmpty(HOST)){
            initConfig();
        }
        url = HOST + key;
        return url;

    }

    /**
     * 采集地址
     * @return
     */
    public static String getHostCaijiUrl(){
        String url = "";
        if(TextUtils.isEmpty(HOST_CAIJI)){
            initConfig();
        }
        url = HOST_CAIJI;
        return url;

    }

    /**
     * 升级地址
     * @return
     */
    public static String getHostUpgradeUrl(){
        String url = "";
        if(TextUtils.isEmpty(HOST_UPGRADE)){
            initConfig();
        }
        url = HOST_UPGRADE;
        return url;

    }

    /**
     * 获取模板地址
     * @return
     */
    public static String getContentTemplate(){
        if(TextUtils.isEmpty(contentTemplate)){
            initSplashConfig();
        }
        return contentTemplate;
    }

    /**
     * 获取直播分享地址
     * @return
     */
    public static String getLiveShareUrl(){
        if(TextUtils.isEmpty(liveShareUrl)){
            initSplashConfig();
        }
        return liveShareUrl;
    }

    /**
     * 专题分享地址
     * @return
     */
    public static String getSpecialShareUrl(){
        if(TextUtils.isEmpty(specialShareUrl)){
            initSplashConfig();
        }
        return specialShareUrl;
    }

    /**
     * 获取会员中心地址
     * @return
     */
    public static String getMemberCenterUrl(){
        if(TextUtils.isEmpty(memberCenterUrl)){
            initSplashConfig();
        }
        return memberCenterUrl;
    }

    /**
     * 获取新会员中心地址
     * @return
     */
    public static String getNewMemberCenterUrl(){
        if(TextUtils.isEmpty(newMemberCenterUrl)){
            initSplashConfig();
        }
        return newMemberCenterUrl;
    }

    /**
     * 获取会员中心地址
     * @return
     */
    public static String getRecommendUCenterUrl(){
        if(TextUtils.isEmpty(recommendUCenterUrl)){
            initSplashConfig();
        }
        return recommendUCenterUrl;
    }

    /**
     * 获取会员中心地址
     * @return
     */
    public static String getActivityUrl(){
        if(TextUtils.isEmpty(activityUrl)){
            initSplashConfig();
        }
        return activityUrl;
    }

    /**
     * 获取南方号id
     * @return
     */
    public static String getNFHID(){
        if(TextUtils.isEmpty(nfhID)){
            initSplashConfig();
        }
        return nfhID;
    }

    /**
     * 获取推荐id
     * @return
     */
    public static String getTJID(){
        if(TextUtils.isEmpty(tjID)){
            initSplashConfig();
        }
        return tjID;
    }

    /**
     * 设置启动页数据
     */
    public static void initSplashConfig(){
        String splashContent = UtilSp.getSplashData();
        if(TextUtils.isEmpty(splashContent)){
            return;
        }
        try{
            JSONObject jsonObject = new JSONObject(splashContent);
            contentTemplate = jsonObject.optString("template",contentTemplate);
            liveShareUrl = jsonObject.optString("liveShare",liveShareUrl);
            specialShareUrl = jsonObject.optString("specialShare",specialShareUrl);
            memberCenterUrl = jsonObject.optString("memberCenterUrl",memberCenterUrl);
            newMemberCenterUrl = jsonObject.optString("newMemberCenterUrl",newMemberCenterUrl);
            recommendUCenterUrl = jsonObject.optString("recommendUCenterUrl",recommendUCenterUrl);
            activityUrl = jsonObject.optString("activityUrl",activityUrl);
            nfhID = jsonObject.optString("nfhID");
            tjID = jsonObject.optString("tjID");
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
