package com.example.briandemaio.succulenttimer;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChoiceActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.briandemaio.succulenttimer.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            // all good, we use the fragments defined in the layout
            return;
        }

        if(savedInstanceState != null){
            // cleanup any existing fragments in case we are in detailed mode
            manager.executePendingTransactions();
            Fragment fragmentById = manager.
                    findFragmentById(R.id.succulent_choice_placeholder);
            if (fragmentById != null) {
                manager.beginTransaction().remove(fragmentById).commit();
            }
        }

        SucculentChoiceFragment choiceFragment = new SucculentChoiceFragment();
        manager.beginTransaction()
                .replace(R.id.succulent_choice_placeholder, choiceFragment).commit();
        }
    }
}

