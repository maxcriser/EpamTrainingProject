package com.maxcriser.cards.loader.image;

import com.maxcriser.cards.async.ProgressCallback;

public abstract class ITask<Params, Progress, Result> {

    public abstract Result doInBackground(Params pParams, ProgressCallback<Progress> pProgressCallback);

}