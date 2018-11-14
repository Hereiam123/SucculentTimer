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
            new Succulent(R.string.cacti, R.drawable.succulent_1),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_2),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_3),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_4),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_5),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_6),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_7),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_8),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_9),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_10),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_11),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_12),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_13),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_14),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_15),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_16),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_17),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_18),
            new Succulent(R.string.aloe_vera, R.drawable.succulent_19)
    };
}
