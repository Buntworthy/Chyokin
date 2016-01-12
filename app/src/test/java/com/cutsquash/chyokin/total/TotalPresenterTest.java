package com.cutsquash.chyokin.total;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Justin on 12/01/2016.
 */
public class TotalPresenterTest {

    @Mock
    private TotalContract.View mTotalView;

    private TotalPresenter mTotalPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mTotalPresenter = new TotalPresenter(mTotalView);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnClickAdd() throws Exception {
        // When the presenter click add
        mTotalPresenter.onClickAdd(true);

        // Show add method is called in view
        verify(mTotalView).showAdd(); // shown in the UI
    }

    @Test
    public void testOnClickNumber() throws Exception {

    }

    @Test
    public void testOnSubmit() throws Exception {

        // When the presenter click add
        mTotalPresenter.onSubmit();

        // Show add method is called in view
        verify(mTotalView).showTotal(); // shown in the UI

    }
}