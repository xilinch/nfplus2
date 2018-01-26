package com.nfdaily.nfplus1.network;

import java.io.File;

import okhttp3.MediaType;

/**
 * @author fengjingyu@foxmail.com
 */
public class UploadFileWrapper {

    private File file;

    public UploadFileWrapper(File file) {
        this.file = file;
    }

    public String getName() {
        if (isExist()) {
            return file.getName();
        } else {
            return "file not exist " + System.currentTimeMillis();
        }
    }

    public File getFile() {
        return file;
    }

    public MediaType getMediaType() {
        if (isExist()) {
            return getFileContentType();
        } else {
            return null;
        }
    }

    private MediaType getFileContentType() {
        return MediaType.parse("application/octet-stream");
    }

    public long length() {
        if (isExist()) {
            return file.length();
        } else {
            return 0;
        }
    }

    public boolean isExist() {
        return file != null && file.exists();
    }

}