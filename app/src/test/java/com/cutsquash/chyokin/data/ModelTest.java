package com.cutsquash.chyokin.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by Justin on 19/01/2016.
 */
public class ModelTest {

    ModelContract.Model mModel;
    ModelContract.DataStore mDataStore = new FakeDataStore();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mModel = new Model(mDataStore);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

        // add a value
        int value1 = 342;
        mModel.addValue(1, value1);

        // Delete the data
        mModel.delete();
        assertEquals(mModel.getTotal(), 0);

        // add a value
        int value2 = 512;
        mModel.addValue(2, value2);
        assertEquals(mModel.getTotal(), value2);

    }

    @Test
    public void testGetTotal() throws Exception {
        // Add some values
        int value1 = 134;
        int value2 = 943;
        int result = value1 + value2;

        // 0 time is 0 value
        mModel.addValue(1, value1);
        mModel.addValue(2, value2);

        assertEquals(mModel.getTotal(), result);
    }
}