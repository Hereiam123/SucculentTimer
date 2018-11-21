package com.example.briandemaio.succulenttimer;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChoiceActivity extends AppCompatActivity implements
        SucculentChoiceAdapter.OnItemSelectedListener{

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

        if (savedInstanceState != null) {
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

    @Override
    public void onSucculentItemSelected(String text) {
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            SucculentNameFragment fragment = (SucculentNameFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.choiceFragment);
            fragment.setText(text);
        } else {
            // replace the fragment
            // Create fragment and give it an argument for the selected article
            SucculentNameFragment newFragment = new SucculentNameFragment();
            Bundle args = new Bundle();
            args.putString(SucculentNameFragment.EXTRA_TEXT, text);
            newFragment.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back

            transaction.replace(R.id.succulent_choice_placeholder, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public void onSucculentItemSelected(int imageId) {
        SucculentNameFragment fragment = (SucculentNameFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nameFragment);

    }
}


