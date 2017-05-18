package cn.nekocode.dividerdrawable.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.nekocode.dividerdrawable.DividerDrawable;
import cn.nekocode.dividerdrawable.DividerLayout;
import cn.nekocode.dividerdrawable.DividerUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DividerDrawable dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setCenter(DividerLayout.CENTER_VERTICAL);
        DividerUtils.addDividersTo(findViewById(R.id.linear1), dividerDrawable);

        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setMarginLeftDp(50)
                .setCenter(DividerLayout.CENTER_VERTICAL);
        DividerUtils.addDividersTo(findViewById(R.id.linear2), dividerDrawable);

        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setOrientation(DividerLayout.ORIENTATION_VERTICAL)
                .setAlign(DividerLayout.ALIGN_PARENT_RIGHT)
                .setMarginTopDp(16)
                .setMarginBottomDp(16);
        DividerUtils.addDividersTo(findViewById(R.id.text_3_1), dividerDrawable);
        DividerUtils.addDividersTo(findViewById(R.id.text_3_3), dividerDrawable);
        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setOrientation(DividerLayout.ORIENTATION_VERTICAL)
                .setAlign(DividerLayout.ALIGN_PARENT_RIGHT)
                .setMarginTopDp(32)
                .setMarginBottomDp(32);
        DividerUtils.addDividersTo(findViewById(R.id.text_3_2), dividerDrawable);

        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setMarginLeftDp(16)
                .setLengthDp(100);
        DividerUtils.addDividersTo(findViewById(R.id.text_4_1), dividerDrawable);
        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setOrientation(DividerLayout.ORIENTATION_VERTICAL)
                .setCenter(DividerLayout.CENTER_VERTICAL)
                .setLengthDp(46);
        DividerUtils.addDividersTo(findViewById(R.id.text_4_1), dividerDrawable);
        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setCenter(DividerLayout.CENTER_HORIZONTAL)
                .setLengthDp(100);
        DividerUtils.addDividersTo(findViewById(R.id.text_4_2), dividerDrawable);
        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setOrientation(DividerLayout.ORIENTATION_VERTICAL)
                .setAlign(DividerLayout.ALIGN_PARENT_RIGHT)
                .setCenter(DividerLayout.CENTER_VERTICAL)
                .setLengthDp(46);
        DividerUtils.addDividersTo(findViewById(R.id.text_4_2), dividerDrawable);
        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setAlign(DividerLayout.ALIGN_PARENT_RIGHT)
                .setMarginRightDp(16)
                .setLengthDp(100);
        DividerUtils.addDividersTo(findViewById(R.id.text_4_3), dividerDrawable);
        dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setCenter(DividerLayout.CENTER_HORIZONTAL)
                .setLengthDp(300);
        DividerUtils.addDividersTo(findViewById(R.id.text_4_4), dividerDrawable);
    }
}
