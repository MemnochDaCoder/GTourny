package edu.utah.cs4962.battleshiptest;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BattleshipMenuFragment extends Fragment implements ListAdapter
{

    ListView _gameMenu = null;
    public ArrayList<String> _testList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        _gameMenu = new ListView(getActivity());
        _gameMenu.setBackgroundColor(Color.LTGRAY);

         _testList = new ArrayList<String>();

        for(int i = 0; i < 100;i++)
        {
            _testList.add(Integer.toString(i));
        }

        /*//New Game button
        Button newGame = new Button(getActivity());
        newGame.setText("New Game");
        newGame.setId(101);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

        _gameMenu.addView(newGame, buttonParams);*/

        return _gameMenu;
    }

    public ListView getGameBoard()
    {
        return _gameMenu;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return false;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer)
    {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer)
    {

    }

    @Override
    public int getCount()
    {
        return _testList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return _testList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }
}
