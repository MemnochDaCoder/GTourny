package edu.utah.cs4962.fingerpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ljohnson on 9/21/14.
 */
public class BlotView extends View
{
    RectF _contentRect;
    float _radius;
    int _color = Color.BLUE;

    OnBlotTouchListener _onBlotTouchListener = null;

    public interface OnBlotTouchListener
    {
        public void onBlotTouched(BlotView v);
    }

    public BlotView(Context context)
    {
        super(context);
        setMinimumWidth(50);
        setMinimumHeight(50);
    }

    public int getColor()
    {
        return _color;
    }

    public void setColor(int color)
    {
        this._color = color;
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
        paint.setStrokeWidth(2.0f);
        paint.setStyle(Paint.Style.FILL);


        _contentRect = new RectF (
                getPaddingLeft(),
                getPaddingTop(),
                getWidth() - getPaddingRight(),
                getHeight() - getPaddingBottom());

        Path path = new Path();

        PointF center = new PointF(_contentRect.centerX(), _contentRect.centerY());
        float maxRadius = Math.min(_contentRect.width() * 0.3f, _contentRect.height() * 0.3f);
        float minRadius = 0.15f * maxRadius;
        _radius = (maxRadius - minRadius) * 0.25f;

        int pointCount = 100;
        for (int pointIndex = 0; pointIndex < pointCount; pointIndex += 3)
        {
            PointF control1 = new PointF();
            float control1Radius = _radius + (float)(Math.random() - 0.25f) * 1.25f * 20.0f;
            control1.x = center.x + control1Radius * (float)Math.cos((double)pointIndex / (double)pointCount * 2.0 * Math.PI);
            control1.y = center.y + control1Radius * (float)Math.sin((double)pointIndex / (double)pointCount * 2.0 * Math.PI);

            PointF control2 = new PointF();
            float control2Radius = _radius + (float)(Math.random() - 2.5f) * 0.5f * 20.0f;
            control2.x = center.x + control2Radius * (float)Math.cos((double)pointIndex / (double)pointCount * 2.0 * Math.PI);
            control2.y = center.y + control2Radius * (float)Math.sin((double)pointIndex / (double)pointCount * 2.0 * Math.PI);

            PointF point = new PointF();
            point.x = center.x + _radius * (float)Math.cos((double)pointIndex / (double)pointCount * 2.0 * Math.PI);
            point.y = center.y + _radius * (float)Math.sin((double)pointIndex / (double)pointCount * 2.0 * Math.PI);

            if(pointIndex == 0)
            {
                path.moveTo(point.x, point.y);
            }
            else
            {
                path.cubicTo(control1.x, control1.y, control2.x, control2.y, point.x, point.y);
            }

        }

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int widthSpec = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSpec = View.MeasureSpec.getSize(heightMeasureSpec);

        int width = getSuggestedMinimumWidth();
        int height = getSuggestedMinimumHeight();

        if(widthMode == View.MeasureSpec.AT_MOST)
            width = widthSpec;

        if(heightMode == View.MeasureSpec.AT_MOST)
            height = heightSpec;

        if(widthMode == View.MeasureSpec.EXACTLY)
            width = widthSpec;

        if(heightMode == View.MeasureSpec.EXACTLY)
        {
            height = heightSpec;
            width = height;
        }

        if(widthMode == View.MeasureSpec.EXACTLY)
        {
            width = widthSpec;
            height = width;
        }

        //TODO: Respect padding!!

        if (width > height && widthMode != View.MeasureSpec.EXACTLY)
            width = height;

        if(height > width && heightMode != View.MeasureSpec.EXACTLY)
            height = width;

        //This is required!  MEASURED_STATE_TOO_SMALL can be set to 0, this handles the exactly case
        //Where the width and height is set to exactly the screen size
        setMeasuredDimension(
                resolveSizeAndState(width, widthMeasureSpec, width < getSuggestedMinimumWidth() ? MEASURED_STATE_TOO_SMALL : 0),
                resolveSizeAndState(height, heightMeasureSpec, height < getSuggestedMinimumHeight() ? MEASURED_STATE_TOO_SMALL : 0));
    }
}
