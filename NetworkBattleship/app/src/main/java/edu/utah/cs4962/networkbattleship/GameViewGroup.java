package edu.utah.cs4962.networkbattleship;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ljohnson on 10/28/14.
 */
public class GameViewGroup extends ViewGroup
{
    /**
     * Interface for the cell clicked listener.
     */
    public interface OnCellClickedListener
    {
        public void OnCellClick(int cellIndex);
    }

    OnCellClickedListener _onCellClickedListener = null;

    /**
     * Sets the global listener value for the cell clicked listener.
     *
     * @param onCellClickedListener
     */
    public void setOnCellClickedListener(OnCellClickedListener onCellClickedListener)
    {
        _onCellClickedListener = onCellClickedListener;
    }

    /**
     * Constructor for the game view group, calls the set cells method
     * that adds the list of children to the view group.
     *
     * @param context
     * @param cells
     */
    public GameViewGroup(Context context, ArrayList<Cell> cells)
    {
        super(context);

        setCells(cells);
    }

    /**
     * Using an incoming array list of cells sets up the view.
     *
     * @param cells
     */
    public void setCells(ArrayList<Cell> cells){

        removeAllViews();
        for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++)
        {
            CellView v = new CellView(getContext());

            switch (cells.get(cellIndex).getState())
            {
                case 0:
                    v.setBackgroundColor(Color.WHITE);
                    break;
                case 1:
                    v.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    v.setBackgroundColor(Color.BLUE);
                    break;
                default:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    break;
            }

            v.setCellIndex(cellIndex);

            v.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    _onCellClickedListener.OnCellClick(((CellView) v).getCellIndex());
                }
            });

            addView(v);
        }
        invalidate();
    }

    /**
     * Using the measurements of the parent, sets up the children of the view into a grid for the
     * game board.
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int childWidthMax = 0;
        int childHeightMax = 0;

        for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
        {
            childWidthMax = getWidth() / 10;
            childHeightMax = getHeight() / 10;
        }

        for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
        {
            View child = getChildAt(childIndex);

            int currentColumn = childIndex % 10;
            int currentRow = childIndex / 10;

            Rect childLayout = new Rect();
            childLayout.left = currentColumn * childWidthMax;
            childLayout.top = currentRow * childHeightMax;
            childLayout.right = childLayout.left + childWidthMax;
            childLayout.bottom = childLayout.top + childHeightMax;

            child.layout(childLayout.left, childLayout.top, childLayout.right, childLayout.bottom);
        }
    }

    /**
     * Sets the state of the child view using the incoming childIndex, state is a
     * color for setting the background properly.
     *
     * @param childIndex
     * @param state
     */
    public void setCellState(int childIndex, int state)
    {
        CellView thisKid = (CellView) getChildAt(childIndex);
        thisKid.setBackgroundColor(state);

        postInvalidate();
    }
}
