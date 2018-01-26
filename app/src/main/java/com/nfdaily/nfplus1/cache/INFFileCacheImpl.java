package com.nfdaily.nfplus1.cache;

import android.os.Build;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import com.nfdaily.nfplus1.application.ReaderApplication;
import com.nfdaily.nfplus1.util.FileUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zhangzhihao on 2017/11/16.
 * 最少5M空间
 */

public class INFFileCacheImpl implements INFCache {
    private static final String TAG = "INFFileCacheImpl";

    private static final long FILE_CACHE_TIME = 60 * 3 * 1000;
    private static final String FOLDER_NAME = "nfplus";
    private static final String ABSOLUTE_FOLDER = ReaderApplication.getInstance().getFilesDir().getAbsolutePath() + File.separator + FOLDER_NAME + File.separator;
    private static final long SMALLEST_CACHE_SIZE = 8 * 1024 * 1024;

    /**
     * 读取缓存
     *
     * @param fileName 文件名
     * @return
     */
    @Override
    public String readCache(String fileName) {
        StringBuffer result = new StringBuffer("");
        if (!isCached(fileName)) {
            return result.toString();
        }
        File file = new File(ABSOLUTE_FOLDER, fileName);
        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String s;
            while ((s = reader.readLine()) != null) {
                result.append(s);
            }
            reader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                return "";
            }
            return result.toString();
        }
    }

    /**
     * 写入缓存内容
     * 会清空前一次的文件内容
     * 写入出错则删除文件
     *
     * @param fileName 文件名
     * @param content  内容
     * @return
     */
    @Override
    public boolean writeCache(String fileName, String content) {
        if (isCacheEffective(fileName)) {
            return true;
        }
        return write(fileName, content);
    }

    /**
     * 强制写入缓存
     * 清空上一次内容
     * 写入出错则删除文件
     *
     * @param fileName
     * @param content
     * @return
     */
    public boolean writeCacheForce(String fileName, String content) {
        return write(fileName, content);
    }

    /**
     * 写文件
     *
     * @param fileName
     * @param content
     * @return
     */
    private boolean write(String fileName, String content) {
        boolean result = false;
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            File file = new File(ABSOLUTE_FOLDER, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write(content);
            writer.flush();
            writer.close();
            fileWriter.close();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
            deleteFile(fileName);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                deleteFile(fileName);
                return false;
            }
            return result;
        }
    }

    /**
     * 是否存在缓存文件
     *
     * @param fileName 文件名
     * @return
     */
    @Override
    public boolean isCached(String fileName) {
        File file = new File(ABSOLUTE_FOLDER, fileName);
        return file.exists();
    }

    /**
     * 缓存是否有效
     *
     * @param fileName 文件名
     * @return
     */
    @Override
    public boolean isCacheEffective(String fileName) {
        if (!isCached(fileName)) {
            return false;
        }
        File file = new File(ABSOLUTE_FOLDER, fileName);
        long different = System.currentTimeMillis() - file.lastModified();
        return different < FILE_CACHE_TIME;
    }

    /**
     * 清除缓存
     *
     * @return
     */
    @Override
    public boolean clearCache() {
        return FileUtils.deleteDirectory(ABSOLUTE_FOLDER);
    }

    /**
     * 缓存初始化
     * 创建文件夹，判断存储空间大小
     *
     * @return
     */
    @Override
    public boolean init() {
        File absolute = ReaderApplication.getInstance().getFilesDir();
        StatFs stat = new StatFs(absolute.getPath());
        long size;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            size = stat.getAvailableBytes();
        } else {
            size = stat.getBlockSize() * stat.getAvailableBlocks();
        }
        Log.d(TAG, "cache size:" + size);
        if (size < SMALLEST_CACHE_SIZE) {
            Log.d(TAG, "size not enough");
//            ToastUtils.toastShow(ReaderApplication.getInstance(),
// ReaderApplication.getInstance().getString(R.string.cache_not_enough));
            return false;
        }

        File parent = new File(ABSOLUTE_FOLDER);
        if (!parent.exists()) {
            return parent.mkdirs();
        }

        return true;
    }

    private boolean deleteFile(String fileName) {
        File file = new File(ABSOLUTE_FOLDER, fileName);
        return file.delete();
    }
}
