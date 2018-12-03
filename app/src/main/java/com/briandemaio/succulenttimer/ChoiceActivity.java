package com.briandemaio.succulenttimer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

public class ChoiceActivity extends AppCompatActivity implements
        SucculentChoiceAdapter.OnItemSelectedListener, SucculentNameFragment.OnSucculentNameFragmentInteractionListener{

    public static final String EXTRA_REPLY =
            "com.briandemaio.succulenttimer.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        FragmentManager manager = getSupportFragmentManager();

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
    public void onSucculentItemSelected(int imageId) {
        Bundle args = new Bundle();
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
        SucculentNameFragment fragment = (SucculentNameFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nameFragment);
            fragment.setImage(imageId);
        } else {
            // replace the fragment
            // Create fragment and give it an argument for the selected article
            SucculentNameFragment newFragment = new SucculentNameFragment();
            args.putInt("imageId", imageId);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.succulent_choice_placeholder, newFragment);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public void onSetSave(String succulent, int imageId) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, succulent);
        replyIntent.putExtra("imageID", imageId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}


