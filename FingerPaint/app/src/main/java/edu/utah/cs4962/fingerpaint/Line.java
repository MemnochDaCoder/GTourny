package edu.utah.cs4962.fingerpaint;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ljohnson on 10/2/14.
 */
public class Line implements Parcelable
{

    ArrayList<Point> list;

    public Line()
    {
        list = new ArrayList<Point>();
    }

    public Line(Parcel p)
    {
        super();
        p.readTypedList(list, Point.CREATOR);
    }

    public void add(Point p)
    {
        list.add(p);
    }

    public int size()
    {
        return list.size();
    }

    public Point get(int pos)
    {
        return list.get(pos);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeTypedList(list);
    }

    public static final Creator<Line> CREATOR =
            new Creator<Line>()
            {
                public Line createFromParcel(Parcel in)
                {
                    return new Line(in);
                }

                public Line[] newArray(int size)
                {
                    return new Line[size];
                }
            };
}
