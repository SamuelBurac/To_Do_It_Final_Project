package com.zybooks.todoit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.zybooks.todoit.R;

public class TestDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle
                                         savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.test_title);
        builder.setMessage(R.string.test_message);
        builder.setPositiveButton(R.string.ok, null);
        return builder.create();
    }

    public interface OnNewTaskListener {
        void OnNewTaskClick();
    }
}