package edu.utah.cs4962.battleshiptest;

import org.xmlpull.v1.XmlPullParser;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class BattleshipGameFragment extends Fragment
{

	GridView _gameBoard = null;
    //TableLayout _tableLayout = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        _gameBoard = new GridView(getActivity());
        _gameBoard.setBackgroundColor(Color.DKGRAY);
        _gameBoard.setNumColumns(10);
        _gameBoard.setAdapter(new GameBoardAdapter(getActivity(), new ArrayList()));
        _gameBoard.setBackgroundResource(R.drawable.ocean);

        _gameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // Ask the model if this position is a hit or miss
                // Set the state to hit or miss of this cell
            }
        });

        return _gameBoard;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState)
    {
        super.onViewStateRestored(savedInstanceState);
    }

    public GridView getGameBoard()
    {
        //return _tableLayout;
        return _gameBoard;
    }
}
