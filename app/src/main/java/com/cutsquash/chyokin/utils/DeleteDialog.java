package com.cutsquash.chyokin.utils;

/**
 * Created by Justin on 31/01/2016.
 */

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.cutsquash.chyokin.R;


/**
 * Created by Justin on 29/12/2015.
 */
public class DeleteDialog extends DialogFragment {

    // Interface to communicate category selection
    public interface DeleteDialogListener {
        public void deleteData();
    }

    // Use this instance of the interface to deliver action events
    private DeleteDialogListener mListener;

    public void setListener(DeleteDialogListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_message)
                .setPositiveButton(R.string.target_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.deleteData();
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

