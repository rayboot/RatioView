/**
 * <pre>
 * Copyright 2015 Soulwolf Ching
 * Copyright 2015 The Android Open Source Project for xiaodaow3.0-branche
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </pre>
 */
package com.github.rayboot.widget.ratioview.utils;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.github.rayboot.widget.ratioview.R;

/**
 * author: Rayboot Created on 2014/12/25 .
 * email : sy0725work@gmail.com
 */
public final class RatioViewDelegate<T extends View & RatioMeasureDelegate> {

    public static <T extends View & RatioMeasureDelegate> RatioViewDelegate obtain(T target, RatioFixMode mode, float ratioW, float ratioH) {
        return new RatioViewDelegate(target, mode, ratioW, ratioH);
    }

    public static <T extends View & RatioMeasureDelegate> RatioViewDelegate obtain(T target, AttributeSet attrs) {
        return obtain(target, attrs, 0);
    }

    public static <T extends View & RatioMeasureDelegate> RatioViewDelegate obtain(T target, AttributeSet attrs, int defStyleAttr) {
        return obtain(target, attrs, 0, 0);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View & RatioMeasureDelegate> RatioViewDelegate obtain(T target, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        return new RatioViewDelegate(target, attrs, defStyleAttr, defStyleRes);
    }

    private final T mRatioMeasureDelegate;

    private RatioFixMode mRatioFixMode;

    private float mFixWidth = 0.0f;

    private float mFixHeight = 0.0f;

    private int mWidthMeasureSpec, mHeightMeasureSpec;

    private RatioViewDelegate(T target, RatioFixMode mode, float ratioW, float ratioH) {
        this.mRatioMeasureDelegate = target;
        mRatioFixMode = mode;
        mFixWidth = ratioW;
        mFixHeight = ratioH;
    }

    private RatioViewDelegate(T target, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.mRatioMeasureDelegate = target;
        TypedArray typedArray = mRatioMeasureDelegate.getContext().obtainStyledAttributes(attrs, R.styleable.RatioSizeOpt, defStyleAttr, defStyleRes);
        if (typedArray != null) {
            int fix = typedArray.getInt(R.styleable.RatioSizeOpt_fixRatio, 0);
            if (fix == 1) {
                mRatioFixMode = RatioFixMode.FIX_WIDTH;
            } else if (fix == 2) {
                mRatioFixMode = RatioFixMode.FIX_HEIGHT;
            }
            mFixWidth = typedArray.getFloat(R.styleable.RatioSizeOpt_widthRatio, mFixWidth);
            mFixHeight = typedArray.getFloat(R.styleable.RatioSizeOpt_heightRatio, mFixHeight);
            typedArray.recycle();
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.mWidthMeasureSpec = widthMeasureSpec;
        this.mHeightMeasureSpec = heightMeasureSpec;
        if (mRatioFixMode != null && mFixWidth != 0 && mFixHeight != 0) {
            mRatioMeasureDelegate.setDelegateMeasuredDimension(View.getDefaultSize(0, mWidthMeasureSpec),
                    View.getDefaultSize(0, mHeightMeasureSpec));
            int measuredWidth = mRatioMeasureDelegate.getMeasuredWidth();
            int measuredHeight = mRatioMeasureDelegate.getMeasuredHeight();
            if (mRatioFixMode == RatioFixMode.FIX_WIDTH) {
                measuredHeight = (int) (measuredWidth / mFixWidth * mFixHeight);
            } else {
                measuredWidth = (int) (measuredHeight / mFixHeight * mFixWidth);
            }
            mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY);
            mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY);
        }
    }

    public int getWidthMeasureSpec() {
        return mWidthMeasureSpec;
    }

    public int getHeightMeasureSpec() {
        return mHeightMeasureSpec;
    }

    public void setRatio(@NonNull RatioFixMode mode, @NonNull float fixWidth, @NonNull float fixHeight) {
        this.mRatioFixMode = mode;
        this.mFixWidth = fixWidth;
        this.mFixHeight = fixHeight;
        this.mRatioMeasureDelegate.requestLayout();
    }
}
