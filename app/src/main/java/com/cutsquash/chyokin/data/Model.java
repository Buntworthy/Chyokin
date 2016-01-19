package com.cutsquash.chyokin.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Justin on 14/01/2016.
 */
public class Model implements ModelContract.Model {

    private ModelContract.DataStore mDataStore;
    private LinkedHashMap<Long, Integer> mData;

    public Model(ModelContract.DataStore dataStore) {

        this.mDataStore = dataStore;
        // load data from the dataStore
        open();
    }

    @Override
    public void open() {
        try {
            mData = mDataStore.load();
        } catch (Exception e) {
            // TODO deal with exception
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            mDataStore.save(mData);
        } catch (Exception e) {
            // TODO deal with exception
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        mDataStore.delete();
        open();
    }

    @Override
    public void addValue(long time, int value) {
        mData.put(time, value);
    }

    @Override
    public int getTotal() {

        int total = 0;
        for (Map.Entry<Long, Integer> entry : mData.entrySet() ) {
            total += entry.getValue();
        }
        return total;

    }
}
