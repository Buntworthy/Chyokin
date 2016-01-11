package com.cutsquash.chyokin.add;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutsquash.chyokin.R;
import com.cutsquash.chyokin.total.TotalPresenter;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFragment extends Fragment implements AddContract.View {

    private AddContract.Presenter mPresenter;

    public AddFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new AddPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        rootView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClick();
            }
        });
        return rootView;

    }

    @Override
    public void updateValue(int value) {

    }

    @Override
    public void showTotal() {
        getActivity().finish();
    }
}
