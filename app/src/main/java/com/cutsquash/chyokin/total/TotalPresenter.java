package com.cutsquash.chyokin.total;

import android.util.Log;

import com.cutsquash.chyokin.R;
import com.cutsquash.chyokin.data.ModelContract;
import com.cutsquash.chyokin.utils.DeleteDialog;
import com.cutsquash.chyokin.utils.TargetDialog;

/**
 * Created by Justin on 11/01/2016.
 */
public class TotalPresenter
        implements TotalContract.Presenter,
                    TargetDialog.TargetDialogListener,
                    DeleteDialog.DeleteDialogListener{

    private TotalContract.View mView;
    private ModelContract.Model mModel;
    private ModelContract.Target mTarget;

    private boolean saveStatus;

    public TotalPresenter(TotalContract.View view,
                          ModelContract.Model model,
                          ModelContract.Target target) {

        this.mView = view;
        this.mModel = model;
        this.mTarget = target;
    }

    @Override
    public void onCreate() {
        mView.showTotalView(false);
        updateTotal();
    }

    @Override
    public void onPause() {
        mModel.close();
    }

    @Override
    public void onClickSave(boolean saving) {

        mView.showAddView(saving);
        saveStatus = saving;
    }

    @Override
    public void onClickNumber(int id) {
        int value;
        switch (id){
            case R.id.digit_0:
                value = 0;
                break;
            case R.id.digit_1:
                value = 1;
                break;
            case R.id.digit_2:
                value = 2;
                break;
            case R.id.digit_3:
                value = 3;
                break;
            case R.id.digit_4:
                value = 4;
                break;
            case R.id.digit_5:
                value = 5;
                break;
            case R.id.digit_6:
                value = 6;
                break;
            case R.id.digit_7:
                value = 7;
                break;
            case R.id.digit_8:
                value = 8;
                break;
            case R.id.digit_9:
                value = 9;
                break;
            case R.id.submit:
                value = -1;
                break;
            case R.id.cancel:
                value = -2;
                break;
            default:
                value = 0;
                Log.e(getClass().getSimpleName(), "Unrecognised ID");
        }

        if (value == -1) {
            // The submit button
            onClickSubmit();
        } else if (value == -2) {
            // Show Total view
            mView.showTotalView(true);
        } else {
            mView.updateValueDisplay(value);
        }
    }

    @Override
    public void onClickSubmit() {

        int value = mView.getValueDisplay();
        if (!saveStatus) { value *= -1; }
        mModel.addValue(System.currentTimeMillis(), value);
        mView.showTotalView(true);
        updateTotal();

    }

    @Override
    public void deleteData(){
        mModel.delete();
        mView.showTotalView(false);
        updateTotal();
    }

    @Override
    public void updateTotal() {
        int total = mModel.getTotal();
        mView.updateTotalDisplay(total);

        int target = mTarget.getTarget();
        if (target > 0) {
            float fraction = (float)total / target;
            Log.d("Target", Float.toString(fraction));
            mView.updateTargetDisplay(fraction);
        }
    }

    @Override
    public void setTarget(int target) {
        mTarget.setTarget(target);
        updateTotal();
    }
}
