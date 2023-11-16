package edu.illinois.cs465.traveltales.ui.add;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import edu.illinois.cs465.traveltales.R;

public class DiscardEditDialogFragment extends DialogFragment {

    // The activity that creates an instance of this dialog fragment must
    // implement this interface to receive event callbacks. Each method passes
    // the DialogFragment in case the host needs to query it.
    public interface DiscardEditDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    DiscardEditDialogListener discardEditDialogListener;

    // Override the Fragment.onAttach() method to instantiate the
    // DiscardDialogListener.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface.
        try {
            // Instantiate the DiscardDialogListener so you can send events to
            // the host.
            discardEditDialogListener = (DiscardEditDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface. Throw exception.
            throw new ClassCastException(getActivity().toString()
                    + " must implement DiscardDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_discard_edit)
                .setPositiveButton(R.string.discard, (dialog, id) -> {
                    // TODO: Continue to previous page
                    discardEditDialogListener.onDialogPositiveClick(DiscardEditDialogFragment.this);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // User cancels the dialog and stay on edit page
                    discardEditDialogListener.onDialogNegativeClick(DiscardEditDialogFragment.this);
                });
        // Create the AlertDialog object and return it.
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Button positive = ((AlertDialog) Objects.requireNonNull(getDialog())).getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setTextColor(Color.RED);
        positive.setTypeface(Typeface.DEFAULT_BOLD);
    }
}
