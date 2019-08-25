package com.briandemaio.succulenttimer;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.briandemaio.succulenttimer.ChoiceActivity.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    private SucculentViewModel mSucculentViewModel;
    private SwipeController mSwipeController;
    public static final int NEW_SUCCULENT_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_SUCCULENT_ACTIVITY_REQUEST_CODE = 2;
    private FirebaseAnalytics mFirebaseAnalytics;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        RecyclerView recyclerView = findViewById(R.id.main_view);
        final AddedSucculentAdapter adapter = new AddedSucculentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        mSucculentViewModel = ViewModelProviders.of(this).get(SucculentViewModel.class);
        mSucculentViewModel.getAllSucculents().observe(this, new Observer<List<Succulent>>() {
            @Override
            public void onChanged(@Nullable final List<Succulent> succulents) {
                // Update the cached copy of the words in the adapter.
                adapter.setSucculents(succulents);
            }
        });

        mSwipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                Succulent mySucculent = adapter.getSucculentAtPosition(position);
                Toast.makeText(MainActivity.this, "Deleting " +
                        mySucculent.getName(), Toast.LENGTH_LONG).show();

                //Delete Alarm
                cancelSucculentTimeAlarm(mySucculent);

                // Delete the succulent
                mSucculentViewModel.delete(mySucculent);

                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            }

            @Override
            public void onLeftClicked(int position){
                Succulent mySucculent = adapter.getSucculentAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
                intent.putExtra("updateId", mySucculent.getId());
                intent.putExtra("updateTime", mySucculent.getExpiryTime());
                startActivityForResult(intent, UPDATE_SUCCULENT_ACTIVITY_REQUEST_CODE);
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(mSwipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                mSwipeController.onDraw(c);
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

        adapter.setOnItemClickListener(new AddedSucculentAdapter.ClickListener()  {
            @Override
            public void onWaterItemClick(View v, int position) {
                Succulent succulent = adapter.getSucculentAtPosition(position);
                long expiryTime = System.currentTimeMillis() + 604800000;
                succulent.setExpiryTime(expiryTime);
                setSucculentTimeAlarm(succulent);
                mSucculentViewModel.update(succulent);
            }

            @Override
            public void onEditTimeClick(final View v, final int position) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        //Get set expiration year, month and date for future use
                        final int expirYear = year;
                        final int expirMonth = month + 1;
                        final int expirDay= dayOfMonth;

                        //get current hour of day and minute
                        int mHour = c.get(Calendar.HOUR_OF_DAY);
                        int mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(),
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                        Date date = null;
                                        try {
                                            date = sdf.parse(expirYear+"/"+expirMonth+"/"+expirDay+" "+hourOfDay+":"+minute+":00");
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        Succulent succulent = adapter.getSucculentAtPosition(position);
                                        long expiryTime = date.getTime();
                                        succulent.setExpiryTime(expiryTime);
                                        setSucculentTimeAlarm(succulent);
                                        mSucculentViewModel.update(succulent);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        createNotificationChannel();
        MobileAds.initialize(this, "ca-app-pub-2580444339985264~7777983759");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reverse_order) {
            return true;
        }
        else if(id == R.id.original_order){

        }

        return super.onOptionsItemSelected(item);
    }

    public void setSucculentTimeAlarm(Succulent succulent) {
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        int timeId = (int) succulent.getExpiryTime();
        notifyIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, timeId);
        notifyIntent.putExtra(AlarmReceiver.NOTIFICATION, succulent.getName());
        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, timeId, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                succulent.getExpiryTime(), notifyPendingIntent);
    }

    public void cancelSucculentTimeAlarm(Succulent succulent) {
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        int timeId = (int) succulent.getExpiryTime();
        notifyIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, timeId);
        PendingIntent cancelPendingIntent= PendingIntent.getBroadcast
                (this, timeId, notifyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        cancelPendingIntent.cancel();
        alarmManager.cancel(cancelPendingIntent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_SUCCULENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            long triggerTime = System.currentTimeMillis() + 604800000;
            Succulent succulent = new Succulent(data.getStringExtra(EXTRA_REPLY), data.getIntExtra("imageID", 0), triggerTime);
            mSucculentViewModel.insert(succulent);
            setSucculentTimeAlarm(succulent);
        }
        else if(requestCode == UPDATE_SUCCULENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Succulent succulent = new Succulent(data.getStringExtra(EXTRA_REPLY), data.getIntExtra("imageID", 0));
            succulent.setId(data.getIntExtra("updateId",0));
            succulent.setExpiryTime(data.getLongExtra("updateTime", 0));
            mSucculentViewModel.update(succulent);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    "Not Saved",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Create a notification manager object.
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Succulent Water Notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies user to water plants");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
