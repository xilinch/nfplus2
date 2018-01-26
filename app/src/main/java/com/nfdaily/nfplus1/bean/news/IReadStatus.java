package com.nfdaily.nfplus1.bean.news;

/**
 * Created by xilinch on 18-1-25.
 * 新闻读过接口
 */

public interface IReadStatus {

    /**
     * 获取读过状态
     * @return
     */
    boolean getReadStatus();


    /**
     * 设置读过状态
     */
    void setReadStatus();

    /**
     * 设置阅读历史
     */
    void setReadHistory();
}
