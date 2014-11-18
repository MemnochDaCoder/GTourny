package edu.utah.cs4962.fingerpaint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ljohnson on 10/5/14.
 */
public class MovieActivity extends Activity
{
    public static ArrayList<Line> _allPoints = new ArrayList<Line>();
    private MovieView _movieView;
    private Button _createButton;
    private Button _paletteButton;
    private Button _playButton;
    private Button _pauseButton;
    private SeekBar _seekBar;
    public boolean _play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        _movieView = new MovieView(this);
        _movieView.setBackgroundColor(Color.WHITE);

        setTitle("Movie View");

        _movieView.set_allPoints(PaintAreaView._allPoints);

        rootLayout.addView(_movieView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 80));

        _seekBar = new SeekBar(this);

        _seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                _movieView._sliding = true;
                _movieView.drawSliderLines(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                _movieView._sliding = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                _movieView._sliding = false;
            }
        });

        rootLayout.addView(_seekBar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        LinearLayout menuLayout = new LinearLayout(this);
        menuLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        //Palette Button
        _paletteButton = new Button(this);
        _paletteButton.setText("Palette");
        _paletteButton.setId(101);
        _paletteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openThePalette();
            }
        });

        //Create Mode Button
        _createButton = new Button(this);
        _createButton.setText("Create");
        _createButton.setId(102);
        _createButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.putExtra(PaintActivity._buttonColorString, PaletteView._theActiveColor);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        //Play Button
        _playButton = new Button(this);
        _playButton.setText("Play |>");
        _playButton.setId(103);
        _playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast;
                if (_playButton.getText() == "Play |>")
                {

                    toast = Toast.makeText(getBaseContext(), "Play", Toast.LENGTH_SHORT);
                    _playButton.setText("Pause ||");
                    //_watchView.init();
                    _movieView.PlayAnimation();
                    _play = true;
                } else
                {
                    toast = Toast.makeText(getBaseContext(), "Pause", Toast.LENGTH_SHORT);
                    _playButton.setText("Play |>");
                    _movieView.PauseAnimation();
                    _play = false;
                }

                toast.show();
            }
        });

        //Pause Button
        _pauseButton = new Button(this);
        _pauseButton.setText("Pause ||");
        _pauseButton.setId(104);
        _pauseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast;
                if (!_play)
                {

                    toast = Toast.makeText(getBaseContext(), "Play", Toast.LENGTH_SHORT);
                    _playButton.setVisibility(View.INVISIBLE);
                    _pauseButton.setVisibility(View.VISIBLE);
                    _movieView.PauseAnimation();
                    _play = false;
                } else
                {
                    toast = Toast.makeText(getBaseContext(), "Pause", Toast.LENGTH_SHORT);
                    _playButton.setVisibility(View.VISIBLE);
                    _pauseButton.setVisibility(View.INVISIBLE);
                    _movieView.PlayAnimation();
                    _play = true;
                }

                toast.show();
            }
        });
        _pauseButton.setVisibility(View.INVISIBLE);

        menuLayout.addView(_paletteButton, buttonParams);
        menuLayout.addView(_createButton, buttonParams);
        menuLayout.addView(_playButton, buttonParams);
        //menuLayout.addView(_pauseButton, buttonParams);

        rootLayout.addView(menuLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        setContentView(rootLayout);

    }

    /**
     * Method to set up the intent to expect a return value of the active
     * color and pass it along if the create button is pressed.
     */
    private void openThePalette()
    {
        Intent intent = new Intent(this, PaletteActivity.class);
        this.startActivityForResult(intent, 6);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //If the color was returned in the extra data
        //set the color values for this view and the button
        if (resultCode == Activity.RESULT_OK)
        {
            _allPoints = data.getParcelableArrayListExtra("LINE_ARRAY_LIST");
            _movieView.set_allPoints(_allPoints);
        }
    }
}
