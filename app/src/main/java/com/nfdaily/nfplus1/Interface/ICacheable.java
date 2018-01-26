package com.nfdaily.nfplus1.Interface;

/**
 * Created by xilinch on 18-1-8.
 */

public interface ICacheable<T> {

    /**
     * 存入缓存
     * @param content
     */
    void save2Cache();

    /**
     * 从缓存中获取
     * @return
     */
    T getFromCache();
}
