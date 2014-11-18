package edu.utah.cs4962.art;

import android.app.Fragment;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by ljohnson on 10/20/14.
 */
public class ArtListFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ListView artListView = new ListView(getActivity());
        artListView.setAdapter(this);
        return artListView;
    }
}
