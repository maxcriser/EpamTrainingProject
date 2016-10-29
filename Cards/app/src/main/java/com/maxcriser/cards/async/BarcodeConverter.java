package com.maxcriser.cards.async;

import com.maxcriser.cards.barcode.EAN13CodeBuilder;

public class BarcodeConverter implements Task<String, String, String> {

    @Override
    public String doInBackground(String pS, ProgressCallback<String> pStringProgressCallback) throws Exception {
        EAN13CodeBuilder mEAN13CodeBuilder = new EAN13CodeBuilder(pS);
        String converted = mEAN13CodeBuilder.getCode();
        return converted;
    }
}
