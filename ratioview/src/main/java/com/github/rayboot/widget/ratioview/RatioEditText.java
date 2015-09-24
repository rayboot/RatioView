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
package com.github.rayboot.widget.ratioview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

import com.github.rayboot.widget.ratioview.utils.RatioFixMode;
import com.github.rayboot.widget.ratioview.utils.RatioMeasureDelegate;
import com.github.rayboot.widget.ratioview.utils.RatioViewDelegate;

/**
 * author: Rayboot Created on 2014/12/25 .
 * email : sy0725work@gmail.com
 */
public class RatioEditText extends EditText implements RatioMeasureDelegate {

    private RatioViewDelegate mRatioLayoutDelegate;

    public RatioEditText(Context context) {
        super(context);
    }

    public RatioEditText(Context context, RatioFixMode mode, float ratioW, float ratioH) {
        super(context);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, mode, ratioW, ratioH);
    }

    public RatioEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, attrs);
    }

    public RatioEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatioEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mRatioLayoutDelegate != null) {
            mRatioLayoutDelegate.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
        if (mRatioLayoutDelegate != null) {
            mRatioLayoutDelegate.setRatio(mode, datumWidth, datumHeight);
        }
    }
}
