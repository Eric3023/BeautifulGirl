package com.dong.beautifulgirl.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.dong.beautifulgirl.R;

/**
 * Created by donghuadong on 2018/4/22.
 */

public class RoundImageView extends android.support.v7.widget.AppCompatImageView {

    private float radius;
    private float[] radii;


    public RoundImageView(Context context) {
        super(context);
        init(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        radius = context.getResources().getDimension(R.dimen.x5);
        //圆角的半径，依次为左上角xy半径，右上角，右下角，左下角
        radii = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
    }


    /**
     * 画图
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int width = this.getWidth();
        int height = this.getHeight();
        RectF rectF = new RectF(0, 0, width, height);
        path.addRoundRect(rectF, radii, Path.Direction.CCW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
