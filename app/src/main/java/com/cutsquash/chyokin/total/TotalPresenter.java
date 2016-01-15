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
    public void onClickAdd(boolean saving) {

        mView.showAdd();
    }

    @Override
    public void addSaving(int value) {
        mModel.addValue(System.currentTimeMillis(), value);
    }

    @Override
    public void onSubmit() {

        mView.showTotal();
        mView.updateTotal(mModel.getTotal());
    }

    @Override
    public void loadData() {
        mModel.open();
    }

    @Override
    public void saveData() {
        mModel.close();
    }
}
