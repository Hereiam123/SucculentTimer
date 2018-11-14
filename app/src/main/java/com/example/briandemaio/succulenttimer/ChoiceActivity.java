package com.example.briandemaio.succulenttimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        SucculentAdapter succulentAdapter = new SucculentAdapter(this, succulents);
        gridView.setAdapter(succulentAdapter);
    }

    private Succulent[] succulents = {
            new Succulent(R.string.cacti, R.drawable.succulent_1)
    };
}
