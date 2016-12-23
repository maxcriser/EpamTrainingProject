package com.maxcriser.cards.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.activities.NFCReaderActivity;

import java.io.IOException;
import java.nio.charset.Charset;

public class NFCWriteFragment extends DialogFragment {

    public static final String TAG = NFCWriteFragment.class.getSimpleName();

    public static NFCWriteFragment newInstance() {

        return new NFCWriteFragment();
    }

    private TextView mTvMessage;
    private ProgressBar mProgress;
    private Listener mListener;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_write, container, false);
        initViews(view);
        return view;
    }

    private void initViews(final View view) {

        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
        mProgress = (ProgressBar) view.findViewById(R.id.progress);
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

    public void onNfcDetected(final Ndef ndef1, final String messageToWrite) {

        mProgress.setVisibility(View.VISIBLE);
        writeToNfc(ndef1, messageToWrite);
    }

    private void writeToNfc(final Ndef ndef1, final String message) {

        mTvMessage.setText(getString(R.string.message_write_progress));
        if (ndef1 != null) {

            try {
                ndef1.connect();
                final NdefRecord mimeRecord = NdefRecord.createMime("text/plain", message.getBytes(Charset.forName("US-ASCII")));
                ndef1.writeNdefMessage(new NdefMessage(mimeRecord));
                //TODO move to final
//                ndef1.close();
                //Write Successful
                mTvMessage.setText(getString(R.string.message_write_success));

            } catch (IOException | FormatException e) {
                Log.d("ERROR", e.toString());
                mTvMessage.setText(getString(R.string.message_write_error));

            } finally {
                mProgress.setVisibility(View.GONE);
                try {
                    ndef1.close();
                } catch (IOException pE) {
                    Log.d("ERROR", pE.toString());
                }
            }
        }
    }
}
