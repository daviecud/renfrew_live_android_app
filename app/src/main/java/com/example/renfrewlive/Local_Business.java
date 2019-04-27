package com.example.renfrewlive;

public class Local_Business {

    private int id;
    private String business_name, business_address, business_tel, business_web, business_summary;

    private int image;

    public Local_Business(int id, String business_name, String business_address, String business_tel, String business_web, String business_summary, int image) {
        this.id = id;
        this.business_name = business_name;
        this.business_address = business_address;
        this.business_tel = business_tel;
        this.business_web = business_web;
        this.business_summary = business_summary;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public void setBusiness_address(String business_address) {
        this.business_address = business_address;
    }

    public String getBusiness_tel() {
        return business_tel;
    }

    public void setBusiness_tel(String business_tel) {
        this.business_tel = business_tel;
    }

    public String getBusiness_web() {
        return business_web;
    }

    public void setBusiness_web(String business_web) {
        this.business_web = business_web;
    }

    public String getBusiness_summary() {
        return business_summary;
    }

    public void setBusiness_summary(String business_summary) {
        this.business_summary = business_summary;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}


