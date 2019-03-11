package com.xitric.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ColorButtons extends View {

    private final Paint TOP_LEFT_COLOR = new Paint();
    private final Paint TOP_RIGHT_COLOR = new Paint();
    private final Paint BOTTOM_LEFT_COLOR = new Paint();
    private final Paint BOTTOM_RIGHT_COLOR = new Paint();
    private final Paint BORDER_COLOR = new Paint();

    private final Rect TOP_LEFT_RECT;
    private final Rect TOP_RIGHT_RECT;
    private final Rect BOTTOM_LEFT_RECT;
    private final Rect BOTTOM_RIGHT_RECT;
    private final RectF CIRCLE_RECT;

    private Paint circleColor = new Paint();
    private int circleX;
    private int circleY;

    public ColorButtons(Context context, AttributeSet attrs) {
        super(context, attrs);

        TOP_LEFT_COLOR.setARGB(255, 255, 0, 0);
        TOP_RIGHT_COLOR.setARGB(255, 0, 255, 0);
        BOTTOM_LEFT_COLOR.setARGB(255, 0, 0, 255);
        BOTTOM_RIGHT_COLOR.setARGB(255, 255, 255, 0);
        BORDER_COLOR.setColor(Color.BLACK);
        BORDER_COLOR.setStyle(Paint.Style.STROKE);
        BORDER_COLOR.setStrokeWidth(10);

        TOP_LEFT_RECT = new Rect(0, 0, 0, 0);
        TOP_RIGHT_RECT = new Rect(0, 0, 0, 0);
        BOTTOM_LEFT_RECT = new Rect(0, 0, 0, 0);
        BOTTOM_RIGHT_RECT = new Rect(0, 0, 0, 0);
        CIRCLE_RECT = new RectF(0, 0, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();

        TOP_LEFT_RECT.set(0, 0, w / 2, h / 2);
        TOP_RIGHT_RECT.set(w / 2, 0, w, h / 2);
        BOTTOM_LEFT_RECT.set(0, h / 2, w / 2, h);
        BOTTOM_RIGHT_RECT.set(w / 2, h / 2, w, h);
        CIRCLE_RECT.set(circleX - 200, circleY - 200, circleX + 200, circleY + 200);

        canvas.drawRect(TOP_LEFT_RECT, TOP_LEFT_COLOR);
        canvas.drawRect(TOP_RIGHT_RECT, TOP_RIGHT_COLOR);
        canvas.drawRect(BOTTOM_LEFT_RECT, BOTTOM_LEFT_COLOR);
        canvas.drawRect(BOTTOM_RIGHT_RECT, BOTTOM_RIGHT_COLOR);

        canvas.drawRect(TOP_LEFT_RECT, BORDER_COLOR);
        canvas.drawRect(TOP_RIGHT_RECT, BORDER_COLOR);
        canvas.drawRect(BOTTOM_LEFT_RECT, BORDER_COLOR);
        canvas.drawRect(BOTTOM_RIGHT_RECT, BORDER_COLOR);

        canvas.drawOval(CIRCLE_RECT, circleColor);
        canvas.drawOval(CIRCLE_RECT, BORDER_COLOR);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        circleX = (int) event.getX();
        circleY = (int) event.getY();

        int x = (int) (getWidth() - event.getX());
        int y = (int) (getHeight() - event.getY());

        if (TOP_LEFT_RECT.contains(x, y)) {
            circleColor = TOP_LEFT_COLOR;
        }
        if (TOP_RIGHT_RECT.contains(x, y)) {
            circleColor = TOP_RIGHT_COLOR;
        }
        if (BOTTOM_LEFT_RECT.contains(x, y)) {
            circleColor = BOTTOM_LEFT_COLOR;
        }
        if (BOTTOM_RIGHT_RECT.contains(x, y)) {
            circleColor = BOTTOM_RIGHT_COLOR;
        }
        invalidate();
        return true;
    }
}
