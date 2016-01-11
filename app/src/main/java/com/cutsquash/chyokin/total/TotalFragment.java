package com.cutsquash.chyokin.total;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutsquash.chyokin.R;
import com.cutsquash.chyokin.add.AddActivity;

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
        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickAdd(true);
            }
        });
        return rootView;

    }

    @Override
    public void updateTotal(int total) {
        // Update the total display
    }

    @Override
    public void showAdd() {
        // Start intent to add activity
        Intent intent = new Intent(getActivity(), AddActivity.class);
        startActivity(intent);
    }
}
