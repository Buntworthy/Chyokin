package com.cutsquash.chyokin.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.cutsquash.chyokin.R;

/**
 * Created by Justin on 02/02/2016.
 */
public class TargetDialog extends DialogFragment {

    // Interface to communicate category selection
    public interface TargetDialogListener {
        public void setTarget(int target);
    }

    // Use this instance of the interface to deliver action events
    private TargetDialogListener mListener;

    public void setListener(TargetDialogListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        // TODO add text changed listener for currency formatting

        builder.setView(inflater.inflate(R.layout.dialog_target, null))
                .setMessage(getString(R.string.target_dialog_message))
                .setPositiveButton(R.string.target_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        EditText targetValue = (EditText) ((AlertDialog) dialog)
                                .findViewById(R.id.target);
                        // Set the target (convert to pence)
                        mListener.setTarget(
                                100*Integer.parseInt(targetValue.getText().toString()));

                    }
                })
                .setNegativeButton(R.string.target_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog, do nothing
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
