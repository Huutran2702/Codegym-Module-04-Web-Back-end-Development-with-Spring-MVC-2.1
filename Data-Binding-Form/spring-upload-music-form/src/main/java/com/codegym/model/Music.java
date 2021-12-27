package com.codegym.model;

public class Music {
    private int id;
    private String name;
    private String style;
    private String singer;
    private String filePath;

    public Music() {
    }

    public Music(int id, String name, String style, String singer, String filePath) {
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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
