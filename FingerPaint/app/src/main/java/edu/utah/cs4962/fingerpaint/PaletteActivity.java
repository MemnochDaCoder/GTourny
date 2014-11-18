package edu.utah.cs4962.fingerpaint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by ljohnson on 10/5/14.
 */
public class PaletteActivity extends Activity
{
    PaletteView _paletteView;
    private Button _movieButton;
    private Button _createButton;
    private SeekBar _seekBar;
    int _activeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        LinearLayout paletteLayout = new LinearLayout(this);
        paletteLayout.setOrientation(LinearLayout.VERTICAL);

        _paletteView = new PaletteView(this);

        if(_paletteView.getColors().length == 0)
        {
            //Create the initial palette colors
            _paletteView.addColor(0xFFFF0000);//Red
            _paletteView.addColor(0xFF0000FF);//Blue
            _paletteView.addColor(0xFF00FF00);//Green
            _paletteView.addColor(0xFF000000);//Black
            _paletteView.addColor(0xFFFFFF00);//Yellow
            _paletteView.addColor(0xFF660066);//Purple
        }

        _paletteView.setOnDragListener(new View.OnDragListener()
        {
            @Override
            public boolean onDrag(View v, DragEvent event)
            {
                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED)
                    return true;
                if (event.getAction() == DragEvent.ACTION_DROP)
                    ((View) event.getLocalState()).setVisibility(View.VISIBLE);
                return true;
            }
        });

        _paletteView.setOnActiveColorSelectedListener(new PaletteView.OnActiveColorSelectedListener()
        {
            @Override
            public void onActiveColorSelected(PaletteView paletteView)
            {
                if (paletteView != null)
                {
                    _activeColor = paletteView.getActiveColor();
                    PaintActivity._buttonColor = paletteView.getActiveColor();
                }
            }
        });

        LinearLayout.LayoutParams paletteViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 90);
        paletteLayout.addView(_paletteView, paletteViewLayoutParams);

        _movieButton = new Button(this);
        _movieButton.setText("Movie Mode");
        _movieButton.setId(101);
        _movieButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openMovieView();
            }
        });

        _createButton = new Button(this);
        _createButton.setText("Create Mode");
        _createButton.setId(102);
        _createButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.putExtra(PaintActivity._buttonColorString, _paletteView.getActiveColor());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

        LinearLayout menuLayout = new LinearLayout(this);
        menuLayout.setOrientation(LinearLayout.HORIZONTAL);
        menuLayout.addView(_createButton, buttonParams);
        menuLayout.addView(_movieButton, buttonParams);
        menuLayout.setBackgroundColor(Color.LTGRAY);

        setTitle("Color Selection");

        paletteLayout.addView(menuLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        setContentView(paletteLayout);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        Gson gson = new Gson();
        String jsonPointsList = gson.toJson(_paletteView.getColors());
        try
        {
            File file = new File(getFilesDir(), "Palette.txt");
            FileWriter textWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(textWriter);
            bufferedWriter.write(jsonPointsList);
            bufferedWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        try
        {
            File file = new File(getFilesDir(), "Palette.txt");
            FileReader textReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(textReader);
            String jsonPaletteColors = null;

            jsonPaletteColors = bufferedReader.readLine();
            Gson gson = new Gson();
            Type colorsArrayType = new TypeToken<int[]>(){}.getType();
            int[] colorsList = gson.fromJson(jsonPaletteColors, colorsArrayType);
            for(int colors : colorsList)
                _paletteView.addColor(colors);

            bufferedReader.close();

        }
        catch (FileNotFoundException f)
        {
            f.printStackTrace();
            Log.e("FileNotFound: ", "The file you were looking for was not found!");
        } catch (IOException e)
        {
            e.printStackTrace();
            Log.e("IOException: ", "There was a read/write error with the I/O!");
        }
    }

    private void openMovieView()
    {
        Intent intent = new Intent(this, MovieActivity.class);
        this.startActivity(intent);
    }

}
