package com.example.senikaru.tangooboe;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SenikaRu on 1/24/2018.
 */

public class Tango_Adapter extends BaseAdapter {

    HashMap<String, String>[] HashArr;
    ArrayList<HashMap<String, String>> Tangos;
    int mResource;
    LayoutInflater mInflater;


    public Tango_Adapter(@NonNull Context context, int layoutId, ArrayList<HashMap<String, String>>  object) {

        mResource=layoutId;
        Tangos=object;
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return Tangos.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public HashMap<String, String> getItem(int i) {
        return Tangos.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView;
        TextView korean;
        FuriganaView kanji, hidden;

        if(view==null) {
            mView = mInflater.inflate(mResource, viewGroup, false);
        }
        else
            mView=view;


        korean=(TextView)mView.findViewById(R.id.tango_korean);
        kanji=(FuriganaView)mView.findViewById(R.id.tango_kanji);
        hidden=(FuriganaView)mView.findViewById(R.id.tango_kanji_hidden);

        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.parseColor("#00000000"));
        textPaint.setTextSize(130);

        hidden.text_set(textPaint, "{猫;ねこ}",12,13);

        if(((String)getItem(i).get("kanji")).length()<=6)
            textPaint.setTextSize(130);
        else
            textPaint.setTextSize(130*6/((String)getItem(i).get("kanji")).length());
        textPaint.setColor(Color.parseColor("#ffffffff"));

        korean.setText((String)getItem(i).get("korean"));
        kanji.text_set(textPaint,(String)getItem(i).get("furigana"),12,13);

        return mView;
    }
}
