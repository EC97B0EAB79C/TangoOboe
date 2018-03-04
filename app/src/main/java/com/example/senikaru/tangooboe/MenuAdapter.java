package com.example.senikaru.tangooboe;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SenikaRu on 3/3/2018.
 */
public class MenuAdapter extends BaseAdapter {
    int mResource;
    ArrayList<String> mObjects;
    LayoutInflater minflater;

    public MenuAdapter(Context context, int layoutId, ArrayList<String> object){
        mResource=layoutId;
        mObjects=object;
        minflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        FuriganaView text1;
        if(convertView==null)
            view=minflater.inflate(mResource,parent,false);
        else
            view=convertView;

        text1=(FuriganaView) view.findViewById(R.id.grid_text);

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(80);
        textPaint.setColor(Color.parseColor("#ffffffff"));


        text1.text_set(textPaint, mObjects.get(position), 11,13);

        return view;
    }
}
