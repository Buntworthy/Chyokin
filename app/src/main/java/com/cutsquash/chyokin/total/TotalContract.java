package com.cutsquash.chyokin.total;

/**
 * MVP contract for total savings
 */
public interface TotalContract {

    interface View {

        void showTotalView();

        void showAddView();

        void updateTotalDisplay(int total);

        void updateValueDisplay(int value);

        int getValueDisplay();
    }

    interface Presenter {

        void onCreate();

        void onPause();

        // Click "Saved" or "Wasted" buttons on total view
        void onClickSave(boolean saving);

        // Click number key on add view
        void onClickNumber();

        // Click "Submit" button on add view
        void onClickSubmit();

        void deleteData();

        void updateTotal();
    }
}
