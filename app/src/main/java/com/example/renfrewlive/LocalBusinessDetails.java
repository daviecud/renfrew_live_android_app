package com.example.renfrewlive;

import android.widget.TextView;

public class LocalBusinessDetails {

    private String name, address, telephone, web, summary;

    public LocalBusinessDetails(String name, String address, String telephone, String web, String summary) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.web = web;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
