package edu.utah.cs4962.fingerpaint;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ljohnson on 10/2/14.
 */
public class PaintPoint implements Parcelable
{
    float x;
    float y;
    int color;

    public PaintPoint(Parcel p)
    {
        x = p.readFloat();
        y = p.readFloat();
        color = p.readInt();
    }

    public PaintPoint(float x, float y, int color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeFloat(x);
        dest.writeFloat(y);
        dest.writeInt(color);
    }

    public static final Creator<PaintPoint> CREATOR =
            new Creator<PaintPoint>()
            {
                public PaintPoint createFromParcel(Parcel in)
                {
                    return new PaintPoint(in);
                }

                public PaintPoint[] newArray(int size)
                {
                    return new PaintPoint[size];
                }
            };
}
