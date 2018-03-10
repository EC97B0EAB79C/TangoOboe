package com.example.senikaru.tangooboe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SenikaRu on 3/11/2018.
 */

public class ChapterListAdapter extends BaseAdapter {

    HashMap<String, String>[] HashArr;
    ArrayList<String> chap_name;
    int mResource;
    LayoutInflater mInflater;

    public ChapterListAdapter(@NonNull Context context, int layoutId, ArrayList<String> object) {
        mResource = layoutId;
        chap_name = object;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return chap_name.size();
    }

    @Override
    public Object getItem(int i) {
        return chap_name.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView;
        TextView chap, chap_str;

        if (view == null) {
            mView = mInflater.inflate(mResource, viewGroup, false);
        } else
            mView = view;

        chap=(TextView)mView.findViewById(R.id.id_text);
        chap_str=(TextView)mView.findViewById(R.id.content);

        chap.setText(Integer.toString(i+1));
        chap_str.setText((String)getItem(i));

        return mView;
    }

}
