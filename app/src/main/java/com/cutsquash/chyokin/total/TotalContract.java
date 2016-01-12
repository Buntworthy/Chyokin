package com.cutsquash.chyokin.total;

/**
 * MVP contract for total savings
 */
public interface TotalContract {

    interface View {

        void updateTotal(int total);

        void showAdd();

        void updateValue(int value);

        void showTotal();
    }

    interface Presenter {

        void onClickAdd(boolean saving);

        void onClickNumber();

        void onSubmit();

    }
}
