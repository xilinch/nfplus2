package com.nfdaily.nfplus1.cache;

/**
 * Created by xilinch on 17-11-20.
 */

public class NFCacheManager {

    /**
     * 单例
     */
    private static NFCacheManager instance;
    /**
     * 获取对象
     */
    private boolean hasInit;

    private NFCacheManager() {

    }

    public synchronized static NFCacheManager getInstance() {
        if (instance == null) {
            instance = new NFCacheManager();
        }
        return instance;
    }

    public INFCache getFileCache() {
        INFCache infCache = new INFFileCacheImpl();
        if (!hasInit) {
            synchronized (INFFileCacheImpl.class) {
                infCache.init();
                hasInit = true;
            }
        }
        return infCache;
    }

}
