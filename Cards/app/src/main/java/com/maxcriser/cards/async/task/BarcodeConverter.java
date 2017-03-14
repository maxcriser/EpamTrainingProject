package com.maxcriser.cards.async.task;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;
import com.maxcriser.cards.barcode.BarcodeBuilder;

public class BarcodeConverter implements Task<Void, Void, String> {

    private final String barcode;

    public BarcodeConverter(final String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String doInBackground(final Void pVoid, final ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        try {
            final BarcodeBuilder mBarcodeBuilder = new BarcodeBuilder(barcode);
            return mBarcodeBuilder.getCode();
        } catch (final Exception e) {
            return null;
        }
    }
}