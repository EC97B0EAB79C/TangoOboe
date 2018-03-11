package com.example.senikaru.tangooboe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by SenikaRu on 3/11/2018.
 */

public class TestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnTouchListener {

    ImageButton star;
    FuriganaView furiganaView;
    GridView gridView;
    ArrayList<String> arrayList;
    ArrayList<HashMap<String, String>> Tangoes;
    HashMap<String, String>[] HashArr;
    int curr_pos=0, correct;
    TextPaint textPaint;
    String mode_id;
    String chp_id;
    RelativeLayout layout;
    TextView info;
    Boolean starred;
    DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        layout=(RelativeLayout)findViewById(R.id.test_layout);
        layout.setOnTouchListener(this);

        star=(ImageButton)findViewById(R.id.star);
        furiganaView=(FuriganaView)findViewById(R.id.test_furigana);
        gridView=(GridView)findViewById(R.id.test_menu);
        info=(TextView)findViewById(R.id.information);

        String[] menu={"O","X"};
        arrayList=new ArrayList<String>(Arrays.asList(menu));
        MenuAdapter adapter=new MenuAdapter(this, R.layout.grid_array, arrayList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);
        star.setOnClickListener(this);

        Intent intent=getIntent();
        chp_id=intent.getStringExtra("chp_id");
        mode_id=intent.getStringExtra("mode");
        String rand_id=intent.getStringExtra("rand");

        //Database
        dbManager=new DBManager(this);
        Tangoes= dbManager.getChap(chp_id);
        if(rand_id.trim().equals("true".trim())){
            Collections.shuffle(Tangoes);
        }
        HashArr=Tangoes.toArray(new HashMap[Tangoes.size()]);

        //Set question
        textPaint = new TextPaint();
        textPaint.setColor(Color.parseColor("#FFFFFFFF"));
        textPaint.setTextSize(130);

        question();

    }

    public void question(){
        if(HashArr[curr_pos].get("stared").trim().equals("1".trim())) {
            starred = true;
            star.setBackgroundResource(R.mipmap.star02);
        }
        else {
            starred=false;
            star.setBackgroundResource(R.mipmap.star01);
        }
        if(mode_id.trim().equals("koka".trim())) {
            while (HashArr[curr_pos].get("korean").length() == 0) curr_pos++;
            furiganaView.text_set(textPaint, HashArr[curr_pos].get("korean"), 12, 13);
        }
        else if(mode_id.trim().equals("hika".trim())) {
            while (HashArr[curr_pos].get("hiragana").length() == 0) curr_pos++;
            furiganaView.text_set(textPaint, HashArr[curr_pos].get("hiragana"), 12, 13);
        }
        else if (mode_id.trim().equals("kako".trim())) {
            while (HashArr[curr_pos].get("hiragana").length() == 0) curr_pos++;
            furiganaView.text_set(textPaint, HashArr[curr_pos].get("kanji"), 12, 13);
        }
    }

    public void answer(){
        if(mode_id.trim().equals("koka".trim())) {
            furiganaView.text_set(textPaint, HashArr[curr_pos].get("furigana"), 12, 13);
        }
        else if(mode_id.trim().equals("hika".trim())) {
            furiganaView.text_set(textPaint, HashArr[curr_pos].get("kanji"), 12, 13);
        }
        else if (mode_id.trim().equals("kako".trim())) {
            furiganaView.text_set(textPaint, HashArr[curr_pos].get("hiragana"), 12, 13);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(HashArr[curr_pos].get("stared").trim().equals("1".trim())!=starred){

            dbManager.setStar(starred,chp_id, HashArr[curr_pos].get("furigana"));

        }

        if(curr_pos==Tangoes.size()){
            finish();
        }
        if(i==0){
            correct++;
            curr_pos++;
            question();
        }
        else if(i==1){
            curr_pos++;
            question();
        }



        info.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View view) {

        if(starred) {
            starred = false;
            star.setBackgroundResource(R.mipmap.star01);
        }
        else {
            starred=true;
            star.setBackgroundResource(R.mipmap.star02);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                answer();
                break;

            case MotionEvent.ACTION_UP:
                //question();
        }


        return super.onTouchEvent(motionEvent);
    }
}
