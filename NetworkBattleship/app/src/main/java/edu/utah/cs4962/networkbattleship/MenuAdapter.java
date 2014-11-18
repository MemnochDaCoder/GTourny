package edu.utah.cs4962.networkbattleship;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljohnson on 10/23/14.
 */
public class MenuAdapter extends ArrayAdapter<String>
{
    static ArrayList<String> _fileSave = new ArrayList<String>();

    /**
     * Constructor method used to set up the list menu using the incoming array list of
     * strings.
     *
     * @param context
     * @param resource
     * @param objects
     */
    public MenuAdapter(Context context, int resource, List<String> objects)
    {
        super(context, resource, objects);

        ArrayList<String> gameList = new ArrayList<String>();

        refreshList();

        if(gameList != null)
            _fileSave.addAll(gameList);
    }

    /**
     * Called to refresh the menu list when there has been a change of data to display.
     */
    public void refreshList()
    {
        File[] fileNames;
        _fileSave.clear();
        _fileSave.add("New Game");

        try
        {
            fileNames = getContext().getFilesDir().listFiles();

            for(File file : fileNames)
            {
                int dotIndex = file.toString().indexOf(".txt");
                int fileIndex = file.toString().indexOf("Battle");
                String fileName = file.toString().substring(fileIndex, dotIndex);

                _fileSave.add(fileName);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    /**
     * Returns the list item clicked on in the menu.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView thisListItem = new TextView(getContext());
        thisListItem.setText(_fileSave.get(position));
        thisListItem.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return thisListItem;
    }

    /**
     * Returns the count of the items in the menu list.
     *
     * @return
     */
    @Override
    public int getCount()
    {
        return _fileSave.size();
    }

}
