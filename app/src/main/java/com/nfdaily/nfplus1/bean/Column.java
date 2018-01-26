package com.nfdaily.nfplus1.bean;

import java.io.Serializable;

/**
 * Created by xilinch on 18-1-9.
 */

public class Column implements Serializable{


    /**
     * columnId : 94
     * columnName : 国际
     * topCount : 2
     * phoneIcon : http://183.63.143.166/app/btn_zhitianxia.png
     * padIcon : http://183.63.143.166/app/btn_zhitianxia.png
     * orderId : 16
     * keyword :
     * description : 管窥天下，纵览全球。
     * linkUrl :
     * isForbidden : false
     * colNumber :
     * colTemplate : 0
     * linkQRCode :
     * qRCodeShare :
     * introduction :
     * createDate : 2015-09-18 17:22:49
     * colVersion : 0
     * parentColID : 0
     * headerStyleType : 0
     * recommend :
     * columnType : 4002
     * columnStyle : 101
     */
    /**
     * 101：新闻频道
     */
    public final static int TYPE_CHANNEL_NEWS_PAGE = 101;

    /**
     * 201：普通栏目
     */
    public final static int TYPE_COLUMN_NEWS = 201;
    /**
     * 202：图片栏目  (目前没用)
     */
    public final static int TYPE_COLUMN_IMAGE = 202;
    /**
     * 204：推荐栏目  (目前没用)
     */
    public final static int TYPE_COLUMN_RECOMMEND = 204;
    /**
     * 205：地方栏目
     */
    public final static int TYPE_COLUMN_LOCAL = 205;
    /**
     * 206：外链栏目
     */
    public final static int TYPE_COLUMN_WEB = 206;
    /**
     * 209:生活栏目
     */
    public final static int TYPE_COLUMN_LIFE = 209;

    /**
     * 5003
     */
    public final static int TYPE_COLUMN_BOTTOM_SERVICE = 5003;

    /**
     * 5004:活动
     */
    public final static int TYPE_COLUMN_BOTTOM_ACTIVITY = 5004;
    /**
     * 5005: 底部个人中心
     */
    public final static int TYPE_COLUMN_BOTTOM_PERSONAL = 5005;
    /**
     * 栏目数据类型，最新(目前没用)
     */
    public final static int TYPE_DATA_NEWEST = 501;
    /**
     * 栏目数据类型，推荐(目前没用)
     */
    public final static int TYPE_DATA_RECOMMEND = 502;

    /**
     * 显示当前标签
     */
    public final static int POS_TYPE_CHANNEL_LEFT = 4002;
    /**
     * 数字报
     */
    public final static int TYPE_EPAPER = 4001;

    private static final long serialVersionUID = -3563794809210853295L;


    /**
     * columnId  id=21表示是订阅按钮
     *
     */

    private int columnId;
    private String columnName;
    private int topCount;
    private String phoneIcon;
    private String padIcon;
    private int orderId;
    private String keyword;
    private String description;
    private String linkUrl;
    private boolean isForbidden;
    private String colNumber;
    private int colTemplate;
    private String linkQRCode;
    private String qRCodeShare;
    private String introduction;
    private String createDate;
    private int colVersion;
    private int parentColID;
    private int headerStyleType;
    private String recommend;
    private String columnType;
    private String columnStyle;

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getTopCount() {
        return topCount;
    }

    public void setTopCount(int topCount) {
        this.topCount = topCount;
    }

    public String getPhoneIcon() {
        return phoneIcon;
    }

    public void setPhoneIcon(String phoneIcon) {
        this.phoneIcon = phoneIcon;
    }

    public String getPadIcon() {
        return padIcon;
    }

    public void setPadIcon(String padIcon) {
        this.padIcon = padIcon;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public boolean isIsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(boolean isForbidden) {
        this.isForbidden = isForbidden;
    }

    public String getColNumber() {
        return colNumber;
    }

    public void setColNumber(String colNumber) {
        this.colNumber = colNumber;
    }

    public int getColTemplate() {
        return colTemplate;
    }

    public void setColTemplate(int colTemplate) {
        this.colTemplate = colTemplate;
    }

    public String getLinkQRCode() {
        return linkQRCode;
    }

    public void setLinkQRCode(String linkQRCode) {
        this.linkQRCode = linkQRCode;
    }

    public String getQRCodeShare() {
        return qRCodeShare;
    }

    public void setQRCodeShare(String qRCodeShare) {
        this.qRCodeShare = qRCodeShare;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getColVersion() {
        return colVersion;
    }

    public void setColVersion(int colVersion) {
        this.colVersion = colVersion;
    }

    public int getParentColID() {
        return parentColID;
    }

    public void setParentColID(int parentColID) {
        this.parentColID = parentColID;
    }

    public int getHeaderStyleType() {
        return headerStyleType;
    }

    public void setHeaderStyleType(int headerStyleType) {
        this.headerStyleType = headerStyleType;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnStyle() {
        return columnStyle;
    }

    public void setColumnStyle(String columnStyle) {
        this.columnStyle = columnStyle;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnId=" + columnId +
                ", columnName='" + columnName + '\'' +
                ", topCount=" + topCount +
                ", phoneIcon='" + phoneIcon + '\'' +
                ", padIcon='" + padIcon + '\'' +
                ", orderId=" + orderId +
                ", keyword='" + keyword + '\'' +
                ", description='" + description + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", isForbidden=" + isForbidden +
                ", colNumber='" + colNumber + '\'' +
                ", colTemplate=" + colTemplate +
                ", linkQRCode='" + linkQRCode + '\'' +
                ", qRCodeShare='" + qRCodeShare + '\'' +
                ", introduction='" + introduction + '\'' +
                ", createDate='" + createDate + '\'' +
                ", colVersion=" + colVersion +
                ", parentColID=" + parentColID +
                ", headerStyleType=" + headerStyleType +
                ", recommend='" + recommend + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnStyle='" + columnStyle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column column = (Column) o;

        if (columnId != column.columnId) return false;
        if (topCount != column.topCount) return false;
        if (orderId != column.orderId) return false;
        if (isForbidden != column.isForbidden) return false;
        if (colTemplate != column.colTemplate) return false;
        if (colVersion != column.colVersion) return false;
        if (parentColID != column.parentColID) return false;
        if (headerStyleType != column.headerStyleType) return false;
        if (columnName != null ? !columnName.equals(column.columnName) : column.columnName != null)
            return false;
        if (phoneIcon != null ? !phoneIcon.equals(column.phoneIcon) : column.phoneIcon != null)
            return false;
        if (padIcon != null ? !padIcon.equals(column.padIcon) : column.padIcon != null)
            return false;
        if (keyword != null ? !keyword.equals(column.keyword) : column.keyword != null)
            return false;
        if (description != null ? !description.equals(column.description) : column.description != null)
            return false;
        if (linkUrl != null ? !linkUrl.equals(column.linkUrl) : column.linkUrl != null)
            return false;
        if (colNumber != null ? !colNumber.equals(column.colNumber) : column.colNumber != null)
            return false;
        if (linkQRCode != null ? !linkQRCode.equals(column.linkQRCode) : column.linkQRCode != null)
            return false;
        if (qRCodeShare != null ? !qRCodeShare.equals(column.qRCodeShare) : column.qRCodeShare != null)
            return false;
        if (introduction != null ? !introduction.equals(column.introduction) : column.introduction != null)
            return false;
        if (createDate != null ? !createDate.equals(column.createDate) : column.createDate != null)
            return false;
        if (recommend != null ? !recommend.equals(column.recommend) : column.recommend != null)
            return false;
        if (columnType != null ? !columnType.equals(column.columnType) : column.columnType != null)
            return false;
        return columnStyle != null ? columnStyle.equals(column.columnStyle) : column.columnStyle == null;
    }

    @Override
    public int hashCode() {
        int result = columnId;
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + topCount;
        result = 31 * result + (phoneIcon != null ? phoneIcon.hashCode() : 0);
        result = 31 * result + (padIcon != null ? padIcon.hashCode() : 0);
        result = 31 * result + orderId;
        result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (linkUrl != null ? linkUrl.hashCode() : 0);
        result = 31 * result + (isForbidden ? 1 : 0);
        result = 31 * result + (colNumber != null ? colNumber.hashCode() : 0);
        result = 31 * result + colTemplate;
        result = 31 * result + (linkQRCode != null ? linkQRCode.hashCode() : 0);
        result = 31 * result + (qRCodeShare != null ? qRCodeShare.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + colVersion;
        result = 31 * result + parentColID;
        result = 31 * result + headerStyleType;
        result = 31 * result + (recommend != null ? recommend.hashCode() : 0);
        result = 31 * result + (columnType != null ? columnType.hashCode() : 0);
        result = 31 * result + (columnStyle != null ? columnStyle.hashCode() : 0);
        return result;
    }
}
