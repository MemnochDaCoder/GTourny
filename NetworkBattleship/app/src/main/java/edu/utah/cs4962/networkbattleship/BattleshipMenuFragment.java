package edu.utah.cs4962.networkbattleship;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BattleshipMenuFragment extends Fragment
{

    ListView _gameMenu = null;
    OnNewGameClickedListener _onNewGameClickedListener = null;

    /**
     * Interface for clicking an item in the menu list.
     */
    public interface OnNewGameClickedListener
    {
        void onNewGameClicked();

        void onLoadGameClicked(String gameName);
    }

    /**
     * Setter for the click listener.
     *
     * @param onNewGameClickedListener
     */
    public void setOnNewGameClickedListener(OnNewGameClickedListener onNewGameClickedListener)
    {
        _onNewGameClickedListener = onNewGameClickedListener;
    }

    /**
     * Default on create view overridden to set up the list menu for the new and load game selections.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        _gameMenu = new ListView(getActivity());
        _gameMenu.setBackgroundColor(Color.LTGRAY);

        final MenuAdapter listMenuAdapter = new MenuAdapter(getActivity(), 0, new ArrayList<String>());

        _gameMenu.setAdapter(listMenuAdapter);

        _gameMenu.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position == 0)
                {
                    _onNewGameClickedListener.onNewGameClicked();
                }
                else
                {
                    _onNewGameClickedListener.onLoadGameClicked(MenuAdapter._fileSave.get(position) + ".txt");
                }
            }
        });

        return _gameMenu;
    }

    /**
     * Method to redraw the view when there is new data to display.
     */
    public void refreshList(){
        ((MenuAdapter)_gameMenu.getAdapter()).refreshList();
    }

}
