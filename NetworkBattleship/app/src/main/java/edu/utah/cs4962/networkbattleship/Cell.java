package edu.utah.cs4962.networkbattleship;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ljohnson on 10/21/14.
 */
public class Cell implements Parcelable
{
    private int state;

    /**
     * Returns the set state for the cell.
     *
     * @return
     */
    public int getState()
    {
        return state;
    }

    /**
     * Sets the state for the cell.
     *
     * @param state
     */
    public void setState(int state)
    {
        this.state = state;
    }

    /**
     * Method setting up how to read the state from a parcel.
     *
     * @param p
     */
    public Cell(Parcel p)
    {
        state = p.readInt();
    }

    /**
     * Sets the state into a parcel.
     *
     * @param s
     */
    public Cell(int s)
    {
        this.state = s;
    }

    /**
     * Returns the description of the cell, in this case the state.
     *
     * @return
     */
    @Override
    public int describeContents()
    {
        return state;
    }

    /**
     * Details how to write the state to a parcel.
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(state);
    }

    /**
     * Details how to serialize an array list of type cell.
     */
    public static final Creator<Cell> CREATOR =
            new Creator<Cell>()
            {
                public Cell createFromParcel(Parcel in)
                {
                    return new Cell(in);
                }

                public Cell[] newArray(int size)
                {
                    return new Cell[size];
                }
            };
}
