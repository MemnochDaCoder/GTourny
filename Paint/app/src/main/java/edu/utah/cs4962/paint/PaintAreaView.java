package edu.utah.cs4962.paint;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import android.graphics.Path;

import java.util.ArrayList;

/**
 * Created by ljohnson on 9/17/14.
 */
public class PaintAreaView extends ViewGroup
{
    View _transformedView = null;
    //Stores the paint color for the line
    Paint _polyLinePaint;

    //Stores the poly line points for the poly line
    Path _polyLinePath;

    //Stores the poly line paths for each individually drawn poly line.
    ArrayList<Path> _paths = new ArrayList<Path>();

    public PaintAreaView(Context context)
    {
        super(context);
        setBackgroundColor(Color.BLUE);
        _polyLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _polyLinePath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
        {
            View child = getChildAt(childIndex);
            child.layout(
                    getWidth() / 2 - child.getMeasuredWidth(),
                    getHeight() / 2 - child.getMeasuredHeight(),
                    getWidth() / 2 + child.getMeasuredWidth(),
                    getHeight() / 2 + child.getMeasuredHeight()
            );
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        _polyLinePaint.setStyle(Paint.Style.STROKE);
        _polyLinePaint.setStrokeWidth(2.0f);
        _polyLinePaint.setColor(Color.RED);

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                _polyLinePath.moveTo(x, y);
                _polyLinePath.lineTo(x + 1, y + 1);
                break;
            case MotionEvent.ACTION_MOVE:
                _polyLinePath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                _paths.add(_polyLinePath);
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        for(Path path : _paths)
        {
            canvas.drawPath(path, _polyLinePaint);
        }
    }
}

//_transformedView = new View(context);
//_transformedView.setBackgroundColor(Color.GREEN);
//_transformedView.setLayoutParams(new LayoutParams(100, 100));
//addView(_transformedView);

/**
 Paint layoutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
 layoutPaint.setStyle(Paint.Style.STROKE);
 layoutPaint.setStrokeWidth(2.0f);
 layoutPaint.setColor(Color.CYAN);
 layoutPaint.setPathEffect(new DashPathEffect(new float[] {4.0f, 8.0f}, 0));
 Path layoutPath = new Path();
 //layoutPath.moveTo();

 Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
 textPaint.setColor(Color.YELLOW);
 textPaint.setTextSize(28.0f);
 textPaint.setFakeBoldText(true);
 canvas.drawText("Left:" + _transformedView.getLeft(), 20, 30, textPaint);
 canvas.drawText("Top:" + _transformedView.getTop(), 20, 60, textPaint);
 canvas.drawText("Right:" + _transformedView.getRight(), 20, 90, textPaint);
 canvas.drawText("Bottom:" + _transformedView.getBottom(), 20, 120, textPaint);
 canvas.drawText("x:" + _transformedView.getX(), 20, 150, textPaint);
 canvas.drawText("y:" + _transformedView.getY(), 20, 180, textPaint);
 canvas.drawText("Translation x:" + _transformedView.getTranslationX(), 20, 210, textPaint);
 canvas.drawText("Translation y:" + _transformedView.getTranslationY(), 20, 240, textPaint);
 canvas.drawText("Rotation: " + _transformedView.getRotation(), 20, 270, textPaint);
 canvas.drawText("ScaleX: " + _transformedView.getScaleX(), 20, 300, textPaint);
 canvas.drawText("ScaleY: " + _transformedView.getScaleY(), 20, 330, textPaint);
 **/


        /*
        for(PointF point : _points)
        {
            polyLinePath.lineTo(point.x, point.y);
        }*/

//canvas.drawPath(polyLinePath, polyLinePaint);

//_transformedView.setX(x - _transformedView.getWidth() / 2);
//_transformedView.setY(y - _transformedView.getHeight() / 2);
//_transformedView.setRotation(x);
//_transformedView.setScaleX(x / getWidth() / 2);
//_transformedView.setScaleY(y / getHeight() / 2);
//_transformedView.setTranslationX(x);
//_transformedView.setTranslationY(y);
//_transformedView.setLeft((int)x);
//_transformedView.setTop((int)y);
/**
 if(event.getActionMasked() == MotionEvent.ACTION_UP)
 {
 float[] pointsX = new float[_points.size()];
 float[] pointsY = new float[_points.size()];

 for(int pointIndex = 0; pointIndex < _points.size(); pointIndex++)
 {
 pointsX[pointIndex] = _points.get(pointIndex).x;
 pointsY[pointIndex] = _points.get(pointIndex).y;
 }

 ObjectAnimator animator = new ObjectAnimator();
 animator.setTarget(_transformedView);
 animator.setPropertyName("x");
 animator.setFloatValues(0.0f, 400.0f);
 animator.setDuration(1000);
 animator.setValues(PropertyValuesHolder.ofFloat("x", pointsX),
 PropertyValuesHolder.ofFloat("y", pointsY));

 animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
 {
 @Override public void onAnimationUpdate(ValueAnimator animation)
 {
 invalidate();
 }
 });
 animator.start();
 }
 **/