package edu.utah.cs4962.paint;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class PaintActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PaintView paintView = new PaintView(this);
        paintView.setPadding(10, 20, 30, 40);
        setContentView(paintView);
    }
}