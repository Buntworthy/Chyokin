package com.cutsquash.chyokin.data;

import java.util.LinkedHashMap;

/**
 * Created by Justin on 19/01/2016.
 */
public class FakeDataStore implements ModelContract.DataStore {

    LinkedHashMap<Long, Integer> mData = null;

    @Override
    public LinkedHashMap load() throws Exception {
        if (mData != null) {
            return (LinkedHashMap) mData.clone();
        } else {
            LinkedHashMap<Long, Integer> data;
            data = new LinkedHashMap<>();
            data.put(0L, 0);
            return data;
        }
    }

    @Override
    public void save(LinkedHashMap<Long, Integer> data) throws Exception {
        mData = (LinkedHashMap<Long, Integer>) data.clone();
    }

    @Override
    public void delete() {
        mData = null;
    }
}
