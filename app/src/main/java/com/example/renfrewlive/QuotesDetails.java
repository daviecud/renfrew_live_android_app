package com.example.renfrewlive;

public class QuotesDetails {

    String name, address, town, postcode, telephone, web;

    public QuotesDetails() {

    }

    public QuotesDetails(String name, String address, String town, String postcode, String telephone, String web) {
        this.name = name;
        this.address = address;
        this.town = town;
        this.postcode = postcode;
        this.telephone = telephone;
        this.web = web;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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
}
