package com.visilabs.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

public class MiniCircleImageView extends androidx.appcompat.widget.AppCompatImageView {
    public MiniCircleImageView(Context context) {
        super(context);
        init();
    }

    public MiniCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MiniCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @SuppressWarnings("deprecation")
    private void init() {
        mWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (Build.VERSION.SDK_INT >= 23) {
            mWhitePaint.setColor(getResources().getColor(android.R.color.white, null));
        } else {
            mWhitePaint.setColor(getResources().getColor(android.R.color.white));
        }
        mWhitePaint.setStyle(Paint.Style.STROKE);

        Resources r = getResources();
        float strokePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());
        mWhitePaint.setStrokeWidth(strokePx);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final float centerX = mCanvasWidth / 2;
        final float centerY = mCanvasHeight / 2;
        final float radius = 0.7f * Math.min(centerX, centerY);
        canvas.drawCircle(centerX, centerY, radius, mWhitePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCanvasWidth = w;
        mCanvasHeight = h;
    }

    private Paint mWhitePaint;
    private int mCanvasWidth;
    private int mCanvasHeight;
}