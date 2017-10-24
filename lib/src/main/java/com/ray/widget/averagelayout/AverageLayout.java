package com.ray.widget.averagelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zyl on 2017/10/16.
 */
public class AverageLayout extends LinearLayout {
    private static final String TAG = AverageLayout.class.getSimpleName();

    private boolean holdEdgeSpace;
    private int sizeAccordingId;
    private int itemWidth;
    private int itemHeight;

    public AverageLayout(Context context) {
        this(context, null);
    }

    public AverageLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AverageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.AverageLayout);
        holdEdgeSpace = t.getBoolean(R.styleable.AverageLayout_holdEdgeSpace, false);
        sizeAccordingId = t.getResourceId(R.styleable.AverageLayout_sizeAccordingId, NO_ID);
        itemWidth = t.getDimensionPixelSize(R.styleable.AverageLayout_itemWidth, -1);
        itemHeight = t.getDimensionPixelSize(R.styleable.AverageLayout_itemHeight, -1);
        t.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int count = getChildCount();
        if (sizeAccordingId != NO_ID) {
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                if (sizeAccordingId == child.getId()) {
                    ViewGroup.LayoutParams lp = child.getLayoutParams();
                    itemWidth = lp.width;
                    itemHeight = lp.height;
                    if (itemWidth <= 0 && itemHeight <= 0) {
                        Log.w(TAG, "Size according is not work, because size is wrap_content/match_parent.");
                    }
                    break;
                }
            }
        }
        if (itemWidth > 0 || itemHeight > 0) {
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                ViewGroup.LayoutParams lp = child.getLayoutParams();
                if (lp != null) {
                    lp.width = itemWidth;
                    lp.height = itemHeight;
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int width = r - l - getPaddingLeft() - getPaddingRight();
        int height = b - t - getPaddingTop() - getPaddingBottom();
        int childWidth = 0;
        int childHeight = 0;
        int spaceCount = holdEdgeSpace ? count + 1 : count - 1;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            childWidth += child.getMeasuredWidth();
            childHeight += child.getMeasuredHeight();
        }
        int orientation = getOrientation();
        int spaceSize = orientation == HORIZONTAL ? (width - childWidth) / spaceCount : (height - childHeight) / spaceCount;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (orientation == HORIZONTAL) {
                int leftMargin = i == 0 && holdEdgeSpace ? spaceSize : 0;
                int rightMargin = i == count - 1 && !holdEdgeSpace ? 0 : spaceSize;
                lp.setMargins(leftMargin, lp.topMargin, rightMargin, lp.bottomMargin);
            } else {
                int topMargin = i == 0 && holdEdgeSpace ? spaceSize : 0;
                int bottomMargin = i == count - 1 && !holdEdgeSpace ? 0 : spaceSize;
                lp.setMargins(lp.leftMargin, topMargin, lp.rightMargin, bottomMargin);
            }
//            child.setLayoutParams(lp);
        }
        super.onLayout(changed, l, t, r, b);
    }

}
