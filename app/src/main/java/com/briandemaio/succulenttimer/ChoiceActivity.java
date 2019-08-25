package com.briandemaio.succulenttimer;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class ChoiceActivity extends AppCompatActivity implements
        SucculentChoiceAdapter.OnItemSelectedListener, SucculentNameFragment.OnSucculentNameFragmentInteractionListener{

    public static final String EXTRA_REPLY =
            "com.briandemaio.succulenttimer.REPLY";

    private int mUpdateId;
    private long mUpdateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        FragmentManager manager = getSupportFragmentManager();
        Intent intent = getIntent();

        mUpdateId = intent.getIntExtra("updateId",0);
        mUpdateTime = intent.getLongExtra("updateTime", 0);

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
        if(mUpdateId != 0){
            replyIntent.putExtra("updateId", mUpdateId);
            replyIntent.putExtra("updateTime", mUpdateTime);
        }
        setResult(RESULT_OK, replyIntent);
        finish();
    }

}


