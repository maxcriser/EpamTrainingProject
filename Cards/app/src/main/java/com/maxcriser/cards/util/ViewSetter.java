package com.maxcriser.cards.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.maxcriser.cards.R;

public final class ViewSetter extends View implements View.OnTouchListener {

    private final Paint paint;
    private final int maskColor;
    private final int frameColor;
    private final int cornerColor;
    private Rect frame;
    private Point screenResolution;

    private int lastX, lastY;

    private static final int MIN_FRAME_WIDTH = 50; // originally 240
    private static final int MIN_FRAME_HEIGHT = 20; // originally 240
    private static final int MAX_FRAME_WIDTH = 800; // originally 480
    private static final int MAX_FRAME_HEIGHT = 600; // originally 360

    public ViewSetter(Context context) {
        super(context);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maskColor = Color.TRANSPARENT;
        frameColor = Color.BLACK;
        cornerColor = Color.WHITE;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        screenResolution = new Point(width, height);
        calcFramingRect();
    }

    public Rect getFramingRect() {
        return frame;
    }

    private void adjustFramingRect(int deltaWidth, int deltaHeight,
                                   Point screenResolution) {

        // Set maximum and minimum sizes
        if ((frame.width() + deltaWidth > screenResolution.x - 4)
                || (frame.width() + deltaWidth < 50)) {
            deltaWidth = 0;
        }
        if ((frame.height() + deltaHeight > screenResolution.y - 4)
                || (frame.height() + deltaHeight < 50)) {
            deltaHeight = 0;
        }

        int newWidth = frame.width() + deltaWidth;
        int newHeight = frame.height() + deltaHeight;
        int leftOffset = (screenResolution.x - newWidth) / 2;
        int topOffset = (screenResolution.y - newHeight) / 2;
        frame = new Rect(leftOffset, topOffset, leftOffset + newWidth,
                topOffset + newHeight);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (frame == null) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Draw the exterior (i.e. outside the framing rect) darkened
        paint.setColor(Color.argb(153, 255, 255, 255));
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
                paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);

        // Draw a two pixel solid border inside the framing rect
        paint.setAlpha(0);
        paint.setStyle(Style.FILL);
        paint.setColor(frameColor);
        canvas.drawRect(frame.left, frame.top, frame.right + 1, frame.top + 2,
                paint);
        canvas.drawRect(frame.left, frame.top + 2, frame.left + 2,
                frame.bottom - 1, paint);
        canvas.drawRect(frame.right - 1, frame.top, frame.right + 1,
                frame.bottom - 1, paint);
        canvas.drawRect(frame.left, frame.bottom - 1, frame.right + 1,
                frame.bottom + 1, paint);

        // Draw the framing rect corner UI elements
        paint.setColor(cornerColor);
        canvas.drawRect(frame.left - 15, frame.top - 15, frame.left + 15,
                frame.top, paint);
        canvas.drawRect(frame.left - 15, frame.top, frame.left, frame.top + 15,
                paint);
        canvas.drawRect(frame.right - 15, frame.top - 15, frame.right + 15,
                frame.top, paint);
        canvas.drawRect(frame.right, frame.top - 15, frame.right + 15,
                frame.top + 15, paint);
        canvas.drawRect(frame.left - 15, frame.bottom, frame.left + 15,
                frame.bottom + 15, paint);
        canvas.drawRect(frame.left - 15, frame.bottom - 15, frame.left,
                frame.bottom, paint);
        canvas.drawRect(frame.right - 15, frame.bottom, frame.right + 15,
                frame.bottom + 15, paint);
        canvas.drawRect(frame.right, frame.bottom - 15, frame.right + 15,
                frame.bottom + 15, paint);

    }

    private void calcFramingRect() {
        if (frame == null) {
            int width = screenResolution.x * 3 / 5;
            if (width < MIN_FRAME_WIDTH) {
                width = MIN_FRAME_WIDTH;
            } else if (width > MAX_FRAME_WIDTH) {
                width = MAX_FRAME_WIDTH;
            }
            int height = screenResolution.y * 1 / 5;
            if (height < MIN_FRAME_HEIGHT) {
                height = MIN_FRAME_HEIGHT;
            } else if (height > MAX_FRAME_HEIGHT) {
                height = MAX_FRAME_HEIGHT;
            }
            int leftOffset = (screenResolution.x - width) / 2;
            int topOffset = (screenResolution.y - height) / 2;
            frame = new Rect(leftOffset, topOffset, leftOffset + width,
                    topOffset + height);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = -1;
                lastY = -1;
                return true;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int) event.getX();
                int currentY = (int) event.getY();

                try {
                    Rect rect = getFramingRect();

                    final int BUFFER = 50;
                    final int BIG_BUFFER = 60;
                    if (lastX >= 0) {
                        if (((currentX >= rect.left - BIG_BUFFER && currentX <= rect.left
                                + BIG_BUFFER) || (lastX >= rect.left - BIG_BUFFER && lastX <= rect.left
                                + BIG_BUFFER))
                                && ((currentY <= rect.top + BIG_BUFFER && currentY >= rect.top
                                - BIG_BUFFER) || (lastY <= rect.top
                                + BIG_BUFFER && lastY >= rect.top
                                - BIG_BUFFER))) {
                            adjustFramingRect(2 * (lastX - currentX),
                                    2 * (lastY - currentY), screenResolution);
                        } else if (((currentX >= rect.right - BIG_BUFFER && currentX <= rect.right
                                + BIG_BUFFER) || (lastX >= rect.right - BIG_BUFFER && lastX <= rect.right
                                + BIG_BUFFER))
                                && ((currentY <= rect.top + BIG_BUFFER && currentY >= rect.top
                                - BIG_BUFFER) || (lastY <= rect.top
                                + BIG_BUFFER && lastY >= rect.top
                                - BIG_BUFFER))) {
                            adjustFramingRect(2 * (currentX - lastX),
                                    2 * (lastY - currentY), screenResolution);
                        } else if (((currentX >= rect.left - BIG_BUFFER && currentX <= rect.left
                                + BIG_BUFFER) || (lastX >= rect.left - BIG_BUFFER && lastX <= rect.left
                                + BIG_BUFFER))
                                && ((currentY <= rect.bottom + BIG_BUFFER && currentY >= rect.bottom
                                - BIG_BUFFER) || (lastY <= rect.bottom
                                + BIG_BUFFER && lastY >= rect.bottom
                                - BIG_BUFFER))) {
                            adjustFramingRect(2 * (lastX - currentX),
                                    2 * (currentY - lastY), screenResolution);
                        } else if (((currentX >= rect.right - BIG_BUFFER && currentX <= rect.right
                                + BIG_BUFFER) || (lastX >= rect.right - BIG_BUFFER && lastX <= rect.right
                                + BIG_BUFFER))
                                && ((currentY <= rect.bottom + BIG_BUFFER && currentY >= rect.bottom
                                - BIG_BUFFER) || (lastY <= rect.bottom
                                + BIG_BUFFER && lastY >= rect.bottom
                                - BIG_BUFFER))) {
                            adjustFramingRect(2 * (currentX - lastX),
                                    2 * (currentY - lastY), screenResolution);
                        } else if (((currentX >= rect.left - BUFFER && currentX <= rect.left
                                + BUFFER) || (lastX >= rect.left - BUFFER && lastX <= rect.left
                                + BUFFER))
                                && ((currentY <= rect.bottom && currentY >= rect.top) || (lastY <= rect.bottom && lastY >= rect.top))) {
                            adjustFramingRect(2 * (lastX - currentX), 0,
                                    screenResolution);
                        } else if (((currentX >= rect.right - BUFFER && currentX <= rect.right
                                + BUFFER) || (lastX >= rect.right - BUFFER && lastX <= rect.right
                                + BUFFER))
                                && ((currentY <= rect.bottom && currentY >= rect.top) || (lastY <= rect.bottom && lastY >= rect.top))) {
                            adjustFramingRect(2 * (currentX - lastX), 0,
                                    screenResolution);
                        } else if (((currentY <= rect.top + BUFFER && currentY >= rect.top
                                - BUFFER) || (lastY <= rect.top + BUFFER && lastY >= rect.top
                                - BUFFER))
                                && ((currentX <= rect.right && currentX >= rect.left) || (lastX <= rect.right && lastX >= rect.left))) {
                            adjustFramingRect(0, 2 * (lastY - currentY),
                                    screenResolution);
                        } else if (((currentY <= rect.bottom + BUFFER && currentY >= rect.bottom
                                - BUFFER) || (lastY <= rect.bottom + BUFFER && lastY >= rect.bottom
                                - BUFFER))
                                && ((currentX <= rect.right && currentX >= rect.left) || (lastX <= rect.right && lastX >= rect.left))) {
                            adjustFramingRect(0, 2 * (currentY - lastY),
                                    screenResolution);
                        }
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                v.invalidate();
                lastX = currentX;
                lastY = currentY;
                return true;
            case MotionEvent.ACTION_UP:
                lastX = -1;
                lastY = -1;
                return true;
        }
        return false;
    }
}