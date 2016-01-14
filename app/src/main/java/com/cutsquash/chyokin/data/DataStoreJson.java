package com.cutsquash.chyokin.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Justin on 14/01/2016.
 */
public class DataStoreJson implements ModelContract.DataStore {

    private static final String DATA_FILENAME = "Chyokin.json";
    private static final String TIME_KEY = "time";
    private static final String VALUE_KEY = "value";

    @Override
    public LinkedHashMap<Long, Integer> load() {
        // Read in the json file
        return null;
    }

    @Override
    public void save(LinkedHashMap<Long, Integer> data) throws Exception {

        // Construct JSON array
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<Long, Integer> entry : data.entrySet() ) {

            JSONObject jsonEntry = new JSONObject();
            jsonEntry.put(TIME_KEY, entry.getKey());
            jsonEntry.put(VALUE_KEY, entry.getValue());

            jsonArray.put(jsonEntry);
        }

        // TODO Write JSON array to file

    }
}
