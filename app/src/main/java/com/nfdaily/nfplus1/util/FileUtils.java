package com.nfdaily.nfplus1.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * 类说明： 文件读写 如果往手机中写文件，文件保存在 /data/data/com.nfdaily.nfplus/files 目录下
 * 如果是SD卡，则自建目录
 *
 * @author Liucd
 * @version 1.0
 * @date Sep 29, 2010 6:50:25 AM
 */
public class FileUtils {

    /**
     * 下载时，至少剩余的sd卡空间大小 8M
     */
    private static final long MIN_LEFT_STORE = 1024 * 1024 * 8;

    /**
     * 缓冲区大小
     */
    public static final int BUFF_SIZE = 1024 * 4;

    private final static String TAG = "FileUtils";
    // 缓存存储到手机上
    public static int STORE_PLACE_PHONE = 1;

    // 缓存存储到SD卡上
    public static int STORE_PLACE_SDCARD = 2;

    // 文件的大小
    public static long size = 0;
    /**
     * @author cyliu
     */
    private static final String SDCardRoot = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    public static final String APP_DIR = SDCardRoot + File.separator
            + "FounderReader";
    public static final String DIR_DOWNLOAD = APP_DIR + File.separator
            + "downloads";

    static {
        File dirDownload = new File(DIR_DOWNLOAD);
        if (!dirDownload.exists()) {
            dirDownload.mkdirs();
        }
        dirDownload = null;
    }

    /**
     * 获取当前文件存储位置
     *
     * @return
     */
    public static int getStorePlace() {
        return STORE_PLACE_PHONE;
        //return STORE_PLACE_SDCARD;
    }

    /**
     * 判断文件是否存在
     *
     * @param context
     * @param folderName
     * @param fileName
     * @param storePlace
     * @return
     */
    public static boolean isFileExist(Context context, String folderName,
                                      String fileName, int storePlace) {
        File file = getFile(context, folderName, fileName, storePlace);
        return file != null && file.exists();
    }

    /**
     * 删除文件
     *
     * @param context
     * @param folderName
     * @param fileName
     * @param storePlace
     * @return
     */
    public static boolean delete(Context context, String folderName,
                                 String fileName, int storePlace) {
        boolean ret = false;
        if (isFileExist(context, folderName, fileName,
                FileUtils.STORE_PLACE_PHONE) || isFileExist(context, folderName, fileName,
                FileUtils.STORE_PLACE_SDCARD)) {
            File file = getFile(context, folderName, fileName, storePlace);
            ret = file.delete();
        }
        return ret;
    }

    /**
     * 根据文件目录和文件名获取File对象
     *
     * @param context
     * @param folderName
     * @param storePlace
     * @return
     */
    public static File getFile(Context context, String folderName,
                               int storePlace) {
        File file = null;
        if (STORE_PLACE_PHONE == storePlace) {
            String absolutePath = context.getFilesDir() + File.separator
                    + folderName;
            file = new File(absolutePath);
        } else {
            String absolutePath = Environment.getExternalStorageDirectory()
                    + File.separator + folderName;
            file = new File(absolutePath);
        }

        return file;
    }

    /**
     * 根据文件目录和文件名获取File对象
     *
     * @param context
     * @param folderName
     * @param fileName
     * @param storePlace
     * @return
     */
    public synchronized static File getFile(Context context, String folderName,
                                            String fileName, int storePlace) {
        File file = null;
        if (context != null) {
            if (STORE_PLACE_PHONE == storePlace) {
                String absolutePath = context.getFilesDir() + File.separator
                        + folderName;
                file = new File(absolutePath, fileName);
            } else {
                String absolutePath = Environment.getExternalStorageDirectory()
                        + File.separator + folderName;
                file = new File(absolutePath, fileName);
            }
        }

        return file;
    }

    /**
     * 向手机或者SD卡写文件
     *
     * @param context
     * @param folderName
     * @param fileName
     * @param buffer
     * @param storePlace
     * @return
     */
    public synchronized static boolean writeFile(Context context, String folderName, String fileName, byte[] buffer, int storePlace) {
        boolean writeSucc = false;
        String absoluteFolder = "";

        if (STORE_PLACE_PHONE == storePlace) {
            absoluteFolder = context.getFilesDir() + File.separator + folderName + File.separator;
        } else {
            boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
            if (sdCardExist) {
                absoluteFolder = Environment.getExternalStorageDirectory() + File.separator + folderName + File.separator;
            } else {
                absoluteFolder = "";
            }
        }
        if (!UtilString.isBlank(absoluteFolder)) {
            File fileDir = new File(absoluteFolder);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = new File(absoluteFolder, fileName);
            writeSucc = writeFile(context, file, buffer);
        }
        return writeSucc;
    }

    /**
     * 写文件
     *
     * @param context
     * @param file
     * @param buffer
     * @return
     */
    public static boolean writeFile(Context context, File file, byte[] buffer) {
        boolean writeSucc = false;
        FileOutputStream out = null;
        try {
            File fileDir = file.getParentFile();
            if (fileDir != null && !fileDir.exists()) {
                fileDir.mkdirs();
            }

            // add huazhang
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            out.write(buffer);
            writeSucc = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return writeSucc;
    }

    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (UtilString.isBlank(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * 根据文件的绝对路径获取文件名但不包含扩展名
     *
     * @param filePath
     * @return
     */
    public static String getFileNameNoFormat(String filePath) {
        if (UtilString.isBlank(filePath)) {
            return "";
        }

        int point = filePath.lastIndexOf('.');
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1,
                point);
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileFormat(String fileName) {
        if (UtilString.isBlank(fileName))
            return "";

        int point = fileName.lastIndexOf('.');
        return fileName.substring(point + 1);
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath) {
        long size = 0;

        File file = new File(filePath);
        if (file != null && file.exists()) {
            size = file.length();
        }
        return size;
    }

    /**
     * 获取文件大小
     *
     * @param size 字节
     * @return
     */
    public static String getFileSize(long size) {
        if (size <= 0)
            return "0K";
        java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
        float temp = (float) size / 1024;
        if (temp >= 1024) {
            return df.format(temp / 1024) + "M";
        } else {
            return df.format(temp) + "K";
        }
    }

    /**
     * InputStream转byte数组
     *
     * @param in
     * @return
     */
    public static byte[] toBytes(InputStream in) throws IOException {
        if (in == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int ch = 0;
        while ((ch = in.read()) != -1) {
            out.write(ch);
        }

        byte[] buffer = out.toByteArray();
        out.close();
        return buffer;
    }

    /**
     * 将根据本地文件路径得到byte数组
     *
     * @param filepath
     * @return
     */
    public static byte[] fileToByte(String filepath) {
        byte[] fileByte = null;
        if (!UtilString.isBlank(filepath)) {
            File file = new File(filepath);
            if (file.exists()) {
                FileInputStream fileStream = null;
                try {
                    fileStream = new FileInputStream(file);
                    int fileLen = fileStream.available();
                    fileByte = new byte[fileLen];
                    fileStream.read(fileByte);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileStream != null) {
                        try {
                            fileStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return fileByte;
    }

    /**
     * 得到网络文件流
     *
     * @param webUrl
     * @return
     */
    public static InputStream getInputStream(String webUrl) {
        URL url = null;
        InputStream in = null;

        try {
            url = new URL(webUrl);
            if (url != null) {
                in = (InputStream) url.getContent();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    /**
     * 文件删除 如果是目录则删除目录下所有文件
     *
     * @param filepath
     */
    public static void deleteFiles(String filepath) {
        File file = new File(filepath);
        deleteFiles(file);
    }

    public static void getFileLen(String filePath) {
        File file = new File(filePath);
        getFileSize(file);
    }

    /**
     * 文件删除 如果是目录则删除目录下所有文件
     */
    public static void deleteFiles(File file) {
        // 文件
        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }
        // 目录
        else {
            File[] delFiles = file.listFiles();
            if (delFiles != null) {
                int fileCounter = delFiles.length;
                for (int i = 0; i < fileCounter; i++) {
                    if (delFiles[i].isDirectory()) {
                        File member = new File(delFiles[i].getAbsolutePath());
                        deleteFiles(member);
                    }

                    delFiles[i].delete();
                }
            }
        }
    }

    public static void getFileSize(File file) {
        // 文件
        if (file.exists() && !file.isDirectory()) {
            size += file.length();
        }
        // 目录
        else {
            File[] delFiles = file.listFiles();
            int fileCounter = delFiles.length;
            for (int i = 0; i < fileCounter; i++) {
                if (delFiles[i].isDirectory()) {
                    File member = new File(delFiles[i].getAbsolutePath());
                    getFileSize(member);
                }

                size += delFiles[i].length();
            }
        }
    }

    /**
     * 数据流转换为字符串
     *
     * @param in
     * @return
     */
    public static String inputStreamToString(InputStream in) {
        String strInputStream = null;
        if (in != null) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                int i = 0;
                while ((i = in.read()) != -1) {
                    out.write(i);
                }
                out.flush();
                strInputStream = out.toString();
            } catch (IOException e) {
                e.printStackTrace();
                strInputStream = null;
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return strInputStream;
    }

    public static String inputStream2String(InputStream is) {
        String strInputStream = null;
        if (is != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[512];
            try {
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                strInputStream = baos.toString();
            } catch (IOException e) {
                e.printStackTrace();
                strInputStream = null;
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return strInputStream;
    }


    public static final String readFile(String filePath) {
        String result = null;
        if (!UtilString.isBlank(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream in = null;
                try {
                    in = new FileInputStream(file);
                    result = inputStream2String(in);
                } catch (IOException e) {
                    e.printStackTrace();
                    result = null;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return result;
    }

    // 遍历一个目录下所有文件，并返回所有文件（不包括目录）的绝对路径给static filesPaths
    static ArrayList<String> filesPaths = new ArrayList<String>();

    public static void listFilesPath(File file) {
        if (file.isDirectory()) {
            File[] f = file.listFiles();
            for (int i = 0; i < f.length; i++) {
                listFilesPath(f[i]);
            }
        }
        if (file.isFile()) {
            String filesPath = file.getParent() + File.separator
                    + file.getName();
            filesPaths.add(filesPath);
        }
        // return filesPaths;
    }

    // 遍历一个目录所有的一级文件(包括文件夹)
    // static ArrayList<String> filesPaths=new ArrayList<String> ();
    public static ArrayList<String> listFilesAndFolderPath(File file) {
        ArrayList<String> filesPaths = new ArrayList<String>();
        if (file.isDirectory()) {
            File[] f = file.listFiles();
            for (int i = 0; i < f.length; i++) {
                File fileMsg = f[i];
                if (fileMsg != null && fileMsg.isDirectory()) {
                    // 二级栏目下的问价及文件夹
                    File[] fileSecond = fileMsg.listFiles();
                    for (int j = 0; j < fileSecond.length; j++) {
                        File fileThree = fileSecond[j];
                        filesPaths.add(fileThree.getParent()
                                + File.separator + fileThree.getName());
                    }
                } else {
                    // 一级栏目下的文件
                    filesPaths.add(fileMsg.getParent() + File.separator
                            + fileMsg.getName());
                }
            }
        }
        return filesPaths;
    }

    public static long getFilesAndFolderSize(File file) {
        long size = 0;
        if (file != null && file.isDirectory()) {
            File[] f = file.listFiles();
            for (int i = 0; i < f.length; i++) {
                File fileMsg = f[i];
                String name = fileMsg.getName();
                if (fileMsg.getName().equals("localClientTemplate")) {
                    continue;
                } else {
                    if (fileMsg != null && fileMsg.isDirectory()) {
                        // 二级栏目下的问价及文件夹
                        File[] fileSecond = fileMsg.listFiles();
                        for (int j = 0; j < fileSecond.length; j++) {
                            File fileThree = fileSecond[j];
                            size += fileThree.length();
                        }
                    } else {
                        // 一级栏目下的文件
                        size += fileMsg.length();
                    }
                }
            }
        }
        return size;
    }

    /**
     * 循环删除空的文件夹
     */
    public static void deleteEmptyDir(File dir) {
        if (dir.isDirectory()) {
            File[] fs = dir.listFiles();
            if (fs != null && fs.length > 0) {
                for (int i = 0; i < fs.length; i++) {
                    File tmpFile = fs[i];
                    if (tmpFile.isDirectory()) {
                        deleteEmptyDir(tmpFile);
                    }
                    if (tmpFile.isDirectory()
                            && tmpFile.listFiles().length <= 0) {
                        tmpFile.delete();
                    }
                }
            }
            if (dir.isDirectory() && dir.listFiles().length == 0) {
                dir.delete();
            }
        }
    }

    // /////////////////////////////////////////////////////////

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static long getSDFreeSize() {
        if (isExternalStorageWritable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(path.getPath());
            long blockSize = sf.getBlockSize();
            long freeBlocks = sf.getAvailableBlocks();
            return freeBlocks * blockSize;
        }
        return 0;
    }

    /**
     * 判断磁盘空间是否够用
     *
     * @author cyliu
     */
    public static boolean isSDFreeSizeEnough(int needSize) {
        return getSDFreeSize() >= needSize + MIN_LEFT_STORE;
    }

    public static boolean deleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (!file.exists()) {
            return flag;
        } else {
            if (file.isFile()) {
                return deleteFile(sPath);
            } else {
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     */
    public static boolean deleteDirectory(String sPath) {
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        return dirFile.delete();
    }

    /**
     * 删除单个文件
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        copyFile(new FileInputStream(sourceFile), targetFile);
    }

    // 复制文件
    public static void copyFile(InputStream in, File targetFile)
            throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(in);

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[BUFF_SIZE];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null) {
                try {
                    inBuff.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (outBuff != null) {
                try {
                    outBuff.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 复制文件夹
//    public static void copyDirectiory(String sourceDir, String targetDir)
//            throws IOException {
//        // 新建目标目录
//        (new File(targetDir)).mkdirs();
//        // 获取源文件夹当前下的文件或目录
//        File[] file = (new File(sourceDir)).listFiles();
//        for (int i = 0; i < file.length; i++) {
//            if (file[i].isFile()) {
//                // 源文件
//                File sourceFile = file[i];
//                // 目标文件
//                File targetFile = new File(
//                        new File(targetDir).getAbsolutePath() + File.separator
//                                + file[i].getName());
//                copyFile(sourceFile, targetFile);
//            }
//            if (file[i].isDirectory()) {
//                // 准备复制的源文件夹
//                String dir1 = sourceDir + "/" + file[i].getName();
//                // 准备复制的目标文件夹
//                String dir2 = targetDir + "/" + file[i].getName();
//                copyDirectiory(dir1, dir2);
//            }
//        }
//    }

    // 复制文件夹
//    public static void copyDirectiory(Context context, String targetDir)
//            throws IOException {
//        // 新建目标目录
//        (new File(targetDir)).mkdirs();
//        AssetManager asset = context.getResources().getAssets();
//        String[] files = asset.list("newsTemplate");
//        for (String f : files) {
//            String file = "newsTemplate" + File.separator + f;
//            InputStream in = asset.open(file);
//            File targetFile = new File(new File(targetDir).getAbsolutePath()
//                    + File.separator + f);
//            copyFile(in, targetFile);
//        }
//    }

//    public static void copyWeatherDirectiory(Context context, String targetDir)
//            throws IOException {
//        // 新建目标目录
//        (new File(targetDir)).mkdirs();
//        AssetManager asset = context.getResources().getAssets();
//        InputStream in = asset.open("localWeatherTemplate.zip");
//        File targetFile = new File(new File(targetDir).getAbsolutePath()
//                + File.separator + "localWeatherTemplate.zip");
//        copyFile(in, targetFile);
//        if(in != null){
//            try{
//                in.close();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        }
//    }

    public static String readJS(File file) {
        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;
        StringBuffer strBuffer = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            inputReader = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(inputReader);
            // 读取一行
            String line = null;
            strBuffer = new StringBuffer();

            while ((line = bufferReader.readLine()) != null) {
                strBuffer.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputReader != null) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strBuffer.toString();
    }

    //----------------------------------------------分割线 下方为新增代码-------------------------------//

    /**
     * 写入文件内容
     *
     * @param strcontent
     * @param filePath
     * @param fileName
     */
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d(TAG, "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e(TAG, "Error on write File:" + e);
        }
    }

    // 生成文件
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    public static String getExternalSdCardPath() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File sdCardFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sdCardFile.getAbsolutePath();
        }
        String path = null;
        File sdCardFile = null;
        ArrayList<String> devMountList = getDevMountList();
        for (String devMount : devMountList) {
            File file = new File(devMount);
            if (file.isDirectory() && file.canWrite()) {
                path = file.getAbsolutePath();
            }
        }

        if (path != null) {
            sdCardFile = new File(path);
            return sdCardFile.getAbsolutePath();
        }

        return null;
    }

    private static ArrayList<String> getDevMountList() {
        String[] toSearch = FileUtils.readFile("/etc/vold.fstab").split(" ");
        ArrayList<String> out = new ArrayList<String>();
        for (int i = 0; i < toSearch.length; i++) {
            if (toSearch[i].contains("dev_mount")) {
                if (new File(toSearch[i + 2]).exists()) {
                    out.add(toSearch[i + 2]);
                }
            }
        }
        return out;
    }

    public static byte[] returnBitMap(String strUrl) {
        byte[] image = null;
        InputStream is = null;
        try {
            URL url = new URL(strUrl);
            URLConnection conn = url.openConnection();
            is = conn.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = is.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            image = swapStream.toByteArray();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return image;

    }
}