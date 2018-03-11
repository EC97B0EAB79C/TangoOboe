package com.example.senikaru.tangooboe;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SenikaRu on 3/11/2018.
 */

public class TangoRecyclerViewAdapter extends RecyclerView.Adapter<TangoRecyclerViewAdapter.ViewHolder> {

    HashMap<String, String>[] HashArr;
    ArrayList<HashMap<String, String>> Tangos;
    int mResource;
    LayoutInflater mInflater;



    public TangoRecyclerViewAdapter(ArrayList<HashMap<String, String>> items, int itemLayout) {
        this.Tangos = items;
        this.mResource = itemLayout;
    }

    public HashMap<String, String> getItem(int i) {
        return Tangos.get(i);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(mResource, parent, false);

        return new ViewHolder(mView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView korean;
        public FuriganaView kanji, hidden;
        RelativeLayout relativeLayout;

        public ViewHolder(View mView) {
            super(mView);
            korean=(TextView)mView.findViewById(R.id.tango_korean);
            kanji=(FuriganaView)mView.findViewById(R.id.tango_kanji);
            hidden=(FuriganaView)mView.findViewById(R.id.tango_kanji_hidden);
            relativeLayout=(RelativeLayout)mView.findViewById(R.id.tango_re);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.parseColor("#00000000"));
        textPaint.setTextSize(130);

        holder.hidden.text_set(textPaint, "{猫;ねこ}",12,13);

        if(((String)getItem(i).get("kanji")).length()<=6)
            textPaint.setTextSize(130);
        else
            textPaint.setTextSize(130*6/((String)getItem(i).get("kanji")).length());
        textPaint.setColor(Color.parseColor("#ffffffff"));

        if (getItem(i).get("stared").trim().equals("1".trim()))
            holder.relativeLayout.setBackgroundResource(R.drawable.box_border_yellow);
        else
            holder.relativeLayout.setBackgroundResource(R.drawable.box_border);
        holder.korean.setText((String)getItem(i).get("korean"));
        holder.kanji.text_set(textPaint,(String)getItem(i).get("furigana"),12,13);
    }

    @Override
    public int getItemCount() {
        return Tangos.size();
    }
}
