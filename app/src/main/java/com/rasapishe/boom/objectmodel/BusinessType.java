package com.rasapishe.boom.objectmodel;

import com.rasapishe.boom.R;

/**
 * Created by Zahra Jamshidi
 * on 10/02/2017.
 */

public enum BusinessType {
    GYM(1, R.mipmap.ic_launcher ,"باشگاه ورزشی"),
    W_beauty_SHOP(2, R.mipmap.ic_bussiness, "سالن زیبایی بانوان"),
    BUILDING_CO(3,R.mipmap.ic_launcher, "شرکت ساختمانی");

    int id;
    int imageId;
    String title;

    BusinessType(int id, int imageId, String title) {
        this.id = id;
        this.imageId = imageId;
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

    public int getImageId(){return imageId;}
}
