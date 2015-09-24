package com.github.rayboot.widget.ratioview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.github.rayboot.widget.ratioview.utils.RatioFixMode;
import com.github.rayboot.widget.ratioview.utils.RatioMeasureDelegate;
import com.github.rayboot.widget.ratioview.utils.RatioViewDelegate;

/**
 * author: rayboot  Created on 15/9/3.
 * email : sy0725work@gmail.com
 */
public class RatioViewPager extends ViewPager implements RatioMeasureDelegate {

    private RatioViewDelegate mRatioLayoutDelegate;

    public RatioViewPager(Context context) {
        super(context);
    }

    public RatioViewPager(Context context, RatioFixMode mode, float ratioW, float ratioH) {
        super(context);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, mode, ratioW, ratioH);
    }

    public RatioViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mRatioLayoutDelegate != null){
            mRatioLayoutDelegate.onMeasure(widthMeasureSpec,heightMeasureSpec);
            widthMeasureSpec = mRatioLayoutDelegate.getWidthMeasureSpec();
            heightMeasureSpec = mRatioLayoutDelegate.getHeightMeasureSpec();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setDelegateMeasuredDimension(int measuredWidth, int measuredHeight) {
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public void setRatio(RatioFixMode mode, float datumWidth, float datumHeight) {
        if(mRatioLayoutDelegate != null){
            mRatioLayoutDelegate.setRatio(mode,datumWidth,datumHeight);
        }
    }
}
