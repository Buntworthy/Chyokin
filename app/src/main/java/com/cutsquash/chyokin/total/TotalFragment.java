package com.cutsquash.chyokin.total;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
        hasOptionsMenu();
        Model model = new Model(new DataStoreFile(getContext()));
        mPresenter = new TotalPresenter(this, model);
        mPresenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_total, container, false);
        rootView.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickSave(true);
            }
        });

        rootView.findViewById(R.id.button_total).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickSubmit();
            }
        });
        return rootView;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        totalView.setText(Integer.toString(total));
    }

    @Override
    public void updateValueDisplay(int value) {
        // TODO Set value view to display value

    }

    @Override
    public int getValueDisplay() {
        EditText editText = (EditText) getView().findViewById(R.id.editValue);
        return Integer.parseInt(editText.getText().toString());
    }
}
