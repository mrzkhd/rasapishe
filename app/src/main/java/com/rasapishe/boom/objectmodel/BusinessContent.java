package com.rasapishe.boom.objectmodel;

import android.content.res.AssetManager;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class BusinessContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Business> ITEMS = new ArrayList<Business>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Business> ITEM_MAP = new HashMap<String, Business>();


    private static void addItem(Business item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    public static void makeBusiness(InputStream is){
        Scanner inputReader = new Scanner(is, "UTF-8");
        inputReader.nextLine();
        while(inputReader.hasNextLine()){
            String line = inputReader.nextLine();
            String[] tokens = line.split(",");
            if(tokens.length != 5) continue;
            addItem(new Business(tokens[0], tokens[1], BusinessType.getById(Integer.parseInt(tokens[2])), tokens[3], tokens[4], "", ""));

        }
    }

    public static List<Business> findBusiness(String query) {
        if (query == null || query.trim().length() == 0)
            return ITEMS;

        query = query.trim();
        List<Business> result = new ArrayList<Business>();
        Method[] methods = Business.class.getMethods();


        for (Business business : ITEMS) {
            for (Method method : methods) {
                String name = method.getName();
                if (name.startsWith("get") && !name.equalsIgnoreCase("getClass")) {
                    try {
                        Object value = method.invoke(business);
                        if (value.toString().contains(query)) {
                            result.add(business);
                            break;
                        }
                    }  catch (InvocationTargetException e) {
                        //ignore it
                    } catch (IllegalAccessException e) {
                        //ignore it
                    }
                }
            }
        }
        return result;
    }

}
