package com.cutsquash.chyokin.total;

import com.cutsquash.chyokin.data.ModelContract;

/**
 * Created by Justin on 11/01/2016.
 */
public class TotalPresenter implements TotalContract.Presenter {

    private TotalContract.View mView;
    private ModelContract.Model mModel;

    public TotalPresenter(TotalContract.View view,
                          ModelContract.Model model) {

        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void onCreate() {
        mModel.open();
        mView.showTotalView();
        updateTotal();
    }

    @Override
    public void onPause() {
        mModel.close();
    }

    @Override
    public void onClickSave(boolean saving) {

        mView.showAddView();
    }

    @Override
    public void onClickNumber() {
        int value = 0; //TODO get value of clicked number
        mView.updateValueDisplay(value);
    }

    @Override
    public void onClickSubmit() {

        int value = mView.getValueDisplay();
        mModel.addValue(System.currentTimeMillis(), value);
        mView.showTotalView();
        updateTotal();

    }

    @Override
    public void deleteData(){
        mModel.delete();
        mView.showTotalView();
        updateTotal();
    }

    @Override
    public void updateTotal() {
        int total = mModel.getTotal();
        mView.updateTotalDisplay(total);
    }
}
