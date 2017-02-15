package com.objectlife.mynewsdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by objectlife on 2/13/17.
 */

public class MyAdapter extends BaseAdapter {

    private List<String> mStrings;
    private LayoutInflater mInflater;

    public MyAdapter(List<String> strings) {
        mStrings = strings;
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        TextView textView = (TextView) mInflater.inflate(R.layout.item_text, parent, false);
        textView.setText(mStrings.get(position));
        return textView;
    }
}
