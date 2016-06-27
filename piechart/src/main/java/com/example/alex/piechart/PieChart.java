package com.example.alex.piechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PieChart extends View {

    private int mNumWedges;
    private String mWedgeName;
    private Paint mPaint;
    private RectF rectF;
    private ArrayList<Float> percentatges = new ArrayList<>();
    private ArrayList<Integer> colors = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private int mStrokeWidth = 1;
    private int mWedgeColor = Color.BLACK;
    private float mPercentatge;
    private CustomView customView;
    MotionEvent hey;

    public PieChart(Context context) {
        super(context);
        init(null, 0);
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        TypedArray TWedge = getContext().obtainStyledAttributes(
                attrs, R.styleable.Wedge, defStyle, 0);
        /*

        CharSequence s = TWedge.getString(R.styleable.Wedge_wedgeName);
        if (s != null) {setmWedgeName(s.toString());}

        float percentatge = TWedge.getFloat(R.styleable.Wedge_wedgePercent, 0);

        if (percentatges.isEmpty()) {setmPercentatge(percentatge);}

        int color = TWedge.getColor(R.styleable.Wedge_wedgeColor, Color.GRAY);

        if (color != 0) {setmWedgeColor(color);}
        */

        TWedge.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 300;
        int height = 300;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                if (width > widthSize) {
                    width = widthSize;
                }
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                if (height > heightSize) {
                    height = heightSize;
                }
                break;
        }
        this.setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int width = getWidth();
        final int height = getHeight();

        if (percentatges != null) {

            rectF = new RectF(height / 4, width / 4, getHeight(), getHeight());
            float[] segment = pieSegment();
            float segStartPoint = 0;
            int count = -1;
            float r = rectF.width() / 2;
            canvas.save();

            drawRectAndText(segment, segStartPoint, count, r, canvas);

        }
    }

    public void setmWedgeName(String text) {
        mWedgeName = text;
        this.names.add(text);
        invalidate();
        requestLayout();
    }

    public void setmWedgeColor(int mWedgeColor) {
        this.mWedgeColor = mWedgeColor;
        this.colors.add(mWedgeColor);
        invalidate();
        requestLayout();
    }

    public int getmNumWedges() {
        return mNumWedges;
    }

    public void setmNumWedges(int mNumWedges) {
        this.mNumWedges = mNumWedges;
    }

    public String getmWedgeName(int item) {
        mWedgeName = names.get(item);
        return mWedgeName;
    }

    public float getmPercentatge() {
        return mPercentatge;
    }

    public void setmPercentatge(float percentatge) {
        mPercentatge = percentatge;
        this.percentatges.add(percentatge);
        requestLayout();
        invalidate();
    }

    public int getmWedgeColor(int item) {
        mWedgeColor = colors.get(item);
        return mWedgeColor;
    }

    private float[] pieSegment() {
        float[] scaledValues = new float[this.percentatges.size()];
        for (int i = 0; i < this.percentatges.size(); i++) {
            scaledValues[i] = (this.percentatges.get(i) * 360) / 100; // Scale each value
        }
        return scaledValues;
    }

    private void drawRectAndText(float[] segment, float segStartPoint, int count, float r, Canvas canvas) {
        for (float aSegment : segment) {
            count++;

            double trad = ((aSegment/2) + segStartPoint) * (Math.PI/180d);

            float x = (float) ((r/2) * Math.cos(trad))+ rectF.centerX();
            float y = (float) ((r/2) * Math.sin(trad))+ rectF.centerY();

            mPaint.setColor(getmWedgeColor(count));
            canvas.drawArc(rectF, segStartPoint, aSegment, true, mPaint);

            mPaint.setColor(Color.WHITE);
            mPaint.setStrokeWidth(1);
            canvas.drawText(getmWedgeName(count), x, y, mPaint);

            segStartPoint += aSegment;

        }
    }
}
