package com.nfdaily.nfplus1.cache;

/**
 * Created by xilinch on 17-11-16.
 */

public interface INFCache {


    /**
     * 读取缓存，文件名
     * @param fileName
     * @return
     */
    String readCache(String fileName);

    /**
     * 写入缓存内容
     * @param fileName
     * @param content
     * @return
     */
    boolean writeCache(String fileName, String content);


    /**
     * 是否存在缓存
     * @param fileName
     * @return
     */
    boolean isCached(String fileName);

    /**
     * 缓存是否有效
     * @param fileName
     * @return
     */
    boolean isCacheEffective(String fileName);

    /**
     * 清除缓存
     * @return
     */
    boolean clearCache();


    /**
     * 缓存初始化
     * @return
     */
    boolean init();
}
