package com.cutsquash.chyokin.data;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

/**
 * Created by Justin on 14/01/2016.
 */
public class DataStoreFile implements ModelContract.DataStore {

    private static final String DATA_FILENAME = "Chyokin.json";
    private Context mContext;

    public DataStoreFile(Context context) {

        this.mContext = context;
    }

    @Override
    public LinkedHashMap<Long, Integer> load() throws Exception {

        LinkedHashMap<Long, Integer> data=null;

        // Check for the file
        File file = new File(mContext.getFilesDir(), DATA_FILENAME);

        if (file.exists()) {
            // Read in the file
            FileInputStream inputStream = mContext.openFileInput(DATA_FILENAME);
            Log.d("datastore", "Found data file");
            if (inputStream.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                data = (LinkedHashMap<Long, Integer>) ois.readObject();
            } else {
                Log.d(this.toString(), "Problem with file, starting fresh");
                data = new LinkedHashMap<>();
                data.put(System.currentTimeMillis(), 0);
            }
            inputStream.close();
            Log.d("datastore", "Finished reading data");
        } else {
            // No existing file
            Log.d(this.toString(), "No existing file");
            data = new LinkedHashMap<>();
            data.put(System.currentTimeMillis(), 0);
        }
        return data;

    }

    @Override
    public void save(LinkedHashMap<Long, Integer> data) throws Exception {

        // Write a file containing the serialised hashmap
        FileOutputStream fos = mContext.openFileOutput(DATA_FILENAME, 0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();
        fos.close();

    }

    @Override
    public void delete() {
        // Delete the file
        File file = new File(mContext.getFilesDir(), DATA_FILENAME);
        file.delete();
    }
}
