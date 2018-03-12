package com.example.senikaru.tangooboe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ArrayList<String> arrayModel;
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        String[] menu={"{文法;ぶんぽ}の{単語;たんご}","{自分;じぶん}の{単語;たんご}","{検索;けんさく}"};

        arrayModel=new ArrayList<String>(Arrays.asList(menu));
        MenuAdapter adapter = new MenuAdapter(this, R.layout.grid_array, arrayModel);
        grid = (GridView) findViewById(R.id.main_menu_grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
        TextView mine_ind=(TextView)findViewById(R.id.sub_title);
        mine_ind.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (position == 0) {

            Intent it =new Intent(this, BookSelectActivity.class);
            it.putExtra("Mine", false);
            startActivity(it);

        }
        else if (position == 1) {

            Intent it =new Intent(this, BookSelectActivity.class);
            it.putExtra("Mine", true);
            startActivity(it);

        }
        else if (position == 2) {

            Intent it =new Intent(this, TangoActivity.class);
            startActivity(it);

        }
    }
}
