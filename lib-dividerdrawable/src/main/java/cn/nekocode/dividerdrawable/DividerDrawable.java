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

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
public class DividerDrawable extends Drawable {
    public static final int DEFAULT_COLOR = 0xFFCCCCCC;
    public static final int DEFAULT_STROKE_WIDTH = 1;

    private Paint paint;
    private DividerLayout layout;
    private int wh[] = new int[2];
    private int layouted[];


    public DividerDrawable() {
        this(null, null);
    }

    public DividerDrawable(@Nullable Paint paint) {
        this(paint, null);
    }

    public DividerDrawable(@Nullable Paint paint, @Nullable DividerLayout layout) {
        if (paint == null) {
            paint = new Paint();
            paint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
            paint.setColor(DEFAULT_COLOR);
        }
        this.paint = paint;

        if (layout == null) {
            layout = new DividerLayout();
        }
        this.layout = layout;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(@NonNull Paint paint) {
        this.paint = paint;
    }

    public DividerLayout getLayout() {
        return layout;
    }

    public void setLayout(@NonNull DividerLayout layout) {
        this.layout = layout;
        notifyLayoutChanged();
    }

    public void notifyLayoutChanged() {
        layouted = null;
        invalidateSelf();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final int w = canvas.getWidth();
        final int h = canvas.getHeight();
        if (layouted == null || wh[0] != w || wh[1] != h) {
            layouted = layout.layout(w, h, paint.getStrokeWidth());
            wh[0] = w;
            wh[1] = h;
        }
        canvas.drawLine(layouted[0], layouted[1], layouted[2], layouted[3], paint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
