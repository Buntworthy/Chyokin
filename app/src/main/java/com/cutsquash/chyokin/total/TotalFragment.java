package com.cutsquash.chyokin.total;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutsquash.chyokin.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TotalFragment extends Fragment implements TotalContract.View {

    private TotalContract.Presenter mPresenter;

    public TotalFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new TotalPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_total, container, false);
        rootView.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickAdd(true);
            }
        });

        rootView.findViewById(R.id.button_total).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onSubmit();
            }
        });
        return rootView;

    }

    @Override
    public void showAdd() {
        getView().findViewById(R.id.button_add).setVisibility(View.GONE);
        getView().findViewById(R.id.button_total).setVisibility(View.VISIBLE);
    }

    @Override
    public void showTotal() {
        getView().findViewById(R.id.button_add).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.button_total).setVisibility(View.GONE);
    }

    @Override
    public void updateTotal(int total) {
        // Set total view to display total
    }

    @Override
    public void updateValue(int value) {
        // Set value view to display value

    }
}
