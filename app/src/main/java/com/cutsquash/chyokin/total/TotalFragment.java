package com.cutsquash.chyokin.total;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cutsquash.chyokin.R;
import com.cutsquash.chyokin.data.DataStoreFile;
import com.cutsquash.chyokin.data.Model;

import java.util.Formatter;

/**
 * A placeholder fragment containing a simple view.
 */
public class TotalFragment extends Fragment implements TotalContract.View {

    private TotalContract.Presenter mPresenter;
    private int mCurrentValue;

    public TotalFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        Model model = new Model(new DataStoreFile(getContext()));
        mPresenter = new TotalPresenter(this, model);
        mPresenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_total, container, false);
        // Add listener
        rootView.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickSave(true);
            }
        });
        // Button listener
        CalculatorNumericPadLayout numberPad =
                (CalculatorNumericPadLayout) rootView.findViewById(R.id.pad_numeric);
        numberPad.setButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickNumber(v.getId());

            }
        });
        return rootView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_total, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here
        int id = item.getItemId();

        if (id == R.id.action_delete) {
            mPresenter.deleteData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void showAddView() {
        getView().findViewById(R.id.totalView).setVisibility(View.GONE);
        getView().findViewById(R.id.addView).setVisibility(View.VISIBLE);
        clearValueDisplay();
    }

    @Override
    public void showTotalView() {
        getView().findViewById(R.id.totalView).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.addView).setVisibility(View.GONE);
    }

    @Override
    public void updateTotalDisplay(int total) {
        // Set total view to display total
        TextView totalView = (TextView) getView().findViewById(R.id.totalValue);
        totalView.setText(formatValue(total));
    }

    @Override
    public void updateValueDisplay(int value) {
        int currentValue = mCurrentValue;
        if (value < 10) {
            currentValue *= 10;
            currentValue += value;
        } else {
            currentValue *= 100;
        }
        mCurrentValue = currentValue;
        String formattedValue = formatValue(currentValue);
        TextView valueDisplay = (TextView) getView().findViewById(R.id.valueDisplay);
        valueDisplay.setText(formattedValue);


    }

    @Override
    public int getValueDisplay() {
        return mCurrentValue;
    }

    // Private methods ////////////////////////////////////////////
    private void clearValueDisplay() {
        mCurrentValue = 0;
        updateValueDisplay(mCurrentValue);
    }

    private String formatValue(int value) {
        String formatted = Integer.toString(value);
        if (value < 100) { // pence
            formatted += "p";
        } else { // pounds
            formatted = new StringBuilder(formatted)
                    .insert(formatted.length() - 2, ".")
                    .insert(0, "£")
                    .toString();
        }
        return formatted;
    }
}
