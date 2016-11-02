package com.maxcriser.cards.async.task;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;
import com.maxcriser.cards.barcode.BarcodeBuilder;

public class BarcodeConverter implements Task<String, String, String> {

    @Override
    public String doInBackground(String pS, ProgressCallback<String> pStringProgressCallback) throws Exception {
        BarcodeBuilder mBarcodeBuilder = new BarcodeBuilder(pS);
        String converted = mBarcodeBuilder.getCode();
        return converted;
    }
}
