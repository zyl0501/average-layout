package com.ray.widget.averagelayout;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * @Author Denny Ye
 * @Date 2014-10-14
 * @Version 1.0
 */
final class ViewUtils {
    public static final int INVALID_MARGIN = -1;
    public static final int INVALID_SIZE = -100;

    /**
     * 设置view的margin，如果不改动，传入{@link #INVALID_MARGIN}
     *
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null && lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams lpMargin = (ViewGroup.MarginLayoutParams) lp;
            left = left == INVALID_MARGIN ? lpMargin.leftMargin : left;
            top = top == INVALID_MARGIN ? lpMargin.topMargin : top;
            right = right == INVALID_MARGIN ? lpMargin.rightMargin : right;
            bottom = bottom == INVALID_MARGIN ? lpMargin.bottomMargin : bottom;
            lpMargin.setMargins(left, top, right, bottom);
            view.setLayoutParams(lpMargin);
        }
    }

    public static void setSize(View view, int width, int height) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            if (width != INVALID_SIZE) lp.width = width;
            if (height != INVALID_SIZE) lp.height = height;
            view.setLayoutParams(lp);
        }
    }
}
