package com.cutsquash.chyokin.total;

import android.util.Log;

import com.cutsquash.chyokin.R;
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
    public void onClickNumber(int id) {
        switch (id){
            case R.id.digit_0:
                Log.d("presenter", "digit 0!");
                break;
            case R.id.digit_00:
                Log.d("presenter", "digit 00!");
                break;
        }

        mView.updateValueDisplay(0);
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
