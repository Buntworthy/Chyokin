package com.cutsquash.chyokin.total;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cutsquash.chyokin.R;
import com.cutsquash.chyokin.data.DataStoreFile;
import com.cutsquash.chyokin.data.Model;

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
        Model model = new Model(new DataStoreFile(getContext()));
        mPresenter = new TotalPresenter(this, model);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_total, container, false);
        rootView.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickAdd(true);
            }
        });

        rootView.findViewById(R.id.button_total).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO is this a memory leak?
                EditText editText = (EditText) rootView.findViewById(R.id.editValue);
                int value = Integer.parseInt(editText.getText().toString());
                mPresenter.addSaving(value);
                mPresenter.onSubmit();
            }
        });
        return rootView;

    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.saveData();
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
        TextView totalView = (TextView) getView().findViewById(R.id.totalView);
        totalView.setText(Integer.toString(total));
    }

    @Override
    public void updateValue(int value) {
        // Set value view to display value

    }
}
