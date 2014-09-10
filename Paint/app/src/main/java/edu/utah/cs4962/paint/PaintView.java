package edu.utah.cs4962.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Memnoch on 9/9/2014.
 */
public class PaintView extends View
{
    public PaintView(Context context)
    {
        super(context);
        //setBackgroundColor(0xFF7363DC);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        Path path = new Path();

        RectF contentRect = new RectF();
        contentRect.left = getPaddingLeft();
        contentRect.top = getPaddingTop();
        contentRect.right = getWidth() - getPaddingRight();
        contentRect.bottom = getHeight() - getPaddingBottom();

        PointF center = new PointF(contentRect.centerX(), contentRect.centerY());
        float radius = Math.min(contentRect.width() * 0.5f, contentRect.height() * 0.5f);
        int pointCount = 50;
        for (int pointIndex = 0; pointIndex < pointCount; pointIndex++)
        {
            radius += (Math.random() - 0.5) * 1.5 * .05 * contentRect.width();
            PointF point = new PointF();
            point.x = center.x + radius * (float)Math.cos((double)pointIndex / (double)pointCount * 2.0 * Math.PI);
            point.y = center.y + radius * (float)Math.sin((double)pointIndex / (double)pointCount * 2.0 * Math.PI);

            if(pointIndex == 0)
                path.moveTo(point.x, point.y);
            else
                path.lineTo(point.x, point.y);

        }

        canvas.drawPath(path, paint);
    }
}
/**
 Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
 linePaint.setStrokeWidth(5.0f);
 canvas.drawLine(10.0f, 10.0f, 100.0f, 100.0f, linePaint);

 canvas.clipRect(new Rect(20, 200, 300, 500));

 float[] points = {10.0f, 10.0f, 200.4f, 255.4f, 100.0f, 125.0f, 256.5f, 500.6f};
 canvas.drawLines(points, linePaint);

 linePaint.setColor(0x7F5CE180);
 canvas.drawOval(new RectF(40.0f, 100.5f, 440.5f, 207.0f), linePaint);

 Path path = new Path();
 path.moveTo(40.0f, 200.0f);
 path.lineTo(400.0f, 600.0f);
 path.lineTo(450.0f, 800.0f);
 path.lineTo(500.0f, 1000.0f);
 path.close();
 linePaint.setStyle(Paint.Style.STROKE);
 canvas.drawPath(path, linePaint);
 **/