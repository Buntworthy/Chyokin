package com.cutsquash.chyokin.add;

/**
 * Created by Justin on 11/01/2016.
 */
public interface AddContract {

    interface View {

        void updateValue(int value);

        void showTotal();
    }

    interface Presenter {

        void onClick();

        void onSubmit();
    }
}
