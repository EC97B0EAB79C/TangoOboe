package com.example.senikaru.tangooboe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SenikaRu on 3/4/2018.
 */

public class BookSelectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<String> arrayModel;
    GridView grid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] books={"다락원 초중급", "다락원 중급", "일본어 上級 점프", "일본어 파이널 점프"};

        arrayModel=new ArrayList<String>(Arrays.asList(books));
        MenuAdapter adapter = new MenuAdapter(this, R.layout.grid_array, arrayModel);
        grid = (GridView) findViewById(R.id.main_menu_grid);
        grid.setNumColumns(1);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (position == 0) {

            CharSequence text = "0";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
/*
            Intent it =new Intent(this, ItemListActivity.class);
            startActivity(it);
*/

        }
        else if (position == 1) {

            CharSequence text = "1";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        }
        else if (position == 2) {

            CharSequence text = "2";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        }
        else if (position == 3) {

            CharSequence text = "3";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        }
    }
}
