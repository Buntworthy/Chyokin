package com.cutsquash.chyokin.data;

import org.json.JSONException;

import java.util.LinkedHashMap;

/**
 * Created by Justin on 12/01/2016.
 */
public interface ModelContract {

    interface Model {

        void open();

        void close();

        void addValue(long time, int value);

        int getTotal();
    }

    interface DataStore {

        LinkedHashMap load();

        void save(LinkedHashMap<Long, Integer> data) throws Exception;
    }
}
