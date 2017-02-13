package com.rasapishe.boom.objectmodel;

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
    private String image;

    public Business(String id, String title, BusinessType type, String phone, String address, String description, String image) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.phone = phone;
        this.address = address;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
