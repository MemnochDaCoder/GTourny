package edu.utah.cs4962.books;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class BookListActivity extends Activity implements ListAdapter
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ListView textListView = new ListView(this);
        textListView.setAdapter(this);
        setContentView(textListView);

        ArrayAdapter<String> booksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        booksAdapter.addAll("See Disk Run", "Run Disk Run", "The Fall of the Byte", "To Kill a Mockingbird", "Catcher in the Rye");
        ListView bookListView = new ListView(this);
        bookListView.setAdapter(booksAdapter);
        setContentView(bookListView);
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public int getCount()
    {
        return 10;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    @Override
    public Object getItem(int position)
    {
        return "Some text." + position;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String text = (String)getItem(position);

        TextView textView = null;
        if(convertView != null && convertView.getClass() == TextView.class)
            textView = (TextView)convertView;
        else
            textView = new TextView(this);

        return textView;
    }
}
