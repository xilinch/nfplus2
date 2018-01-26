package com.nfdaily.nfplus1.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.nfdaily.nfplus1.Interface.ICacheable;
import com.nfdaily.nfplus1.application.ReaderApplication;
import com.nfdaily.nfplus1.constant.Constants;
import com.nfdaily.nfplus1.util.FileUtils;
import com.nfdaily.nfplus1.util.UtilLog;
import com.nfdaily.nfplus1.util.UtilString;
import java.io.File;
import java.io.Serializable;

public class Account implements Serializable,ICacheable<Account> {

    /**
     *
     */
    private static final long serialVersionUID = 8431041951041535181L;
    private static String TAG = "Account";

    /**
     * 手机绑定状态
     */
    @SerializedName("bindingStatus")
    private String bindState = "0"; //1-已绑定 0-未绑定
    /**
     * 账户ID
     */
    @SerializedName("userOldId")
    private String userId;
    @SerializedName("uuid")
    private String userUUID;
    /**
     * 账户电话
     */
    private String phone;
    /**
     * 账户名称
     */
    private String loginName;
    /**
     * 昵称
     */
    @SerializedName("nickname")
    private String nickName;
    /**
     * 头像
     */
    @SerializedName("avatarImg")
    private String userPhoto;
    /**
     * 是否从第三方登录进行登录的
     */
    private boolean isThirdPartyLogin;
    /**
     * 积分
     */
    private String score;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    @SerializedName("gender")
    private String sex;//1-male 2-female 3-unknown
    /**
     * 生日
     */
    private String birthday;
    /**
     * 地区
     */
    private String region;
    /**
     * 邀请码
     */
    private String invitationCode;

    private String token;

    /**
     * 登录方式  手机=0 wetChat=1 weiBo=2 QQ=3
     */
    private int type;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isThirdPartyLogin() {
        return isThirdPartyLogin;
    }

    public void setThirdPartyLogin(boolean isThirdPartyLogin) {
        this.isThirdPartyLogin = isThirdPartyLogin;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getBindState() {
        return bindState;
    }

    public void setBindState(String bindState) {
        this.bindState = bindState;
    }

    @Override
    public String toString() {

        return "Account [userId=" + userId + ", phone=" + phone
                + ", loginName=" + loginName + ", nickName=" + nickName
                + ", userPhoto=" + userPhoto + ", isThirdPartyLogin="
                + isThirdPartyLogin + ", score=" + score + ", realName="
                + realName + token + ", token=" + ", address=" + address + ", email=" + email
                + ", invitationCode=" + invitationCode + ", sex=" + sex + ", birthday=" + birthday + ", region="
                + region + ", type=" + type + "]";
    }


    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    /**
     * 对象转化为string
     */
    @Override
    public void save2Cache() {
        String accountJson = new Gson().toJson(this);
        String folderName = Constants.CACHE_FOLDER + File.separator + "Account";
        String fileName =  "1_" + "nfaccount.txt";
        boolean isSuccess = FileUtils.writeFile(ReaderApplication.getInstance(), folderName, fileName, accountJson.getBytes(), FileUtils.STORE_PLACE_PHONE);
        UtilLog.e("my","save2Cache  isSuccess" + isSuccess);
    }

    @Override
    public Account getFromCache() {
        String folderName = Constants.CACHE_FOLDER + File.separator + "Account";
        String fileName = "1_" + "nfaccount.txt";
        File accountInfo = FileUtils.getFile(ReaderApplication.getInstance(), folderName, fileName, FileUtils.STORE_PLACE_PHONE);
        try {
            if (accountInfo != null && accountInfo.exists()) {
                String info = FileUtils.readJS(accountInfo);
                if (info != null && !UtilString.isBlank(info)) {
                    Account account = new Gson().fromJson(info, Account.class);
                    return account;
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

}
