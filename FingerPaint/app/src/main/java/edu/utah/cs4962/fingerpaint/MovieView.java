package edu.utah.cs4962.fingerpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ljohnson on 10/5/14.
 */
public class MovieView extends View
{
    long _startTime;
    long _animationDuration = 10000; // 10 seconds
    int _framesPerSecond = 10;
    boolean _pauseAnimation = false;
    int _numberOfLinesDrawn = 0;
    boolean _sliding = false;

    public static ArrayList<Line> _progressPoints = new ArrayList<Line>();

    private static ArrayList<Line> _allPoints = new ArrayList<Line>();

    public MovieView(Context context)
    {
        super(context);
    }

    public static void set_allPoints(ArrayList<Line> _allPoints)
    {
        MovieView._allPoints = _allPoints;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if (!_pauseAnimation)
        {
            long elapsedTime = System.currentTimeMillis() - _startTime;

            Log.w("elapsedTime", elapsedTime + "");

            drawLines(canvas, (int) (elapsedTime / 50));

            if (elapsedTime < _animationDuration)
                this.postInvalidateDelayed(1000 / _framesPerSecond);
        }
        else
        {
            drawLines(canvas, _numberOfLinesDrawn);
        }

        if(_sliding)
        {
            drawSliderLines(canvas);
        }
    }

    private void drawSliderLines(Canvas canvas)
    {
        for (int lineIndex = 0; lineIndex < _progressPoints.size(); lineIndex++)
        {
            Path polyLinePath = new Path();
            Paint polyLinePaint = new Paint();

            polyLinePaint.setStyle(Paint.Style.STROKE);
            polyLinePaint.setStrokeWidth(3.0f);
            polyLinePaint.setStrokeJoin(Paint.Join.ROUND);
            polyLinePaint.setStrokeCap(Paint.Cap.ROUND);
            polyLinePaint.setDither(true);

            for (int pointIndex = 0; pointIndex < _progressPoints.get(lineIndex).size(); pointIndex++)
            {
                polyLinePaint.setColor(_progressPoints.get(lineIndex).get(0).color);

                if (pointIndex == 0)
                    polyLinePath.moveTo(_progressPoints.get(lineIndex).get(pointIndex).x, _progressPoints.get(lineIndex).get(pointIndex).y);
                else
                    polyLinePath.lineTo(_progressPoints.get(lineIndex).get(pointIndex).x, _progressPoints.get(lineIndex).get(pointIndex).y);
            }
            canvas.drawPath(polyLinePath, polyLinePaint);
        }
    }

    private void drawLines(Canvas canvas, int numOfLinesToDraw)
    {

        _numberOfLinesDrawn = numOfLinesToDraw;
        int count = 0;

        for (int lineIndex = 0; lineIndex < _allPoints.size(); lineIndex++)
        {
            Path polyLinePath = new Path();
            Paint polyLinePaint = new Paint();

            polyLinePaint.setStyle(Paint.Style.STROKE);
            polyLinePaint.setStrokeWidth(3.0f);
            polyLinePaint.setStrokeJoin(Paint.Join.ROUND);
            polyLinePaint.setStrokeCap(Paint.Cap.ROUND);
            polyLinePaint.setDither(true);
            polyLinePaint.setColor(_allPoints.get(lineIndex).get(0).color);

            for (int pointIndex = 0; pointIndex < _allPoints.get(lineIndex).size(); pointIndex++)
            {
                if(count < _numberOfLinesDrawn)
                {
                    if (pointIndex == 0)
                        polyLinePath.moveTo(_allPoints.get(lineIndex).get(pointIndex).x, _allPoints.get(lineIndex).get(pointIndex).y);
                    else
                        polyLinePath.lineTo(_allPoints.get(lineIndex).get(pointIndex).x, _allPoints.get(lineIndex).get(pointIndex).y);

                    count++;
                }
            }

            canvas.drawPath(polyLinePath, polyLinePaint);
        }
    }

    public void PauseAnimation()
    {
        _pauseAnimation = true;
        postInvalidate();
    }

    public void PlayAnimation()
    {
        this._startTime = System.currentTimeMillis();
        _pauseAnimation = false;
        postInvalidate();
    }

    public void drawSliderLines(int sliderProgress)
    {
        int numberOfPoints = 0;
        ArrayList<Line> toDraw = new ArrayList<Line>();
        Line pToDraw;

        for(int numberOfLines = 0; numberOfLines < _allPoints.size();numberOfLines++)
        {
            for(int pointCount = 0; pointCount < _allPoints.get(numberOfLines).size();pointCount++)
            {
                numberOfPoints++;
            }
        }

        int percent = (int)(sliderProgress * .01f);
        int pointsToDraw = (int)((sliderProgress * .01f) * numberOfPoints);
        int newPointsCount = 0;

        for(int newLines = 0;newLines < _allPoints.size();newLines++)
        {
            pToDraw = new Line();

            for(int newPoints = 0;newPoints < _allPoints.get(newLines).size();newPoints++)
            {
                if(newPointsCount < pointsToDraw)
                {
                    pToDraw.add(_allPoints.get(newLines).get(newPoints));
                    newPointsCount++;
                }
            }

            toDraw.add(pToDraw);
        }

        _progressPoints = toDraw;
        invalidate();
    }
}