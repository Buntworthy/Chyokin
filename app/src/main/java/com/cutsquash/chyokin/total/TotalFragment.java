package com.cutsquash.chyokin.total;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cutsquash.chyokin.R;
import com.cutsquash.chyokin.about.AboutActivity;
import com.cutsquash.chyokin.data.DataStoreFile;
import com.cutsquash.chyokin.data.Model;
import com.cutsquash.chyokin.data.ModelContract;
import com.cutsquash.chyokin.data.Target;
import com.cutsquash.chyokin.utils.AnimUtils;
import com.cutsquash.chyokin.utils.DeleteDialog;
import com.cutsquash.chyokin.utils.TargetDialog;
import com.cutsquash.chyokin.utils.Utils;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TotalFragment extends Fragment implements TotalContract.View {

    private TotalContract.Presenter mPresenter;
    private int mCurrentValue;
    private int mTotal;
    private boolean saveStatus;

    public TotalFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        Model model = new Model(new DataStoreFile(getContext()));
        ModelContract.Target target = new Target(getActivity());
        mPresenter = new TotalPresenter(this, model, target);
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
        // Waste listener
        rootView.findViewById(R.id.button_waste).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickSave(false);
            }
        });
        // Button listener
        LinearLayout numberPad =
                (LinearLayout) rootView.findViewById(R.id.pad_numeric);
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickNumber(v.getId());

            }
        };
        setOnButtons(numberPad,
                new ButtonMethod() {
                    @Override
                    public void run(Button b) {
                        b.setOnClickListener(listener);
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
            DeleteDialog dialog = new DeleteDialog();
            dialog.setListener((DeleteDialog.DeleteDialogListener) mPresenter);
            dialog.show(getFragmentManager(), "deleteConfirm");

            return true;
        } else if (id == R.id.action_setTarget) {
            TargetDialog dialog = new TargetDialog();
            dialog.setListener((TargetDialog.TargetDialogListener) mPresenter);
            dialog.show(getFragmentManager(), "setTarget");
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(getContext(), AboutActivity.class);
            startActivity(intent);
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
    public void showAddView(final boolean save) {

        saveStatus = save;
        final View animView;
        if (save) {
            animView = getView().findViewById(R.id.add_animation);
            } else {
            animView = getView().findViewById(R.id.waste_animation);
        }
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
                        setViewColours(save);
                        animateShowAddView();
                    }
                });
        animView.startAnimation(animation);
        clearValueDisplay();

    }

    @Override
    public void showTotalView(boolean withAnimation) {
        getView().findViewById(R.id.totalView).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.addView).setVisibility(View.GONE);
        setViewColours(true);

        if (withAnimation) {
            final View animView;
            if (saveStatus) {
                animView = getView().findViewById(R.id.add_animation);
            } else {
                animView = getView().findViewById(R.id.waste_animation);
            }
            animView.setVisibility(View.VISIBLE);
            Animation animation = AnimUtils.animationWithCallback(
                    // Animation
                    AnimationUtils.loadAnimation(getContext(), R.anim.save_button_reverse_anim),
                    // Callback
                    new AnimUtils.AnimationCallback() {
                        @Override
                        public void callback() {
                            animView.setVisibility(View.GONE);
                        }
                    });
            animView.startAnimation(animation);
        }
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
    public void updateTargetDisplay(float fraction) {
        ProgressBar bar = (ProgressBar) getView().findViewById(R.id.progress_bar);
        float progress = fraction * bar.getMax();
        bar.setProgress((int) progress);
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

    private void animateShowAddView() {

        View addView = getView().findViewById(R.id.addView);
        addView.setVisibility(View.VISIBLE);
        List<View> views = Utils.getAllChildren(addView);
        int buttonCount = 0;
        for (int childIndex = 0; childIndex < views.size(); childIndex++) {
            final View v = views.get(childIndex);
            if (v instanceof Button) {
                final Button b = (Button) v;
                Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.number_button_anim);
                a.setStartOffset(10 * (buttonCount++));
                b.startAnimation(a);
            }
        }

    }

    private void setViewColours(boolean save) {
        final int colourToSet;
        View rootView = getView();

        // Choose a colour
        if (save) {
            colourToSet = ContextCompat.getColor(getContext(), R.color.colorPrimarySave);
        } else {
            colourToSet = ContextCompat.getColor(getContext(), R.color.colorPrimaryWaste);
        }

        // Set colours
        rootView.findViewById(R.id.addView).setBackgroundColor(colourToSet);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(colourToSet));

        // Set the number pad buttons;
        setOnButtons((ViewGroup) rootView.findViewById(R.id.pad_numeric),
                new ButtonMethod() {
                    @Override
                    public void run(Button b) {
                        b.setTextColor(colourToSet);
                    }
                });

        // Set the text correctly
        TextView caption = (TextView) rootView.findViewById(R.id.valueCaption);
        if (save) {
            caption.setText(R.string.valueSave_text);
        } else {
            caption.setText(R.string.valueWaste_text);
        }

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

    interface ButtonMethod {
        void run(Button b);
    }

    private void setOnButtons(ViewGroup numberPad, ButtonMethod buttonMethod) {
        List<View> views = Utils.getAllChildren(numberPad);
        for (int childIndex = 0; childIndex < views.size(); childIndex++) {
            final View v = views.get(childIndex);
            if (v instanceof Button) {
                final Button b = (Button) v;
                buttonMethod.run(b);
            }
        }
    }
}
