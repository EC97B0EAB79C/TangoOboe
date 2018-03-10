package com.example.senikaru.tangooboe;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SenikaRu on 1/24/2018.
 */

public class TangoActivity extends AppCompatActivity{

    ArrayList<HashMap<String, String>> dataArr;
    GridView listview;
    EditText tange_search;
    String Value;
    Context context;
    Tango_Adapter adapter;
    ImageView wowsuch;
    int orientation;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        orientation = newConfig.orientation;

        switch (orientation) {

            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.activity_tango);
                listview.setNumColumns(2);
                break;

            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.activity_tango);
                listview.setNumColumns(1);
                break;
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tango);

        context=this;

        listview=(GridView) findViewById(R.id.tango_list);
        tange_search=(EditText)findViewById(R.id.tango_find);
        wowsuch=(ImageView)findViewById(R.id.wow_such);

        //Database
        dataArr=new ArrayList<HashMap<String, String>>();
        DBManager dbManager=new DBManager(this);
        dataArr=dbManager.getAll();

        adapter=new Tango_Adapter(this, R.layout.tango_item, dataArr);
        listview.setAdapter(adapter);

        tange_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                try{
                    Value = tange_search.getText().toString();
                }catch (Exception e){
                    Value="";
                }

                if(Value.length()!=0){
                    dataArr=new ArrayList<HashMap<String, String>>();
                    DBManager dbManager=new DBManager(context);
                    dataArr=dbManager.getSearch(Value);

                    if(dataArr.size()>0){
                        Tango_Adapter adapter=new Tango_Adapter(context, R.layout.tango_item, dataArr);
                        listview.setAdapter(adapter);
                        listview.setVisibility(View.VISIBLE);
                        wowsuch.setVisibility(View.INVISIBLE);
                    }
                    else{
                        listview.setVisibility(View.INVISIBLE);
                        wowsuch.setVisibility(View.VISIBLE);
                    }

                }
                else{
                    dataArr=new ArrayList<HashMap<String, String>>();
                    DBManager dbManager=new DBManager(context);
                    dataArr=dbManager.getAll();

                    Tango_Adapter adapter=new Tango_Adapter(context, R.layout.tango_item, dataArr);
                    listview.setAdapter(adapter);
                    listview.setVisibility(View.VISIBLE);
                    wowsuch.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

    }


}
