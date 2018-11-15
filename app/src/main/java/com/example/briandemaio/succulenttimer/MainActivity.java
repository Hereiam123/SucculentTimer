package com.example.briandemaio.succulenttimer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import static com.example.briandemaio.succulenttimer.ChoiceActivity.*;

public class MainActivity extends AppCompatActivity {

    private SucculentViewModel mSucculentViewModel;
    public static final int NEW_SUCCULENT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AddedSucculentAdapter adapter = new AddedSucculentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSucculentViewModel = ViewModelProviders.of(this).get(SucculentViewModel.class);
        mSucculentViewModel.getAllSucculents().observe(this, new Observer<List<Succulent>>() {
            @Override
            public void onChanged(@Nullable final List<Succulent> succulents) {
                // Update the cached copy of the words in the adapter.
                adapter.setSucculents(succulents);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
                startActivityForResult(intent, NEW_SUCCULENT_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_SUCCULENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Succulent succulent = new Succulent(data.getStringExtra(EXTRA_REPLY),data.getIntExtra("imageID",0));
            mSucculentViewModel.insert(succulent);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Not Saved",
                    Toast.LENGTH_LONG).show();
        }
    }
}
