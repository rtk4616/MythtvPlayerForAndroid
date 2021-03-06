package org.mythtv.android.presentation.view.fragment.phone;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import org.mythtv.android.R;

/**
 *
 *
 *
 * @author dmfrey
 *
 * Created on 7/9/16.
 */

public class CastNotReadyDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View dialogView = inflater.inflate( R.layout.fragment_phone_cast_not_ready, null );

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );

        builder
                .setTitle( R.string.app_name )
                .setPositiveButton( R.string.close, (dialog, which) -> dialog.dismiss())
                .setView( dialogView );

        return builder.create();
    }

}
