package com.lesterhan.units.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class ScaledSeekBar extends SeekBar{

    private float minValue;
    private float maxValue;

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public ScaledSeekBar(Context context) {
        super(context);
    }

    public ScaledSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaledSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScaledSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public synchronized void setProgress(int progressValue){
        int scaledProgress = 0;

        

        super.setProgress(scaledProgress);
    }

}
