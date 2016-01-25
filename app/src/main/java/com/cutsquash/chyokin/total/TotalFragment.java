package com.cutsquash.chyokin.total;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cutsquash.chyokin.R;
import com.cutsquash.chyokin.data.DataStoreFile;
import com.cutsquash.chyokin.data.Model;
import com.cutsquash.chyokin.utils.AnimUtils;
import com.cutsquash.chyokin.utils.Utils;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TotalFragment extends Fragment implements TotalContract.View {

    private TotalContract.Presenter mPresenter;
    private int mCurrentValue;
    private int mTotal;

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
        LinearLayout numberPad =
                (LinearLayout) rootView.findViewById(R.id.pad_numeric);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickNumber(v.getId());

            }
        };
        setButtonListener(numberPad, listener);
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

        final View animView = getView().findViewById(R.id.add_animation);
        animView.setVisibility(View.VISIBLE);
        Animation animation = AnimUtils.animationWithCallback(
                // Animation
                AnimationUtils.loadAnimation(getContext(), R.anim.save_button_anim),
                // Callback
                new AnimUtils.AnimationCallback() {
                    @Override
                    public void callback() {
                        animView.setVisibility(View.GONE);
                        getView().findViewById(R.id.totalView).setVisibility(View.GONE);
                        getView().findViewById(R.id.addView).setVisibility(View.VISIBLE);
                    }
                });
        animView.startAnimation(animation);
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
        startCountAnimation(totalView, mTotal, total);
        mTotal = total;

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

    // Adding £ and p symbols to value
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

    // Animate increase in the total view
    private void startCountAnimation(final TextView view, int startValue, int endValue) {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(startValue, endValue);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setText(formatValue((int) animation.getAnimatedValue()));
            }
        });
        animator.start();
    }

    private void setButtonListener(ViewGroup numberPad, View.OnClickListener listener) {
        List<View> views = Utils.getAllChildren(numberPad);
        for (int childIndex = 0; childIndex < views.size(); childIndex++) {
            final View v = views.get(childIndex);
            if (v instanceof Button) {
                final Button b = (Button) v;
                b.setOnClickListener(listener);
            }
        }
    }
}
