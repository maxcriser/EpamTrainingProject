package com.maxcriser.cards.async.task;

import com.maxcriser.cards.async.ProgressCallback;
import com.maxcriser.cards.async.Task;

import java.io.File;

import static com.maxcriser.cards.util.Storage.removePhotoFile;

public class RemovePhoto implements Task<String, Void, Void> {

    private File file;

    public RemovePhoto(File file) {
        this.file = file;
    }

    @Override
    public Void doInBackground(String pS, ProgressCallback<Void> pVoidProgressCallback) throws Exception {
        removePhotoFile(file, pS);
        return null;
    }
}
