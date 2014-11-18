package edu.utah.cs4962.pallette;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.view.View;


public class PaletteActivity extends Activity
{

    PaletteView _paletteView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        _paletteView = new PaletteView(this);
        _paletteView.setPadding(10, 20, 30, 40);
        _paletteView.setColor(Color.DKGRAY);
        //rootLayout.addView(_paintView, new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(_paletteView);

        _paletteView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _paletteView.setColor(Color.RED);
            }
        });

        _paletteView.setOnBlotTouchListener(new PaletteView.OnBlotTouchListener()
        {
            @Override
            public void onBlotTouched(PaletteView v)
            {
                _paletteView.setColor(Color.GREEN);
            }
        });
    }
}
