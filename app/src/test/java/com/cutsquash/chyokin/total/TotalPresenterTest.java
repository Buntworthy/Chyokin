package com.cutsquash.chyokin.total;

import com.cutsquash.chyokin.data.ModelContract;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by Justin on 12/01/2016.
 */
public class TotalPresenterTest {

    @Mock
    private TotalContract.View mTotalView;
    @Mock
    private ModelContract.Model mModel;

    private TotalPresenter mTotalPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mTotalPresenter = new TotalPresenter(mTotalView, mModel);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnClickAdd() throws Exception {
        // When the presenter click add
        mTotalPresenter.onClickSave(true);

        // Show add method is called in view
        verify(mTotalView).showAddView(); // shown in the UI
    }

    @Test
    public void testOnSubmit() throws Exception {

        // When the presenter click add
        mTotalPresenter.onClickSubmit();

        // Show add method is called in view
        verify(mTotalView).showTotalView(); // shown in the UI

    }
}