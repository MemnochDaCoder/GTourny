package edu.utah.cs4962.art;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class ArtActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Art monaLisa = new Art();
        monaLisa._theArt = BitmapFactory.decodeResource(getResources(), R.drawable.lisa);
        ArtCollection.getInstance().addArt(monaLisa);

        Art dali = new Art();
        dali._theArt = BitmapFactory.decodeResource(getResources(), R.drawable.clocks);
        ArtCollection.getInstance().addArt(dali);

        Art mattSmith = new Art();
        mattSmith._theArt = BitmapFactory.decodeResource(getResources(), R.drawable.smith);
        ArtCollection.getInstance().addArt(mattSmith);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(rootLayout);

        FrameLayout artListLayout = new FrameLayout(this);
        artListLayout.setId(10);
        rootLayout.addView(artListLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 30));

        FrameLayout artDetailLayout = new FrameLayout(this);
        artDetailLayout.setId(11);
        rootLayout.addView(artDetailLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 70));

        FragmentTransaction addTransaction =  getFragmentManager().beginTransaction();
        addTransaction.add(10, new ArtListFragment());
        addTransaction.add(11, new ArtDetailFragment());
        addTransaction.commit();
    }
}
