package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class MusicForm {
    private int id;
    private String name;
    private String style;
    private String singer;
    private MultipartFile filePath;

    public MusicForm() {
    }

    public MusicForm(int id, String name, String style, String singer, MultipartFile filePath) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.singer = singer;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public MultipartFile getFilePath() {
        return filePath;
    }

    public void setFilePath(MultipartFile filePath) {
        this.filePath = filePath;
    }
}
