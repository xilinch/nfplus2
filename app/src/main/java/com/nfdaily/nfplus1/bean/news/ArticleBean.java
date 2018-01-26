package com.nfdaily.nfplus1.bean.news;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


/**
 * 专题页文章
 */
public class ArticleBean extends BaseArticalBean {
    private static final String TAG = "ArticleBean";
    /**
     * fileId : 221980
     * title : 黄常开：“南方号”将助力珠海构建舆论引导新格局
     * version : 1482246635000
     * attAbstract :
     * publishtime : 2016-12-20 19:49:38.0
     * articleType : 0
     * shareUrl : http://static.nfapp.southcn.com/content/201612/20/c221980.html
     * tag :
     * tagStyle :
     * colName : 专题库~珠海政务自媒体入驻“南方号”~领导致辞
     * colID : 1863
     * releaseColName : 领导致辞
     * releaseColNumber :
     * releaseSource :
     * isRel : 0
     * url :
     * urlPad : http://static.nfapp.southcn.com/content/201612/20/c221980.html
     * isBigPic : 0
     * bigPic : 0
     * contentUrl : http://testapi.nfapp.southcn.com/nanfang_if/getArticleContent?articleId=221980&colID=1863
     * linkID : 0
     * countClick : 47666
     * countClickInitial : 46448
     * countClickReal : 465
     * countShareClick : 753
     * countDiscuss : 4
     * countPraise : 0
     * countShare : 7
     * position : 0
     * extGroupID : 24
     * location :
     * region :
     * picMiddle : http://static.nfapp.southcn.com/pic/201612/20/t1_(38X15X577X318)3c54aba5-7165-4a8f-b581-050c75cb0c17.jpg
     * picSmall : http://static.nfapp.southcn.com/pic/201612/20/t0_(81X7X577X379)3c54aba5-7165-4a8f-b581-050c75cb0c17.jpg
     * pic0 : http://static.nfapp.southcn.com/pic/201612/20/3c54aba5-7165-4a8f-b581-050c75cb0c17.jpg
     * picCount : 1
     * 是否计分 : 否
     * 正文折叠 : 否
     */

    private int fileId;
    private String title;
    private long version;
    private String attAbstract;
    @SerializedName("publishtime")
    private String publishTime;
    private int articleType;
    private String shareUrl;
    private String tag;
    private String tagStyle;
    private String colName;
    private int colID;
    private String releaseColName;
    private String releaseColNumber;
    private String releaseSource;
    private int isRel;
    private String url;
    private String urlPad;
    private int isBigPic;
    private int bigPic;
    private String contentUrl;
    private String videoUrl;
    private int videoTime;
    private int linkID;
    private int countClick;
    private int countClickInitial;
    private int countClickReal;
    private int countShareClick;
    private int countDiscuss;
    private int countPraise;
    private int countShare;
    private int position;
    private int extGroupID;
    private String location;
    private String region;
    private String picMiddle;
    private String picSmall;
    private String picBig;
    private String pic0;
    private String pic1;
    private String pic2;
    private String picCount;
    @SerializedName("是否计分")
    private String isScore;
    @SerializedName("正文折叠")
    private String isExpend;
    @SerializedName("art_nfh_icon")
    private String nfhIcon;
    //跳转类型
    private String jumpType;
    //新版专题下显示的文章列表
    @SerializedName("SpecialTopicList")
    private List<ArticleBean> specialList;
    //自定义类型  1 左文右图
    private int customType;
    //是否新版专题
    private int templateID;
    /**
     * 我的订阅过来的列id
     */
    private String mySubColumnId;

    public ArticleBean() {
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public int getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(int videoTime) {
        this.videoTime = videoTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getAttAbstract() {
        return attAbstract;
    }

    public void setAttAbstract(String attAbstract) {
        this.attAbstract = attAbstract;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getArticleType() {
        return articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagStyle() {
        return tagStyle;
    }

    public void setTagStyle(String tagStyle) {
        this.tagStyle = tagStyle;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public int getColID() {
        return colID;
    }

    public void setColID(int colID) {
        this.colID = colID;
    }

    public String getReleaseColName() {
        return releaseColName;
    }

    public void setReleaseColName(String releaseColName) {
        this.releaseColName = releaseColName;
    }

    public String getReleaseColNumber() {
        return releaseColNumber;
    }

    public void setReleaseColNumber(String releaseColNumber) {
        this.releaseColNumber = releaseColNumber;
    }

    public String getReleaseSource() {
        return releaseSource;
    }

    public void setReleaseSource(String releaseSource) {
        this.releaseSource = releaseSource;
    }

    public int getIsRel() {
        return isRel;
    }

    public void setIsRel(int isRel) {
        this.isRel = isRel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlPad() {
        return urlPad;
    }

    public void setUrlPad(String urlPad) {
        this.urlPad = urlPad;
    }

    public int getIsBigPic() {
        return isBigPic;
    }

    public void setIsBigPic(int isBigPic) {
        this.isBigPic = isBigPic;
    }

    public int getBigPic() {
        return bigPic;
    }

    public void setBigPic(int bigPic) {
        this.bigPic = bigPic;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public int getLinkID() {
        return linkID;
    }

    public void setLinkID(int linkID) {
        this.linkID = linkID;
    }

    public int getCountClick() {
        return countClick;
    }

    public void setCountClick(int countClick) {
        this.countClick = countClick;
    }

    public int getCountClickInitial() {
        return countClickInitial;
    }

    public void setCountClickInitial(int countClickInitial) {
        this.countClickInitial = countClickInitial;
    }

    public int getCountClickReal() {
        return countClickReal;
    }

    public void setCountClickReal(int countClickReal) {
        this.countClickReal = countClickReal;
    }

    public int getCountShareClick() {
        return countShareClick;
    }

    public void setCountShareClick(int countShareClick) {
        this.countShareClick = countShareClick;
    }

    public int getCountDiscuss() {
        return countDiscuss;
    }

    public void setCountDiscuss(int countDiscuss) {
        this.countDiscuss = countDiscuss;
    }

    public int getCountPraise() {
        return countPraise;
    }

    public void setCountPraise(int countPraise) {
        this.countPraise = countPraise;
    }

    public int getCountShare() {
        return countShare;
    }

    public void setCountShare(int countShare) {
        this.countShare = countShare;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getExtGroupID() {
        return extGroupID;
    }

    public void setExtGroupID(int extGroupID) {
        this.extGroupID = extGroupID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPicMiddle() {
        return picMiddle;
    }

    public void setPicMiddle(String picMiddle) {
        this.picMiddle = picMiddle;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPic0() {
        return pic0;
    }

    public void setPic0(String pic0) {
        this.pic0 = pic0;
    }

    public String getPicCount() {
        return picCount;
    }

    public void setPicCount(String picCount) {
        this.picCount = picCount;
    }

    public String getIsScore() {
        return isScore;
    }

    public void setIsScore(String isScore) {
        this.isScore = isScore;
    }

    public String getIsExpend() {
        return isExpend;
    }

    public void setIsExpend(String isExpend) {
        this.isExpend = isExpend;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getNfhIcon() {
        return nfhIcon;
    }

    public void setNfhIcon(String nfhIcon) {
        this.nfhIcon = nfhIcon;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public List<ArticleBean> getSpecialList() {
        return specialList;
    }

    public void setSpecialList(List<ArticleBean> specialList) {
        this.specialList = specialList;
    }

    public int getCustomType() {
        return customType;
    }

    public void setCustomType(int customType) {
        this.customType = customType;
    }

    public int getTemplateID() {
        return templateID;
    }

    public void setTemplateID(int templateID) {
        this.templateID = templateID;
    }

    public String getMySubColumnId() {
        return mySubColumnId;
    }

    public void setMySubColumnId(String mySubColumnId) {
        this.mySubColumnId = mySubColumnId;
    }




    @Override
    public void bind() {

    }

    @Override
    public int getItemType() {
        return 0;
    }


    @Override
    public void onclick(Context context) {

    }

    @Override
    public boolean getReadStatus() {
        return false;
    }

    @Override
    public void setReadStatus() {

    }

    @Override
    public void setReadHistory() {

    }
}