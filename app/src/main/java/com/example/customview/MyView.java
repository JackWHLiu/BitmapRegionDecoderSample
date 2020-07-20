package com.example.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

import com.lwh.jackknife.util.Math;
import com.lwh.jackknife.util.ScreenUtils;

import java.io.IOException;
import java.io.InputStream;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = ScreenUtils.getScreenWidth(getContext());
        Resources resources = getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = DisplayMetrics.DENSITY_HIGH;
        options.inScreenDensity = resources.getDisplayMetrics().densityDpi;
        options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        try {
            InputStream inputStream = resources.getAssets().open("datu.jpg");
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(inputStream, false);
            int left = (int) ((width - decoder.getWidth())/2 - Math.ONE_SECOND * decoder.getWidth());
            Bitmap bitmap = decoder.decodeRegion(new Rect(left,0 , left+decoder.getWidth(), width), options);
            canvas.drawBitmap(bitmap, null, new Rect(0, 0, width, width), new Paint());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
