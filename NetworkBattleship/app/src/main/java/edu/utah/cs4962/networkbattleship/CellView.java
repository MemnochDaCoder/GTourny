package edu.utah.cs4962.networkbattleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by ljohnson on 10/28/14.
 */
public class CellView extends View
{
    int _cellIndex = 5;

    /**
     * Constructor for CellView.
     *
     * @param context
     */
    public CellView(Context context)
    {
        super(context);
    }

    /**
     * Sets the cell index within the CellView.
     *
     * @param cellIndex
     */
    public void setCellIndex(int cellIndex)
    {
        _cellIndex = cellIndex;
    }

    /**
     * Returns the index for the CellView.
     *
     * @return
     */
    public int getCellIndex()
    {
        return _cellIndex;
    }

    /**
     * Overridden onDraw to set up the dimensions of the cell view and draw the border.
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint border = new Paint(Paint.ANTI_ALIAS_FLAG);
        border.setColor(Color.BLACK);
        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(2.0f);

        canvas.drawRect(0, 0, getWidth(), getHeight(), border);
    }
}
