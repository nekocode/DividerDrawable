/*
 * Copyright 2017. nekocode (nekocode.cn@gmail.com)
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
 */

package cn.nekocode.dividerdrawable;

import android.content.res.Resources;
import android.support.annotation.IntDef;

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
public class DividerLayout {
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int MATCH_PARENT = -1;
    public static final int ALIGN_PARENT_LEFT = 1;
    public static final int ALIGN_PARENT_TOP = 1 << 1;
    public static final int ALIGN_PARENT_RIGHT = 1 << 2;
    public static final int ALIGN_PARENT_BOTTOM = 1 << 3;
    public static final int CENTER_NONE = 0;
    public static final int CENTER_HORIZONTAL = 1;
    public static final int CENTER_VERTICAL = 1 << 1;
    public static final int CENTER_IN_PARENT = 0b11;


    @IntDef({ORIENTATION_HORIZONTAL, ORIENTATION_VERTICAL})
    public @interface Orientation {
    }

    @IntDef({CENTER_NONE, CENTER_IN_PARENT, CENTER_HORIZONTAL, CENTER_VERTICAL})
    public @interface Center {
    }

    @Orientation
    private int orientation = ORIENTATION_HORIZONTAL;
    private int length = MATCH_PARENT;
    private int align = 0;
    @Center
    private int center = CENTER_NONE;
    private int marginLeft = 0;
    private int marginTop = 0;
    private int marginRight = 0;
    private int marginBottom = 0;


    public int getOrientation() {
        return orientation;
    }

    public DividerLayout setOrientation(int orientation) {
        this.orientation = orientation;
        return this;
    }

    public int getLength() {
        return length;
    }

    public DividerLayout setLength(int length) {
        this.length = (length >= MATCH_PARENT ? length : 0);
        return this;
    }

    public DividerLayout setLengthDp(int lengthDp) {
        this.length = (lengthDp >= MATCH_PARENT ? (int) dp2px(lengthDp) : 0);
        return this;
    }

    public int getAlign() {
        return align;
    }

    public DividerLayout setAlign(int align) {
        this.align = align;
        return this;
    }

    public int getCenter() {
        return center;
    }

    public DividerLayout setCenter(int center) {
        this.center = center;
        return this;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public DividerLayout setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public DividerLayout setMarginLeftDp(int marginLeftDp) {
        this.marginLeft = (int) dp2px(marginLeftDp);
        return this;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public DividerLayout setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public DividerLayout setMarginTopDp(int marginTopDp) {
        this.marginTop = (int) dp2px(marginTopDp);
        return this;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public DividerLayout setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public DividerLayout setMarginRightDp(int marginRightDp) {
        this.marginRight = (int) dp2px(marginRightDp);
        return this;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public DividerLayout setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public DividerLayout setMarginBottomDp(int marginBottomDp) {
        this.marginBottom = (int) dp2px(marginBottomDp);
        return this;
    }

    /**
     * @return int[] {startX, startY, stopX, stopY}
     */
    public int[] layout(int width, int height, float strokeWidth) {
        final boolean alignLeft = !((align & ALIGN_PARENT_RIGHT) > 0);
        final boolean alignTop = !((align & ALIGN_PARENT_BOTTOM) > 0);
        final boolean h = orientation == ORIENTATION_HORIZONTAL;
        final boolean ch = (center & CENTER_HORIZONTAL) > 0;
        final boolean cv = (center & CENTER_VERTICAL) > 0;

        final int[] xs, ys;
        xs = h ? layoutHorizontalXAxis(ch, alignLeft, width) : layoutVerticalXAxis(ch, alignLeft, width, strokeWidth);
        ys = h ? layoutHorizontalYAxis(cv, alignTop, height, strokeWidth) : layoutVerticalYAxis(cv, alignTop, height);

        return new int[]{xs[0], ys[0], xs[1], ys[1]};
    }

    private int[] layoutHorizontalXAxis(boolean centerHorizontal, boolean alignLeft, int width) {
        int sx, ex;
        if (!centerHorizontal) {
            sx = getMarginLeft();
            ex = width - getMarginRight();
            if (length != MATCH_PARENT) {
                if (alignLeft) {
                    ex = Math.min(ex, sx + length);
                } else {
                    sx = Math.max(sx, ex - length);
                }
            }

        } else {
            int len = width - Math.max(getMarginLeft(), getMarginRight()) * 2;
            if (length != MATCH_PARENT) {
                len = Math.min(len, length);
            }

            sx = (width / 2) - (len / 2);
            ex = (width / 2) + (len / 2);
        }

        return new int[]{sx, ex};
    }

    private int[] layoutHorizontalYAxis(boolean centerVertical, boolean alignTop, int height, float strokeWidth) {
        int sy, ey;
        if (!centerVertical) {
            if (alignTop) {
                sy = ey = getMarginTop() + (int) (strokeWidth / 2f);
            } else {
                sy = ey = height - getMarginBottom() - (int) (strokeWidth / 2f);
            }

        } else {
            sy = ey = height / 2;
        }

        return new int[]{sy, ey};
    }

    private int[] layoutVerticalXAxis(boolean centerHorizontal, boolean alignLeft, int width, float strokeWidth) {
        int sx, ex;
        if (!centerHorizontal) {
            if (alignLeft) {
                sx = ex = getMarginLeft() + (int) (strokeWidth / 2f);
            } else {
                sx = ex = width - getMarginRight() - (int) (strokeWidth / 2f);
            }

        } else {
            sx = ex = width / 2;
        }

        return new int[]{sx, ex};
    }

    private int[] layoutVerticalYAxis(boolean centerVertical, boolean alignTop, int height) {
        int sy, ey;
        if (!centerVertical) {
            sy = getMarginTop();
            ey = height - getMarginBottom();
            if (length != MATCH_PARENT) {
                if (alignTop) {
                    ey = Math.min(ey, sy + length);
                } else {
                    sy = Math.max(sy, ey - length);
                }
            }

        } else {
            int len = height - Math.max(getMarginTop(), getMarginBottom()) * 2;
            if (length != MATCH_PARENT) {
                len = Math.min(len, length);
            }
            sy = (height / 2) - (len / 2);
            ey = (height / 2) + (len / 2);
        }

        return new int[]{sy, ey};
    }

    private static float dp2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }
}
