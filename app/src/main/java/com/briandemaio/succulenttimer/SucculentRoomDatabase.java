package com.briandemaio.succulenttimer;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Succulent.class}, version = 1, exportSchema = false)
public abstract class SucculentRoomDatabase extends RoomDatabase {
    public abstract SucculentDao succulentDao();
    private static SucculentRoomDatabase INSTANCE;

    public static SucculentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SucculentRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SucculentRoomDatabase.class, "succulent_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
