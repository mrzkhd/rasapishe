package com.rasapishe.boom;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Build;

import com.rasapishe.boom.objectmodel.BusinessContent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Zahra Jamshidi
 * on 10/02/2017.
 */

public class RasaApplication extends Application {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RasaApplication.class);

    @Override
    public void onCreate()
    {
        logger.debug("Rasa Application is creating");
        setupFont();

        createDummyItems();
    }

    private void setupFont() {
        logger.debug("font setup");
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/iransans-light-4.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void createDummyItems() {
        logger.debug("creating dummy items");
        AssetManager mngr= getAssets();
        try {
            InputStream inputStream=mngr.open("input/Business.txt");
            BusinessContent.makeBusiness(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
