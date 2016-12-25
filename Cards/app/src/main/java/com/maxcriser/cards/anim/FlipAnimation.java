package com.maxcriser.cards.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FlipAnimation extends Animation {

    private Camera camera;
    private final View btnStart;
    private final View btnFinish;
    private float centerX;
    private float centerY;
    private boolean isReverse;

    public FlipAnimation(final View btnStart, final View btnFinish) {
        isReverse = false;
        this.btnStart = btnStart;
        this.btnFinish = btnFinish;

        final int DURATION_MILLIS_FLIP = 700;
        setDuration(DURATION_MILLIS_FLIP);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public void initialize(final int width, final int height, final int parentWidth, final int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX = width / 2;
        centerY = height / 2;
        camera = new Camera();
    }

    @Override
    protected void applyTransformation(final float interpolatedTime, final Transformation t) {
        final double DEGREES_MATH = 180.0;
        float degrees = (float) (DEGREES_MATH * Math.PI * interpolatedTime / Math.PI);

        final float FLOAT_HALF = 0.5f;
        if (interpolatedTime >= FLOAT_HALF) {
            if (isReverse) {
                degrees -= DEGREES_MATH;
                btnFinish.setVisibility(View.GONE);
                btnStart.setVisibility(View.VISIBLE);
            } else {
                degrees -= DEGREES_MATH;
                btnFinish.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
            }
        }

        final Matrix matrix = t.getMatrix();
        camera.save();
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }

    public void setReverse() {
        isReverse = !isReverse;
    }
}
