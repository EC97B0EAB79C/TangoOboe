package com.example.senikaru.tangooboe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by SenikaRu on 3/11/2018.
 */

public class TestSettingActivity extends AppCompatActivity implements View.OnClickListener {

    String chp_id;
    Context context;

    RadioGroup radioGroup;
    Button start;
    CheckBox checkBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_settings);

        Intent intent= getIntent();
        chp_id=intent.getStringExtra("chp_id");
        context=this;

        radioGroup=(RadioGroup)findViewById(R.id.test_radio);
        start=(Button)findViewById(R.id.test_btn_start);
        checkBox=(CheckBox)findViewById(R.id.test_rand);

        radioGroup.check(R.id.rad_koka);
        start.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int select_id=radioGroup.getCheckedRadioButtonId();

        Intent intent=new Intent(context, TestActivity.class);

        intent.putExtra("chp_id", chp_id);

        if(select_id==R.id.rad_hika)
            intent.putExtra("mode", "hika");
        else if(select_id==R.id.rad_kako)
            intent.putExtra("mode", "kako");
        else if(select_id==R.id.rad_koka)
            intent.putExtra("mode", "koka");

        if(checkBox.isChecked())
            intent.putExtra("rand", "true");
        else
            intent.putExtra("rand", "false");

        Snackbar.make(view, chp_id, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        startActivity(intent);
        finish();
    }
}
