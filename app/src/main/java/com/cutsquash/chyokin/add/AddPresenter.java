package com.cutsquash.chyokin.add;

/**
 * Created by Justin on 11/01/2016.
 */
public class AddPresenter implements AddContract.Presenter {

    private AddContract.View mView;

    public AddPresenter(AddContract.View view){
        this.mView = view;
    }
    @Override
    public void onClick() {
        mView.showTotal();
    }

    @Override
    public void onSubmit() {

    }
}
