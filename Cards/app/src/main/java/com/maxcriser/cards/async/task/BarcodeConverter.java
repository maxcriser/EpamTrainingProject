package com.maxcriser.cards.async.task;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;
import com.maxcriser.cards.barcode.BarcodeBuilder;

public class BarcodeConverter implements Task<String, String, String> {

    @Override
    public String doInBackground(final String pS, final ProgressCallback<String> pStringProgressCallback) throws Exception {
        final BarcodeBuilder mBarcodeBuilder = new BarcodeBuilder(pS);
        return mBarcodeBuilder.getCode();
    }
}
