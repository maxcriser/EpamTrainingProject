package com.maxcriser.cards.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.activities.NFCReaderActivity;

import java.io.IOException;

public class NFCReadFragment extends DialogFragment {

    public static final String TAG = NFCReadFragment.class.getSimpleName();

    public static NFCReadFragment newInstance() {

        return new NFCReadFragment();
    }

    private TextView mTvMessage;
    private Listener mListener;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_read, container, false);
        initViews(view);
        return view;
    }

    private void initViews(final View view) {

        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        mListener = (NFCReaderActivity) context;
        mListener.onDialogDisplayed();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onDialogDismissed();
    }

    public void onNfcDetected(final Ndef ndef1) {

        readFromNFC(ndef1);
    }

    private void readFromNFC(final Ndef ndef1) {

        try {
            ndef1.connect();
            final NdefMessage ndefMessage = ndef1.getNdefMessage();
            final String message = new String(ndefMessage.getRecords()[0].getPayload());
            Log.d(TAG, "readFromNFC: " + message);
            mTvMessage.setText(message);

        } catch (IOException | FormatException e) {
            Log.d("ERROR", e.toString());
        } finally {
            try {
                ndef1.close();
            } catch (final IOException pE) {
                Log.d("Error", pE.toString());
            }
        }
    }
}
