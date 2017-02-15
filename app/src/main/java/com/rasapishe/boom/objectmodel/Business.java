package com.rasapishe.boom.objectmodel;

import java.util.Random;

/**
 * Created by Zahra Jamshidi
 * on 10/02/2017.
 */

public class Business {
    private String id;
    private String title;
    private BusinessType type;
    private String phone;
    private String address;
    private String description;

    private int ranking;
    private boolean isMine;

    private double lat;
    private double lang;

    private int allMembers;
    private int factorCounts;
    private int paidMembers;
    private int unpaidMembers;

    public Business() {
        Random r = new Random(500);
        allMembers = r.nextInt(500) + 500;
        factorCounts = allMembers * 4 / 3;
        paidMembers = factorCounts * 2 / 3;
        unpaidMembers = factorCounts - paidMembers;
    }

    public Business(String id, String title, BusinessType type, String phone, String address, String description) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.phone = phone;
        this.address = address;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BusinessType getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(BusinessType type) {
        this.type = type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public int getAllMembers() {
        return allMembers;
    }

    public void setAllMembers(int allMembers) {
        this.allMembers = allMembers;
    }

    public int getFactorCounts() {
        return factorCounts;
    }

    public void setFactorCounts(int factorCounts) {
        this.factorCounts = factorCounts;
    }

    public int getPaidMembers() {
        return paidMembers;
    }

    public void setPaidMembers(int paidMembers) {
        this.paidMembers = paidMembers;
    }

    public int getUnpaidMembers() {
        return unpaidMembers;
    }

    public void setUnpaidMembers(int unpaidMembers) {
        this.unpaidMembers = unpaidMembers;
    }
}
