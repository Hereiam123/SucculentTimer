package com.example.briandemaio.succulenttimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChoiceActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.briandemaio.succulenttimer.REPLY";

    SucculentNameFragment mNameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();

        mNameFragment = (SucculentNameFragment) manager.findFragmentById(R.id.succulent_choice_placeholder);
        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (mNameFragment == null) {
            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.succulent_choice_placeholder, new SucculentChoiceFragment());
            transaction.commit();
        }
    }
}

