package com.fh.shop.backend.controller.common;

import java.io.InputStream;

public class FileInfo {
    //Io流
    private InputStream io;
    //文件名
    private String fileName;
    //文件大小
    private Long  size;
    public InputStream getIo() {
        return io;
    }
    public void setIo(InputStream io) {
        this.io = io;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
