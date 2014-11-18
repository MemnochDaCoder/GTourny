package edu.utah.cs4962.pallette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;

/**
 * Created by LJ Johnson on 9/15/2014.
 */
public class PaletteView extends View
{

    RectF _contentRect;
    float _radius;
    int _color = Color.BLUE;

    public interface OnBlotTouchListener
    {
        public void onBlotTouched(PaletteView v);
    }

    OnBlotTouchListener _onBlotTouchListener = null;

    public PaletteView(Context context)
    {
        super(context);
        setMinimumWidth(200);
        setMinimumHeight(200);
        setBackgroundColor(Color.BLUE);
    }

    public int getColor()
    {
        return _color;
    }

    public void setColor(int _color)
    {
        this._color = _color;
        invalidate();
    }

    public OnBlotTouchListener getOnBlotTouchListener()
    {
        return _onBlotTouchListener;
    }

    public void setOnBlotTouchListener(OnBlotTouchListener onBlotTouchListener)
    {
        _onBlotTouchListener = onBlotTouchListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        float circleCenterX = _contentRect.centerX();
        float circleCenterY = _contentRect.centerY();

        float distance = (float)Math.sqrt(
                (circleCenterX - x) * (circleCenterX - x) +
                        (circleCenterY - y) * (circleCenterY - y));

        if(distance < _radius)
        {
            Log.i("paint_view", "Touched inside the circle!");
            if(_onBlotTouchListener != null)
                _onBlotTouchListener.onBlotTouched(this);
        }
        else
        {
            Log.i("paint_view", "Did NOT touch inside the circle!");
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(_color);

        _contentRect = new RectF (
                getPaddingLeft(),
                getPaddingTop(),
                getWidth() - getPaddingRight(),
                getHeight() - getPaddingBottom());

        Path path = new Path();

        PointF center = new PointF(_contentRect.centerX(), _contentRect.centerY());
        float maxRadius = Math.min(_contentRect.width() * 0.5f, _contentRect.height() * 0.5f);
        float minRadius = 0.0f;
        _radius = (maxRadius - minRadius) * 0.75f;

        int pointCount = 500;
        for (int pointIndex = 0; pointIndex < pointCount; pointIndex++)
        {
            //_radius = (((float) Math.random() - 0.5f) * 10.5f * .05f * _contentRect.width());
            PointF point = new PointF();
            point.x = center.x + _radius * (float)Math.cos((double)pointIndex / (double)pointCount * 2.0 * Math.PI);
            point.y = center.y + _radius * (float)Math.sin((double)pointIndex / (double)pointCount * 2.0 * Math.PI);

            if(pointIndex == 0)
                path.moveTo(point.x, point.y);
            else
                path.lineTo(point.x, point.y);

        }

        canvas.drawPath(path, paint);
    }
/**
 @Override
 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
 {
 int widthMode = MeasureSpec.getMode(widthMeasureSpec);
 int heightMode = MeasureSpec.getMode(heightMeasureSpec);
 int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
 int heightSpec = MeasureSpec.getSize(heightMeasureSpec);

 int width = getSuggestedMinimumWidth();
 int height = getSuggestedMinimumHeight();

 if(widthMode == MeasureSpec.AT_MOST)
 width = widthSpec;

 if(heightMode == MeasureSpec.AT_MOST)
 height = heightSpec;

 if(widthMode == MeasureSpec.EXACTLY)
 width = widthSpec;

 if(height == MeasureSpec.EXACTLY)
 height = heightSpec;

 //TODO: Respect padding
 if (width > height && widthMode != MeasureSpec.EXACTLY)
 width = height;

 if(height > width && heightMode != MeasureSpec.EXACTLY)
 height = width;

 //This is required!  MEASURED_STATE_TOO_SMALL can be set to 0, this handles the exactly case
 //Where the width and height is set to exactly the screen size
 setMeasuredDimension(
 resolveSizeAndState(width, widthMeasureSpec, width < getSuggestedMinimumWidth() ? getSuggestedMinimumWidth() : width),
 resolveSizeAndState(height, heightMeasureSpec, height < getSuggestedMinimumHeight() ? getSuggestedMinimumHeight() : height));
 }**/
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