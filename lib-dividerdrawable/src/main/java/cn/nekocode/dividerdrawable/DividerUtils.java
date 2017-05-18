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
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
public class DividerUtils {

    public static void addDividersTo(@NonNull View view, @NonNull DividerDrawable... dividerDrawables) {
        setBackground(view, addDividersTo(view.getBackground(), dividerDrawables));
    }

    public static Drawable addDividersTo(@Nullable Drawable sourceDrawable, @NonNull DividerDrawable... dividerDrawables) {
        return new CombinedDrawable(sourceDrawable, dividerDrawables);
    }

    public static void clearDividersWith(@NonNull View view) {
        setBackground(view, clearDividersWith(view.getBackground()));
    }

    public static Drawable clearDividersWith(@Nullable Drawable sourceDrawable) {
        if (sourceDrawable instanceof CombinedDrawable) {
            return clearDividersWith(((CombinedDrawable) sourceDrawable).origin);
        } else {
            return sourceDrawable;
        }
    }

    public static float dp2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }

    private static void setBackground(View view, Drawable drawable) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    public static class CombinedDrawable extends LayerDrawable {
        private Drawable origin;

        CombinedDrawable(@Nullable Drawable origin, @NonNull DividerDrawable[] divider) {
            super(combine(origin, divider));
            this.origin = origin;
        }

        static Drawable[] combine(Drawable origin, DividerDrawable[] dividers) {
            final int hasOrigin = origin != null ? 1 : 0;
            final Drawable[] layers = new Drawable[dividers.length + hasOrigin];
            if (hasOrigin > 0) {
                layers[0] = origin;
            }
            System.arraycopy(dividers, 0, layers, hasOrigin, dividers.length);
            return layers;
        }
    }
}
