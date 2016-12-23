package com.maxcriser.cards.async.task;

import android.net.Uri;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;

import java.io.File;

public class RemovePhoto implements Task<Uri, Void, Void> {

    @Override
    public Void doInBackground(final Uri uri, final ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        final File fileToDelete = new File(uri.getPath());
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
        return null;
    }
}
