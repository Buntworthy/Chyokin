package com.cutsquash.chyokin.total;

/**
 * Created by Justin on 11/01/2016.
 */
public class TotalPresenter implements TotalContract.Presenter {

    private TotalContract.View mView;

    public TotalPresenter(TotalContract.View view) {
        this.mView = view;
    }
    @Override
    public void onClickAdd(boolean saving) {
        mView.showAdd();
    }
}
