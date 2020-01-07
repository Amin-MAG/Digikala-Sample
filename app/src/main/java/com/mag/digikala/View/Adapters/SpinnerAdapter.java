package com.mag.digikala.View.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mag.digikala.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Typeface font;

    public SpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = context.getResources().getFont(R.font.iran_yekan);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(font);
        view.setTextSize(10);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setTypeface(font);
        view.setTextSize(12);
        return view;
    }

}