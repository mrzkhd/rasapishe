package com.rasapishe.boom.objectmodel;

/**
 * Created by Zahra Jamshidi
 * on 10/02/2017.
 */

public enum BusinessType {
    GYM(1, "باشگاه ورزشی"),
    W_beauty_SHOP(2, "سالن زیبایی بانوان"),
    BUILDING_CO(3, "شرکت ساختمانی");

    int id;
    String title;

    BusinessType(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static BusinessType getById(int id) {
        for (BusinessType type : BusinessType.values())
            if (type.getId() == id)
                return type;
        return null;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
