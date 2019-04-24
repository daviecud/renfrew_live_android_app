package com.example.renfrewlive;

public class InfoDetails {

    private int id;
    private String info_name, info_address, info_tel, info_summary;
    private int image;

    public InfoDetails(int id, String info_name, String info_address, String info_tel, String info_summary, int image) {
        this.id = id;
        this.info_name = info_name;
        this.info_address = info_address;
        this.info_tel = info_tel;
        this.info_summary = info_summary;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getInfo_name() {
        return info_name;
    }

    public String getInfo_address() {
        return info_address;
    }

    public String getInfo_tel() {
        return info_tel;
    }

    public String getInfo_summary() {
        return info_summary;
    }

    public int getImage() {
        return image;
    }
}
