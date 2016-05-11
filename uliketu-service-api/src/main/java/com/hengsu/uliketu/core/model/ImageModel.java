package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import java.io.InputStream;
import java.util.Date;

@MapClass("com.hengsu.uliketu.core.entity.Image")
public class ImageModel {

    private Long id;
    private String filename;
    private String path;
    private Date time;
    private String contentType;
    private Long length;
    private InputStream content;


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public InputStream getContent() {
        return content;
    }
}